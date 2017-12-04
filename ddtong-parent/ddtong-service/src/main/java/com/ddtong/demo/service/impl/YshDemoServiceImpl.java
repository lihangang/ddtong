package com.ddtong.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ddtong.core.dao.mapper2.demo.YshDemoMapper;
import com.ddtong.core.entity.YshDemoEntity;
import com.ddtong.demo.service.YshDemoService;

@Service
@Transactional(value ="twoTransactionManager",readOnly = true)
public class YshDemoServiceImpl implements YshDemoService {

	@Autowired
	private YshDemoMapper yshDemoMapper;

	@Override
	public List<YshDemoEntity> getAll() {
		return yshDemoMapper.getAll();
	}

	@Override
	public YshDemoEntity getOne(Long id) {
		return yshDemoMapper.getOne(id);
	}

	@Override
	@Transactional(value ="twoTransactionManager",readOnly = true)
	public void insert(YshDemoEntity user) {
		yshDemoMapper.insert(user);

	}

	@Override
	@Transactional(readOnly = false)
	public void update(YshDemoEntity user) {
		yshDemoMapper.update(user);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Long id) {
		yshDemoMapper.delete(id);
	}

}
