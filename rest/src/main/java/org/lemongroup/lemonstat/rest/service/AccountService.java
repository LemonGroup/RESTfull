package org.lemongroup.lemonstat.rest.service;

import org.lemongroup.lemonstat.rest.db.IAccountRepository;
import org.lemongroup.lemonstat.rest.datamodel.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccountService {

    @Autowired
    IAccountRepository iAccountRepository;

    public Collection getAllAccountsByGroup(long groupId){
       return iAccountRepository.getAllAccountsByGroup(groupId);
    }

    public long createNewAccountByGroup(Account account, long groupId){
       return iAccountRepository.createNewAccountByGroup(account,groupId);
    }

    public boolean updateAccountPrivilegeByGroup(long personId, byte newPrivilege, long groupId) {
        return iAccountRepository.updateAccountPrivilegeByGroup(personId,newPrivilege,groupId);
    }

    public boolean updateAccountMailByGroup(long personId, String newMail, long groupId) {
        return iAccountRepository.updateAccountMailByGroup(personId,newMail,groupId);
    }

    public boolean updateAccountPasswordByGroup(long personId, String newPassword, long groupId) {
        return iAccountRepository.updateAccountPasswordByGroup(personId,newPassword,groupId);
    }

    public boolean deleteAccountByGroup(long accountId, long groupId) {
        return iAccountRepository.deleteAccountByGroup(accountId,groupId);
    }

    public boolean checkUsernameExists(String username) {
        return iAccountRepository.checkUsernameExists(username);
    }

    public boolean checkEmailExists(String email) {
        return iAccountRepository.checkEmailExists(email);
    }

    public long createNewGroup(String groupname) {
        return iAccountRepository.createNewGroup(groupname);
    }

    public long getUserIdByUserName(String username) {
        return iAccountRepository.getUserIdByUserName(username);
    }

    public String getPasswordByUserName(String username) {
        return iAccountRepository.getPasswordByUserName(username);
    }

    public String createNewTokenForUsername(String username) {
        return iAccountRepository.createNewTokenForUsername(username);
    }

    public long getGroupIdByToken(String token) {
        return iAccountRepository.getGroupIdByToken(token);
    }
}
