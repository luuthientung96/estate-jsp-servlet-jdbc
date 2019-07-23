package com.laptrinhjavaweb.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.impl.BuildingService;
import com.laptrinhjavaweb.utils.HttpUtil;
@WebServlet(urlPatterns = {"/api-admin-building"})
public class BuildingAPI extends HttpServlet {
	private static final long serialVersionUID = -7319894879440008039L;
	
	private IBuildingService buildingService;
	public BuildingAPI() {
		buildingService = new BuildingService();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Đối tượng giúp chuyển qua lại giữa chuỗi JSON và model 
		ObjectMapper mapper= new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		//chuyển chuỗi JSON vào model
		BuildingDTO buildingDTO= HttpUtil.of(request.getReader()).toModel(BuildingDTO.class); 
		
		//logic
		
		buildingDTO=buildingService.save(buildingDTO);
		//Trả về JSON từ model
		mapper.writeValue(response.getOutputStream(), buildingDTO);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Đối tượng giúp chuyển qua lại giữa chuỗi JSON và model 
		ObjectMapper mapper= new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		//chuyển chuỗi JSON vào model
		BuildingDTO buildingDTO= HttpUtil.of(request.getReader()).toModel(BuildingDTO.class); 
		
		//logic
		
		//buildingDTO=buildingService.save(buildingDTO);
		//Trả về JSON từ model
		mapper.writeValue(response.getOutputStream(), buildingDTO);
	}
}
