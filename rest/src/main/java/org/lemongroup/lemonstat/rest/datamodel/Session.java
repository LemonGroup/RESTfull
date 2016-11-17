package org.lemongroup.lemonstat.rest.datamodel;

public class Session {

    private String sessionUuid;
    private long groupId;
    private byte privilege;
    private int idleTimeout;

    public Session(){
    }
    public Session(String sessionUuid, long groupId, byte privilege, int idleTimeout){

	this.sessionUuid = sessionUuid;
	this.groupId = groupId;
	this.privilege = privilege;
	this.idleTimeout = idleTimeout;
    }
    public String getSessionUuid() {
        return sessionUuid;
    }

    public void setSessionUuid(String sessionUuid) {
        this.sessionUuid = sessionUuid;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public byte getPrivilege() {
        return privilege;
    }

    public void setPrivilege(byte privilege) {
        this.privilege = privilege;
    }

    public int getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout (int idleTimeout) {
        this.idleTimeout = idleTimeout;
    }
 }
