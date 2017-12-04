package com.ddtong.demo.service;

import java.util.List;

import com.ddtong.entity.YshExampleEntity;

public interface YshExampleService {
	
	List<YshExampleEntity> getAll();
	
	YshExampleEntity getOne(Long id);

	void insert(YshExampleEntity user);

	void update(YshExampleEntity user);

	void delete(Long id);
	
}
