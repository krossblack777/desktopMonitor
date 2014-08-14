

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
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

public class TaskTray {

	getProperty prop = new getProperty();
	Image image = ImageIO
			.read(new File(prop.getProperty("TASK_TRAY_ICON")));
	final TrayIcon icon = new TrayIcon(image);
	HashMap<String, String> map = new HashMap<String, String>();


	public TaskTray(HashMap<String, String> map, File file) throws IOException, AWTException {
		this.map = map;
		//バルーン表示時のクリック時orタスクトレイアイコンダブルクリックで
		icon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkFile();
			}
		});

		PopupMenu menu = new PopupMenu();
		//右クリック時１ 現在時刻のファイルデータを取得し直す
		MenuItem aItem = new MenuItem("リセット");
		aItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				icon.displayMessage("menu example", "the menu was selected",
						MessageType.ERROR);
			}
		});
		//右クリック時2 ファイル選択からやり直し
		MenuItem bItem = new MenuItem("初期化");
		aItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				icon.displayMessage("menu example", "the menu was selected",
						MessageType.ERROR);
			}
		});
		//右クリック時3 終了する
		MenuItem exitItem = new MenuItem("exit");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		menu.add(aItem);
		menu.add(bItem);
		menu.add(exitItem);
		icon.setPopupMenu(menu);

		SystemTray.getSystemTray().add(icon);

		//指定時間ごとに確認メッセージ
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
		HashMap<String,String> currentmap = new HashMap<String,String>();
		currentmap = gfi.callgetInfo(Main.file);
		if (map.get("ddd").equals(currentmap.get("ddd"))) {
			icon.displayMessage("monitoring file", map.get("aaa"), MessageType.INFO);

		} else {
			DateFormat df = new DateFormat();
			icon.displayMessage("file changed", map.get("aaa") + "is changed.\n"
					+ "started is " + df.dateFormat(Long.parseLong(map.get("ddd"))) + " \n"
					+ "current is " + df.dateFormat(Long.parseLong(currentmap.get("ddd"))), MessageType.WARNING);
		}

	}

}