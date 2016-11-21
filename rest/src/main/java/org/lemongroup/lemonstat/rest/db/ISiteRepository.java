package org.lemongroup.lemonstat.rest.db;

public interface ISiteRepository {

    public long createNewSiteByGroup(String siteName, long groupId);
}
