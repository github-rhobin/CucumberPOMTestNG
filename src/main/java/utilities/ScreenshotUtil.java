package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import driver.DriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public final class ScreenshotUtil {

	private ScreenshotUtil() {
	}

	public static String getBase64Image() {
		try {
			return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
		return null;
	}

	// experimental
	public static String imageToBase64(File file) {
		String base64 = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			fis.read(bytes);
			base64 = new String(Base64.encodeBase64(bytes), "UTF-8"); // need apache poi dependency
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return base64;
	}

	// experimental fullpage screenshot
	public static File fullScreenshot() throws IOException {
		Screenshot ss = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
				.takeScreenshot(DriverManager.getDriver());
		String path = "./src/test/resources/ashot/fullscreenshot.png";
		File file = new File(path);
		ImageIO.write(ss.getImage(), "png", file);
		return file;
	}
}
