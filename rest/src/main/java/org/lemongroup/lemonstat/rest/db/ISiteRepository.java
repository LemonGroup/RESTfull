package org.lemongroup.lemonstat.rest.db;

public interface ISiteRepository {

    public long createNewSiteByGroup(String site, long groupId);
}
