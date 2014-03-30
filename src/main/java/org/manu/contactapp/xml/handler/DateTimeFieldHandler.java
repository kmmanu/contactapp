package org.manu.contactapp.xml.handler;

import java.util.Properties;

import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.exolab.castor.mapping.ValidityException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeFieldHandler extends GeneralizedFieldHandler {
	private static String dateFormatPattern;

	@Override
	public void setConfiguration(final Properties config)
			throws ValidityException {
		dateFormatPattern = config.getProperty("date-format");
	}

	@Override
	public Object convertUponGet(final Object value) {
		final DateTime dateTime = (DateTime) value;
		return format(dateTime);
	}

	@Override
	public Object convertUponSet(final Object value) {
		final String dateTimeString = (String) value;
		return parse(dateTimeString);
	}

	@Override
	public Class<DateTime> getFieldType() {
		return DateTime.class;
	}

	private String format(final DateTime dateTime) {
		String dateTimeString = "";
		if (dateTime != null) {
			final DateTimeFormatter dateTimeFormatter = DateTimeFormat
					.forPattern(dateFormatPattern);
			dateTimeString = dateTimeFormatter.print(dateTime);
		}
		return dateTimeString;
	}

	private DateTime parse(final String dateTimeString) {
		DateTime dateTime = new DateTime();
		if (dateTimeString != null) {
			final DateTimeFormatter dateTimeFormatter = DateTimeFormat
					.forPattern(dateFormatPattern);
			dateTime = dateTimeFormatter.parseDateTime(dateTimeString);
		}
		return dateTime;
	}
}
