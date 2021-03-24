package com.weida.epuser.service.impl;

import com.weida.epuser.dto.DictItem;
import com.weida.epuser.mapper.DictItemMapper;
import com.weida.epuser.service.DictItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DictItemServiceImpl implements DictItemService {

    @Resource
    private DictItemMapper dictItemMapper;

    @Override
    public void delDictItemById(DictItem dictItem) {
        dictItemMapper.delDictItemById(dictItem);
    }
}
