package com.fanli.app.common;



/**
 * 
 * @author leo.chen
 *
 */
public class AppBizException extends AbstractException {
	private static final long serialVersionUID = 3919435389039003461L;
	
	public AppBizException() {
		super();
	}

	public AppBizException(ErrorCode errCode) {
		super(errCode);
	}
	
	public AppBizException(ErrorCode errCode, Object[] values) {
		super(errCode,values);
	}
	
	public AppBizException(String errMsg) {
		super(errMsg);
	}
	
	public AppBizException(String errMsg,Object... args) {
		super(errMsg,args);
	}
	
	public AppBizException(ErrorCode errCode,String errMsg) {
		super(errCode,errMsg);
	}
	
	public AppBizException(ErrorCode errCode, Object[] values,String errMsg) {
		super(errCode,values,errMsg);
	}
	
	public AppBizException(ErrorCode errCode, String errMsg,Object... args) {
		super(errCode,errMsg,args);
	}
	
	public AppBizException(ErrorCode errCode, Object[] values,String errMsg,Object... args) {
		super(errCode,values,errMsg,args);
	}
	
	public AppBizException(Throwable throwable) {
		super(throwable);
	}

	public AppBizException(ErrorCode errCode,Throwable throwable) {
		super(errCode,throwable);
	}
	
	public AppBizException(ErrorCode errCode,Object[] values,Throwable throwable) {
		super(errCode,values,throwable);
	}
	
	public AppBizException(String errMsg,Throwable throwable) {
		super(errMsg,throwable);
	}
	
	public AppBizException(String errMsg,Throwable throwable,Object... args) {
		super(errMsg,throwable,args);
	}
	
	public AppBizException(ErrorCode errCode,String errMsg, Throwable throwable) {
		super(errCode,errMsg,throwable);
	}
	
	public AppBizException(ErrorCode errCode,Object[] values,String errMsg, Throwable throwable) {
		super(errCode,values,errMsg,throwable);
	}
	
	public AppBizException(ErrorCode errCode, String errMsg, Throwable throwable,Object... args) {
		super(errCode,errMsg,throwable,args);
	}

	public AppBizException(ErrorCode errCode,Object[] values, String errMsg, Throwable throwable,Object... args) {
		super(errCode,values,errMsg,throwable,args);
	}

}
