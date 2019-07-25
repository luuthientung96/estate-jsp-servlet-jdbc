package com.laptrinhjavaweb.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentArea;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.repository.IRentAreaRepository;
import com.laptrinhjavaweb.repository.impl.RentAreaRepository;

public class BuildingConverter {


	private IRentAreaRepository rentAreaRepository = new RentAreaRepository();

	public BuildingDTO convertToDTO(BuildingEntity buildingEntity) {
		ModelMapper modelMapper = new ModelMapper();
		BuildingDTO result = modelMapper.map(buildingEntity, BuildingDTO.class);
		Map<String, Object> condition = new HashMap<>();
		condition.put("buildingid", buildingEntity.getId());
		List<RentArea> rentAreas = rentAreaRepository.findAll(condition, new PageRequest(null, null, null));
		// Java 7
		List<String> areas = new ArrayList<>();
		for (RentArea item : rentAreas) {
			areas.add(item.getValue());
		}
		/*
		 * Java 8 List<RentArea> rentAreas = rentAreaRepository.findAll(condition, new
		 * PageRequest(null, null, null))
		 * .stream().map(RentArea::getValue).collect(Collectors.toList());
		 */
		if (areas.size() > 0) {
			// Chuyển đổi qua chuỗi rentArea
			result.setRentArea(StringUtils.join(areas, ","));
		}
		if(StringUtils.isNotBlank(buildingEntity.getType())) {
			 result.setBuildingTypes(buildingEntity.getType().split(","));
		}
		return result;
	}

	public BuildingEntity convertToEntity(BuildingDTO buildingDTO) {
		ModelMapper modelMapper = new ModelMapper();
		BuildingEntity result = modelMapper.map(buildingDTO, BuildingEntity.class);
		if(StringUtils.isNotBlank(buildingDTO.getNumberOfBasement())) {
			result.setNumberOfBasement(Integer.parseInt(buildingDTO.getNumberOfBasement()));
		}
		if(StringUtils.isNotBlank(buildingDTO.getBuildingArea())){
			result.setBuildingArea(Integer.parseInt(buildingDTO.getBuildingArea()));
		}
		
		return result;
	}
}
