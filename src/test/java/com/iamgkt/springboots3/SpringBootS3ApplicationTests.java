package com.iamgkt.springboots3;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.iamgkt.springboots3.service.S3Service;

@SpringBootTest
class SpringBootS3ApplicationTests {

	@Autowired
	S3Service s3Service;

	@Autowired
	AmazonS3 client;

	@Test
	void TruncantBucket() {

		ObjectListing listObjects = client.listObjects("iamgkt-spring-s3");
		List<S3ObjectSummary> objectSummaries = listObjects.getObjectSummaries();
		objectSummaries.forEach(os -> client.deleteObject(os.getBucketName(), os.getKey()));

	}

	@Test
	public void deleteBucket() {
		s3Service.deleteBucket("iamgkt-spring-s3");
	}
	@Test
	public void createBucket() {
		s3Service.createNewBucket("iamgkt-spring-s3");
	}

}
