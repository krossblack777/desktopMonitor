

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class FileChooserView {

	public File openFileDialog(String directory) throws Exception {

		UIManager
				.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

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
