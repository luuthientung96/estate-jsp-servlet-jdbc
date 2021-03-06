package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.paging.Sorter;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.impl.BuildingService;
import com.laptrinhjavaweb.utils.DataUtils;
import com.laptrinhjavaweb.utils.FormUtil;

@WebServlet(urlPatterns = { "/admin-building" })
public class BuildingController extends HttpServlet {
	private static final long serialVersionUID = 2686801510274002166L;
	private IBuildingService buildingService = new BuildingService();

	/*
	 * public BuildingController() {
	 * 
	 * if (buildingService == null) { buildingService = new BuildingService(); } }
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy giá trị name trên thanh URL put vào BuildingDTO
		BuildingDTO model = FormUtil.toModel(BuildingDTO.class, request);
		String action = request.getParameter("action");
		String url = "";
		if (action.equals("LIST")) {
			url = "/views/building/list.jsp";
			// HAm Put giá trị từ BuildingDTO vào builder
			BuildingSearchBuilder builder = initBuildingBuilder(model);
			Sorter sorter=new Sorter(model.getSortName(), model.getSortBy());
			Pageble pageble = new PageRequest(model.getPage(),model.getMaxPageItem(),sorter);
			model.setTotalItem(buildingService.getTotalItem(builder));
			model.setTotalPage((int) Math.ceil((double)model.getTotalItem()/model.getMaxPageItem()));
			model.setListResults(buildingService.findAll(builder, pageble));
			// request.setAttribute("buildings",buildingService.findAll(builder, pageble));
		} else if (action.equals("EDIT")) {
			url = "/views/building/edit.jsp";
			if (model.getId() != null) {
				model = buildingService.findById(model.getId());
			}
		}
		// DataUtils render giá trị từ Enum
		request.setAttribute("districts", DataUtils.getDistricts());
		request.setAttribute("buildingTypes", DataUtils.getBuildingTypes());
		request.setAttribute("model", model);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}

	// Hàm Put giá trị từ BuildingDTO vào builder
	private BuildingSearchBuilder initBuildingBuilder(BuildingDTO model) {
		BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder().setName(model.getName())
				.setWard(model.getWard()).setStreet(model.getStreet()).setDistrict(model.getDistrict())
				.setNumberOfBasement(model.getNumberOfBasement()).setBuildingArea(model.getBuildingArea())
				.setAreaRentFrom(model.getAreaRentFrom()).setAreaRentTo(model.getAreaRentTo())
				.setCostRentFrom(model.getCostRentFrom()).setBuildingTypes(model.getBuildingTypes())
				.setCostRentTo(model.getCostRentTo()).build();
		return builder;// builder build 1 đối tượng mà chỉ chứa những cái ta cần
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
