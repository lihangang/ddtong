package com.ddtong.core.dao.mapper1.demo;

import java.util.List;

import com.ddtong.core.entity.YshExampleEntity;

public interface YshExampleMapper {
	
	List<YshExampleEntity> getAll();
	
	YshExampleEntity getOne(Long id);

	void insert(YshExampleEntity user);

	void update(YshExampleEntity user);

	void delete(Long id);

}