package com.bank.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ApiResponseDto implements Serializable {

	private LocalDateTime timestamp;
	
	protected int status;
	
	protected String path;
	
	protected Object response;
	
	public ApiResponseDto(int status, String path) {
		this.status = status;
		this.path = path;
		this.timestamp = LocalDateTime.now();
	}

	private static final long serialVersionUID = 8140207020324123921L;
}