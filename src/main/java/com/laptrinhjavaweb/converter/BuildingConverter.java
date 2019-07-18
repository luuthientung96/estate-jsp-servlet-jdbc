package com.laptrinhjavaweb.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentArea;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.repository.IRentAreaRepository;

public class BuildingConverter {
	
	@Inject 
	private IRentAreaRepository rentAreaRepository;
	
	public BuildingDTO convertToDTO(BuildingEntity buildingEntity) {
		ModelMapper modelMapper = new ModelMapper();
		BuildingDTO result = modelMapper.map(buildingEntity, BuildingDTO.class);
		Map<String, Object> condition= new HashMap<>();
		condition.put("buildingid",buildingEntity.getId());
 		List<RentArea> rentAreas = rentAreaRepository.findAll(condition, new PageRequest(null, null, null));
 		//Java 7
		List<String> areas = new ArrayList<>();
		for (RentArea item : rentAreas) {
			areas.add(item.getValue());
		}
		/*Java 8
		List<RentArea> rentAreas = rentAreaRepository.findAll(condition, new PageRequest(null, null, null))
		.stream().map(RentArea::getValue).collect(Collectors.toList());
		*/
		if(areas.size()>0) {
			result.setRentArea(StringUtils.join(areas,","));
		}
		
		return result;
	}
	
	public BuildingEntity convertToEntity(BuildingDTO buildingDTO) {
		ModelMapper modelMapper = new ModelMapper();
		BuildingEntity result = modelMapper.map(buildingDTO, BuildingEntity.class);
		return result;
	}
}
