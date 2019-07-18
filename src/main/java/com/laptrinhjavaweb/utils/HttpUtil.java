package com.laptrinhjavaweb.utils;

import java.io.BufferedReader;

import com.fasterxml.jackson.databind.ObjectMapper;
//Chuyển file JSon về dạng String và boot giá trị đó vào model
public class HttpUtil {
	//cho giá trị String trong hàm of về String value
	private String value;

	public HttpUtil(String value) {
		this.value=value;
	}
	//map vào model
	public <T> T toModel(Class<T> tClass) {
		try {
			//System.out.println(new ObjectMapper().readValue(value, tClass));
			return new ObjectMapper().readValue(value, tClass);//hàm readValue chuyển Json vào model
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return null;
	}
	
	//Chuyển JSON về dạng String
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
