package com.fitriarien.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor //need default constructor for JSON Parsing
@AllArgsConstructor
@Data
@Builder
public class LoginUserRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	
	private String username;

	private String password;

}