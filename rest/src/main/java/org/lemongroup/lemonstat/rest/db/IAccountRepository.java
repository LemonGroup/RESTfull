package org.lemongroup.lemonstat.rest.db;

import org.lemongroup.lemonstat.rest.datamodel.Account;
import java.util.Collection;

public interface IAccountRepository {
    public long getUserIdByUserName(String user);
    public String getPasswordByUserId(long uId); 
    public long getGroupIdByUser(String user);
    public byte getPrivilegeByUser(String user); 
    public String getTokenByUser(String user);
    public long getGroupIdByToken(String token);
    public Collection getAllAccountsByGroup(long groupId);
    public long createNewAccountByGroup(Account account, long groupId);
    public boolean deleteAccountByGroup(long accountId, long groupId);
}
