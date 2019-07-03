package com.laptrinhjavaweb.utils;

import java.util.HashMap;
import java.util.Map;

import com.laptrinhjavaweb.enums.BuildingTypeEnum;
import com.laptrinhjavaweb.enums.DistrictEnum;

public class DataUtils {
	public static Map<String, String> getBuildingTypes() {
		Map<String, String> results = new HashMap<String, String>();
		/*
		Stream.of(BuildingTypeEnum.values()).forEach(item->{
			results.put(item.name(),item.getValue());
		});
		*/
		for (BuildingTypeEnum item : BuildingTypeEnum.values()) {
			results.put(item.name(), item.getValue());
		}
		return results;
		}
	public static Map<String, String> getDistricts() {
		Map<String, String> results = new HashMap<String, String>();
		for (DistrictEnum item : DistrictEnum.values()) {
			results.put(item.name(), item.getValue());
		}
		return results;
		}
}
