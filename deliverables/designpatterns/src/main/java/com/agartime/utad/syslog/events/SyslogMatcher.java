package com.agartime.utad.syslog.events;

public class SyslogMatcher {
	private final static String STANDARD_PATTERN="(\\w*)\\s(\\d*)\\s(\\d*):(\\d*):(\\d*)\\s(\\w*)\\s*(\\w*|\\D*\\[\\d*]):(\\s.*)";
	private enum TokenNumbers {MONTH,DAY,HOUR,MINUTE,SECOND,HOSTNAME,APPLICATION}
	
	public static boolean isSyslog(String line) {
		return line.matches(STANDARD_PATTERN);
	}

    public static String getSplitChars() {
        return "\\s|:";
    }

    public static String getRegex() {
		return STANDARD_PATTERN;
	}
	
	public static int getTokenNumberForMonth() {
		return TokenNumbers.MONTH.ordinal();
	}
	
	public static int getTokenNumberForDay() {
		return TokenNumbers.DAY.ordinal();
	}
	
	public static int getTokenNumberForHour() {
		return TokenNumbers.HOUR.ordinal();
	}
	
	public static int getTokenNumberForMinute() {
		return TokenNumbers.MINUTE.ordinal();
	}
	
	public static int getTokenNumberForSecond() {
		return TokenNumbers.SECOND.ordinal();
	}
	
	public static int getTokenNumberForHostname() {
		return TokenNumbers.HOSTNAME.ordinal();
	}
	
	public static int getTokenNumberForApplication() {
		return TokenNumbers.APPLICATION.ordinal();
	}
}
