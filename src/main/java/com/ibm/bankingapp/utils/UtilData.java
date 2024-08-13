package com.ibm.bankingapp.utils;

import java.util.HashMap;
import java.util.Map;


public class UtilData {
	public static Map<String, Integer> roleIntMap = new HashMap<>();
	static {
		roleIntMap.put("CUSTOMER", 2345);
		roleIntMap.put("ADMIN", 5634);
		roleIntMap.put("SUPER_ADMIN", 5609);
	}
}
