package com.yfk.service;

import java.io.Serializable;
import java.util.List;

import com.yfk.webapp.util.PrincipalNullException;

public interface UniversalManager {
	List getAll(Class clazz);

	List getAllDistinct(Class clazz);

	Object get(Class clazz, Serializable id);

	boolean exists(Class clazz, Serializable id);

	void save(Object object) throws PrincipalNullException;

	void update(Object object) throws PrincipalNullException;

	void remove(Object object);

	void remove(Class clazz, Serializable id);

	void executeByHql(String hql);

	void executeByHql(String hql, Object[] params);

	void executeByNativeSql(String sql);

	void executeByNativeSql(String sql, Object[] params);

	List findByNamedQuery(String queryName, Object[] queryParams);

	List findByHql(String hql, Object[] queryParams);

	List findByNativeSql(String sql, Object[] queryParams);

	List findByNativeSql(Class clazz, String sql, Object[] queryParams);
}
