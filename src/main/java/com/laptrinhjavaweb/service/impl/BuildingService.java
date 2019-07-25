package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentArea;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.repository.IBuildingRepository;
import com.laptrinhjavaweb.repository.IRentAreaRepository;
import com.laptrinhjavaweb.repository.impl.BuildingRepository;
import com.laptrinhjavaweb.repository.impl.RentAreaRepository;
import com.laptrinhjavaweb.service.IBuildingService;

public class BuildingService implements IBuildingService {
	//@Inject
	private IBuildingRepository buildingRepository = new BuildingRepository();
	//@Inject //chỉ sử dụng được cho interface,nếu class có constructor không dùng được denpedency 
	private BuildingConverter buildingConverter = new BuildingConverter();
	//@Inject 
	private IRentAreaRepository rentAreaRepository = new RentAreaRepository();
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
		buildingEntity.setCreateBy("");
		if(buildingDTO.getBuildingTypes().length>0) {
			buildingEntity.setType(StringUtils.join(buildingDTO.getBuildingTypes(),","));
		}
		
		Long id=buildingRepository.insert(buildingEntity);
		//save rentArea
		String[] array=buildingDTO.getRentArea().split(",");
		for(String item : array) {
				RentArea rentArea = new RentArea();
				rentArea.setValue(item);
				rentArea.setBuildingId(id);
				rentAreaRepository.insert(rentArea);
		}
		return buildingConverter.convertToDTO(buildingRepository.findbyId(id));
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
		//Chuyển Entity sang DTO rồi thêm vào mảng result
		//Hàm map chuyển đổi từ đối tượng này qua đối tượng khác,stream tạo luồng
		List<BuildingDTO> results = buildingEntities.stream().map(item -> buildingConverter.convertToDTO(item))
				.collect(Collectors.toList());

		return results;
	}

	@Override
	public BuildingDTO findById(Long id) {
		
		return buildingConverter.convertToDTO(buildingRepository.findbyId(id));
	}

	@Override
	public void update(BuildingDTO updateBuilding,Long id) {
		BuildingEntity oldBuilding = buildingRepository.findbyId(id);
		BuildingEntity newBuilding=buildingConverter.convertToEntity(updateBuilding);
		newBuilding.setCreateBy(oldBuilding.getCreateBy());
		newBuilding.setCreateDate(oldBuilding.getCreateDate());
		//rent area
		String rentArea=updateBuilding.getRentArea();	
		updateRenArea(rentArea,id);
		newBuilding.setType(StringUtils.join(updateBuilding.getBuildingTypes(),","));
		buildingRepository.update(newBuilding);
	}

	private void updateRenArea(String rentAreaStr, Long buildingId) {
		// delete rentarea theo building id
		rentAreaRepository.deleteByBuilding(buildingId);
		for (String item : rentAreaStr.split(",")) {
			RentArea rentArea = new RentArea();
			rentArea.setBuildingId(buildingId);
			rentArea.setValue(item);
			//insert rentarea
			rentAreaRepository.insert(rentArea);
			
		}
	}

	@Override
	public void delete(Long[] ids) {
		for(long item : ids) {
			rentAreaRepository.deleteByBuilding(item);
			buildingRepository.delete(item);
		}
		
	}


}
