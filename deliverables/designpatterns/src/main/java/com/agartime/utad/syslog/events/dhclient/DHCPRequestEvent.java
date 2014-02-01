package com.agartime.utad.syslog.events.dhclient;

import com.agartime.utad.syslog.events.SyslogEvent;
import com.agartime.utad.syslog.events.SyslogMatcher;

public class DHCPRequestEvent extends SyslogEvent {
	private String requestedIpAddress;
	private String clientInterface;
	private String broadcastIpAddress;
	private int portNumber;
	private String xid;
	
	public DHCPRequestEvent(String info) throws Exception {
		super(info);
    	if (!DHCPRequestMatcher.isDHCPRequestInfo(super.getRest())) return;

    	String [] tokens = super.getRest().split(DHCPRequestMatcher.getSplitChars());
    	this.requestedIpAddress = tokens[DHCPRequestMatcher.getTokenNumberForRequestedIp()];
    	this.clientInterface = tokens[DHCPRequestMatcher.getTokenNumberForClientInterface()];
    	this.broadcastIpAddress = tokens[DHCPRequestMatcher.getTokenNumberForBroadcastIp()];
    	this.portNumber = Integer.parseInt(tokens[DHCPRequestMatcher.getTokenNumberForPort()]);
    	this.xid = tokens[DHCPRequestMatcher.getTokenNumberForXid()];

	}
	
	public String getRequestedIpAddress() {
		return requestedIpAddress;
	}

	public String getClientInterface() {
		return clientInterface;
	}

	public String getBroadcastIpAddress() {
		return broadcastIpAddress;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public String getXid() {
		return xid;
	}
	
	@Override
	public String getInfo() {
		return this.requestedIpAddress;
	}

}
