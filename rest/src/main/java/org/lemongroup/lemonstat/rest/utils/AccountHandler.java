package org.lemongroup.lemonstat.rest.utils;

import org.lemongroup.lemonstat.rest.datamodel.AuthResponse;
import org.lemongroup.lemonstat.rest.db.DataAccountRepository;
import org.lemongroup.lemonstat.rest.db.IAccountRepository;

import java.util.Map;

public class AccountHandler {

    private static AccountHandler instance;
    private static IAccountRepository accountRepo;

    private AccountHandler() {
    }

    public static AccountHandler getInstance() {
        if (instance == null) {
            instance = new AccountHandler();
            accountRepo = DataAccountRepository.getInstance();
        }
        return instance;
    }

    public boolean auth(Map<String, String> authParams) {
        long uId = accountRepo.getUserIdByUserName(authParams.get("user"));
        System.out.println("uid: " + uId);
        if (uId == 0) {
            //No such user
            return false;
        } else {
            //Compare passwords
            if (authParams.get("pass").equals(accountRepo.getPasswordByUserId(uId))) {
                return true;
            }
        }
        return false;
    }

    public AuthResponse getAuthResponse(String user) {
        String token = accountRepo.getTokenByUser(user);
        long groupId = accountRepo.getGroupIdByUser(user);
        byte privilege = accountRepo.getPrivilegeByUser(user);
        return new AuthResponse(token, groupId, privilege);
    }

    public long getGroupIdByToken(String token) {
        return accountRepo.getGroupIdByToken(token);
    }
}
