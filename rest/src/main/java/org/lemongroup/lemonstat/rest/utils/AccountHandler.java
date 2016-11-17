package org.lemongroup.lemonstat.rest.utils;

import org.lemongroup.lemonstat.rest.datamodel.Session;
import org.lemongroup.lemonstat.rest.db.AccountRepository;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class AccountHandler {

    private static AccountHandler instance;

    private AccountHandler() {
    }

    public static AccountHandler getInstance(){
	if(instance == null){
	    instance = new AccountHandler();
	}
	return instance;
    }
    public static boolean auth(Map<String,String> authParams) {
	AccountRepository ar = AccountRepository.getInstance();
	long uId = ar.getUserIdByUserName(authParams.get("user"));
	System.out.println("uid: " + uId);
	if(uId == 0) {
	    //No such user
	    return false;
	} else {
	    //Compare passwords
	    if(authParams.get("pass").equals(ar.getPasswordByUserId(uId))){
		return true;
	    }
	}
	return false;
    }
    public static Session startSession(String user) {
	AccountRepository ar = AccountRepository.getInstance();
	String uuid = UUID.randomUUID().toString();
	long groupId = ar.getGroupIdByUser(user);
	byte privilege = ar.getPrivilegeByUser(user);
	int idleTimeout = 300;
	return new Session(uuid,groupId,privilege,idleTimeout);
    }


}
