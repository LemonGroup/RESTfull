package org.lemongroup.lemonstat.rest.db;

public interface IAccountRepository {
    public long getUserIdByUserName(String user);
    public String getPasswordByUserId(long uId); 
    public long getGroupIdByUser(String user);
    public byte getPrivilegeByUser(String user); 
}
