package org.lemongroup.lemonstat.rest.db;

import org.lemongroup.lemonstat.rest.datamodel.Site;

import java.util.Collection;

public interface ISiteRepository {

    public Collection getAllSites(long groupId);
    public long createNewSiteByGroup(String site, long groupId);
    public boolean updateSiteByGroup(long siteId, String newSite, long groupId);
    public boolean deleteSiteByGroup(long siteId, long groupId);
    public Site getSiteByGroupIdAndSiteName (String site, long groupId);
}
