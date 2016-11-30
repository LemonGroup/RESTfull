package org.lemongroup.lemonstat.rest.db;

import java.util.Collection;
import java.util.Date;

public interface IStatRepository {

    public Collection getOverStatByPersonBySite(long personId, long siteId);

    public Collection getDaylyStatByPersonBySiteByDay(long personId, long siteId, Date day);
}
