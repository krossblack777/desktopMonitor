


import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tray {
	private Image image;
	protected static TrayIcon icon;

	public TrayIcon getIcon() {
		return icon;
	}

	public Tray() {
		getProperty prop = new getProperty();
		try {
			image = ImageIO.read(new File(prop.getProperty("TASK_TRAY_ICON")));
			icon = new TrayIcon(image);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.exit(1);
		}

	}

}


