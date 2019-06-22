package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.BuildingSearchDTO;
import com.laptrinhjavaweb.paging.Pageble;

public interface IBuildingService {
BuildingDTO save(BuildingDTO newBuilding);
List<BuildingDTO> findAll(BuildingSearchBuilder buildingSearchBuilder ,Pageble pageble);


}
