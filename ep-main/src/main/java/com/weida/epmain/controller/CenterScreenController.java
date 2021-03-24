package com.weida.epmain.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.weida.epcommon.jwt.Payload;
import com.weida.epcommon.jwt.UserInfo;
import com.weida.epcommon.util.CommonResult;
import com.weida.epmain.dto.vo.DataDefineVO;
import com.weida.epmain.dto.vo.ScreenDataVO;
import com.weida.epmain.service.impl.CenterScreenServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/centerScreenController")
@Slf4j
public class CenterScreenController {

    @Autowired
    private CenterScreenServiceImpl centerScreenServiceImpl;


    /**
     * @Description: TODO 获取大屏最新数据
     * @Author: HX
     * @CreateDate: 2021/1/13/14:04
     * @param: [request]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @RequestMapping("/getCenterScreenData")
    public CommonResult getCenterData(HttpServletRequest request){
        //获取用户信息
        Payload<UserInfo> infoFromToken = centerScreenServiceImpl.getUserInfo(request);
        if(null !=infoFromToken){
            Integer userId = infoFromToken.getUserInfo().getId();
            Map<String,Object> screenDataMap = centerScreenServiceImpl.getCenterScreenData(userId);
            return CommonResult.success(screenDataMap);
        }else {
            return CommonResult.fail("数据获取失败……");
        }
    }


    /**
     * @Description: TODO 获取项目历史警告数据
     * @Author: HX
     * @CreateDate: 2021/1/22/14:04
     * @param: [request]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @RequestMapping("/getCenterScreenHistoryData")
    public CommonResult getCenterScreenHistoryData(HttpServletRequest request){

        //默认获取最近24小时内的警告记录
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endDate = sdf.format(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.DATE,-1);
        String startDate = sdf.format(calendar.getTime());

        //获取用户信息
        Payload<UserInfo> infoFromToken = centerScreenServiceImpl.getUserInfo(request);
        if(null !=infoFromToken){
            Integer userId = infoFromToken.getUserInfo().getId();
            List<ScreenDataVO> screenWarnLogDataList = centerScreenServiceImpl.getCenterScreenHistoryData(startDate,endDate, userId);
            return CommonResult.success(screenWarnLogDataList);
        }else {
            return CommonResult.fail("数据获取失败……");
        }
    }


    /**
     * @Description: TODO 根据项目id获取该项目历史警告记录
     * @Author: HX
     * @CreateDate: 2021/1/23/10:30
     * @param: [projectId, date]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @RequestMapping("/getProjectWarnLogDetail")
    public CommonResult getProjectWarnLogDetail(@RequestParam("projectId") String projectId,
                                                @RequestParam(value = "time" , required = false) String time,@RequestParam(value = "dataNameId",required = false) String dataNameId,@RequestParam("warnType") String warnType,
                                                @RequestParam("pageNum")int pageNum, @RequestParam("pageSize")int pageSize){

        Map<String, Object> paramMap = Maps.newHashMap();

        //默认获取最近24小时内的警告记录
        Integer minutes = 24*60;
        String startDate = "";
        String endDate = "";
        if(null!=time && ""!=time){
            minutes = Integer.parseInt(time);
        }
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        endDate = sdf.format(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.MINUTE,-minutes);
        startDate = sdf.format(calendar.getTime());

        paramMap.put("pageNum", pageNum);
        paramMap.put("pageSize", pageSize);
        paramMap.put("projectId",projectId);
        paramMap.put("dataNameId",dataNameId);
        paramMap.put("warnType",warnType);
        paramMap.put("startDate",startDate);
        paramMap.put("endDate",endDate);

        try {
            PageInfo<ScreenDataVO> screenWarnLogDataList = centerScreenServiceImpl.getProjectWarnLogDetail(paramMap );
            return CommonResult.success(screenWarnLogDataList);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return CommonResult.fail("数据获取失败……");
    }

    /**
     * @Description: TODO 获取指标项
     * @Author: HX
     * @CreateDate: 2021/1/27/16:25
     * @param: [projectId, warnType]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @RequestMapping("/getDataNameOptions")
    public CommonResult getDataNameOptions(@RequestParam("projectId") String projectId,@RequestParam("warnType") String warnType){

        try {
            List<DataDefineVO> dataNameOptionList = centerScreenServiceImpl.getDataNameOptions(projectId,warnType);
            return CommonResult.success(dataNameOptionList);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return CommonResult.fail("数据获取失败……");
    }


    /**
     * @Description: TODO 项目历史警告记录概览
     * @Author: HX
     * @CreateDate: 2021/1/29/14:51
     * @param: [projectId]
     * @return: com.weida.epcommon.util.CommonResult
     * @Version: V1.0.0
     * @Copyright:Anhui University
     **/
    @RequestMapping("/getProjectScreenHistoryData")
    public CommonResult getProjectScreenHistoryData(@RequestParam("projectId") String projectId){
        //默认获取最近24小时内的警告记录
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endDate = sdf.format(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.DATE,-1);
        String startDate = sdf.format(calendar.getTime());

        try {
            ScreenDataVO screenWarnLogData = centerScreenServiceImpl.getProjectScreenHistoryData(projectId,startDate,endDate);
            return CommonResult.success(screenWarnLogData);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
            return CommonResult.fail("数据获取失败……");
    }
}
