package com.iamgkt.springboots3.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonConfig {

	@Value("${awsAccessKey}")
	private String awsAccessKey;

	@Value("${awsSecretKey}")
	private String awsSecretKey;

	@Value("${awsRegion}")
	private String awsRegion;

	@Bean
	public AmazonS3 amazonS3() {

		AWSCredentials awsCredentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);

		return AmazonS3ClientBuilder.standard().withRegion(awsRegion)
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();

	}

}
