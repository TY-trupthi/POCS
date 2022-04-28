package com.tyss.batchprocessing.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.tyss.batchprocessing.pojo.ProductDetails;
import com.tyss.batchprocessing.repository.ProductDetailsRepository;
import com.tyss.batchprocessing.service.MyJobListener;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private ProductDetailsRepository productDetailsRepository;

	@Bean
	public FlatFileItemReader<ProductDetails> reader(){
		FlatFileItemReader<ProductDetails> reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource("products.csv"));
		//reader.setResource(new FileSystemResource("D:/samples/products.csv"));
		//reader.setResource(new UrlResource("http://abcd.com/files/product.csv"));
		reader.setLineMapper(new DefaultLineMapper<>() {{
			setLineTokenizer(new DelimitedLineTokenizer(){{
				setDelimiter(DELIMITER_COMMA);
				setNames("productId","productCode","productCost");
			}});
			
			setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
				setTargetType(ProductDetails.class);
			}});
		}});
		return reader;
	}
	
	@Bean
	public ItemProcessor<ProductDetails, ProductDetails> processor(){
		return (ProductDetails item)->{
			double cost = item.getProductCost();
			item.setProductDiscount(cost*12/100.0);
			item.setProductGST(cost*22/100.0);
			return item;
		};
	}
	
	@Bean
    public RepositoryItemWriter<ProductDetails> writer() {
        RepositoryItemWriter<ProductDetails> writer = new RepositoryItemWriter<>();
        writer.setRepository(productDetailsRepository);
        writer.setMethodName("save");
        return writer;
    }
	
	@Bean
	public JobExecutionListener listener() {
		return new MyJobListener();
	}
	
	@Bean
	public Step stepA() {
		 return  stepBuilderFactory.get("stepA")
				 .<ProductDetails, ProductDetails>chunk(2)//<Input,Output>chunk
				 .reader(reader())
	             .processor(processor())
	             .writer(writer())
	             .build();
	}
	
	@Bean
	public Job jobA() {
		return this.jobBuilderFactory.get("jobA")
				.incrementer(new RunIdIncrementer())
                .listener(listener())
                .start(stepA())
               // .next(stepB())
                .build();

	}
	
	
	
	
	
	
	
	
}
