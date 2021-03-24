package com.weida.epmain.service.impl;

import com.weida.epmain.dto.Data;
import com.weida.epmain.mapper.DataMapper;
import com.weida.epmain.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class DataServiceImpl implements DataService {

    @Resource
    private DataMapper dataMapper;


    @Override
    public void addData(Data data) {
        try {
            dataMapper.insert(data);
        }catch (DuplicateKeyException e){
            log.info(e.getMessage());
        }

    }
}
