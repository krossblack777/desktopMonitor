


import java.awt.AWTException;
import java.awt.SystemTray;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

	Map<String, String> map = new HashMap<String, String>();
	static File file = null;

	public static void main(String[] args) throws AWTException {

		final MainModel model = new MainModel();
		final InputFilePathView view = new InputFilePathView();
		view.setVisible(true);

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				if (!view.isShowing())
					model.checkFile();
			}
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 1 * 60 * 1000);

	}
}
