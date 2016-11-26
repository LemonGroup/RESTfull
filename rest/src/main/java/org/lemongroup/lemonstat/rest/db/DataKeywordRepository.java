package org.lemongroup.lemonstat.rest.db;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.lemongroup.lemonstat.rest.datamodel.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;

@Repository
@Transactional
public class DataKeywordRepository implements IKeywordRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Collection getAllKeywordsByGroup(long groupId) {
	Session session = sessionFactory.getCurrentSession();
	Query query = session.createQuery("from Keyword " +
		"where personid in " + 
		"(select p.id from Person p where groupid = :groupId)");
	query.setParameter("groupId", groupId);
	return query.list();
    }

    @Override
    public long createNewKeywordByGroup(long personId, String keyword, long groupId) {
        Keyword keyword1 = new Keyword();
        keyword1.setKeyword(keyword);
        keyword1.setPersonId(personId);
        sessionFactory.getCurrentSession().save(keyword1);
        return keyword1.getId();
    }

    @Override
    public boolean updateKeywordByGroup(long keywordId, String newKeyword, long groupId) {
        Session session =  sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE org.lemongroup.lemonstat.rest.datamodel.Keyword SET keyword = :keyword WHERE id = :id");
        query.setParameter("keyword", newKeyword);
        query.setParameter("id", keywordId);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean deleteKeywordByGroup(long keywordId, long groupId){
        Session session =  sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE org.lemongroup.lemonstat.rest.datamodel.Keyword  WHERE id = :id");
        query.setParameter("id", keywordId);
        int result = query.executeUpdate();
        return result == 1;
    }

}
