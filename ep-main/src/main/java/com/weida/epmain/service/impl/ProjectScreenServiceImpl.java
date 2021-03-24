package com.weida.epmain.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.weida.epcommon.chart.common.*;
import com.weida.epcommon.util.DateUtil;
import com.weida.epmain.dto.*;
import com.weida.epmain.dto.dto.CustomTargetDto;
import com.weida.epmain.dto.vo.*;
import com.weida.epmain.mapper.*;
import com.weida.epmain.service.ProjectScreenService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProjectScreenServiceImpl implements ProjectScreenService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ChartMapper chartMapper;

    @Resource
    private ChartDetailMapper chartDetailMapper;

    @Resource
    private DataDefineMapper dataDefineMapper;

    @Resource
    private DataMapper dataMapper;

    @Resource
    private ProjectMonitorMapper projectMonitorMapper;

    @Resource
    private ProjectMapper projectMapper;


    @Override
    public Project getProjectInfo(Integer projectId) {
        return projectMapper.selectByPrimaryKey(projectId);
    }

    /**
     * @Auther: zhaofei
     * @Date: 2021/1/7 13:34
     * @Desc: 获取项目大屏中所有的图表（折线和柱状）
     */
    @Override
    public List<Object> getOptions(Integer projectId) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("projectId", projectId);
        parameters.put("status", 1);//1为有效
        parameters.put("chartTypeNo", 0);//0为表格，剔除掉
        List<Chart> charts = chartMapper.selectCharts(parameters);
        List list = Lists.newArrayList();

        for(Chart chart : charts){
            OptionVO optionVO= getOptionVO(chart);
            list.add(optionVO);
        }
        long endTime = System.currentTimeMillis();
        log.info("执行时间为: " + (endTime - startTime));
        return list;
    }

    private String createDynamicSql(ChartDetail chartDetail, String collectTimeSql, Integer projectId, String startTime){
        StringBuilder dynamicSql = new StringBuilder();
        Integer dataType = chartDetail.getDataType();
        if(dataType == 0){
            //原始数据
            DataDefine dataDefine = dataDefineMapper.selectByPrimaryKey(chartDetail.getDataDefineId());
            //要查询的字段
            String filed = dataDefine.getMysqlField();
            dynamicSql.append(" select ")
                    .append(" collect_time as collectTime, ")
                    .append(filed).append(" as value ")
                    .append(" from data ")
                    .append(" where project_id = ").append(projectId)
                    .append(" and collect_time >= '").append(startTime).append("' ");
        }else if(dataType == 1){
            //统计数据
            DataDefine dataDefine = dataDefineMapper.selectByPrimaryKey(chartDetail.getDataDefineId());
            //要查询的字段
            String filed = dataDefine.getMysqlField();
            dynamicSql.append(" select  ")
                    .append(collectTimeSql).append(" as collectTime, ")
                    .append(" sum(").append(filed).append(") as value ")
                    .append(" from data ")
                    .append("where project_id = ").append(projectId)
                    .append(" and collect_time >= '").append(startTime).append("' ")
                    .append(" group by ").append(collectTimeSql);
        }else if (dataType == 2){
            //平均值
            DataDefine dataDefine = dataDefineMapper.selectByPrimaryKey(chartDetail.getDataDefineId());
            //要查询的字段
            String filed = dataDefine.getMysqlField();
            dynamicSql.append(" select  ")
                    .append(collectTimeSql).append(" as collectTime, ")
                    .append(" round(avg(").append(filed).append("), 4) as value ")
                    .append(" from data ")
                    .append("where project_id = ").append(projectId)
                    .append(" and collect_time >= '").append(startTime).append("' ")
                    .append(" group by ").append(collectTimeSql);
        }else {
            dynamicSql.append(" select  ")
                    .append(collectTimeSql).append(" as collectTime, ")
                    .append(chartDetail.getConstantValue()).append(" as value ")
                    .append(" from data ")
                    .append("where project_id = ").append(projectId)
                    .append(" and collect_time >= '").append(startTime).append("' ")
                    .append(" group by ").append(collectTimeSql);
        }
        return dynamicSql.toString();
    }

    private String createDynamicSql(ChartDetail chartDetail, Integer projectId, String startTime){
        StringBuilder dynamicSql = new StringBuilder();

        Integer dataType = chartDetail.getDataType();
        if(dataType == 0){
            //原始数据
            //取离当前时间最近（最后一条）数据
            DataDefine dataDefine = dataDefineMapper.selectByPrimaryKey(chartDetail.getDataDefineId());
            //要查询的字段
            String filed = dataDefine.getMysqlField();
            dynamicSql.append(" select  ")
                    .append(filed).append(" as value ")
                    .append("  from data  ")
                    .append("  where project_id = ").append(projectId)
                    .append("  order by collect_time desc  limit 1 ");
        }else if(dataType == 1){
            //统计数据
            DataDefine dataDefine = dataDefineMapper.selectByPrimaryKey(chartDetail.getDataDefineId());
            //要查询的字段
            String filed = dataDefine.getMysqlField();
            dynamicSql.append(" select  ")
                    .append(" sum(").append(filed).append(") as value ")
                    .append(" from data ")
                    .append("where project_id = ").append(projectId)
                    .append(" and collect_time >= '").append(startTime).append("' ");
        }else if(dataType == 2){
            //平均值
            DataDefine dataDefine = dataDefineMapper.selectByPrimaryKey(chartDetail.getDataDefineId());
            //要查询的字段
            String filed = dataDefine.getMysqlField();
            dynamicSql.append(" select  ")
                    .append(" avg(").append(filed).append(") as value ")
                    .append(" from data ")
                    .append("where project_id = ").append(projectId)
                    .append(" and collect_time >= '").append(startTime).append("' ");
        }else {
            dynamicSql.append(" select  ")
                    .append(chartDetail.getConstantValue()).append(" as value ")
                    .append(" from dual ");
        }
        return dynamicSql.toString();
    }

    /**
     * @Auther: zhaofei
     * @Date: 2021/1/8 9:02
     * @Desc: 查询类型为列表的图表
     */
    @Override
    public List<Object> getTables(Integer projectId) {
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("projectId", projectId);
        parameters.put("status", 1);//1为有效
        parameters.put("chartType", 0);//0为表格
        List<Chart> charts = chartMapper.selectCharts(parameters);
        List list = Lists.newArrayList();
        for(Chart chart : charts) {
            OptionVO optionVO = getTableVO(chart);
            list.add(optionVO);

        }
        return list;
    }

    /**
     * @Auther: zhaofei
     * @Date: 2021/1/8 9:08
     * @Desc: 获取中心位置的所有监控数据
     */
    @Override
    public Map<String, Object> getCenterDatas(Integer projectId) {
        //返回中心数据集合
        Map<String, Object> returnMap = Maps.newHashMap();
        List<CenterDataVO> centerDataVOS = Lists.newArrayList();
        //1表示警告 2 表示超标
        int warnType = 0;
        returnMap.put("centerDataVOS", centerDataVOS);
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("projectId", projectId);
        List<ProjectMonitor> projectMonitors = projectMonitorMapper.selectProjectMonitors(parameters);
        if(projectMonitors.size() == 0)
            return returnMap;
        StringBuilder fields = new StringBuilder();
        fields.append("id, collect_time collectTime");
        Map<Integer,Object> minotorMap = Maps.newHashMap();
        projectMonitors.forEach((a) -> {
            DataDefine dataDefine = dataDefineMapper.selectByPrimaryKey(a.getDataDefineId());
            if(dataDefine != null)
                fields.append(",").append(dataDefine.getMysqlField());
            minotorMap.put(a.getId(), dataDefine.getMysqlField());
        });
        parameters.put("fields", fields);
        Map<String, Object> dataMap = dataMapper.selectCenterDatas(parameters);

        for(ProjectMonitor a:projectMonitors){
            Double value = dataMap == null ? 0 : (Double) dataMap.get(minotorMap.get(a.getId()));
            if(a.getXcoord() != null && a.getYcoord() != null)
                centerDataVOS.add(new CenterDataVO().setX(a.getXcoord()).setY(a.getYcoord()).setValue(value));
            if(value >= a.getNormalMin() && value < a.getNormalMax()){
                //默认正常，无需变化
            }else if(value >= a.getWarnMin() && value < a.getWarnMax()){
                warnType = warnType == 2 ? 2 : 1;
            }else {
                warnType = 2;
            }
        }
        Project project = projectMapper.selectByPrimaryKey(projectId);
        Date collectTime = (Date) dataMap.get("collectTime");
        Date offLineDate = DateUtils.addSeconds(collectTime, project.getOfflineTime());
        if(offLineDate.before(new Date()))
            warnType = 3;
        returnMap.put("warnType", warnType);
        return returnMap;
    }

    /**
     * @Auther: zhaofei
     * @Date: 2021/1/8 9:08
     * @Desc: 折线图和柱状图转换为列表功能
     */
    @Override
    public  Map<String, Object> optionToTable(Integer chartMainId, Integer projectId) {
        log.info("折线图和柱状图转换为列表");
        //获取时间跨度
        Chart chart = chartMapper.selectByPrimaryKey(chartMainId);
        Integer groupType = chart.getGroupType();
        Integer subLength = 13;
        String collectTimeSql;

        Integer hour = chart.getHour();
        Date startDate = DateUtils.addHours(new Date(), -hour);
        String format = null;
        if(groupType == 1){
            //时
            collectTimeSql = "DATE_FORMAT(collect_time, '%Y-%m-%d %H')";
            //计算查询的数据开始时间
            format = DateUtil.DATE_FORMAT_HOUR;
        }else if(groupType == 2){
            //天
            collectTimeSql = "DATE_FORMAT(collect_time, '%Y-%m-%d')";
            subLength = 10;
            format = DateUtil.DATE_FORMAT_DAY;
        }else {
            //月
            collectTimeSql = "DATE_FORMAT(collect_time, '%Y-%m')";
            subLength = 7;
            format = DateUtil.DATE_FORMAT_MONTH;

        }
        String startTime = DateUtil.format(startDate, format);

        //查询图表详情
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("chartMainId", chartMainId);
        List<ChartDetail> chartDetails = chartDetailMapper.selectChartsDetails(parameters);

        //以采集时间为key
        List<String> xAxisData = Lists.newArrayList();
        //组装table表头
        List<TableLabelVO> tableLabel = Lists.newArrayList();
        TableLabelVO tableLabelVO = new TableLabelVO();
        tableLabelVO.setProp("collectTime");
        tableLabelVO.setLabel("采集时间");
        tableLabel.add(tableLabelVO);
        //初始化一个装在map的list，将chartDetails中的数据转为map存贮
        List<Map<String, DataVO>>  dataVoMapList= Lists.newArrayList();
        //装在对应下表map的数据类型
        List<Integer> detailTypes = Lists.newArrayList();
        for(int i=0; i<chartDetails.size(); i++){
            /*****************chartDetails循环结开始*************/
            ChartDetail chartDetail = chartDetails.get(i);
            // 属性别名
            String prop = "prop" + i;

            TableLabelVO  labelVO= new TableLabelVO();
            labelVO.setProp(prop);
            labelVO.setLabel(chartDetail.getChartName());
            tableLabel.add(labelVO);

            //如果是预测数据，并且是原始值的情况，曲线根据预测时间整体平移
            if(chartDetail.getPredictDataDefineId() != null && chartDetail.getDataType() == 0){
                String predictStartTime = DateUtil.timeStrAddTime(startTime, -10 * 60, format);
                String dynamicSql = createDynamicSql(chartDetail, collectTimeSql, chart.getProjectId(), predictStartTime);
                List<DataVO> datas = dataMapper.selectByDynamicSql(dynamicSql);
                StringBuilder sql = new StringBuilder();
                DataDefine dataDefine = dataDefineMapper.selectByPrimaryKey(chartDetail.getPredictDataDefineId());
                String filed = dataDefine.getMysqlField();
                sql.append(" select ")
                        .append(" collect_time as collectTime, ")
                        .append(filed).append(" as value ")
                        .append(" from data ")
                        .append(" where project_id = ").append(chart.getProjectId())
                        .append(" and collect_time >= '").append(predictStartTime).append("' ");
                List<DataVO> predictTimeDatas = dataMapper.selectByDynamicSql(sql.toString());
                //list转map存贮
                Map<String, DataVO> dataVOMap = new TreeMap<>();
                for(int j = 0; j < datas.size(); j++){
                    String collectTime = DateUtil.timeStrAddTime(datas.get(j).getCollectTime(),
                            predictTimeDatas.get(j).getValue().intValue());
                    datas.get(j).setProp(prop);
                    dataVOMap.put(collectTime, datas.get(j));

                    if(!xAxisData.contains(collectTime) && collectTime.compareTo(startTime) > 0){
                        xAxisData.add(collectTime);
                    }
                }
                dataVoMapList.add(dataVOMap);
                //数据类型做标记  100 表示预测值
                detailTypes.add(100);
            }else {
                String dynamicSql = createDynamicSql(chartDetail, collectTimeSql, chart.getProjectId(), startTime);
                List<DataVO> datas = dataMapper.selectByDynamicSql(dynamicSql);
                //list转map存贮
                Map<String, DataVO> dataVOMap = new TreeMap<>();
                datas.forEach(a -> dataVOMap.put(a.getCollectTime(), a));
                dataVoMapList.add(dataVOMap);
                //数据类型做标记
                detailTypes.add(chartDetail.getDataType());
                for(DataVO dataVO : datas){
                    dataVO.setProp(prop);
                    if(i==0) {
                        xAxisData.add(dataVO.getCollectTime());
                    }
                }

            }

            /*****************chartDetails循环结束*************/
        }

        //组装table数据
        List<Map<String, Object>> tableData = Lists.newArrayList();
        for (String key : xAxisData) {
            Map<String, Object> map = Maps.newHashMap();
            map.put("collectTime", key);
            for (int i=0; i<dataVoMapList.size(); i++) {
                Map<String, DataVO> dataVOMap = dataVoMapList.get(i);
                if(detailTypes.get(i) == 100){
                    //预测值
                    DataVO dataVO = getNearValue(dataVOMap, key);
                    if(dataVO != null){
                        Double value = dataVO.getValue();
                        String prop = dataVO.getProp();
                        map.put(prop, value);
                    }
                }else if(detailTypes.get(i) == 0){
                    //原始值
                    DataVO dataVO = dataVOMap.get(key);
                    if(dataVO != null){
                        Double value = dataVO.getValue();
                        String prop = dataVO.getProp();
                        map.put(prop, value);
                    }
                }else {
                    //平均 统计 常量
                    String k = key.substring(0, subLength);
                    DataVO dataVO = dataVOMap.get(k);
                    if(dataVO != null){
                        Double value = dataVO.getValue();
                        String prop = dataVO.getProp();
                        map.put(prop, value);
                    }
                }

//                DataVO dataVO= dataVOMap.get(key);
//                if(dataVO == null){
//                    String k = key.substring(0,subLength);
//                    if(dataVOMap.get(k) != null){
//                        Double value = dataVOMap.get(k).getValue();
//                        String prop = dataVOMap.get(k).getProp();
//                        map.put(prop, value);
//                    }
//
//                }else {
//                    Double value = dataVO.getValue();
//                    String prop = dataVO.getProp();
//                    map.put(prop, value);
//                }
            }
            tableData.add(map);
        }

        Map<String, Object> returnMap = Maps.newHashMap();
        returnMap.put("tableLabel", tableLabel);
        returnMap.put("tableData", tableData);
        return returnMap;
    }

    @Override
    public Object getcustomTargetChart(CustomTargetDto customTargetDto) {
        Map<String, Object> parameters = Maps.newHashMap();
        StringBuilder fields = new StringBuilder().append(" DATE_FORMAT(collect_time, '%Y-%m-%d %H:%i:%s') collectTime ");
        StringBuilder avgFields = new StringBuilder().append(" DATE_FORMAT(collect_time, '%Y-%m-%d %H') collectTime ");
        List<DataDefine> defineList = Lists.newArrayList();
        //初始化图表上方的图标
        List<String> legendDatas = Lists.newArrayList();

        List<Integer> defineIds = Lists.newArrayList();
        defineIds.add(customTargetDto.getDefineIdOne());
        defineIds.add(customTargetDto.getDefineIdTwo());
        defineIds.add(customTargetDto.getDefineIdThree());

        List<Boolean> flags = Lists.newArrayList();
        flags.add(customTargetDto.getDefineIdOne() == null ? false : customTargetDto.getOneFlag());
        flags.add(customTargetDto.getDefineIdTwo() == null ? false : customTargetDto.getTwoFlag());
        flags.add(customTargetDto.getDefineIdThree() == null ? false : customTargetDto.getThreeFlag());

        List<Boolean> avgFlags = Lists.newArrayList();
        avgFlags.add(customTargetDto.getDefineIdOne() == null ? false : customTargetDto.getOneAvgFlag());
        avgFlags.add(customTargetDto.getDefineIdTwo() == null ? false : customTargetDto.getTwoAvgFlag());
        avgFlags.add(customTargetDto.getDefineIdThree() == null ? false : customTargetDto.getThreeAvgFlag());

        for(int i = 0; i < defineIds.size(); i ++ ){
            Integer id = defineIds.get(i);
            DataDefine dataDefine = null;
            if(id != null){
                dataDefine = dataDefineMapper.selectByPrimaryKey(id);
                if(dataDefine != null && StringUtils.isNotEmpty(dataDefine.getMysqlField())) {
                    if(flags.get(i)){
                        fields.append(", ").append(dataDefine.getMysqlField());
                        legendDatas.add(dataDefine.getDataName());
                    }
                    if(avgFlags.get(i)){
                        avgFields.append(", ").append("avg(").append(dataDefine.getMysqlField()).append(") as ").append(dataDefine.getMysqlField());
                        legendDatas.add(dataDefine.getDataName()+"均值");
                    }
                }
            }
            defineList.add(dataDefine);
        }


        parameters.put("fields", fields);
        parameters.put("avgFields", avgFields);
        parameters.put("projectId", customTargetDto.getProjectId());
        parameters.put("startTime", customTargetDto.getStartTime());
        parameters.put("endTime", customTargetDto.getEndTime());

        Boolean flag = flags.get(0) || flags.get(1) || flags.get(2);
        Boolean avgFlag = avgFlags.get(0) || avgFlags.get(1) || avgFlags.get(2);;

        //初始化线条或柱子的颜色
        List<String> colors = Options.getDefaultColors();
        //初始化X坐标值数据
        List<String> xAxisData = Lists.newArrayList();
        Option option = Options.getDefaultOption(legendDatas, colors, xAxisData);
        List<Serie> series = Lists.newArrayList();

        List<Map<String, Object>> dataList = null;
        List<Map<String, Object>> dataAvgList = null;

        if(flag){
            dataList = dataMapper.selectCustomTarget(parameters);
            Map<String, Object> dataMap;
            for(int j = 0; j < dataList.size(); j++ ){
                dataMap =  dataList.get(j);
                xAxisData.add(dataMap.get("collectTime").toString());
            }
            Map<String, Map<String, Object>> listMap = Maps.newHashMap();
            dataList.forEach(a -> {
                listMap.put(a.get("collectTime").toString(), a);
            });

            DataDefine dataDefine;
            for(int i = 0; i<defineList.size(); i++){
                dataDefine =  defineList.get(i);
                if(flags.get(i) && dataDefine != null){
                    Serie serie = new Serie();
                    List<Double> data = new ArrayList<>();
                    String key;
                    for(int j = 0; j < xAxisData.size(); j++ ){
                        System.out.println(j);
                        key = xAxisData.get(j);
                        Double d = (Double) listMap.get(key).get(dataDefine.getMysqlField());
                        data.add(d);
                    }
                    serie.setData(data);
                    serie.setName(dataDefine.getDataName());//设置折线名称
                    series.add(serie);
                }

            }
        }


        if(avgFlag){
            dataAvgList = dataMapper.selectCustomTargetAvg(parameters);
            if(!flag){
                Map<String, Object> dataMap;
                for(int j = 0; j < dataAvgList.size(); j++ ){
                    dataMap =  dataAvgList.get(j);
                    xAxisData.add(dataMap.get("collectTime").toString());
                }
            }
            Map<String, Map<String, Object>> listMap = Maps.newHashMap();
            dataAvgList.forEach(a -> {
                listMap.put(a.get("collectTime").toString(), a);
            });


            DataDefine dataDefine;
            for(int i = 0; i<defineList.size(); i++){
                dataDefine =  defineList.get(i);
                if(avgFlags.get(i) && dataDefine != null){

                    Serie serie = new Serie();
                    List<Double> data = new ArrayList<>();
                    String key;
                    for(int j = 0; j < xAxisData.size(); j++ ){
                        key = xAxisData.get(j);
                        Map<String, Object> map = listMap.get(key);
                        if(map == null)
                            map = listMap.get(key.substring(0, 13));
                        Double d = (Double) map.get(dataDefine.getMysqlField());
                        data.add(d);
                    }
                    serie.setData(data);
                    serie.setName(dataDefine.getDataName()+"均值");//设置折线名称
                    series.add(serie);
                }

            }
        }

        //对x轴坐标做简化处理
        simplifyAxis(xAxisData);

        option.setSeries(series);
        log.info(JSON.toJSON(option).toString());
        return JSON.toJSON(option);
    }

    @Override
    public Object getOption(String chartMainId, String hour) {
        log.info("进入getOption方法，获取图表id为{}", chartMainId);
        Chart chart = chartMapper.selectByPrimaryKey(Integer.parseInt(chartMainId));
        if(StringUtils.isNotEmpty(hour))
            chart.setHour(Integer.parseInt(hour));
        OptionVO optionVO= getOptionVO(chart);
        return optionVO;
    }

    @Override
    public Object getTable(String chartMainId, String hour) {
        log.info("进入getTable方法，获取图表id为{}", chartMainId);
        Chart chart = chartMapper.selectByPrimaryKey(Integer.parseInt(chartMainId));
        if(StringUtils.isNotEmpty(hour))
            chart.setHour(Integer.parseInt(hour));
        OptionVO optionVO = getTableVO(chart);
        return optionVO;
    }

    public OptionVO getOptionVO(Chart chart){
        log.info("进入getOptionVO方法");
        Integer groupType = chart.getGroupType();
        Integer subLength = 13;
        String collectTimeSql;
        //时间跨度
        Integer hour = chart.getHour();
        Date startDate = DateUtils.addHours(new Date(), -hour);
        String format = null;
        if(groupType == 1){
            //时
            collectTimeSql = "DATE_FORMAT(collect_time, '%Y-%m-%d %H')";
            //计算查询的数据开始时间
            format = DateUtil.DATE_FORMAT_HOUR;
        }else if(groupType == 2){
            //天
            collectTimeSql = "DATE_FORMAT(collect_time, '%Y-%m-%d')";
            subLength = 10;
            format = DateUtil.DATE_FORMAT_DAY;
        }else {
            //月
            collectTimeSql = "DATE_FORMAT(collect_time, '%Y-%m')";
            subLength = 7;
            format = DateUtil.DATE_FORMAT_MONTH;

        }
        String startTime = DateUtil.format(startDate, format);


        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("chartMainId", chart.getId());
        List<ChartDetail> chartDetails = chartDetailMapper.selectChartsDetails(parameters);

        /***********初始化图表***********/
        //初始化图表上方的图标
        List<String> legendDatas = Lists.newArrayList();
        //初始化线条或柱子的颜色
        List<String> colors = Lists.newArrayList();
        //初始化X坐标值数据
        List<String> xAxisData = Lists.newArrayList();
        Option option = Options.getDefaultOption(legendDatas, colors, xAxisData);
        //柱子或线条集合
        List<Serie> series = Lists.newArrayList();

        //初始化一个装在map的list，将chartDetails中的数据转为map存贮
        List<Map<String, DataVO>>  dataVoMapList= Lists.newArrayList();
        List<Integer> detailTypes = Lists.newArrayList();
        for(int i=0; i<chartDetails.size(); i++){
            /*****************chartDetails循环结开始*************/
            ChartDetail chartDetail = chartDetails.get(i);
            //0表示在图表中不显示
            if(chartDetail.getChartShow() == 0) continue;
            //上方折线图标名称
            legendDatas.add(chartDetail.getChartName());
            //折线颜色
            colors.add(chartDetail.getColor());


            //如果是预测数据，并且是原始值的情况，曲线根据预测时间整体平移， 平均值和统计值不做处理
            if(chartDetail.getPredictDataDefineId() != null && chartDetail.getDataType() == 0){
                //曲线右移之后，左边会空出一段，默认时间多查询10分钟的数据
                String predictStartTime = DateUtil.timeStrAddTime(startTime, -10 * 60, format);
                String dynamicSql = createDynamicSql(chartDetail, collectTimeSql, chart.getProjectId(), predictStartTime);
                List<DataVO> datas = dataMapper.selectByDynamicSql(dynamicSql);
                StringBuilder sql = new StringBuilder();
                DataDefine dataDefine = dataDefineMapper.selectByPrimaryKey(chartDetail.getPredictDataDefineId());
                String filed = dataDefine.getMysqlField();
                sql.append(" select ")
                        .append(" collect_time as collectTime, ")
                        .append(filed).append(" as value ")
                        .append(" from data ")
                        .append(" where project_id = ").append(chart.getProjectId())
                        .append(" and collect_time >= '").append(predictStartTime).append("' ");
                List<DataVO> predictTimeDatas = dataMapper.selectByDynamicSql(sql.toString());
                //list转map存贮
                Map<String, DataVO> dataVOMap = new TreeMap<>();
                for(int j = 0; j < datas.size(); j++){
                    String collectTime = DateUtil.timeStrAddTime(datas.get(j).getCollectTime(),
                            predictTimeDatas.get(j).getValue().intValue());
                    dataVOMap.put(collectTime, datas.get(j));

                    if(!xAxisData.contains(collectTime) && collectTime.compareTo(startTime) > 0){
                        xAxisData.add(collectTime);
                    }
                }
                //数据类型做标记  100 表示预测值
                detailTypes.add(100);
                dataVoMapList.add(dataVOMap);
            }else {
                String dynamicSql = createDynamicSql(chartDetail, collectTimeSql, chart.getProjectId(), startTime);
                List<DataVO> datas = dataMapper.selectByDynamicSql(dynamicSql);
                //list转map存贮
                Map<String, DataVO> dataVOMap = new TreeMap<>();
                datas.forEach(a -> dataVOMap.put(a.getCollectTime(), a));
                //数据类型做标记
                detailTypes.add(chartDetail.getDataType());
                dataVoMapList.add(dataVOMap);
                if(i==0){
                    for(DataVO dataVO : datas){
                        xAxisData.add(dataVO.getCollectTime());
                    }
                }
            }


            /*****************chartDetails循环结束*************/
        }
        ItemStyle itemStyle = ItemStyles.getDefaultItemStyle();
        for (int i=0; i<dataVoMapList.size(); i++) {
            Map<String, DataVO> dataVOMap = dataVoMapList.get(i);
            Serie serie = new Serie();
            if(chart.getChartType() == 2){
                serie.setType("bar");
                serie.setItemStyle(itemStyle);
            }
            List<Double> data = new ArrayList<>();
            for (String xData : xAxisData) {
                if(detailTypes.get(i) == 100){
                    //预测值
                    DataVO dataVO = getNearValue(dataVOMap, xData);
                    data.add(dataVO == null ? null : dataVO.getValue());
                }else if(detailTypes.get(i) == 0){
                    //原始值
                    DataVO dataVO = dataVOMap.get(xData);
                    data.add(dataVO == null ? null : dataVO.getValue());
                }else {
                    //平均 统计 常量
                    String key = xData.substring(0,subLength);
                    DataVO dataVO = dataVOMap.get(key);
                    data.add(dataVO == null ? null : dataVO.getValue());
                }
//                DataVO dataVO= dataVOMap.get(xData);
//                if(dataVO == null){
//                    String key = xData.substring(0,subLength);
//                    data.add(dataVOMap.get(key) == null ? null : dataVOMap.get(key).getValue());
//                }else {
//                    data.add(dataVO.getValue());
//                }
            }
            serie.setData(data);
            serie.setName(legendDatas.get(i));//设置折线名称
            series.add(serie);
        }

        //对x轴坐标做简化处理
        simplifyAxis(xAxisData);


        option.setSeries(series);
        OptionVO optionVO = new OptionVO();
        optionVO.setTitle(chart.getChartName());
        optionVO.setChartMainId(chart.getId());
        optionVO.setPosition(chart.getPosition());
        optionVO.setChartType(chart.getChartType());
        optionVO.setOption(JSON.toJSON(option));
        log.info(JSON.toJSON(option).toString());
        return  optionVO;
    }

    public OptionVO getTableVO(Chart chart) {
        List<TableVO> tables = Lists.newArrayList();
        Integer hour = chart.getHour();
        Date startDate = DateUtils.addHours(new Date(), -hour);
        String startTime = DateUtil.format(startDate, DateUtil.DATE_FORMAT_SECOND);
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("chartMainId", chart.getId());
        List<ChartDetail> chartDetails = chartDetailMapper.selectChartsDetails(parameters);
        for(ChartDetail chartDetail : chartDetails){
            if(chartDetail.getChartShow() == 0){
                //0表示在图表中不显示
                continue;
            }
            String dynamicSql = createDynamicSql(chartDetail, chart.getProjectId(), startTime);
            List<DataVO> datas = dataMapper.selectByDynamicSql(dynamicSql);
            TableVO tableVO = new TableVO();
            tableVO.setName(chartDetail.getChartName());
            Double value = ( datas.isEmpty() || datas.get(0) == null) ? 0 :datas.get(0).getValue();
            tableVO.setValue(value);
            tableVO.setDataType(chartDetail.getDataType());
            tableVO.setHour(String.valueOf(chart.getHour()));
            tableVO.setUnit(chartDetail.getUnit());
            tables.add(tableVO);
        }
        OptionVO optionVO = new OptionVO();
        optionVO.setTitle(chart.getChartName());
        optionVO.setChartMainId(chart.getId());
        optionVO.setChartType(chart.getChartType());
        optionVO.setPosition(chart.getPosition());
        optionVO.setTable(tables);
        return  optionVO;
    }

    private void simplifyAxis(List<String> xAxisData){
        if(xAxisData.size() > 0){
            String xKey = xAxisData.get(0);
            int sub = 11;
            if(xKey.length() == 10){
                sub = 8;
            }else if(xKey.length() == 7){
                sub = 5;
            }
            for (int j=0; j<xAxisData.size(); j++) {
                String xData = xAxisData.get(j);
                xData = xData.substring(sub);
                xAxisData.set(j, xData);
            }
        }
    }

    /**
     * @Auther: zhaofei
     * @Date: 2021/3/2 13:45
     * @Desc: 预测数据为空时会寻找其附近时间的值 左边2秒 右边5秒
     */
    private DataVO getNearValue(Map<String, DataVO> dataVOMap, String xData){
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_FORMAT_SECOND);
        long keyTime = 0;
        try {
            keyTime = sdf.parse(xData).getTime() - 2000;
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        int j = 0;
        DataVO dataVO = dataVOMap.get(xData);
        while(dataVO == null && j < 7){
            String predictKey = DateUtil.timeStamp2Date(String.valueOf(keyTime), DateUtil.DATE_FORMAT_SECOND);
            dataVO = dataVOMap.get(predictKey);
            keyTime = keyTime + 1000 ;
            j++;
        }
        return dataVO;
    }

}
