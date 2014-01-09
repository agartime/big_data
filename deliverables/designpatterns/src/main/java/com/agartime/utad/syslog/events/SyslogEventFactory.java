package com.agartime.utad.syslog.events;

import com.agartime.utad.syslog.events.dhclient.DHCPRequestEvent;
import org.apache.hadoop.io.Text;

/**
 * Created by antoniogonzalezartime on 30/12/13.
 * SysLogEventFactory parses syslog lines to known events.
 */
public class SyslogEventFactory {

	
    public static SyslogEvent getEvent(Text fullEvent) {
    	String application = SyslogEventFactory.getApplication(fullEvent.toString());
    	if (application == null) return null;

    	if (application.equals("dhclient")) {
    		// For now, we only have DHCPRequestEvent's, so we try to parse it to it.
    		// Here we could add more events for dhclient.
			try {
				DHCPRequestEvent event = new DHCPRequestEvent(fullEvent.toString());
				if (event.getInfo() != null)
                    return event;
			} catch (Exception e) {
				e.printStackTrace();
			}
       	}
    	return null;
    }
    
    private static String getApplication(String line) {
    	if (!SyslogMatcher.isSyslog(line)) return null;	
    	
    	String [] tokens = line.split(SyslogMatcher.getSplitChars());
    	if (tokens.length < SyslogMatcher.getTokenNumberForApplication()) return null;
    	return tokens[SyslogMatcher.getTokenNumberForApplication()];
    }
}
