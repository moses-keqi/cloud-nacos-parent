package com.moses.cloud.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moses.cloud.security.po.Attachment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author HanKeQi
 * @Date 2021/2/18 下午2:55
 * @Version 1.0
 **/
public interface AttachmentMapper extends BaseMapper<Attachment> {

    /**
     *
     * @param objectId
     * @param id
     */
    void updateAttachment(@Param("objectId") String objectId, @Param("id") String id);

    /**
     * 根据ObjectId获取附件列表
     * 作者，GJY
     * @return
     */
    List<Attachment> selectAttachByObjectId(@Param("objectId") String objectId);

    /**
     * 根据ID列表查询对应附件信息
     * WZY
     * @param idList
     * @return
     */
    List<Attachment> selectByIdList(@Param("idList") List<String> idList);
}
