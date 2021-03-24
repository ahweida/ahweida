package com.weida.epmain.controller;

import com.weida.epcommon.util.CommonResult;
import com.weida.epmain.dto.Project;
import com.weida.epmain.dto.dto.CustomTargetDto;
import com.weida.epmain.dto.vo.CenterDataVO;
import com.weida.epmain.service.ProjectScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/projectScreen")
public class ProjectScreenController {

    @Autowired
    private ProjectScreenService projectScreenService;

    /**
     * @Auther: zhaofei
     * @Date: 2021/1/8 9:15
     * @Desc: 获取大屏名称
     */
    @RequestMapping("/getProjectInfo")
    public CommonResult getProjectInfo(Integer projectId) {
        Project data = projectScreenService.getProjectInfo(projectId);
        return CommonResult.success(data);
    }

    /**
     * @Auther: zhaofei
     * @Date: 2021/1/7 13:34
     * @Desc: 获取项目大屏中所有的图表（折线和柱状）
     */
    @RequestMapping("/getOptions")
    public CommonResult getOptions(Integer projectId) {
        List<Object> options = projectScreenService.getOptions(projectId);
        return CommonResult.success(options);
    }

    /**
     * @Auther: zhaofei
     * @Date: 2021/1/8 9:02
     * @Desc: 查询类型为列表的图表
     */
    @RequestMapping("/getTables")
    public CommonResult getTables(Integer projectId) {
        List<Object> tables = projectScreenService.getTables(projectId);
        return CommonResult.success(tables);
    }

    /**
     * @Auther: zhaofei
     * @Date: 2021/1/8 9:08
     * @Desc: 折线图和柱状图转换为列表功能
     */
    @RequestMapping("/optionToTable")
    public CommonResult optionToTable(Integer chartMainId, Integer projectId) {
        Map<String, Object> tables = projectScreenService.optionToTable(chartMainId, projectId);
        return CommonResult.success(tables);
    }

    /**
     * @Auther: zhaofei
     * @Date: 2021/1/8 9:08
     * @Desc: 获取中心位置的所有监控数据
     */
    @RequestMapping("/getCenterDatas")
    public CommonResult getCenterDatas(Integer projectId) {
        Map<String, Object> data = projectScreenService.getCenterDatas(projectId);
        return CommonResult.success(data);
    }

    @RequestMapping("/getcustomTargetChart")
    public CommonResult getcustomTargetChart(CustomTargetDto customTargetDto) {
        //这里添加校验
        Object o = projectScreenService.getcustomTargetChart(customTargetDto);
        return CommonResult.success(o);
    }

    /**
     * @Auther: zhaofei
     * @Date: 2021/2/3 16:52
     * @Desc: 图表放大功能
     */
    @RequestMapping("/getOption")
    public CommonResult getOption(@RequestParam("chartMainId") String chartMainId, @RequestParam("hour") String hour) {
        Object option = projectScreenService.getOption(chartMainId, hour);
        return CommonResult.success(option);
    }

    /**
     * @Auther: zhaofei
     * @Date: 2021/3/4 13:52
     * @Desc: 获取单个列表
     */
    @RequestMapping("/getTable")
    public CommonResult getTable(@RequestParam("chartMainId") String chartMainId, @RequestParam("hour") String hour) {
        Object table = projectScreenService.getTable(chartMainId, hour);
        return CommonResult.success(table);
    }


}
