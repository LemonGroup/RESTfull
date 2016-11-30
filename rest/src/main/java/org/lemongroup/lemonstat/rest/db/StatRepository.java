package org.lemongroup.lemonstat.rest.db;

import org.lemongroup.lemonstat.rest.datamodel.DailyStat;
import org.lemongroup.lemonstat.rest.datamodel.OverMentionStatItem;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 *  * Stat data repository
 *   */
@Repository
public class StatRepository implements IStatRepository{

	@Override
	public Collection getOverStatByPersonBySite(long personId, long siteId) {
		//Fake data
		List<OverMentionStatItem> list = new ArrayList<>();
			list.add(new OverMentionStatItem("Путин", 100000));
			list.add(new OverMentionStatItem("Медведев", 50000));
			list.add(new OverMentionStatItem("Навальный", 25000));
		return list;
	}

	@Override
	public Collection getDaylyStatByPersonBySiteByDay(long personId, long siteId, Date day) {
		return null;
	}
}
