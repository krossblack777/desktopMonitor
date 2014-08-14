

public class CheckFile {
	public int checkChageFile(Long initdate, Long currentdate){

		if (initdate.equals(currentdate)) {
			return 0;
		}

		return 1;

	}

}
