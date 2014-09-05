import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class execCommand {
	public int exec(String filepath) throws IOException {

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
		Process p = pb.start();

		try {
			p.waitFor();
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return 0;
	}

}
