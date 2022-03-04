package com.volkovt.hangmanapi.dto.exceptions;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BusinessError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String message;
	private String path;
	
}
