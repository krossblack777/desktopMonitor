

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
	public String dateFormat(String time) {

			Date date = new Date(Long.parseLong(time));

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd' 'HH:mm:ss");

			return sdf.format(date);
	}

}
