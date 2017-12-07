
package wangzhongqiu.spring.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class ClassLoaderUtil {
	public static URL loadResource(String resourcePath, Class<?> clazz) {
		URL url = Thread.currentThread().getContextClassLoader().getResource(resourcePath);

		if (url == null) {
			url = ClassLoaderUtil.class.getResource(resourcePath);
		}

		if (url == null && clazz != null) {
			url = clazz.getResource(resourcePath);
		}

		return url;
	}

	public static Properties loadProperties(String location, Class<?> clazz) {
		URL resUrl = loadResource(location, clazz);

		Properties properties = new Properties();

		if (resUrl != null) {
			InputStream is = null;
			try {
				is = resUrl.openStream();
				properties.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return properties;
	}
}
