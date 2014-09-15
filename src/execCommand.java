

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class execCommand {
	public int exec(String filepath) {

		List<String> cmd = new ArrayList<String>();
		String osname = System.getProperty("os.name");
		if (osname.indexOf("Windows") >= 0) {
			cmd.add("cmd");
			cmd.add("/c");
			cmd.add("explorer");

		} else if (osname.indexOf("Linux") >= 0) {
			// 未
			System.out.println("linux");
			return 1;

		} else {
			// 未
			System.out.println("not");
			return 1;

		}

		cmd.add(filepath);

		ProcessBuilder pb = new ProcessBuilder(cmd);
		Process p;
		try {
			p = pb.start();
			p.waitFor();
		} catch (IOException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
