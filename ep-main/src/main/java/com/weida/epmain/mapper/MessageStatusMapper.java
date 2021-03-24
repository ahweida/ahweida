package com.weida.epmain.mapper;


import com.weida.epmain.dto.MessageStatus;
import com.weida.epmain.dto.vo.MessageStatusVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MessageStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageStatus record);

    int insertSelective(MessageStatus record);

    MessageStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageStatus record);

    int updateByMessageIdSelective(MessageStatus record);

    int updateByPrimaryKey(MessageStatus record);

    List<MessageStatusVO> selectList(Map<String, Object> parameters);
}