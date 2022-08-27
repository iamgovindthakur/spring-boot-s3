package com.iamgkt.springboots3.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "first_name", "last_name", "email", "gender", "ip_address" })
@Data
public class Person  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9198968005368317869L;
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("last_name")
	private String lastName;
	@JsonProperty("email")
	private String email;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("ip_address")
	private String ipAddress;

}
