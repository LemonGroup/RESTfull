package org.lemongroup.lemonstat.rest.db;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.lemongroup.lemonstat.rest.datamodel.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class DataTokenRepository implements ITokenRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public DataTokenRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long getAccountIdByToken(String token) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Token.class);
        criteria.add(Restrictions.eq("token", token));
        Token result = (Token) criteria.uniqueResult();
        return result.getAccountId();
    }
}
