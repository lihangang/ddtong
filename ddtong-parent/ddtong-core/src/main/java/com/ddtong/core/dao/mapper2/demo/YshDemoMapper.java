package com.ddtong.core.dao.mapper2.demo;

import java.util.List;

import com.ddtong.core.entity.YshDemoEntity;

public interface YshDemoMapper {
	
	List<YshDemoEntity> getAll();
	
	YshDemoEntity getOne(Long id);

	void insert(YshDemoEntity user);

	void update(YshDemoEntity user);

	void delete(Long id);

}