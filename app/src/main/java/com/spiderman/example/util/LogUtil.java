package com.spiderman.example.util;

import android.util.Log;

import com.spiderman.example.config.Constants;


public class LogUtil {
	public static final String TAG = LogUtil.class.getSimpleName();

	public LogUtil() {}

//	public static final void analytics(String log) {
//		if (DEBUG)
//			Log.d(LOG_TAG, log);
//	}

	public static final void info(String log){

		if(Constants.DEBUG){
			Log.i(TAG, log);
		}

	}

	public static final void info(String TAG, String log){

		if(Constants.DEBUG){
			Log.i(TAG, log);
		}

	}

	public static final void verbose(String log){

		if(Constants.DEBUG){
			Log.v(TAG, log);
		}

	}

	public static final void verbose(String TAG, String log){

		if(Constants.DEBUG){
			Log.v(TAG, log);
		}

	}

	public static final void debug(String log){

		if(Constants.DEBUG){
			Log.d(TAG, log);
		}

	}

	public static final void debug(String TAG, String log){

		if(Constants.DEBUG){
			Log.d(TAG, log);
		}

	}

	public static final void warn(String log){

		if(Constants.DEBUG){
			Log.w(TAG, log);
		}

	}

	public static final void warn(String TAG, String log){

		if(Constants.DEBUG){
			Log.w(TAG, log);
		}

	}

	public static final void error(String log){

		if(Constants.DEBUG){
			Log.e(TAG, log);
		}

	}

	public static final void error(String TAG, String log){

		if(Constants.DEBUG){
			Log.e(TAG, log);
		}

	}

}
