

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Main {
	static File file = null;

	public static void main(String [] args) {

		selectFile sf = new selectFile();
		 file = sf.openFileDialog();

		getFileInfo gfi = new getFileInfo();
		HashMap<String,String> map = new HashMap<String,String>();
		map = gfi.callgetInfo(file);
		try {
			TaskTray tray = new TaskTray(map, file);

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

}
