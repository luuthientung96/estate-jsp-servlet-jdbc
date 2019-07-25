package com.laptrinhjavaweb.repository.impl;

import com.laptrinhjavaweb.entity.RentArea;
import com.laptrinhjavaweb.repository.IRentAreaRepository;

public class RentAreaRepository extends AbstractJDBC<RentArea> implements IRentAreaRepository {

	@Override
	public void deleteByBuilding(long id){
		String where="WHERE buildingid="+id+"";
		this.deleteByProperty(where);
		
	}


}
