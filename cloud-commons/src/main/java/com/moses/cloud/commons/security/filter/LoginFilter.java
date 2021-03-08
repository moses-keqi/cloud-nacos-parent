package com.moses.cloud.commons.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.moses.cloud.commons.exception.ExceptionMsg;
import com.moses.cloud.commons.msg.ResponseData;
import com.moses.cloud.commons.security.SecurityConstants;
import com.moses.cloud.commons.security.model.SecurityModel;
import com.moses.cloud.commons.security.model.TokenModel;
import com.moses.cloud.commons.security.token.UsernamePasswordWithToken;
import com.moses.cloud.commons.utils.*;
import com.moses.cloud.commons.vo.CacheDictVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.springframework.http.HttpMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 登陆
 * @author renker
 * @date 2019年8月20日
 */
@Slf4j
public class LoginFilter extends BaseFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue)
			throws Exception {
		HttpServletRequest request = WebUtils.toHttp(servletRequest);
		HttpServletResponse response = WebUtils.toHttp(servletResponse);
		try {
			String json = null;
			// 检测是否为post请求
			String method = request.getMethod();
			if(!HttpMethod.POST.toString().equalsIgnoreCase(method)){
				WebUtils.responseMsg(response, "Method Not Allowed", 405);
				return false;
			}
			//aes256.开关是否开启aes256.enabled
			String client = ServletUtils.getHeader(SecurityConstants.CLIENT);
			if (Strings.isNullOrEmpty(client)){
				WebUtils.responseMsg(response, ResponseData.newInstanceError(ExceptionMsg.DEFAULT_ERROR.getCode(), "CLIENT不能为空"));
				return false;
			}
			client = client.toLowerCase();

			CacheDictVo cacheDictVo = DictUtils.findByCode(String.format("aes256.%s.enabled", client.toLowerCase()));
			if (cacheDictVo != null && Boolean.valueOf(cacheDictVo.getDictValue())){
				String sign = request.getParameter(SecurityConstants.PARAM_SIGN);
				String value = request.getParameter(SecurityConstants.PARAM_VALUE);
				if (Strings.isNullOrEmpty(value)){
					WebUtils.responseMsg(response, ResponseData.newInstanceOfDefaultError());
					return false;
				}
				String requestData = AES256Utils.decoder(value);
				log.debug("requestData = {}", requestData);
				Map<String, String> map = UrlUtils.getUrlParamsInOrder(requestData);
				StringBuilder str = new StringBuilder();
				for (Map.Entry<String, String> entry : map.entrySet()) {
					if ("timestamp".equals(entry.getKey())) {
						// timestamp两次append
						str.append(entry.getValue());
					}
					str.append(entry.getValue());
				}

				//验签出错
				String localMd5Str = Md5EncoderUtils.encodeBit32WithNoSalt(str.toString().getBytes());
				if (Strings.isNullOrEmpty(localMd5Str) || !localMd5Str.equals(sign)) {
					//签名失败
					log.info("签名失败 = {}", localMd5Str, sign);
					WebUtils.responseMsg(response, ResponseData.newInstanceOfDefaultError());
					return false;
				}
				json = JsonUtils.toJSONString(map);
			}else {
				json = WebUtils.getJson(request);
			}
			JSONObject object = JSON.parseObject(json);
			String username = object.getString(SecurityConstants.USERNAME);
			String password = object.getString(SecurityConstants.PASSWORD);
			String type = object.getString(SecurityConstants.TYPE);
			UsernamePasswordWithToken token = new UsernamePasswordWithToken();
			token.setUsername(username);
			token.setPassword(password.toCharArray());
			token.setType(type);
			// 登陆
			getSubject(servletRequest, servletResponse).login(token);

			String utoken = (String) request.getAttribute(SecurityConstants.REQUEST_TOKEN_PARAMETER_NAME);
			request.removeAttribute(SecurityConstants.REQUEST_TOKEN_PARAMETER_NAME);

			// 返回响应体
			TokenModel model = new TokenModel(utoken, username);
			SecurityModel securityModel = (SecurityModel) getSubject(servletRequest, servletResponse).getPrincipals().getPrimaryPrincipal();
			model.setName(securityModel.getFullName());
			model.setUsername(username);
			model.setId(securityModel.getId());
			String phoneNo = securityModel.getPhoneNo();
			if (!Strings.isNullOrEmpty(phoneNo)){
				phoneNo = phoneNo.substring(phoneNo.length() - 4);
				model.setPhoneNo(phoneNo);
			}
			boolean vCode = securityModel.isVCode();
			model.setVCode(vCode);
			WebUtils.responseMsg(response, ResponseData.<TokenModel>builder().success().data(model).build());

		} catch(AuthenticationException e){
			log.error("登陆时异常",e);
			String msg = "";
			if(e instanceof AccountException || e instanceof UnknownAccountException || e instanceof LockedAccountException || e instanceof IncorrectCredentialsException){
				msg = e.getMessage();
			} else {
				msg = "系统异常";
			}
			WebUtils.responseMsg(response, ResponseData.newInstanceError(ExceptionMsg.ERROR_FAIL.getCode(), msg));

		} catch(Exception e){
			log.error("系统异常",e);
			WebUtils.responseMsg(response, ResponseData.newInstanceOfExceptionMsg(ExceptionMsg.DEFAULT_ERROR));
		}
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// 终止拦截器链继续执行
		return false;
	}

}
