package com.bank.dto;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ResultDto implements Serializable {
	
	protected String result;
	
	protected String message;
	
	protected Map<String, Object> errors;
	
	protected Map<String, Object> details;
	
	
	public ResultDto(String result, String message) {
		this.result = result;
		this.message = message;
	}

	public ResultDto(String result) {
		this.result = result;
	}

	private static final long serialVersionUID = -1657238841657755082L;

}