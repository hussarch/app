package com.fanli.app.common;

import org.apache.commons.lang.StringUtils;


/**
 * 
 * @author leo.chen
 *
 */
public class CheckUtils {
	
	public static void checkNotEmpty(Object value,String fieldName)throws AppBizException{
		if(value==null){
			throw new AppBizException(SystemErrorCode.ARGUMENT_MISS, new Object[]{fieldName});
		}
		if(value instanceof String){
			if(((String) value).trim().length()==0){
				throw new AppBizException(SystemErrorCode.ARGUMENT_MISS, new Object[]{fieldName});
			}
		}
	}
	
	public static void checkMaxLength(String value,int maxLength,String fieldName)throws AppBizException{		
		if(value!=null){
			if(value.length()>maxLength){
				throw new AppBizException(SystemErrorCode.ARGUMENT_MAX_LEN,new Object[]{fieldName,maxLength});
			}
		}
	}
	
	public static void checkMinLength(String value,int minLength,String fieldName)throws AppBizException{		
		if(value!=null){
			if(value.length()<minLength){
				throw new AppBizException(SystemErrorCode.ARGUMENT_MIN_LEN,new Object[]{fieldName,minLength});
			}
		}
	}
	
	public static void checkLength(String value,int length,String fieldName)throws AppBizException{		
		if(value!=null){
			if(value.length()!=length){
				throw new AppBizException(SystemErrorCode.ARGUMENT_LEN,new Object[]{fieldName,length});
			}
		}
	}
	
	public static void checkSupported(String fieldName,String value,String regex)throws AppBizException{		
		if(value!=null){
			if(!value.matches(regex)){
				throw new AppBizException(SystemErrorCode.ARGUMENT_UNSUPPORTED,new Object[]{fieldName,value});
			}
		}
	}
	
	
	public static void checkNumric(Object value,String fieldName)throws AppBizException{
		if(value==null){
			throw new AppBizException(SystemErrorCode.ARGUMENT_MISS,new Object[]{fieldName});
		}
		if(value instanceof String){
			if(((String) value).trim().length()==0){
				throw new AppBizException(SystemErrorCode.ARGUMENT_MISS,new Object[]{fieldName});
			}
		}
		if(!StringUtils.isNumeric(value.toString()))
			throw new AppBizException(SystemErrorCode.ARGUMENT_INVALID,new Object[]{fieldName});
	}
	
}
