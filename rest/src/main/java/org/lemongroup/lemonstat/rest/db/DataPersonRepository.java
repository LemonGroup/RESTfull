package org.lemongroup.lemonstat.rest.db;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.lemongroup.lemonstat.rest.datamodel.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
@Transactional
public class DataPersonRepository implements IPersonRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public DataPersonRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Collection getAllPersonsByGroup(long groupId) {
        Session session = sessionFactory.getCurrentSession();
	Query query = session.createQuery("from Person where groupid = :groupId");
	query.setParameter("groupId", groupId);
	return query.list();
    }

    @Override
    public long createNewPersonByGroup(String personName, long groupId) {
        Person person = new Person(personName,groupId);
        sessionFactory.getCurrentSession().save(person);
        return person.getId();
    }

    @Override
    public boolean updatePersonByGroup(long personId, String newPersonName, long groupId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE org.lemongroup.lemonstat.rest.datamodel.Person " +
                "SET personName = :person WHERE id = :id " +
                "AND groupId = :groupId");
        query.setParameter("person", newPersonName);
        query.setParameter("id", personId);
        query.setParameter("groupId", groupId);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean deletePersonByGroup(long personId, long groupId) {

        Session session =  sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE org.lemongroup.lemonstat.rest.datamodel.Person " +
                " WHERE id = :id " +
                "AND groupId = :groupId");
        query.setParameter("id", personId);
        query.setParameter("groupId", groupId);
        int result = query.executeUpdate();
        return result == 1;
    }
}
