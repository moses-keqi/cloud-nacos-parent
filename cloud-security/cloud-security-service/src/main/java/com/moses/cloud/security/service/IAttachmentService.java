package com.moses.cloud.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moses.cloud.security.form.UploadForm;
import com.moses.cloud.security.po.Attachment;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author renker
 * @since 2019-08-26
 */
public interface IAttachmentService extends IService<Attachment> {
	
	Attachment upload(String objectId, String type, String path, String originalFilename, byte[] bytes);

	byte[] findFileById(String id);

	byte[] findFileByObjectId(String objectId);

	/**
	 * 根据关联ID和类型查找附件
	 * @param form
	 * @return
	 */
	List<Attachment> findFileByType(UploadForm form);

	/**
	 * 根据关联ID查找附件
	 * @param objectId
	 * @return
	 */
	List<Attachment> findByObjectId(String objectId);

	List<Attachment> findByObjectIdAndType(String objectId, String type);

	/**
	 * 根据关联ID 类型查找附件 降序排序
	 * @param objectId
	 * @param type
	 * @return
	 */
	List<Attachment> findByObjectIdAndTypeOrdeyDesc(String objectId, String type);

	/**
	 * 上传二维码图片方法
	 * @param objectId
	 * @param type
	 * @param path
	 * @param originalFilename
	 * @param bytes
	 * @return
	 */
	Attachment uploadTwoCode(String objectId, String type, String path, String originalFilename, byte[] bytes);

	String uploadFileReturnPath(String objectId, String type, String path, String originalFilename, byte[] bytes);
}
