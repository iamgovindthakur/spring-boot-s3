package com.iamgkt.springboots3.service;

import java.io.IOException;
import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.iamgkt.springboots3.dto.Person;

public interface S3Service {

	ObjectMetadata addItemToS3(Person p) throws IOException;

	Person getPersonDetailFromS3(Integer id)
			throws AmazonServiceException, SdkClientException, IOException, ClassNotFoundException;

	void deleteAPersonfromS3(Integer id);

	void createNewBucket(String bucketName);

	void deleteBucket(String bucketName);

	void copyItemFromOneBucketToOther(String sourceBucketName, String sourceKey, String destinationBucketName,
			String destinationKey);

	List<String> listAllBucketName();

}