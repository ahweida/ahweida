package com.weida.epuser.service.impl;

import com.weida.epuser.dto.Dict;
import com.weida.epuser.mapper.DictMapper;
import com.weida.epuser.service.DictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DictServiceImpl implements DictService {

    @Resource
    private DictMapper dictMapper;

    @Override
    public void delDictById(Dict dict) {
        dictMapper.delDictById(dict);
    }
}
