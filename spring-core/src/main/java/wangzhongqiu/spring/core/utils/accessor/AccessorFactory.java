package wangzhongqiu.spring.core.utils.accessor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccessorFactory {
	private static Map<String, PropertiesAccessor> cachedPropertiesAccessors = new ConcurrentHashMap<String, PropertiesAccessor>();

	public static PropertiesAccessor createPropertiesAccessor(String location) {
		return createPropertiesAccessor(new String[] { location });
	}

	public static PropertiesAccessor createPropertiesAccessor(String[] locations) {
		return createPropertiesAccessor(locations, true);
	}

	public synchronized static PropertiesAccessor createPropertiesAccessor(String[] locations, boolean singleton) {
		if (!singleton) {
			return new PropertiesAccessor(locations);
		}

		String cacheKey = buildCacheKey(locations);
		PropertiesAccessor cachedPropAccessor = cachedPropertiesAccessors.get(cacheKey);
		if (cachedPropAccessor == null) {
			cachedPropAccessor = new PropertiesAccessor(locations);
			cachedPropertiesAccessors.put(cacheKey, cachedPropAccessor);
		}

		return cachedPropAccessor;
	}

	private static String buildCacheKey(String[] locations) {
		if (locations == null || locations.length == 0) {
			return "_empty";
		}

		StringBuffer sb = new StringBuffer();
		for (String location : locations) {
			if (location == null || !location.endsWith(".properties")) {
				sb.append(location).append("_");
			} else {
				sb.append(location.substring(0, location.lastIndexOf(".properties"))).append("_");
			}
		}

		if (sb.lastIndexOf("_") == sb.length() - 1) {
			sb.delete(sb.length() - 1, sb.length());
		}

		return sb.toString();
	}
}
