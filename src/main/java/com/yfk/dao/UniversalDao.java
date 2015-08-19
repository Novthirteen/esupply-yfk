package com.yfk.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yfk.webapp.util.PrincipalNullException;

public interface UniversalDao {
	List getAll(Class clazz);

	List getAllDistinct(Class clazz);

	Object get(Class clazz, Serializable id);

	boolean exists(Class clazz, Serializable id);

	void save(Object object) throws PrincipalNullException;

	void update(Object object) throws PrincipalNullException;

	void remove(Object object);

	void remove(Class clazz, Serializable id);

	List findByNamedQuery(String queryName, Map<String, Object> queryParams);

	List findByHql(String hql, Map<String, Object> queryParams);

	List findByNativeSql(String sql, Map<String, Object> queryParams);
	
	List findByNativeSql(String sql, Map<String, Object> queryParams, Class clazz);
}
