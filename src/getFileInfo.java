

import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class getFileInfo {

	public Map<String, String> callgetInfo(File file) {
		Map<String, String> map = new HashMap<String, String>();
		if (!file.exists()) {
			System.exit(1);
		}
		map = getInfo(file);
		return map;

	}

	private static Map<String, String> getInfo(File file) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("aaa", file.getAbsolutePath());
		map.put("bbb", file.getPath());
		map.put("ccc", file.getName());
		map.put("ddd", String.valueOf(file.lastModified()));
		map.put("eee", String.valueOf(file.length()));
		return map;
	}
}
