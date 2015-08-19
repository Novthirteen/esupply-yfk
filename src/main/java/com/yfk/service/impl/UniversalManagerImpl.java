package com.yfk.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfk.dao.UniversalDao;
import com.yfk.model.Auditable;
import com.yfk.model.Traceable;
import com.yfk.service.UniversalManager;
import com.yfk.webapp.util.PrincipalNullException;

@Service("universalManager")
public class UniversalManagerImpl implements UniversalManager {
	
	private final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private UniversalDao dao;
	
	@Override
	public List getAll(Class clazz) {
		return dao.getAll(clazz);
	}

	@Override
	public List getAllDistinct(Class clazz) {
		return dao.getAllDistinct(clazz);
	}

	@Override
	public Object get(Class clazz, Serializable id) {
		return dao.get(clazz, id);
	}

	@Override
	public boolean exists(Class clazz, Serializable id) {
		return dao.exists(clazz, id);
	}

	@Override
	public void save(Object object) throws PrincipalNullException {
		dao.save(object);
	}

	@Override
	public void update(Object object) throws PrincipalNullException {
		dao.update(object);
	}

	@Override
	public void remove(Object object) {
		dao.remove(object);
	}

	@Override
	public void remove(Class clazz, Serializable id) {
		dao.remove(clazz, id);
	}

	@Override
	public List findByNamedQuery(String queryName, Map<String, Object> queryParams) {
		return dao.findByNamedQuery(queryName, queryParams);
	}

	@Override
	public List findByHql(String hql, Map<String, Object> queryParams) {
		return dao.findByHql(hql, queryParams);
	}

	@Override
	public List findByNativeSql(String sql, Map<String, Object> queryParams) {
		return dao.findByNativeSql(sql, queryParams);
	}

	@Override
	public List findByNativeSql(String sql, Map<String, Object> queryParams, Class clazz) {
		return dao.findByNativeSql(sql, queryParams, clazz);
	}
	

}
