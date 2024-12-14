package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import enums.ConfigProp;

public final class ConfigUtil {

	private ConfigUtil() {}

	private static Properties prop = new Properties();
	private static final Map<String, String> config_map = new HashMap<>();

	// used static block for eager initialization (order of execution: static block > constructor > instance)
	static {
		try {
			FileInputStream file = new FileInputStream(ConstantUtil.getConfigPath());
			prop.load(file);
			for (Map.Entry<Object, Object> entry : prop.entrySet()) {
				config_map.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim());
				// used trim() to remove whitespaces in between the property file values
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0); // code to exit on static block
		}
	}

	public static String getPropValue(ConfigProp key) {
		if (Objects.isNull(key) || Objects.isNull(config_map.get(key.name()))) {
			throw new RuntimeException("PROPERTY KEY NOT FOUND!");
		}
		return config_map.get(key.name());
	}
}
