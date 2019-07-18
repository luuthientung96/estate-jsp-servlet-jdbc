package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.utils.DataUtils;
import com.laptrinhjavaweb.utils.FormUtil;

@WebServlet(urlPatterns = { "/admin-building" })
public class BuildingController extends HttpServlet {
	private static final long serialVersionUID = 2686801510274002166L;
	@Inject
	private IBuildingService buildingService;
/*
	public BuildingController() {

		if (buildingService == null) {
			buildingService = new BuildingService();
		}
	}
*/
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Lấy giá trị name trên thanh URL put vào BuildingDTO
		BuildingDTO model = FormUtil.toModel(BuildingDTO.class,request);
		String action = request.getParameter("action");
		String url = "";
		if (action.equals("LIST")) {
			url = "/views/building/list.jsp";
			//HAm Put giá trị từ BuildingDTO vào builder
			BuildingSearchBuilder builder = initBuildingBuilder(model);
			Pageble pageble = new PageRequest(null, null, null);
			model.setListResults(buildingService.findAll(builder, pageble));
			// request.setAttribute("buildings",buildingService.findAll(builder, pageble));
		} else if (action.equals("EDIT")) {
			url = "/views/building/edit.jsp";
		}
		request.setAttribute("districts", DataUtils.getDistricts());
		request.setAttribute("buildingTypes", DataUtils.getBuildingTypes());
		request.setAttribute("model", model);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}
	//Hàm Put giá trị từ BuildingDTO vào builder
	private BuildingSearchBuilder initBuildingBuilder(BuildingDTO model) {
		BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
				.setName(model.getName())
				//.setNumberOfBasement(model.getNumberOfBasement())
				.setWard(model.getWard())
				.setStreet(model.getStreet())
				.setAreaRentFrom(model.getAreaRentFrom())
				.setAreaRentTo(model.getAreaRentTo())
				.setCostRentFrom(model.getCostRentFrom())
				.setCostRentTo(model.getCostRentTo()).build();
		return builder;//builder build 1 đối tượng mà chỉ chứa những cái ta cần
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
