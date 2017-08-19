package synergy.ps.vansalesapp.utils;

/*
 * $Id$
 *
 * Copyright (C) 2006 Josh Guilfoyle <jasta@devtcg.org>
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * Code lifted from Informa <http://informa.sourceforge.net>.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Utility class for formatting and parsing the various date formats we expect
 * to encounter.
 */
public class DateUtil {

	public static String formatDateForRequest(String date) {
		String dateStr="";
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date selectedDate = null;
		try {
			selectedDate = format.parse(date);
		} catch (android.net.ParseException e) {

		} catch (ParseException e) {

		} catch (Exception e) {
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ",Locale.ENGLISH);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		if(selectedDate != null)
			dateStr = sdf.format(selectedDate);
		return dateStr;
	}

	public static String formatProfileDateForViews(String date) {
		String dateStr="";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",Locale.ENGLISH);
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date selectedDate = null;
		try {
			selectedDate = format.parse(date);
		} catch (android.net.ParseException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		if(selectedDate != null)
			dateStr = sdf.format(selectedDate);
		return dateStr;
	}
	public static String formatProfileDateAsServerFormat(String date) {
		String dateStr="";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ",Locale.ENGLISH);
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date selectedDate = null;
		try {
			selectedDate = format.parse(date);
		} catch (android.net.ParseException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",Locale.ENGLISH);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		if(selectedDate != null)
			dateStr = sdf.format(selectedDate);
		return dateStr;
	}

	public static String formatChildDateForViews(String date) {
		String dateStr="";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ",Locale.ENGLISH);
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date selectedDate = null;
		try {
			selectedDate = format.parse(date);
		} catch (android.net.ParseException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		if(selectedDate != null)
			dateStr = sdf.format(selectedDate);
		return dateStr;
	}


	//used to fprmat date to string
	public static String getDateStringFromDate(Date date) {
		if(date == null) {
			return "";
		}
		Locale  locale = new Locale("en");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy",locale);
		return dateFormat.format(date);
	}

	public static Date parseDateFromString(String dateString) {
		Date dateFromString = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			dateFromString = formatter.parse(dateString);//catch exception
		} catch (android.net.ParseException e) {
			// TODO Auto-generated catch block

		} catch (ParseException e) {
			// TODO Auto-generated catch block

		} catch (Exception e) {
			// TODO: handle exception
		}

		return dateFromString;
	}
}
