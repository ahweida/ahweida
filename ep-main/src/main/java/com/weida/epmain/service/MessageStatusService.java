package com.weida.epmain.service;

import com.github.pagehelper.PageInfo;
import com.weida.epmain.dto.vo.MessageStatusVO;
import java.util.Map;

/**
 * @Auther: zhaof
 * @Date: 2021/2/22 16:56
 * @Desc: TODO
 */
public interface MessageStatusService {

    PageInfo<MessageStatusVO> getMessageStatusPage(Map<String, Object> parameters);

}
