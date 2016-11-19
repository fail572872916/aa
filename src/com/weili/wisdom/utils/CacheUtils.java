package com.weili.wisdom.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class CacheUtils {

	private  static final String CACHE_FILE_NAME="weili";
	private static  SharedPreferences mSharedPreferences;
	//´æ·Å
	public static void putBoolean(Context context,String key,boolean value){
		if(mSharedPreferences==null){
		context.getSharedPreferences(CACHE_FILE_NAME,Context.MODE_PRIVATE);
	}
	mSharedPreferences.edit().putBoolean(key, value).commit();	
	}
	
//	/È¡³ö

	public  static boolean getBoolean(Context context,String key,boolean defValue){
		if(mSharedPreferences==null){
			mSharedPreferences=context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
			
		}
		
	return mSharedPreferences.getBoolean(key, defValue);	
	}
}
