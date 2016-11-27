package org.lemongroup.lemonstat.rest.db;

import org.lemongroup.lemonstat.rest.datamodel.Account;
import java.util.Collection;

public interface IAccountRepository {
    public long getUserIdByUserName(String user);
    public String getPasswordByUserId(long uId); 
    public String getPasswordByUserName(String username); 
    public long getGroupIdByUser(String user);
    public byte getPrivilegeByUser(String user); 
    public String getTokenByUser(String user);
    public long getGroupIdByToken(String token);
    public Collection getAllAccountsByGroup(long groupId);
    public long createNewAccountByGroup(Account account, long groupId);
    public long createNewGroup(String groupname);
    public boolean deleteAccountByGroup(long accountId, long groupId);
    public boolean updateAccountPrivilegeByGroup(long personId, byte newPrivilege, long groupId);
    public boolean updateAccountMailByGroup(long personId, String newMail, long groupId);
    public boolean updateAccountPasswordByGroup(long personId, String newPassword, long groupId);
    public boolean checkUsernameExists(String username);
    public boolean checkEmailExists(String email);
    public String createNewTokenForUsername(String username);
}
