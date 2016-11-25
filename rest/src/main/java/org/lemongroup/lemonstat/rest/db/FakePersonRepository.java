package org.lemongroup.lemonstat.rest.db;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.lemongroup.lemonstat.rest.datamodel.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
@Transactional
public class FakePersonRepository implements IPersonRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public FakePersonRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Collection getAllPersonsByGroup(long groupId) {
        Session session = sessionFactory.getCurrentSession();
	return session.createQuery("from org.lemongroup.lemonstat.rest.datamodel.PersonDTO").list();
    }

    @Override
    public long createNewPersonByGroup(String personName, long groupId) {
        PersonDTO personDTO = new PersonDTO(personName,groupId);
        sessionFactory.getCurrentSession().save(personDTO);
        return personDTO.getId();
    }

    @Override
    public boolean updatePersonByGroup(long personId, String newPersonName, long groupId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE org.lemongroup.lemonstat.rest.datamodel.PersonDTO " +
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
        Query query = session.createQuery("DELETE org.lemongroup.lemonstat.rest.datamodel.PersonDTO " +
                " WHERE id = :id " +
                "AND groupId = :groupId");
        query.setParameter("id", personId);
        query.setParameter("groupId", groupId);
        int result = query.executeUpdate();
        return result == 1;
    }
}
