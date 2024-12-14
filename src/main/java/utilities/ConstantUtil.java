package utilities;

public final class ConstantUtil {

	private ConstantUtil() {}
	
	private final static String CONFIG_PATH = System.getProperty("user.dir") + "/src/test/resources/config.properties";
	public static String getConfigPath() {
		return CONFIG_PATH;
	}
}
