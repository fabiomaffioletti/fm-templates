package com.fm.template.manager;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fm.template.util.FilterObject;

public interface GenericManager <T, PK extends Serializable> {

    List<T> all();
    
    List<T> filter(Integer first, Integer size, List<FilterObject> filters, String sortColumn, String sortOrder);

    List<T> distinct();

    T get(PK id);
    
    T filter(String property, String value);

    boolean exists(PK id);

    T merge(T object, boolean systemUpdate);
    
    PK save(T object, boolean systemUpdate);
    
    void update(T object, boolean systemUpdate);
    
    void saveOrUpdate(T object, boolean systemUpdate);
    
    void remove(PK id);
    
    void remove(T object);
    
    Integer count();
    
    Integer count(List<FilterObject> filters);

    List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams);
}