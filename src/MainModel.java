
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon.MessageType;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MainModel {

	static Map<String, String> map = new HashMap<String, String>();
	static boolean initFlg = true;

	public void checkFile() {
		getFileInfo gfi = new getFileInfo();
		Map<String, String> currentmap = new HashMap<String, String>();
		DateFormat dft = new DateFormat();
		currentmap = gfi.callgetInfo(Main.file);
		if (map.get("ddd").equals(currentmap.get("ddd"))) {
			Tray.icon.displayMessage("ファイルを監視中です\n", map.get("aaa")
					+ "\n更新日時 : " + dft.dateFormat(map.get("ddd")),
					MessageType.INFO);

		} else {
			DateFormat df = new DateFormat();
			Tray.icon
					.displayMessage(
							"ファイルが変更されました",
							map.get("aaa") + "\n変更前  : "
									+ df.dateFormat(map.get("ddd"))
									+ "\n変更後 :  "
									+ df.dateFormat(currentmap.get("ddd")),
							MessageType.WARNING);
		}

	}

	public void initialize() {
		InputFilePathView jt = new InputFilePathView();
		jt.setVisible(true);

		getFileInfo gfi = new getFileInfo();
		DateFormat dft = new DateFormat();

		map = gfi.callgetInfo(Main.file);

		Tray.icon.displayMessage("ファイル変更", "ファイル名:" + map.get("aaa")
				+ "\n更新日時 : " + dft.dateFormat(map.get("ddd")),
				MessageType.INFO);
	}

	public void resetTime() {
		getFileInfo gfi = new getFileInfo();
		map = gfi.callgetInfo(Main.file);
		DateFormat dft = new DateFormat();
		Tray.icon.displayMessage("リセット",
				"更新日時" + dft.dateFormat(map.get("ddd")), MessageType.INFO);
	}

	public void openDir() {
		getFileInfo gfi = new getFileInfo();
		map = gfi.callgetInfo(Main.file);
		execCommand ec = new execCommand();
		ec.exec(map.get("aaa"));
	}

	public void exitAction() {
		System.exit(0);
	}

	public void settingFile(String text) {
		FileChooserView fileView = new FileChooserView();
		try {
			Main.file = fileView.openFileDialog(text);
		} catch (Exception e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		getFileInfo gfi = new getFileInfo();
		map = gfi.callgetInfo(Main.file);
		if (initFlg) {
			initFlg = false;
			TrayIconMenuView tray = new TrayIconMenuView();
			try {
				SystemTray.getSystemTray().add(Tray.icon);
			} catch (AWTException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

}
