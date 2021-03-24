package com.weida.epmain.controller;

/**
 * @Auther: zhaof
 * @Date: 2021/2/22 15:30
 * @Desc: TODO
 */

/**
 * @Auther: zhaof
 * @Date: 2021/2/22 15:09
 * @Desc: TODO
 */
import com.google.protobuf.ServiceException;
import com.weida.epmain.dto.ExceptionLog;
import com.weida.epmain.service.ExceptionLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Date;

@ControllerAdvice
@Slf4j
public class HandlerException {

    @Autowired
    private ExceptionLogService exceptionLogService;


    /**
     * 自定义业务异常映射,返回JSON格式提示
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public void handler01 (HttpServletRequest request,Exception e){
        log.error(e.getMessage(), e);
        ExceptionLog exceptionLog = new ExceptionLog();
        exceptionLog.setCreateTime(new Date());
        String desc = MessageFormat.format("类名:{0},方法名:{1},行号:{2}出现异常", e.getStackTrace()[0].getClassName(),e.getStackTrace()[0].getMethodName(),e.getStackTrace()[0].getLineNumber());
        exceptionLog.setDescription(desc);
        exceptionLogService.addExceptionLog(exceptionLog);
    }

}

