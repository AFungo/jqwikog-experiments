package experiments.randoopTest;

import net.jqwik.api.*;

import net.jqwik.api.randoop.*;

import org.assertj.core.api.*;
import java.util.*;

public class DateTest {

	@Property(tries=100)
	void dateAfterTest(@ForAll Date date) {
		Assertions.assertThat(date.after(date)).isFalse();
	}

	public static boolean isNewYear(Object o){
		if(o == null){
			return false;
		}
		Date d = (Date) o;
	 	Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		return cal.get(Calendar.MONTH) == Calendar.DECEMBER && cal.get(Calendar.DAY_OF_MONTH) == 31;
	}

	//@AssumeMethod(className = DateTest.class, methodName = "isNewYear")
	@Property(tries=100)
	void changeYearTest(@ForAll Date date) {
		Assume.that(DateTest.isNewYear(date));
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		cal.add(Calendar.DATE, 1);
		Assertions.assertThat(year + 1).isEqualTo(cal.get(Calendar.YEAR));
	}

	public static boolean are31daysLongMonth(Object o){
		if(o == null){
			return false;
		}
		Date d = (Date) o;
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		return cal.get(Calendar.MONTH) == Calendar.JANUARY ||
				cal.get(Calendar.MONTH) == Calendar.MARCH ||
				cal.get(Calendar.MONTH) == Calendar.MAY ||
			   cal.get(Calendar.MONTH) == Calendar.JULY ||
			   cal.get(Calendar.MONTH) == Calendar.AUGUST ||
			   cal.get(Calendar.MONTH) == Calendar.OCTOBER ||
			   cal.get(Calendar.MONTH) == Calendar.DECEMBER;
	}

	//@AssumeMethod(className = DateTest.class, methodName = "are31daysLongMonth")
	@Property(tries=100)
	void longMonthTest(@ForAll Date date) {
		Assume.that(DateTest.are31daysLongMonth(date));
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		Assertions.assertThat(day).isLessThanOrEqualTo(31);
		Assertions.assertThat(day).isGreaterThanOrEqualTo(1);
	}

	public static boolean are30daysLongMonth(Object o){
		if(o == null){
			return false;
		}
		Date d = (Date) o;
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		return !are31daysLongMonth(d) && cal.get(Calendar.MONTH)!= Calendar.FEBRUARY;
	}

//	@AssumeMethod(className = DateTest.class, methodName = "are30daysLongMonth")
	@Property(tries=100)
	void mediumMonthTest(@ForAll
					   Date date) {
		Assume.that(DateTest.are30daysLongMonth(date));
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		Assertions.assertThat(day).isLessThanOrEqualTo(30);
		Assertions.assertThat(day).isGreaterThanOrEqualTo(1);
	}

	public static boolean isFebruary(Object o){
		if(o == null){
			return false;
		}
		Date d = (Date) o;
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		return cal.get(Calendar.MONTH)== Calendar.FEBRUARY && isLeapYear(cal.get(Calendar.YEAR));
	}

	public static boolean isLeapYear(int year) {
		if (year % 4 != 0) {
			return false;
		} else if (year % 400 == 0) {
			return true;
		} else return year % 100 != 0;
	}

//	@AssumeMethod(className = DateTest.class, methodName = "isFebruary")
	@Property(tries=100)
	void februaryMonthTest(@ForAll
						 Date date) {
		Assume.that(DateTest.isFebruary(date));
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		Assertions.assertThat(day).isLessThanOrEqualTo(29);
		Assertions.assertThat(day).isGreaterThanOrEqualTo(1);
	}
}
