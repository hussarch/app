package com.fanli.app.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * @author leo.chen
 *
 */
public abstract class AbstractException extends Exception {
	private static final long serialVersionUID = -1069553732236063500L;
	private ErrorCode errCode;
	private String errMsg;
	protected static Map<Class<?>,Properties> expMap = new HashMap<Class<?>, Properties>();
	
	public AbstractException() {
		super();
	}

	public AbstractException(ErrorCode errCode) {
		this(errCode,new Object[]{},null,new Object[]{});
	}
	
	public AbstractException(ErrorCode errCode, Object[] values) {
		this(errCode,values,null,new Object[]{});
	}
	
	public AbstractException(String errMsg) {
		this(null,errMsg);
	}
	
	public AbstractException(String errMsg,Object... args) {
		this(null,null,errMsg,args);
	}
	
	public AbstractException(ErrorCode errCode,String errMsg) {
		this(errCode,null,errMsg,new Object[]{});
	}
	
	public AbstractException(ErrorCode errCode, Object[] values,String errMsg) {
		this(errCode,values,errMsg,new Object[]{});
	}
	
	public AbstractException(ErrorCode errCode, String errMsg,Object... args) {
		this(errCode,null,errMsg,args);
	}
	
	public AbstractException(ErrorCode errCode, Object[] values,String errMsg,Object... args) {
	}
	
	public AbstractException(Throwable throwable) {
		super(throwable);
	}

	public AbstractException(ErrorCode errCode,Throwable throwable) {
		this(errCode,null,null,throwable,new Object[]{});
	}
	
	public AbstractException(ErrorCode errCode,Object[] values,Throwable throwable) {
		this(errCode,values,null,throwable,new Object[]{});
	}
	
	public AbstractException(String errMsg,Throwable throwable) {
		this(null,null,errMsg,throwable,new Object[]{});
	}
	
	public AbstractException(String errMsg,Throwable throwable,Object... args) {
		this(null,null,errMsg,throwable,args);
	}
	
	public AbstractException(ErrorCode errCode,String errMsg, Throwable throwable) {
		this(errCode,null,errMsg,throwable,new Object[]{});
	}
	
	public AbstractException(ErrorCode errCode,Object[] values,String errMsg, Throwable throwable) {
		this(errCode,values,errMsg,throwable,new Object[]{});
	}
	
	public AbstractException(ErrorCode errCode, String errMsg, Throwable throwable,Object... args) {
		this(errCode,null,errMsg,throwable,args);
	}

	public AbstractException(ErrorCode errCode,Object[] values, String errMsg, Throwable throwable,Object... args) {
	}
	
	public ErrorCode getErrCode() {
		return this.errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
