package com.laptrinhjavaweb.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Table;
import com.laptrinhjavaweb.mapper.ResultSetMapper;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.paging.Sorter;
import com.laptrinhjavaweb.repository.GenericJDBC;

public class AbstractJDBC<T> implements GenericJDBC<T> {

	private Class<T> zClass;

	@SuppressWarnings("unchecked")
	public AbstractJDBC() {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) type;
		zClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	private Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/estate4month2019";
			String user = "root";
			String password = "1234";
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public List<T> query(String sql, Object... parameters) {
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<T>();

		try (Connection conn = getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery();) {

			if (conn != null) {
				// set parameters statement
				for (int i = 0; i < parameters.length; i++) {
					int index = i + 1;
					statement.setObject(index, parameters[i]);
				}

				return resultSetMapper.mapRow(resultSet, this.zClass);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return null;
	}

	@Override
	public void update(String sql, Object... parameters) {

		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = getConnection();
			statement = conn.prepareStatement(sql);
			conn.setAutoCommit(false);// Tắt tự động commit

			if (conn != null) {
				// set parameter to statement
				/*
				 * for (int i = 0; i < parameters.length; i++) { Object parameter=parameters[i];
				 * int index=i+1; if(parameter instanceof String) {
				 * statement.setString(index,(String) parameter); }else if(parameter instanceof
				 * Integer) { statement.setInt(index, (Integer) parameter); } }
				 */
				for (int i = 0; i < parameters.length; i++) {
					int index = i + 1;
					statement.setObject(index, parameters[i]);
				}

				statement.executeUpdate();
				conn.commit();

			}

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resulset = null;

		try {
			conn = getConnection();
			statement = conn.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
			conn.setAutoCommit(false);
			if (conn != null) {
				// set parameter to statement
				for (int i = 0; i < parameters.length; i++) {
					int index = i + 1;
					statement.setObject(index, parameters[i]);
				}
				int rowInsert = statement.executeUpdate();
				conn.commit();
				resulset = statement.getGeneratedKeys();
				if (rowInsert > 0) {
					while (resulset.next()) {
						Long id = resulset.getLong(1);
						return id;

					}
				}
			}

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		return null;
	}

// Insert fixed
	@Override
	public Long insert(Object object) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resulset = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql = createSQLInsert();
			statement = conn.prepareStatement(sql);

			String tableName = "";
			if (zClass.isAnnotationPresent(Table.class)) {
				Table table = zClass.getAnnotation(Table.class);
				tableName = table.name();
			}

			if (conn != null) {
				Class<?> zClass = object.getClass();
				Field[] fields = zClass.getDeclaredFields();
				// set parameter to statement

				for (int i = 0; i < fields.length; i++) {
					Field field = fields[i];
					field.setAccessible(true);
					int index = i + 1;
					statement.setObject(index, field.get(object));
				}
				Class<?> parentClass = zClass.getSuperclass();
				int indexParent = fields.length + 1;
				while (parentClass != null) {

					for (int i = 0; i < parentClass.getDeclaredFields().length; i++) {
						Field field = parentClass.getDeclaredFields()[i];
						field.setAccessible(true);
						statement.setObject(indexParent, field.get(object));
						indexParent = indexParent + 1;
					}
					parentClass = parentClass.getSuperclass();

				}
				int rowInsert = statement.executeUpdate();
				conn.commit();
				resulset = statement.getGeneratedKeys();
				if (rowInsert > 0) {
					while (resulset.next()) {
						Long id = resulset.getLong(1);
						return id;

					}
				}
			}

		} catch (SQLException | IllegalAccessException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		return null;
	}

// Create SQL Insert
	private String createSQLInsert() {
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();

		}
		StringBuilder fields = new StringBuilder("");
		StringBuilder params = new StringBuilder("");
		for (Field field : zClass.getDeclaredFields()) {
			if (fields.length() > 1) {
				fields.append(",");
				params.append(",");
			}
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				fields.append(column.name());
				params.append("?");

			}
		}
		// check parent class
		Class<?> parentClass = zClass.getSuperclass();
		while (parentClass != null) {
			// logic data

			for (Field field : parentClass.getDeclaredFields()) {
				if (fields.length() > 1) {
					fields.append(",");
					params.append(",");
				}
				if (field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					fields.append(column.name());
					params.append("?");
				}
			}
			parentClass = parentClass.getSuperclass(); // de zClass.getSuperclass() se lap khong ngung

		}
		String sql = "INSERT INTO " + tableName + "(" + fields.toString() + ") VALUES(" + params.toString() + ")";
		return sql;
	}

// Update fixed
	@Override
	public void update(Object object) {
		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql = createSQLUpdate();
			statement = conn.prepareStatement(sql);

			if (conn != null) {
				Class<?> zClass = object.getClass();
				Field[] fields = zClass.getDeclaredFields();
				// set parameter to statement

				for (int i = 0; i < fields.length; i++) {
					Field field = fields[i];
					field.setAccessible(true);// Cấp quyền truy cập object
					int index = i + 1;
					statement.setObject(index, field.get(object));
				}
				Class<?> parentClass = zClass.getSuperclass();
				int indexParent = fields.length + 1;
				Object id = null;
				while (parentClass != null) {

					for (int i = 0; i < parentClass.getDeclaredFields().length; i++) {
						Field field = parentClass.getDeclaredFields()[i];
						field.setAccessible(true);
						String name = field.getName();
						if (!name.equals("id")) {
							statement.setObject(indexParent, field.get(object));
							indexParent = indexParent + 1;
						}

					}
					parentClass = parentClass.getSuperclass();

				}
				statement.setObject(indexParent, id);
				statement.executeUpdate();
				conn.commit();
			}

		} catch (SQLException | IllegalAccessException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}

// Create SQL Update
	private String createSQLUpdate() {
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();

		}
		String where = null;
		StringBuilder sets = new StringBuilder("");
		for (Field field : zClass.getDeclaredFields()) {
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				String columnName = column.name();
				String value = columnName + "=?";
				if (!columnName.equals("id")) {
					if (sets.length() > 1) {
						sets.append(", ");
					}
					sets.append(value);
				}

			}
		}
		// check parent class
		Class<?> parentClass = zClass.getSuperclass();
		while (parentClass != null) {
			// logic data

			for (Field field : parentClass.getDeclaredFields()) {
				if (field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					String columnName = column.name();
					String value = columnName + "=?";
					if (!columnName.equals("id")) {
						if (sets.length() > 1) {
							sets.append(", ");
						}
						sets.append(value);
					} else {
						where = " WHERE " + value;
					}

				}
			}
			parentClass = parentClass.getSuperclass(); // de zClass.getSuperclass() se lap khong ngung

		}
		String sql = "UPDATE " + tableName + " SET " + sets.toString() + where;
		System.out.println(sql);
		return sql;
	}

// DELETE FIXED
	@Override
	public void delete(long id) {
		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String tableName = "";
			if (zClass.isAnnotationPresent(Table.class)) {
				Table table = zClass.getAnnotation(Table.class);
				tableName = table.name();
			}
			String sql = "DELETE FROM " + tableName + " WHERE id = ?";
			statement = conn.prepareStatement(sql);

			if (conn != null) {
				statement.setObject(1, id);
				statement.executeUpdate();
				conn.commit();
			}

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}

// FIND BY ID
	@Override
	public <T> T findbyId(long id) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<T>();
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		String sql = "SELECT * FROM " + tableName + " WHERE id=?";
		try {
			conn = getConnection();
			statement = conn.prepareStatement(sql);
			statement.setObject(1, id);
			resultSet = statement.executeQuery();

			if (conn != null) {
				return resultSetMapper.mapRow(resultSet, this.zClass).get(0);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		return null;
	}

//FIND ALL
	@Override
	public List<T> findAll(Map<String, Object> properties,Pageble pageble,Object...where) {
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<T>();
		
		StringBuilder sql = createSQLfindAll(properties);
		if(where!=null && where.length>0) {
			sql.append(where[0]);
		}
		if(pageble!=null) {
			if(pageble.getSorter()!=null) {
				Sorter sorter =pageble.getSorter();
				sql.append(" ORDER BY "+sorter.getSortName()+" "+sorter.getSortBy());
			}
			
			if(pageble.getOffset()!=null && pageble.getLimit()!=null) {
				sql.append(" LIMIT "+pageble.getOffset()+" , "+pageble.getLimit()+"");
			}

		}
		
		try {
			conn = getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql.toString());

			if (conn != null) {
				return resultSetMapper.mapRow(resultSet, this.zClass);
			}

		} catch (SQLException e) {
			e.getMessage();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		return null;
	}

//CREATE SQL FIND ALL
	private StringBuilder createSQLfindAll(Map<String, Object> properties) {
		String tableName = "";
		//Lấy ra tên Table
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		StringBuilder result = new StringBuilder("SELECT * FROM " + tableName + " A WHERE 1=1");
		if (properties != null && properties.size() > 0) {
			String[] params = new String[properties.size()];
			Object[] values = new Object[properties.size()];
			int i = 0;
			//Lấy key và value trong map put vào params và values
			for (Map.Entry<?, ?> item : properties.entrySet()) {
				params[i] = (String) item.getKey();
				values[i] = item.getValue();
				i++;
			}
			//Viết tiếp câu lệnh SELECT*FROM tableName A WHERE 1=1 and LOWER(name) like %abc%
			for (int i1 = 0; i1 < params.length; i1++) {
				if (values[i1] instanceof String) {
					result.append(" and LOWER(" + params[i1] + ") LIKE '%" + values[i1].toString().toLowerCase()+ "%'");
				}else if(values[i1] instanceof Integer) {
					result.append(" and "+ params[i1] +"="+ values[i1]+ " ");
				}
				else if(values[i1] instanceof Long) {
					result.append(" and "+ params[i1] +"="+ values[i1]+ " ");
				}
				
			}
		}
		return result;
	}

}
