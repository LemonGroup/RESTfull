package org.lemongroup.lemonstat.rest.db;

public interface ISiteRepository {

    public long createNewSiteByGroup(String site, long groupId);
    public boolean updateSiteByGroup(long siteId, String newSite, long groupId);
    public boolean deleteSiteByGroup(long siteId, long groupId);
}
