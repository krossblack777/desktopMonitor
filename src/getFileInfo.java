

import java.io.File;
import java.util.HashMap;
import java.util.Map;

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
		map.put(FileInfoEnum.AbusolutePath.toString(), file.getAbsolutePath());
		map.put(FileInfoEnum.FileName.toString(), file.getName());
		map.put(FileInfoEnum.LastModifiedData.toString(), String.valueOf(file.lastModified()));
		map.put(FileInfoEnum.FileNameSize.toString(), String.valueOf(file.length()));
		return map;
	}
}
