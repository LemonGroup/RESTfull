package org.lemongroup.lemonstat.rest.db;

import java.util.UUID;

public class FakeAccountRepository implements IAccountRepository{

    private static FakeAccountRepository instance;
    private static String FAKE_TOKEN = "this-is-fake-token";
    private static String FAKE_USER = "testuser";
    private static String FAKE_PASS = "testpass";
    private static long FAKE_UID = 1;
    private static long FAKE_GID = 3;
    private static byte FAKE_PRIV = 2;

    private FakeAccountRepository() {
    }

    public static FakeAccountRepository getInstance(){
	if(instance == null){
	    instance = new FakeAccountRepository();
	}
	return instance;
    }

    public long getUserIdByUserName(String user) {
	//TODO:Work with DB
	if(user.equals(FAKE_USER)){
	    return FAKE_UID;
	}
	return 0;
    }

    public String getPasswordByUserId(long uId) { 
	//TODO:Work with DB
	if(uId == FAKE_UID){
	    return FAKE_PASS;
	}
	return null;
    }

    public  long getGroupIdByUser(String user) { 
	//TODO:Work with DB
	if(user.equals(FAKE_USER)){
	    return FAKE_GID;
	}
	return 0;
    }

    public byte getPrivilegeByUser(String user) { 
	//TODO:Work with DB
	if(user.equals(FAKE_USER)){
	    return FAKE_PRIV;
	}
	return 0;
    }

    public String getTokenByUser(String user) { 
	//TODO:Work with DB
	if(user.equals(FAKE_USER)){
	    return FAKE_TOKEN;
	}
	return UUID.randomUUID().toString();
    }

    public long getGroupIdByToken(String token) {
	if(token.equals(FAKE_TOKEN)){
	    return FAKE_GID;
	}
	return 0;
    }
}
