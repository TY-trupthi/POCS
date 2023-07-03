package com.te.search.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.te.search.dto.RequestDTO;
import com.te.search.dto.ResponseDTO;
import com.te.search.dto.SearchRequestDTO;

@Service
@SuppressWarnings("deprecation")
public class SimpleFileUploadImpl {

	private static String elasticsearchHost = "localhost";
	private static int elasticsearchPort = 9200;
	private static String indexName = "new-index";
	private static String documentType = "_doc";
	private static String fieldName = "content";

	// Create the REST client
	private static RestHighLevelClient client = new RestHighLevelClient(
			RestClient.builder(new HttpHost(elasticsearchHost, elasticsearchPort, "http")));

	public String upload(RequestDTO requestDTO) {
		try {
			String plainTextContent = convertDocToPlainText(requestDTO.getUrl());
			System.err.println("URL " + requestDTO.getUrl());
			IndexRequest indexRequest = new IndexRequest(indexName, documentType).source("title", requestDTO.getUrl(),
					"content", plainTextContent, "timestamp", LocalDateTime.now().toString());
			IndexResponse index = client.index(indexRequest, RequestOptions.DEFAULT);
			System.out.println("Document uploaded successfully.");
			return index.getId();
		} catch (IOException e) {
			System.err.println("Failed to read the document file: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Failed to upload the document to Elasticsearch: " + e.getMessage());
		}

		return null;
	}

	public String getDocument(String documentId) {

		// Prepare the get request
		GetRequest getRequest = new GetRequest(indexName).id(documentId);

		try {
			// Retrieve the stored document
			GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);

			if (getResponse.isExists()) {
				// Print the document source
				String documentSource = getResponse.getSourceAsString();
				System.out.println("Document Source:\n" + documentSource);
				return documentSource;
			} else {
				System.out.println("Document not found.");
			}
		} catch (Exception e) {
			System.err.println("Failed to retrieve the document: " + e.getMessage());
		}
		return null;
	}

	private static String convertDocToPlainText(String documentPath) {
		try {
			File file = new File(documentPath);
			FileInputStream inputStream = new FileInputStream(file);

			Tika tika = new Tika();
			return tika.parseToString(inputStream);
		} catch (IOException | TikaException e) {
			System.err.println("Failed to convert the DOC file to plain text: " + e.getMessage());
		}

		return null;
	}

	public List<ResponseDTO> anyMatch(SearchRequestDTO searchRequestDTO) {
		
		System.err.println("Keywords " + searchRequestDTO.getKeywords());
		List<ResponseDTO> responseDTOs = new ArrayList<>();
		// Build the search request
		SearchRequest searchRequest = new SearchRequest(indexName);
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

		// Create a TermsQueryBuilder to search the specified field with multiple
		// keywords
		QueryBuilder queryBuilder = QueryBuilders.queryStringQuery(searchRequestDTO.getKeywords())
                .field(fieldName);

		// Add the TermsQueryBuilder to the search source builder
		searchSourceBuilder.query(queryBuilder);
		searchRequest.source(searchSourceBuilder);

		System.err.println("Done");

		try {
			// Execute the search
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

			// Process the search results
			SearchHits hits = searchResponse.getHits();
			System.out.println("Total hits: " + hits.getTotalHits());

			for (SearchHit hit : hits) {
				ObjectMapper mapper = new ObjectMapper();
				mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
				mapper.setVisibility(
						VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));

				ResponseDTO responseDTO = mapper.readValue(hit.getSourceAsString(), ResponseDTO.class);
				responseDTO.setId(hit.getId());
				System.out.println("Document ID: " + hit.getId());
				System.out.println("Document Source: " + hit.getSourceAsString());
				responseDTOs.add(responseDTO);
			}

		} catch (IOException e) {
			System.err.println("Failed to perform the search: " + e.getMessage());
		}
		return responseDTOs;
	}

}
