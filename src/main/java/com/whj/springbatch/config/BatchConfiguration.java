package com.whj.springbatch.config;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.whj.springbatch.model.User;
import com.whj.springbatch.processor.MyProcessor;
import com.whj.springbatch.reader.MyReader;
import com.whj.springbatch.writer.MyWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	
	/*创建job*/
	@Bean
	public Job jobMethod(){
		return jobBuilderFactory.get("whjJob")
				.start(stepMethod())
				.build();
	}
	
	/*创建step*/
	@Bean
	public Step stepMethod(){
		return stepBuilderFactory.get("myStep")
				.<User, User>chunk(3)
				.reader(new MyReader())
				.processor(new MyProcessor())
				.writer(new MyWriter())
				.allowStartIfComplete(true)
				.build();
	}
	

}
