package com.moses.cloud.security.controller.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.moses.cloud.commons.msg.ResponseData;
import com.moses.cloud.commons.utils.BeanUtils;
import com.moses.cloud.orm.form.IdForm;
import com.moses.cloud.security.form.UploadForm;
import com.moses.cloud.security.po.Attachment;
import com.moses.cloud.security.service.IAttachmentService;
import com.moses.cloud.security.vo.AttachmentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author hankeqi
 */
@Api(tags="附件接口管理")
@Slf4j
public class AttachmentController {
	
	@Autowired
	private IAttachmentService attachmentService;

	@ApiOperation(value="附件上传",notes="\n附件上传，限制上传单个文件")
	@PostMapping(value="upload",consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseData<AttachmentVo> upload(UploadForm form) throws IOException{
		HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
		MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(httpRequest);
		MultipartFile multipartFile = multipartRequest.getFile("file");
		Attachment attachment = attachmentService.upload(form.getObjectId(), form.getType(),form.getType(), multipartFile.getOriginalFilename(), multipartFile.getBytes());
		AttachmentVo data = BeanUtils.copy(attachment, AttachmentVo.class);
		return ResponseData.<AttachmentVo>builder().success().data(data).build();
	}
	
	@ApiOperation(value="附件删除",notes="\n附件删除")
	@PostMapping(value="deleteById")
	public ResponseData<String> delete(@RequestBody IdForm form) throws IOException{
		attachmentService.removeById(form.getId());
		return ResponseData.<String>builder().success().build();
	}
	
	@ApiOperation(value="根据ObejctId与类型查询附件",notes="\n根据ObejctId与类型查询附件")
	@PostMapping(value="findByObjectIdAndType")
	public ResponseData<List<AttachmentVo>> findByObjectIdAndType(@RequestBody UploadForm form) throws IOException{
		List<Attachment> list = attachmentService.findByObjectIdAndType(form.getObjectId(), form.getType());
		List<AttachmentVo> data = BeanUtils.copy(list, new TypeReference<List<AttachmentVo>>(){});
		return ResponseData.<List<AttachmentVo>>builder().success().data(data).build();
	}
	
	@ApiOperation(value="附件查看 ",notes="\n附件查看，显示图片")
	@GetMapping(value="show")
	public void attachment(String id, HttpServletResponse response){
		try {
			byte[] bytes= attachmentService.findFileById(id);
			response.getOutputStream().write(bytes);
			response.flushBuffer();
		} catch (Exception e) {
			log.error("查看图片失败 {}",e.getMessage());
		}
		
	}
	
	@ApiOperation(value="附件下载",notes="\n附件下载")
	@GetMapping(value="down")
	public void down(String id, HttpServletResponse response){
		try {
            Attachment attachment = attachmentService.getById(id);
			byte[] bytes= attachmentService.findFileById(id);
			// 设置强制下载不打开
			response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(attachment.getOldName(),"UTF-8"));
			response.getOutputStream().write(bytes);
			response.flushBuffer();
		} catch (Exception e) {
			log.error("下载附件失败 {}", e.getMessage());
		}
	}

	@ApiOperation(value="附件查看 ",notes="\n附件查看，显示图片")
	@GetMapping(value="showByObjectId")
	public void attachmentByObjectId(String objectId, HttpServletResponse response){
		try {
			byte[] bytes= attachmentService.findFileByObjectId(objectId);
			response.getOutputStream().write(bytes);
			response.flushBuffer();
		} catch (Exception e) {
			log.error("查看图片失败 {}",e.getMessage());
		}

	}
	
}
