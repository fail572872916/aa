package com.weili.wisdom.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class CacheUtils {

	private  static final String CACHE_FILE_NAME="weili";
	private static  SharedPreferences mSharedPreferences;
	//���
	public static void putBoolean(Context context,String key,boolean value){
		if(mSharedPreferences==null){
		context.getSharedPreferences(CACHE_FILE_NAME,Context.MODE_PRIVATE);
	}
	mSharedPreferences.edit().putBoolean(key, value).commit();	
	}
	
//	/ȡ��

	public  static boolean getBoolean(Context context,String key,boolean defValue){
		if(mSharedPreferences==null){
			mSharedPreferences=context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
			
		}
		
	return mSharedPreferences.getBoolean(key, defValue);	
	}
	/**
	 * ��SharedPreeferences�д洢һ���ַ���
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putString(Context context,String key,String value){
		if(mSharedPreferences==null){
			mSharedPreferences=context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
			
		}
		mSharedPreferences.edit().putString(key, value).commit();
	}
	
	/**
	 * ��SharedPreneces��ȡһ���ַ���
	 * @param context
	 * @param key
	 * @param defValue
	 * @return defValue ȱʡֵ
	 */
	public  static String getString(Context context,String key,String defValue){
		
		if(mSharedPreferences==null){
			
			mSharedPreferences=context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
		}
		return mSharedPreferences.getString(key, defValue);
	}
	
	
}
