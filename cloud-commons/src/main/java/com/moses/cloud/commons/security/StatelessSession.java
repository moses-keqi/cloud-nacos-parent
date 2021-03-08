package com.moses.cloud.commons.security;

import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;

/**
 * 无状态Session
 * @Author HanKeQi
 * @Date 2020/12/26 下午1:13
 * @Version 1.0
 **/
public class StatelessSession implements ValidatingSession,Serializable{
    private static final long serialVersionUID = -3846182215939886979L;

    private Logger log = LoggerFactory.getLogger(StatelessSession.class);

    private transient Serializable id;
    private transient Date startTimestamp;
    private transient Date stopTimestamp;
    private transient Date lastAccessTime;
    private transient Map<Object, Object> attributes;

    public StatelessSession() {
    }
    public StatelessSession(Serializable id){
        this.id = id;
    }

    public Map<Object, Object> getAttributes() {
        return attributes;
    }
    public void setAttributes(Map<Object, Object> attributes) {
        this.attributes = attributes;
    }
    @Override
    public Serializable getId() {
        return id;
    }

    @Override
    public Date getStartTimestamp() {
        return startTimestamp;
    }

    @Override
    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    @Override
    public long getTimeout() throws InvalidSessionException {
        return 0;
    }

    @Override
    public void setTimeout(long maxIdleTimeInMillis) throws InvalidSessionException {

    }

    @Override
    public String getHost() {
        return null;
    }

    @Override
    public void touch() throws InvalidSessionException {

    }

    @Override
    public void stop() throws InvalidSessionException {

    }

    @Override
    public Collection<Object> getAttributeKeys() throws InvalidSessionException {
        Map<Object, Object> attributes = getAttributes();
        if (attributes == null) {
            return Collections.emptySet();
        }
        return attributes.keySet();
    }

    @Override
    public Object getAttribute(Object key) throws InvalidSessionException {
        Map<Object, Object> attributes = getAttributes();
        if (attributes == null) {
            return null;
        }
        return attributes.get(key);
    }

    @Override
    public void setAttribute(Object key, Object value) throws InvalidSessionException {
        if (value == null) {
            removeAttribute(key);
        } else {
            getAttributesLazy().put(key, value);
        }
    }

    @Override
    public Object removeAttribute(Object key) throws InvalidSessionException {
        Map<Object, Object> attributes = getAttributes();
        if (attributes == null) {
            return null;
        } else {
            return attributes.remove(key);
        }
    }
    @Override
    public boolean isValid() {
        return true;
    }
    @Override
    public void validate() throws InvalidSessionException {
        // 校验
//		log.info("session校验...");
    }

    private Map<Object, Object> getAttributesLazy() {
        Map<Object, Object> attributes = getAttributes();
        if (attributes == null) {
            attributes = new HashMap<Object, Object>();
            setAttributes(attributes);
        }
        return attributes;
    }
}
