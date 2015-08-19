package com.yfk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface UniversalManager {
	List getAll(Class clazz);

	List getAllDistinct(Class clazz);

	Object get(Class clazz, Serializable id);

	boolean exists(Class clazz, Serializable id);

	void save(Object object);

	void update(Object object);

	void remove(Object object);

	void remove(Class clazz, Serializable id);

	List findByNamedQuery(String queryName, Map<String, Object> queryParams);

	List findByHql(String hql, Map<String, Object> queryParams);

	List findByNativeSql(String sql, Map<String, Object> queryParams);
	
	List findByNativeSql(String sql, Map<String, Object> queryParams, Class clazz);
}
