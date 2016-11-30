package org.lemongroup.lemonstat.rest.db;

import java.util.Date;

public interface IStatRepository {

    public Long getOverStatByPersonBySite(long personId, long siteId);

    public Long getDaylyStatByPersonBySiteByDay(long personId, long siteId, Date day);
}
