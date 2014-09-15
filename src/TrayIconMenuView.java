


import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.management.modelmbean.ModelMBean;

 public class TrayIconMenuView extends Tray {
	MainModel model = new MainModel();
	PopupMenu menu = new PopupMenu();

	TrayIconMenuView() {
		icon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.checkFile();
			}
		});
		MenuItem openDir = new MenuItem("開く");
		openDir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.openDir();
				setIcon();
			}
		});

		MenuItem resetItem = new MenuItem("リセット");
		resetItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.resetTime();
			}
		});

		MenuItem initItem = new MenuItem("監視対象を変更");
		initItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.initialize();
			}
		});

		MenuItem exitItem = new MenuItem("終了");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.exitAction();
			}
		});

		menu.add(openDir);
		menu.add(resetItem);
		menu.add(initItem);
		menu.add(exitItem);
		icon.setPopupMenu(menu);

	}

	public TrayIcon getIcon() {
		return icon;
	}

	public void setIcon() {
		icon.setPopupMenu(menu);
	}

}
