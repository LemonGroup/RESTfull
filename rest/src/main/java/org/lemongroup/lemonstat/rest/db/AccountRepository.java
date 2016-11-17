package org.lemongroup.lemonstat.rest.db;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class AccountRepository {

    private static AccountRepository instance;
    private static String FAKE_USER = "testuser";
    private static String FAKE_PASS = "testpass";
    private static long FAKE_UID = 1;
    private static long FAKE_GID = 3;
    private static byte FAKE_PRIV = 2;

    private AccountRepository() {
    }

    public static AccountRepository getInstance(){
	if(instance == null){
	    instance = new AccountRepository();
	}
	return instance;
    }

    public static long getUserIdByUserName(String user) {
	//TODO:Work with DB
	if(user.equals(FAKE_USER)){
	    return FAKE_UID;
	}
	return 0;
    }

    public static String getPasswordByUserId(long uId) { 
	//TODO:Work with DB
	if(uId == FAKE_UID){
	    return FAKE_PASS;
	}
	return null;
    }

    public static long getGroupIdByUser(String user) { 
	//TODO:Work with DB
	if(user.equals(FAKE_USER)){
	    return FAKE_GID;
	}
	return 0;
    }

    public static byte getPrivilegeByUser(String user) { 
	//TODO:Work with DB
	if(user.equals(FAKE_USER)){
	    return FAKE_PRIV;
	}
	return 0;
    }
}
