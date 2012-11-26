package com.fm.template.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fm.template.util.FilterObject;

public interface GenericDAO <T, PK extends Serializable> {

    List<T> all();
    
    Long count();
    
    Long count(List<FilterObject> filters);

    List<T> distinct();

    T get(PK id);

    boolean exists(PK id);

    PK save(T object);
    
    void update(T object);
    
    void saveOrUpdate(T object);
    
    T merge(T object);

    void remove(PK id);
    
    void remove(T object);
    
    T filter(String property, String value);

    List<T> filter(Integer first, Integer size, List<FilterObject> filters, String sortColumn, String sortOrder);
    
    List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams);
	
}