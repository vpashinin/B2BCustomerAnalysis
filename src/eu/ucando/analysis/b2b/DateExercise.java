package eu.ucando.analysis.b2b;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateExercise {
	
	/*
	 * dd = day
	 * MM = month
	 * yyyy = year
	 */
	
	public static void main(String[] args) {
		Date today = new Date();
		System.out.println(today);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String todayInANiceWay = dateFormat.format(today);
		System.out.println(todayInANiceWay);
	}

}
