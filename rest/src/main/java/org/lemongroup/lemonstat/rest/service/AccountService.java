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
/*
    public boolean updateAccountByGroup(long personId, String newAccountName, long groupId) {
        return iAccountRepository.updateAccountByGroup(personId,newAccountName,groupId);
    }
*/
    public boolean deleteAccountByGroup(long accountId, long groupId) {
        return iAccountRepository.deleteAccountByGroup(accountId,groupId);
    }
}
