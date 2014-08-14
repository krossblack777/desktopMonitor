

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class getProperty {

	private final static String PROP_PATH = "./conf/application.properties";

	private static Properties conf = new Properties();

	public  String getProperty(String prop) {

		InputStream inputStream;
		try {
			inputStream = new FileInputStream(
					new File(PROP_PATH));
			conf.load(inputStream);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return conf.getProperty(prop);
	}

}
