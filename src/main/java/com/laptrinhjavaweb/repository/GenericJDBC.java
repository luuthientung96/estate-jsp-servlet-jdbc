package com.laptrinhjavaweb.repository;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.paging.Pageble;

public interface GenericJDBC<T> {
	List<T> query(String sql, Object... parameters);
	public void update(String sql, Object... parameters);
	public Long insert(String sql, Object... parameters);
	List<T> findAll(Map<String,Object> properties,Pageble pageble,Object...where);
	public Long insert(Object object);
	void update(Object object);
	void delete(long id);
	void deleteByProperty(String where);
	<T> T findbyId(long id);
	int countByProperty(Map<String, Object> properties, Object... where);
	
	
}
