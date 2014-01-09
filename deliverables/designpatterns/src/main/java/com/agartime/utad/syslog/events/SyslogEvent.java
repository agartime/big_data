package com.agartime.utad.syslog.events;

import java.util.Date;

import com.agartime.utad.writables.DateTimeWritable;
import com.agartime.utad.util.DateUtils;

/**
 * Created by antoniogonzalezartime on 30/12/13.
 */
public abstract class SyslogEvent {

    //protected final String[] tokens;
    
    private String application;
	private Date date;
    private String hostname;
    private String rest;

    public SyslogEvent(String line) throws Exception{
    	if (!SyslogMatcher.isSyslog(line)) throw new Exception("Not a Syslog Event");
    	
    	String [] tokens = line.split(SyslogMatcher.getSplitChars());
    	this.date = DateUtils.getDate(tokens[SyslogMatcher.getTokenNumberForMonth()],
                    tokens[SyslogMatcher.getTokenNumberForDay()],
                    tokens[SyslogMatcher.getTokenNumberForHour()],
                    tokens[SyslogMatcher.getTokenNumberForMinute()],
                    tokens[SyslogMatcher.getTokenNumberForSecond()]);
    	this.hostname = tokens[SyslogMatcher.getTokenNumberForHostname()];
    	this.application = tokens[SyslogMatcher.getTokenNumberForApplication()];
    	this.rest = line.substring((line.indexOf(this.application))+this.application.length()+2); //+2 = ": "
    }

    public String getApplication() {
		return application;
	}

	public Date getDate() {
		return date;
	}

	public String getHostname() {
		return hostname;
	}

	public String getRest() {
		return rest;
	}

    public DateTimeWritable getDateTimeWritable() {
        return DateUtils.getDateTimeWritable(this.date);
    }

    public abstract String getInfo();
}
