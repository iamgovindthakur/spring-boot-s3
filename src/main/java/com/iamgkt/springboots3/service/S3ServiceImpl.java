package com.iamgkt.springboots3.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iamgkt.springboots3.dto.Person;

@Service
public class S3ServiceImpl implements S3Service {

	private static final String BUCKET_NAME = "iamgkt-spring-s3";

	@Autowired
	AmazonS3 client;

	@Autowired
	ObjectMapper mapper;

	@Override
	public ObjectMetadata addItemToS3(Person p) throws IOException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream serializer = new ObjectOutputStream(bos);
		serializer.writeObject(p);
		serializer.flush();
		serializer.close();
		InputStream stream = new ByteArrayInputStream(bos.toByteArray());
		ObjectMetadata metaData = new ObjectMetadata();
		metaData.setContentType("application/x-java-serialized-object");
		metaData.setContentLength(bos.size());

		PutObjectResult putObject = client.putObject(BUCKET_NAME, p.getId().toString(), stream, metaData);
		return putObject.getMetadata();
	}

	@Override
	public Person getPersonDetailFromS3(Integer id)
			throws AmazonServiceException, SdkClientException, IOException, ClassNotFoundException {

		ObjectInputStream object = null;
		object = new ObjectInputStream(client.getObject(BUCKET_NAME, id.toString()).getObjectContent());
		return (Person) object.readObject();
	}

	@Override
	public void deleteAPersonfromS3(Integer id) {
		client.deleteObject(BUCKET_NAME, id.toString());

	}

	@Override
	public void createNewBucket(String bucketName) {
		client.createBucket(bucketName);
		
	}

	@Override
	public void deleteBucket(String bucketName) {
		client.deleteBucket(bucketName);

	}

	@Override
	public void copyItemFromOneBucketToOther(String sourceBucketName, String sourceKey, String destinationBucketName,
			String destinationKey) {
		client.copyObject(sourceBucketName, sourceKey, destinationBucketName, destinationKey);
	}

	@Override
	public List<String> listAllBucketName() {
		List<String> bucketNameList = new ArrayList<>();
		client.listBuckets().forEach(b -> bucketNameList.add(b.getName()));
		return bucketNameList;
	}

}
