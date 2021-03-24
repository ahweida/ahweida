package com.weida.epmain.service;

import com.github.pagehelper.PageInfo;
import com.weida.epmain.dto.ExceptionLog;
import com.weida.epmain.dto.vo.ExceptionLogVO;

import java.util.Map;


/**
 * @Auther: zhaof
 * @Date: 2021/2/20 9:58
 * @Desc: TODO
 */
public interface ExceptionLogService {

    void addExceptionLog(ExceptionLog exceptionLog);

    PageInfo<ExceptionLogVO> getExceptionLogPage(Map<String, Object> paramMap);

}
