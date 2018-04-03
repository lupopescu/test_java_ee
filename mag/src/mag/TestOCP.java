package mag;

import java.awt.List;
import java.lang.reflect.Array;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.omg.PortableServer.ForwardRequestHelper;

public class TestOCP {

	public static void main(String[] args) {
		TestOCP t = new TestOCP();
		// System.out.println(t.DateInstant());
		// System.out.println(t.ZonedDateTimetoInstant());
		// t.DaylightSavingsTime();
		// t.localTest();
		// Double []arr={1.0,2.0,3.0};
		// Arrays.asList(arr).stream().forEach(d->
		// System.out.print(d.intValue()+" "));
		// System.out.println(Arrays.deepToString(arr));
		// System.out.println(t.fileNaming(new String[]{"doc", "doc", "image",
		// "doc(1)", "doc"}));
		t.extractMatrixColumn(new int[][] { { 1, 1, 1, 2 }, { 0, 5, 0, 4 },
				{ 2, 1, 3, 6 } }, 0);
	}

	String[] fileNaming(String[] names) {
		Map<String, Integer> test = new TreeMap<>();
		// System.out.println(test.put(names[0], 0)+" !!!!!!!!!!!!");
		test.put(names[0], 0);
		System.out.println(test + "_________");

		for (int i = 1; i < names.length; i++) {
			System.out.println(names[i]);
			if (test.put(names[i], 0) == null) {
				System.out.println(test + "in for test");
				continue;
			} else {

				int t = test.get(names[i]) + 1;
				test.put(names[i], t);

				System.out.println("t " + t);

				if (test.get(names[i] + "(" + t + ")") == null) {
					test.put(names[i] + "(" + t + ")", 0);

				} else {

				}

			}
			System.out.println(test + "  " + i);
		}
		// System.out.println(Arrays.toString(names)+"FINEEE");
		System.out.println(test);
		return names;

	}

	int[] extractMatrixColumn(int[][] matrix, int column) {
		
		return Arrays.asList(matrix).stream().mapToInt(a->a[column]).toArray();
	}

	public String isAded(Map<String, Integer> test, String names) {
		int t = test.get(names) + 1;
		Object o = test.put(names + "(" + t + ")", t);
		if (o != null) {
			return names + "(" + t + ")";
		} else {

		}
		return "";

	}

	private void localTest() {
		Locale l = Locale.getDefault();
		System.out.println(l);

	}

	private void DaylightSavingsTime() {
		LocalDate date = LocalDate.of(2016, 03, 13);
		LocalTime time = LocalTime.of(1, 30);
		ZoneId zone = ZoneId.of("US/Eastern");
		ZonedDateTime zoneDateAndTime = ZonedDateTime.of(date, time, zone);
		System.out.println(zoneDateAndTime);
		zoneDateAndTime = zoneDateAndTime.plusHours(1);

		System.out.println(zoneDateAndTime);
	}

	public long ZonedDateTimetoInstant() {
		LocalDate date = LocalDate.of(2018, 03, 20);
		LocalTime time = LocalTime.now();
		ZoneId zone = ZoneId.of("US/Eastern");
		ZonedDateTime zoneDateAndTime = ZonedDateTime.of(date, time, zone);
		Instant i = zoneDateAndTime.toInstant();
		System.out.println(zoneDateAndTime);
		System.out.println(i);
		System.out.println(i.plus(1, ChronoUnit.HOURS));
		return 0;
	}

	public long DateInstant() {
		Instant now = Instant.now();
		for (int i = 0; i < 3001; i++) {
			String s = "";
			s = s + i;
		}
		System.out.println("it will show the diference of time");

		Instant later = Instant.now();
		Duration d = Duration.between(now, later);
		return d.toMillis();

	}

}
