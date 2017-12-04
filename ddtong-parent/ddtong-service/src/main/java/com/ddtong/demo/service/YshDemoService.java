package com.ddtong.demo.service;

import java.util.List;

import com.ddtong.entity.YshDemoEntity;

public interface YshDemoService {
	
	List<YshDemoEntity> getAll();

	YshDemoEntity getOne(Long id);

	void insert(YshDemoEntity user);

	void update(YshDemoEntity user);

	void delete(Long id);
	
}
