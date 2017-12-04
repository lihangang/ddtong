package com.ddtong.core.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapsSortUtils {

	public static LinkedHashMap<String, Object> sortmap(Map<String, Object> map) {
		LinkedHashMap<String, Object> sortmap = new LinkedHashMap<String, Object>();
		if (map == null || map.size() <= 0) {
			return sortmap;
		}

		// key排序
		List<String> listkeys = new ArrayList<String>(map.keySet());
		Collections.sort(listkeys, String.CASE_INSENSITIVE_ORDER);

		for (int i = 0; i < listkeys.size(); i++) {
			sortmap.put(listkeys.get(i), map.get(listkeys.get(i)));
		}

		// 以这种方式遍历
		// for (Entry<?, ?> pair : sortmap.entrySet()) {
		// System.out.println(pair.getKey() + " , " + pair.getValue());
		// }
		return sortmap;
	}
}
