package org.lemongroup.lemonstat.rest.db;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 *  * Stat data repository
 *   */
@Repository
@Transactional
public class DataStatRepository implements IStatRepository{

	private SessionFactory sessionFactory;

	@Autowired
	public DataStatRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Long getOverStatByPersonBySite(long personId, long siteId) throws NullPointerException {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select sum(ppr.rank) from PersonPageRank ppr " +
						"where pageid in " +
						"(select p.id from Page p where siteid = :siteId) and personid = :personId");
		query.setParameter("personId", personId);
		query.setParameter("siteId", siteId);
		return (Long) query.uniqueResult();
	}

	@Override
	public Collection getDaylyStatByPersonBySiteByDay(long personId, long siteId, Date day) {
		return null;
	}
}
