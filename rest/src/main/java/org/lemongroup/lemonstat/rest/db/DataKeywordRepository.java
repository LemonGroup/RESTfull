package org.lemongroup.lemonstat.rest.db;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.lemongroup.lemonstat.rest.datamodel.Keyword;
import org.lemongroup.lemonstat.rest.datamodel.Person;
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
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from org.lemongroup.lemonstat.rest.datamodel.Person p WHERE id = :id ");
        query.setParameter("id", personId);
        Person person = (Person) query.uniqueResult();

        if (person.getGroupId() == groupId) {
            Keyword keyword1 = new Keyword();
            keyword1.setKeyword(keyword);
            keyword1.setPersonId(personId);
            session.save(keyword1);
            return keyword1.getId();
        } else {
            return 0;
        }
    }

    @Override
    public boolean updateKeywordByGroup(long keywordId, String newKeyword, long groupId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE org.lemongroup.lemonstat.rest.datamodel.Keyword SET keyword = :keyword WHERE id = :id " +
                "AND personId in (SELECT id FROM org.lemongroup.lemonstat.rest.datamodel.Person WHERE groupId = :groupId)");
        query.setParameter("id", keywordId);
        query.setParameter("keyword", newKeyword);
        query.setParameter("groupId", groupId);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean deleteKeywordByGroup(long keywordId, long groupId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE org.lemongroup.lemonstat.rest.datamodel.Keyword WHERE id = :id " +
                "AND personId in (SELECT id FROM org.lemongroup.lemonstat.rest.datamodel.Person WHERE groupId = :groupId)");
        query.setParameter("id", keywordId);
        query.setParameter("groupId", groupId);
        int result = query.executeUpdate();
        return result == 1;
    }
}
