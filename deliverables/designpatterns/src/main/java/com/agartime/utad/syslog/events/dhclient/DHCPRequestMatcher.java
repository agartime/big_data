package com.agartime.utad.syslog.events.dhclient;

import com.agartime.utad.syslog.events.SyslogMatcher;

public class DHCPRequestMatcher extends SyslogMatcher {
	private final static String PATTERN="DHCPREQUEST of\\s(\\d*.\\d*.\\d*.\\d*)\\son\\s(\\w+)\\sto\\s(\\d*.\\d*.\\d*.\\d*)\\sport\\s(\\d*)\\s\\(xid=(0[xX][0-9a-fA-F]+)\\)";
	private enum TokenNumbers {DHCPREQUEST,OF,REQUESTED_IP,ON,CLIENT_INTERFACE,TO,BROADCAST_IP,PORT_STR,PORT,NULO,XID_STR,XID}

    public static String getSplitChars() {
        return "\\s|:|=|\\)|\\(";
    }

	public static boolean isDHCPRequestInfo(String line) {
		return line.matches(PATTERN);
	}
	
	public static String getRegex() {
		return PATTERN;
	}
	
	public static int getTokenNumberForRequestedIp() {
		return TokenNumbers.REQUESTED_IP.ordinal();
	}
	
	public static int getTokenNumberForClientInterface() {
		return TokenNumbers.CLIENT_INTERFACE.ordinal();
	}
	
	public static int getTokenNumberForBroadcastIp() {
		return TokenNumbers.BROADCAST_IP.ordinal();
	}
	
	public static int getTokenNumberForPort() {
		return TokenNumbers.PORT.ordinal();
	}
	
	public static int getTokenNumberForXid() {
		return TokenNumbers.XID.ordinal();
	}

}
