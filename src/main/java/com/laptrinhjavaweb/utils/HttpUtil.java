package com.laptrinhjavaweb.utils;

import java.io.BufferedReader;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	private String value;

	public HttpUtil(String value) {
		this.value=value;
	}
	
	public <T> T toModel(Class<T> tClass) {
		try {
			System.out.println(new ObjectMapper().readValue(value, tClass));
			return new ObjectMapper().readValue(value, tClass);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return null;
	}
	
	
	public static HttpUtil of (BufferedReader reader) {

		StringBuilder sb = new StringBuilder();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			System.out.println(sb.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new HttpUtil(sb.toString());
	}
}
