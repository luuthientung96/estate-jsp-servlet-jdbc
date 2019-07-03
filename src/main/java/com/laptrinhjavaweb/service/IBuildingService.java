package com.laptrinhjavaweb.service;

import java.util.List;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.Pageble;

public interface IBuildingService {
BuildingDTO save(BuildingDTO newBuilding);
List<BuildingDTO> findAll(BuildingSearchBuilder builder,Pageble pageble);
}
