package com.weili.wisdom.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class CacheUtils {

	private  static final String CACHE_FILE_NAME="weili";
	private static  SharedPreferences mSharedPreferences;
	//存放
	public static void putBoolean(Context context,String key,boolean value){
		if(mSharedPreferences==null){
		context.getSharedPreferences(CACHE_FILE_NAME,Context.MODE_PRIVATE);
	}
	mSharedPreferences.edit().putBoolean(key, value).commit();	
	}
	
//	/取出

	public  static boolean getBoolean(Context context,String key,boolean defValue){
		if(mSharedPreferences==null){
			mSharedPreferences=context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
			
		}
		
	return mSharedPreferences.getBoolean(key, defValue);	
	}
	/**
	 * 向SharedPreeferences中存储一个字符串
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
	 * 从SharedPreneces中取一个字符串
	 * @param context
	 * @param key
	 * @param defValue
	 * @return defValue 缺省值
	 */
	public  static String getString(Context context,String key,String defValue){
		
		if(mSharedPreferences==null){
			
			mSharedPreferences=context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
		}
		return mSharedPreferences.getString(key, defValue);
	}
	
	
}
