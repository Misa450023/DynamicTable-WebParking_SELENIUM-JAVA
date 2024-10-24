package utility;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyUtil {

	public static long[] calculateBetween(String startDate, String endDate, String startTime, String endTime) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime startDateTime = LocalDateTime.parse(startDate + " " + startTime, formatter);
		LocalDateTime endDateTime = LocalDateTime.parse(endDate + " " + endTime, formatter);

		Duration duration = Duration.between(startDateTime, endDateTime);
		long days = duration.toDays();
		long hours = duration.toHours() % 24;

		long[] daysAndHours = { days, hours };

		return daysAndHours;
	}

}
