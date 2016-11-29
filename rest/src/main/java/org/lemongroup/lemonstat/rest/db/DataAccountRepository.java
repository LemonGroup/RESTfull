package org.lemongroup.lemonstat.rest.db;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.lemongroup.lemonstat.rest.datamodel.Account;
import org.lemongroup.lemonstat.rest.datamodel.Group;
import org.lemongroup.lemonstat.rest.datamodel.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import java.util.UUID;

@Repository
@Transactional
public class DataAccountRepository implements IAccountRepository {

    private static DataAccountRepository instance;
    private static String FAKE_TOKEN = "this-is-fake-token";
    private static String FAKE_USER = "testuser";
    private static String FAKE_PASS = "testpass";
    private static long FAKE_UID = 1;
    private static long FAKE_GID = 3;
    private static byte FAKE_PRIV = 2;

    private SessionFactory sessionFactory;

    @Autowired
    public DataAccountRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private DataAccountRepository() {
    }


    public static DataAccountRepository getInstance() {
        if (instance == null) {
            instance = new DataAccountRepository();
        }
        return instance;
    }

    public String getPasswordByUserId(long uId) {
        //TODO:Work with DB
        if (uId == FAKE_UID) {
            return FAKE_PASS;
        }
        return null;
    }

    public long getGroupIdByUser(String user) {
        //TODO:Work with DB
        if (user.equals(FAKE_USER)) {
            return FAKE_GID;
        }
        return 0;
    }

    public byte getPrivilegeByUser(String user) {
        //TODO:Work with DB
        if (user.equals(FAKE_USER)) {
            return FAKE_PRIV;
        }
        return 0;
    }

    public String getTokenByUser(String user) {
        //TODO:Work with DB
        if (user.equals(FAKE_USER)) {
            return FAKE_TOKEN;
        }
        return UUID.randomUUID().toString();
    }

    @Override
    public long getGroupIdByToken(String token) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a.groupId from Account a, Token t " +
                "WHERE a.id = t.accountId " +
                "AND t.token = :token");
        query.setParameter("token", token);
        long result = Long.parseLong(query.uniqueResult().toString());
        return result;
    }

    @Override
    public long getUserIdByUserName(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a.id from Account a " +
                "WHERE username = :username");
        query.setParameter("username", username);
        long result = Long.parseLong(query.uniqueResult().toString());
        return result;
    }

    @Override
    public String getPasswordByUserName(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a.password from Account a " +
                "WHERE username = :username");
        query.setParameter("username", username);
        return query.uniqueResult().toString();
    }

    @Override
    public boolean checkUsernameExists(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Account where username = :username");
        query.setParameter("username", username);
        return query.list().size() != 0;
    }

    @Override
    public boolean checkEmailExists(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Account where email = :email");
        query.setParameter("email", email);
        return query.list().size() != 0;
    }

    @Override
    public Collection getAllAccountsByGroup(long groupId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Account where groupid = :groupId");
        query.setParameter("groupId", groupId);
        return query.list();
    }

    @Override
    public Account getAccountByEmail(String email) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Account.class);
        criteria.add(Restrictions.eq("email", email));
        return (Account) criteria.uniqueResult();
    }

    @Override
    public long createNewAccountByGroup(Account account, long groupId) {
        account.setGroupId(groupId);
        sessionFactory.getCurrentSession().save(account);
        return account.getId();
    }

    @Override
    public String createNewTokenForUsername(String username) {
        Token token = new Token(UUID.randomUUID().toString());
        long accountId = getUserIdByUserName(username);
        token.setAccountId(accountId);
        sessionFactory.getCurrentSession().save(token);
        return token.getToken();
    }

    @Override
    public long createNewGroup(String groupname) {
        Group group = new Group();
        group.setGroupname(groupname);
        sessionFactory.getCurrentSession().save(group);
        return group.getId();
    }

    @Override
    public boolean deleteAccountByGroup(long accountId, long groupId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE Account " +
                " WHERE id = :id " +
                "AND groupId = :groupId");
        query.setParameter("id", accountId);
        query.setParameter("groupId", groupId);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean updateAccountPrivilegeByGroup(long accountId, byte newPrivilege, long groupId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Account " +
                "SET privilege = :privilege WHERE id = :id " +
                "AND groupId = :groupId");
        query.setParameter("privilege", newPrivilege);
        query.setParameter("id", accountId);
        query.setParameter("groupId", groupId);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean updateAccountMailByGroup(long accountId, String newMail, long groupId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Account " +
                "SET email = :email WHERE id = :id " +
                "AND groupId = :groupId");
        query.setParameter("email", newMail);
        query.setParameter("id", accountId);
        query.setParameter("groupId", groupId);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean updateAccountPasswordByGroup(long accountId, String newPassword, long groupId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Account " +
                "SET password = :password WHERE id = :id " +
                "AND groupId = :groupId");
        query.setParameter("password", newPassword);
        query.setParameter("id", accountId);
        query.setParameter("groupId", groupId);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean updateAccountPassword(long accountId, String newPassword) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Account " +
                "SET password = :password WHERE id = :id ");
        query.setParameter("password", newPassword);
        query.setParameter("id", accountId);
        int result = query.executeUpdate();
        return result == 1;
    }
}
