package com.ddtong.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ddtong.core.dao.mapper1.demo.YshExampleMapper;
import com.ddtong.core.entity.YshExampleEntity;
import com.ddtong.demo.service.YshExampleService;

@Service
@Transactional(value ="oneTransactionManager",readOnly = true)
public class YshExampleServiceImpl implements YshExampleService {

	@Autowired
	private YshExampleMapper yshExampleMapper;

	@Override
	public List<YshExampleEntity> getAll() {
		return yshExampleMapper.getAll();
	}

	@Override
	public YshExampleEntity getOne(Long id) {
		return yshExampleMapper.getOne(id);
	}

	@Override
	@Transactional(value ="oneTransactionManager",readOnly = true)
	public void insert(YshExampleEntity user) {
		yshExampleMapper.insert(user);

	}

	@Override
	@Transactional(readOnly = false)
	public void update(YshExampleEntity user) {
		yshExampleMapper.update(user);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Long id) {
		yshExampleMapper.delete(id);
	}

}
