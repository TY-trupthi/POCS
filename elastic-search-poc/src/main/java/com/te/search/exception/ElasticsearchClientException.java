package com.te.search.exception;

public class ElasticsearchClientException extends Exception{
	 public ElasticsearchClientException(String message) {
	        super(message);
	    }

	    public ElasticsearchClientException(String message, Throwable cause) {
	        super(message, cause);
	    }
}
