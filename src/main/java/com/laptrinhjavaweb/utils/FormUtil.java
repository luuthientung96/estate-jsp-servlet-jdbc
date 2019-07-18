package com.laptrinhjavaweb.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
//Lấy dữ liệu trên thanh URL rồi put vào model(DTO)
public class FormUtil {
	public static <T> T toModel(Class<T> clazz,HttpServletRequest request) {
		T object=null;
		try {
			object=clazz.newInstance();
			//Hàm chuyển đổi dữ liệu trên thanh URL xuống object
			BeanUtils.populate(object, request.getParameterMap());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return object;
	}
}
