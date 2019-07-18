package com.laptrinhjavaweb.repository.impl;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.repository.IBuildingRepository;

public class BuildingRepository extends AbstractJDBC<BuildingEntity> implements IBuildingRepository {

	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder builder, Pageble pageble) {
		Map<String, Object> properties = buildMapSearch(builder);
		StringBuilder whereClause= new StringBuilder();
		if(StringUtils.isNotBlank(builder.getCostRentFrom())) {
			whereClause.append(" AND costrent >= "+builder.getCostRentFrom()+"");
		}
		if(StringUtils.isNotBlank(builder.getCostRentTo())) {
			whereClause.append(" AND costrent <= "+builder.getCostRentTo()+"");
		}
		/*
		Select*From building b where
		Exists (Select*From rentarea ra Where (ra.buildingId=b.id)
				AND ra.value >='400' and ra.value<='700')
		*/
		//Xử lý để search các field phức tạp
		if(StringUtils.isNotBlank(builder.getAreaRentFrom()) ||StringUtils.isNotBlank(builder.getAreaRentTo())) {
			whereClause.append(" AND EXISTS (SELECT*FROM rentarea ra WHERE (ra.buildingId=A.id");
			if(builder.getAreaRentFrom()!=null) {
				whereClause.append(" AND ra.value >='"+builder.getAreaRentFrom()+"' ");
			}
			if(builder.getAreaRentTo()!=null) {
				whereClause.append(" AND ra.value<='"+builder.getAreaRentFrom()+"'");
			}
			whereClause.append("))");
		}
		if(builder.getBuildingTypes().length>0) {
			//Java 7
			/*
			for (String type : builder.getBuildingTypes()) {
				whereClause.append(" AND (A.type LIKE '%"+builder.getBuildingTypes()[0]+"%'");
				if(!type.equals(builder.getBuildingTypes()[0])) {
					whereClause.append(" OR A.type LIKE'&"+type+"&'");
				}
			}
			*/
			//Java 8
			Arrays.stream(builder.getBuildingTypes()).filter(item -> !item.equals(builder.getBuildingTypes()[0]))
			.forEach(item ->whereClause.append(" OR A.type LIKE'&"+item+"&'"));
			whereClause.append(" )");
		}
		return findAll(properties, pageble,whereClause.toString());
	}
	//Chuyển dữ liệu builder vào map
	private Map<String, Object> buildMapSearch(BuildingSearchBuilder builder) {
		Map<String, Object> result = new HashMap<>();
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field field : fields) {
				//loại trừ các field phức tạp và add các field đơn giản vào map
				if (!field.getName().equals("buildingTypes") && !field.getName().startsWith("costRent")
						&& !field.getName().startsWith("areaRent")) {
					field.setAccessible(true);
					if (field.get(builder) != null) {
						result.put(field.getName().toLowerCase(), field.get(builder));
					}

				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return result;
	}
}
