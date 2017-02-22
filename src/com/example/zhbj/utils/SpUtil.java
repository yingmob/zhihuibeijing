package com.example.zhbj.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {

	private static SharedPreferences sp;

	public static void pubBoolean(Context cont, String key, boolean value) {
		if (sp == null) {
			sp = cont.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
		sp.edit().putBoolean(key, value).commit();
	}

	public static boolean getBoolean(Context cont, String key, boolean defValue) {
		if (sp == null) {
			sp = cont.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
		return sp.getBoolean(key, defValue);
	}

	public static void putString(Context cont, String key, String value) {
		if (sp == null) {
			sp = cont.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
		sp.edit().putString(key, value).commit();
	}

	public static String getString(Context cont, String key, String defValue) {
		if (sp == null) {
			sp = cont.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
		return sp.getString(key, defValue);
	}

	public static void remove(Context cont, String key) {
		if (sp == null) {
			sp = cont.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
		sp.edit().remove(key).commit();
	}
}
