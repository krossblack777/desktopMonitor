
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class selectFile {

	public File openFileDialog(String directory) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		String str = null;
		if (0 == directory.length()) {
			getProperty gp = new getProperty();
			str = gp.getProperty("DEFAULT_OPEN_DIALOG");

		} else {
			str = directory;
		}

		JFileChooser chooser = new JFileChooser(str);
		chooser.setDragEnabled(true);

		int ret = chooser.showOpenDialog(null);
		if (ret == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		}
		if (Main.file == null) {
			System.exit(0);
		}

		return Main.file;
	}

}
