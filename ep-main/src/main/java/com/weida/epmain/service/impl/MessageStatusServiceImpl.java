package com.weida.epmain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weida.epmain.dto.Customer;
import com.weida.epmain.dto.Project;
import com.weida.epmain.dto.vo.MessageStatusVO;
import com.weida.epmain.dto.vo.ProjectVO;
import com.weida.epmain.mapper.MessageStatusMapper;
import com.weida.epmain.mapper.ProjectMapper;
import com.weida.epmain.service.MessageStatusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaof
 * @Date: 2021/2/22 16:56
 * @Desc: TODO
 */
@Service
public class MessageStatusServiceImpl implements MessageStatusService {

    @Resource
    private MessageStatusMapper messageStatusMapper;

    @Resource
    private ProjectMapper projectMapper;

    @Override
    public PageInfo<MessageStatusVO> getMessageStatusPage(Map<String, Object> parameters) {
        PageHelper.startPage((int)parameters.get("pageNum"), (int)parameters.get("pageSize"));
        List<MessageStatusVO> messageStatusVOS = messageStatusMapper.selectList(parameters);
        for(MessageStatusVO messageStatusVO : messageStatusVOS){
            Project project = projectMapper.selectByPrimaryKey(messageStatusVO.getProjectId());
            messageStatusVO.setProjectName(project == null ? "" : project.getProjectName());
        }
        return new PageInfo<>(messageStatusVOS);
    }
}
