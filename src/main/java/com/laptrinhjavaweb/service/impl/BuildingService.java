package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.repository.IBuildingRepository;
import com.laptrinhjavaweb.service.IBuildingService;

public class BuildingService implements IBuildingService {
	@Inject
	private IBuildingRepository buildingRepository;
	@Inject
	private BuildingConverter buildingConverter;
	
	/*
	public BuildingService() { 
		if(buildingRepository==null) {
			buildingRepository= new BuildingRepository();
		}
		if(buildingConverter == null) {
			buildingConverter= new BuildingConverter();
		}
	 }
	 */
	@Override
	public BuildingDTO save(BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		buildingEntity.setCreateDate(new Timestamp(System.currentTimeMillis()));
		buildingRepository.update(buildingEntity);
		return null;
	}

	@Override
	public List<BuildingDTO> findAll(BuildingSearchBuilder builder, Pageble pageble) {
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(builder, pageble);
		// Lam theo java 7
		/*
		 * for(BuildingEntity item:buildingEntities) { BuildingDTO
		 * buildingDTO=buildingConverter.convertToDTO(item); result.add(buildingDTO); }
		 */
		// Lam theo java 8
		List<BuildingDTO> results = buildingEntities.stream().map(item -> buildingConverter.convertToDTO(item))
				.collect(Collectors.toList());

		return results;
	}


}
