package egovframework.dnworks.cmm.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DateUtil 
{
	/* **********************************************************************
	 * Date format method
	 * **********************************************************************/

	// Return string date format
	public static String getFormatDate()
	{
		return getFormatDate(new Date());
	}

	public static String getFormatDate(Date param)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(Util.nvl(param));
	}

	// Return string time format
	public static String getFormatTime()
	{
		return getFormatTime(new Date());
	}

	public static String getFormatTime(Date param)
	{
		if(param != null)
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
			return simpleDateFormat.format(Util.nvl(param));
		}
		return null;
	}
	
	// Return string date + time format	
	public static String getFormatDateTime()
	{
		return getFormatDateTime(new Date());
	}	

	public static String getFormatDateTime(Date param)
	{
		if(param != null)
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return simpleDateFormat.format(Util.nvl(param));
		}
		return null;
	}
	
	public static String getFormatDateHour(Date param)
	{
		if(param != null)
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			return simpleDateFormat.format(Util.nvl(param));
		}
		return null;
	}
	
	// String -> Date (yyyy-MM-dd)
	public static Date getDateFromString(String paramYMD)
	{
		if(paramYMD != null)
		{
			return getDateTimeFromString(String.format("%s %s", paramYMD, "00:00:00"));
		}
		return null;
	}

	
	// String -> Date (yyyy-MM-dd HH:mm:ss)
	public static Date getDateTimeFromString(String paramYMDHMS)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
		if(Util.nvl(paramYMDHMS).equals("")) paramYMDHMS = getFormatDateTime();

		try
		{
			date = df.parse(paramYMDHMS);
		}
		catch (java.text.ParseException e)
		{
			e.printStackTrace();
		}

		return date;
	}
	
	public static int getDayOfWeek(String date) throws ParseException 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date applyDate = sdf.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(applyDate);
		int rtnVal = cal.get(Calendar.DAY_OF_WEEK);
		return rtnVal;
	}
	
	public static String getDaysAfter(int days)
	{
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate minDate = now.plusDays(days);
		return minDate.format(formatter);
	}
	
	public static List<LocalDate> getDtBetween(String start_date, String end_date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate startDt = LocalDate.parse(start_date, formatter);
		LocalDate endDt = LocalDate.parse(end_date, formatter);

		
		return getDtBetween(startDt, endDt);
	}
	public static List<LocalDate> getDtBetween(LocalDate startDate, LocalDate endDate) {
		long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);

		if(numOfDaysBetween > -1) {
			return IntStream.iterate(0, i -> i + 1)
	        	.limit(numOfDaysBetween)
	        	.mapToObj(i -> startDate.plusDays(i))
			.collect(Collectors.toList());
		}
		return null;
	}
}
