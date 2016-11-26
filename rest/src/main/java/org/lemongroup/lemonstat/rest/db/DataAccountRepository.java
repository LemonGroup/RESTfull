package org.lemongroup.lemonstat.rest.db;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.lemongroup.lemonstat.rest.datamodel.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import java.util.UUID;

@Repository
@Transactional
public class DataAccountRepository implements IAccountRepository{

    private static DataAccountRepository instance;
    private static String FAKE_TOKEN = "this-is-fake-token";
    private static String FAKE_USER = "testuser";
    private static String FAKE_PASS = "testpass";
    private static long FAKE_UID = 1;
    private static long FAKE_GID = 3;
    private static byte FAKE_PRIV = 2;

    private SessionFactory sessionFactory;

    @Autowired
    public DataAccountRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private DataAccountRepository(){
    }


    public static DataAccountRepository getInstance(){
	if(instance == null){
	    instance = new DataAccountRepository();
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

    @Override
    public Collection getAllAccountsByGroup(long groupId){
        Session session = sessionFactory.getCurrentSession();
	Query query = session.createQuery("from Account where groupid = :groupId");
	query.setParameter("groupId", groupId);
	return query.list();
    }

    @Override
    public long  createNewAccountByGroup(Account account, long groupId){
	Session session = sessionFactory.getCurrentSession();
	account.setGroupId(groupId);
	sessionFactory.getCurrentSession().save(account);
	return account.getId();
    }

    public boolean deleteAccountByGroup(long accountId, long groupId) {
	return true;
    }
}
