package wangzhongqiu.spring.core.utils.accessor;

import wangzhongqiu.spring.core.utils.ClassLoaderUtil;

import java.text.MessageFormat;
import java.util.*;

public class PropertiesAccessor implements Accessor<String, String> {
	private String[] locations;

	private Properties properties;

	private boolean isOverride = true;

	public PropertiesAccessor() {
		this.setProperties(null);
	}

	public PropertiesAccessor(String location) {
		this(new String[] { location });
	}

	public PropertiesAccessor(String[] locations) {
		this.locations = locations;
		loadProperties();
	}

	public PropertiesAccessor(Properties properties) {
		setProperties(properties);
	}

	public void setLocation(String location) {
		this.setLocations(new String[] { location });
	}

	public void setLocations(String[] locations) {
		this.locations = locations;
		loadProperties();
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties != null ? properties : new Properties();
	}

	public boolean isOverride() {
		return isOverride;
	}

	public void setOverride(boolean isOverride) {
		this.isOverride = isOverride;
	}

	protected void loadProperties() {
		Properties properties = null;

		if (this.locations != null) {
			for (String location : locations) {
				if (location == null) {
					continue;
				}

				Properties second = ClassLoaderUtil.loadProperties(location, PropertiesAccessor.class);
				properties = mergeProperties(properties, second);
			}
		}

		this.setProperties(properties);
	}

	private Properties mergeProperties(Properties first, Properties second) {
		Properties properties = new Properties();

		if (first != null) {
			appendProperties(properties, first);
		}

		if (second != null) {
			appendProperties(properties, second);
		}

		return properties;
	}

	private void appendProperties(final Properties properties, Properties appendedProperties) {
		Enumeration<?> keys = appendedProperties.propertyNames();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			if (!properties.containsKey(key) || isOverride) {
				properties.put(key, appendedProperties.getProperty(key));
			}
		}
	}

	public String[] getKeys() {
		List<String> keysList = new ArrayList<String>();

		Set<Object> keysSet = properties.keySet();
		for (Object key : keysSet) {
			keysList.add((String) key);
		}

		return keysList.toArray(new String[keysList.size()]);
	}

	public String getValue(String key) {
		return properties.getProperty(key);
	}

	public String remove(String key) {
		return (String) properties.remove(key);
	}
	public String setValue(String key, String value) {
		if (!isOverride && properties.containsKey(key)) {
			String originalVal = properties.getProperty(key);
			throw new UnsupportedOperationException("The key (" + key + ") has been mapped to the value (" + originalVal + ") yet!");
		}

		return (String) properties.put(key, value);
	}

	public String getValue(String key, Object... arguments) {
		String value = getValue(key);
		if (value == null) {
			return null;
		}

		return MessageFormat.format(value, arguments);
	}

}
