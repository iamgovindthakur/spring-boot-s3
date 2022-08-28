package com.iamgkt.springboots3.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.iamgkt.springboots3.dto.Person;
import com.iamgkt.springboots3.service.S3Service;

@RestController
public class S3Controller {

	@Autowired
	S3Service s3Service;

	@PostMapping("/person")
	public ObjectMetadata addItemToS3(@RequestBody Person p) throws IOException {
		return s3Service.addItemToS3(p);
	}
	
	@GetMapping("/person{id}")
	public Person getItem(@PathVariable Integer id) throws AmazonServiceException, SdkClientException, ClassNotFoundException, IOException
	{
		return s3Service.getPersonDetailFromS3(id);
	}

}
