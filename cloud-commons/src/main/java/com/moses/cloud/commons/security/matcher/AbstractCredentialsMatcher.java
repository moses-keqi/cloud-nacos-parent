package com.moses.cloud.commons.security.matcher;

import com.moses.cloud.commons.security.checker.AuthenticationTokenCheckService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

import java.util.List;

public abstract class AbstractCredentialsMatcher implements CredentialsMatcher {

	/** 用户认证后检查 */
	private List<AuthenticationTokenCheckService> postCredentialsMatchFailedChecks;

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		boolean result = match(token, info);
		if(!result){
			if (!CollectionUtils.isEmpty(postCredentialsMatchFailedChecks)) {
				for (AuthenticationTokenCheckService authenticationTokenCheckService : postCredentialsMatchFailedChecks) {
					authenticationTokenCheckService.authenticationTokenCheck(token, info);
				}
			}
		}
		return result;
	}

	/**
	 * 匹配
	 * @param token
	 * @param info
	 * @return
	 */
	protected abstract boolean match(AuthenticationToken token, AuthenticationInfo info);

	public void setPostCredentialsMatchFailedChecks(List<AuthenticationTokenCheckService> postCredentialsMatchFailedChecks) {
		this.postCredentialsMatchFailedChecks = postCredentialsMatchFailedChecks;
	}

}
