package java8;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class DateAPI {

	public static void main(String[] args) {
		demoClock();
		demoTimezones();
		demoLocalTime();
	}

	/*****
	 * Java 8 contains a brand new date and time API under the package java.time.
	 ******/

	/***
	 * Clock provides access to the current date and time. Clocks are aware of a
	 * timezone and may be used instead of System.currentTimeMillis() to retrieve
	 * the current time in milliseconds since Unix EPOCH. Such an instantaneous
	 * point on the time-line is also represented by the class Instant. Instants can
	 * be used to create legacy java.util.Date objects.
	 ***********/

	public static void demoClock() {
		Clock clock = Clock.systemDefaultZone();
		System.out.println(clock);
		long millis = clock.millis();
		System.out.println(millis);

		Instant instant = clock.instant();
		System.out.println(instant);
		Date legacyDate = Date.from(instant); // legacy java.util.Date
		System.out.println(legacyDate);

		System.out.println(Instant.now());
	}

	/***
	 * Timezones are represented by a ZoneId. They can easily be accessed via static
	 * factory methods. Timezones define the offsets which are important to convert
	 * between instants and local dates and times
	 **********/

	public static void demoTimezones() {
		System.out.println(ZoneId.getAvailableZoneIds());
		// prints all available timezone ids

		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		ZoneId zone2 = ZoneId.of("Brazil/East");
		System.out.println(zone1.getRules());
		System.out.println(zone2.getRules());

		// ZoneRules[currentStandardOffset=+01:00]
		// ZoneRules[currentStandardOffset=-03:00]
	}

	/******
	 * LocalTime represents a time without a timezone, e.g. 10pm or 17:30:15. The
	 * following example creates two local times for the timezones defined above.
	 * Then we compare both times and calculate the difference in hours and minutes
	 * between both times.
	 **********/

	public static void demoLocalTime() {
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		ZoneId zone2 = ZoneId.of("Brazil/East");
		LocalTime now1 = LocalTime.now(zone1);
		LocalTime now2 = LocalTime.now(zone2);

		System.out.println(now1.isBefore(now2)); // false

		long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
		long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

		System.out.println(hoursBetween); // -3
		System.out.println(minutesBetween); // -239

		LocalTime late = LocalTime.of(23, 59, 59);
		System.out.println(late); // 23:59:59

		DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
				.withLocale(Locale.GERMAN);
		System.out.println(germanFormatter);

		LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
		System.out.println(leetTime); // 13:37

		LocalDate today = LocalDate.now();
		System.out.println(today);
		LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
		LocalDate yesterday = tomorrow.minusDays(2);

		LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
		DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
		System.out.println(dayOfWeek); // FRIDAY

		germanFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.GERMAN);

		LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
		System.out.println(xmas); // 2014-12-24

		LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);

		dayOfWeek = sylvester.getDayOfWeek();
		System.out.println(dayOfWeek); // WEDNESDAY

		Month month = sylvester.getMonth();
		System.out.println(month); // DECEMBER

		long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
		System.out.println(minuteOfDay); // 1439

		Instant instant = sylvester.atZone(ZoneId.systemDefault()).toInstant();

		Date legacyDate = Date.from(instant);
		System.out.println(legacyDate); // Wed Dec 31 23:59:59 CET 2014

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy - HH:mm");

		LocalDateTime parsed = LocalDateTime.parse("Nov 03, 2014 - 07:13", formatter);
		String string = formatter.format(parsed);
		System.out.println(string); // Nov 03, 2014 - 07:13
	}

}
