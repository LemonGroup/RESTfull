package org.lemongroup.lemonstat.rest.db;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.lemongroup.lemonstat.rest.datamodel.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
@Transactional
public class DataSiteRepository implements ISiteRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public DataSiteRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Collection getAllSites(long groupId) {
	Session session = sessionFactory.getCurrentSession();
	Query query = session.createQuery("from Site where groupid = :groupId");
	query.setParameter("groupId", groupId);
	return query.list();
    }

    @Override
    public long createNewSiteByGroup(String site, long groupId) {
        Site siteDTO = new Site(site,groupId);
        sessionFactory.getCurrentSession().save(siteDTO);
        return siteDTO.getId();
    }

    @Override
    public boolean updateSiteByGroup(long siteId, String newSite, long groupId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE org.lemongroup.lemonstat.rest.datamodel.Site " +
                "SET site = :site WHERE id = :id " +
                "AND groupId = :groupId");
        query.setParameter("site", newSite);
        query.setParameter("id", siteId);
        query.setParameter("groupId", groupId);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean deleteSiteByGroup(long siteId, long groupId) {
        Session session =  sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE org.lemongroup.lemonstat.rest.datamodel.Site " +
                " WHERE id = :id " +
                "AND groupId = :groupId");
        query.setParameter("id", siteId);
        query.setParameter("groupId", groupId);
        int result = query.executeUpdate();
        return result == 1;
    }
}
