package com.fanli.app.common;

/**
 * 
 * @author leo.chen
 *
 */
public enum SystemErrorCode implements ErrorCode{
	
	OK("SYS.E.00000"),
	ERROR_UNDEFINED("SYS.E.00001"),
	ARGUMENT_MISS("SYS.E.00002"),
	ARGUMENT_INVALID("SYS.E.00003"), 
	ARGUMENT_MAX_LEN("SYS.E.10002"), 
	ARGUMENT_MIN_LEN("SYS.E.10003"),
	ARGUMENT_LEN("SYS.E.10004"),
	ARGUMENT_UNSUPPORTED("SYS.E.10012"),
	ARGUMENT_NOT_NUMERIC("SYS.E.10013"),
	SIGN_FAILED("SYS.E.10014"),
	ARGUMENT_FORMATTED_ERROR("SYS.E.10015"),
	REPEAT_POST("SYS.E.10016"),
	SESSION_EXPIRED("SYS.E.10017"),
	HTTP_WRONG_METHOD("SYS.E.30000"),
	DATA_NODE_MISS("SYS.E.40000");
	
	
	private String errCode;
	private SystemErrorCode(String errCode){
		this.errCode = errCode;
	}
	
	public String getErrCode() {
		return errCode;
	}

	public Class<?> getExceptionClass() {
		return AppBizException.class;
	}
}
