package org.lemongroup.lemonstat.rest.datamodel;

public class AuthResponse {

    private String token;
    private long groupId;
    private byte privilege;

    public AuthResponse(){
    }
    public AuthResponse(String token, long groupId, byte privilege){

	this.token = token;
	this.groupId = groupId;
	this.privilege = privilege;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
 }
