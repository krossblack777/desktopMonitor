import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

public class TaskTray {

	getProperty prop = new getProperty();
	Image image = ImageIO.read(new File(prop.getProperty("TASK_TRAY_ICON")));
	final TrayIcon icon = new TrayIcon(image);
	static Map<String, String> map = new HashMap<String, String>();

	public TaskTray(Map<String, String> map, File file) throws IOException,
			AWTException {
		TaskTray.map = map;
		// バルーン表示時のクリック時orタスクトレイアイコンダブルクリックで
		icon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkFile();
			}
		});

		PopupMenu menu = new PopupMenu();
		// 右クリック時1 対象ファイルリを開く
		MenuItem openDir = new MenuItem("開く");

		openDir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					openDir();
				} catch (IOException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
			}
		});

		// 右クリック時2 現在時刻のファイルデータを取得し直す
		MenuItem resetItem = new MenuItem("リセット");
		resetItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetTime();
			}
		});
		// 右クリック時3 ファイル選択からやり直し
		MenuItem initItem = new MenuItem("監視対象を変更");
		initItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initialize();
			}
		});
		// 右クリック時4 終了する
		MenuItem exitItem = new MenuItem("終了");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		menu.add(openDir);
		menu.add(resetItem);
		menu.add(initItem);
		menu.add(exitItem);
		icon.setPopupMenu(menu);

		SystemTray.getSystemTray().add(icon);

		// 指定時間ごとに確認メッセージ
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				checkFile();
			}
		};
		Timer timer = new Timer();
		timer.schedule(task, 0, 1 * 60 * 1000);
	}

	public void checkFile() {
		getFileInfo gfi = new getFileInfo();
		Map<String, String> currentmap = new HashMap<String, String>();
		DateFormat dft = new DateFormat();
		currentmap = gfi.callgetInfo(Main.file);
		if (map.get("ddd").equals(currentmap.get("ddd"))) {
			icon.displayMessage("ファイルを監視中です\n", map.get("aaa") + "\n更新日時 : "
					+ dft.dateFormat(map.get("ddd")), MessageType.INFO);

		} else {
			DateFormat df = new DateFormat();
			icon.displayMessage(
					"ファイルが変更されました",
					map.get("aaa") + "\n変更前  : "
							+ df.dateFormat(map.get("ddd")) + "\n変更後 :  "
							+ df.dateFormat(currentmap.get("ddd")),
					MessageType.WARNING);
		}

	}

	public void initialize() {
		String temp = TaskTray.map.get("aaa");
		selectFile sf = new selectFile();
		Main.file = sf.openFileDialog();

		getFileInfo gfi = new getFileInfo();
		DateFormat dft = new DateFormat();

		map = gfi.callgetInfo(Main.file);

		if (temp.equals(map.get("aaa"))) {
			icon.displayMessage("変更無し", "ファイル名:" + map.get("aaa") + "\n更新日時 : "
					+ dft.dateFormat(map.get("ddd")), MessageType.INFO);

		} else {
			icon.displayMessage("ファイル変更", "ファイル名:" + map.get("aaa") + "\n更新日時 : "
					+ dft.dateFormat(map.get("ddd")), MessageType.INFO);
		}
	}

	public void resetTime() {
		getFileInfo gfi = new getFileInfo();
		map = gfi.callgetInfo(Main.file);
		DateFormat dft = new DateFormat();
		icon.displayMessage("リセット", "更新日時" + dft.dateFormat(map.get("ddd")),
				MessageType.INFO);
	}

	public void openDir() throws IOException {
		getFileInfo gfi = new getFileInfo();
		map = gfi.callgetInfo(Main.file);
		execCommand ec = new execCommand();
		ec.exec(map.get("aaa"));
	}
}