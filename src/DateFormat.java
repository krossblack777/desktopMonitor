

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
	public String dateFormat(Long time) {
			Date date = new Date(time);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/yy' 'HH:mm:ss");

			return sdf.format(date);
	}

}
