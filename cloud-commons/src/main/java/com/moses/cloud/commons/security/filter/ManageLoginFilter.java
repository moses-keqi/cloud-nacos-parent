package com.moses.cloud.commons.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.moses.cloud.commons.exception.ExceptionMsg;
import com.moses.cloud.commons.msg.ResponseData;
import com.moses.cloud.commons.security.SecurityConstants;
import com.moses.cloud.commons.security.model.SecurityModel;
import com.moses.cloud.commons.security.model.TokenManageModel;
import com.moses.cloud.commons.security.token.UsernamePasswordWithToken;
import com.moses.cloud.commons.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆
 * @author renker
 * @date 2019年8月20日
 */
@Slf4j
public class ManageLoginFilter extends BaseFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue)
			throws Exception {
		HttpServletRequest request = WebUtils.toHttp(servletRequest);
		HttpServletResponse response = WebUtils.toHttp(servletResponse);
		try {
			JSONObject object = JSON.parseObject(WebUtils.getJson(request));
			String username = object.getString(SecurityConstants.USERNAME);
			String password = object.getString(SecurityConstants.PASSWORD);
			String type = object.getString(SecurityConstants.TYPE);
			if (!SecurityConstants.IN_SIDE.equals(type)){
				WebUtils.responseMsg(response, ResponseData.newInstanceOfExceptionMsg(ExceptionMsg.NOT_TYPE_ERROR));
				return false;
			}
			UsernamePasswordWithToken token = new UsernamePasswordWithToken();
			token.setUsername(username);
			token.setPassword(password.toCharArray());
			token.setType(type);
			// 登陆
			getSubject(servletRequest, servletResponse).login(token);

			String utoken = (String) request.getAttribute(SecurityConstants.REQUEST_TOKEN_PARAMETER_NAME);
			request.removeAttribute(SecurityConstants.REQUEST_TOKEN_PARAMETER_NAME);

			// 返回响应体
			TokenManageModel model = new TokenManageModel(utoken, username);
			SecurityModel securityModel = (SecurityModel) getSubject(servletRequest, servletResponse).getPrincipals().getPrimaryPrincipal();
			model.setName(securityModel.getFullName());
			model.setUsername(username);
			model.setId(securityModel.getId());
			WebUtils.responseMsg(response, ResponseData.<TokenManageModel>builder().success().data(model).build());

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
