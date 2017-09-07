package com.leezhong.service.impl;

import com.leezhong.dao.TestMapper;
import com.leezhong.domain.Test;
import com.leezhong.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private TestMapper testMapper;

    public Test getTetsById(Integer id) {

        return testMapper.selectByPrimaryKey(id);

    }
}
