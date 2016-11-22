package org.lemongroup.lemonstat.rest.db;

public class FakeSiteRepository implements ISiteRepository {

    private static FakeSiteRepository instance;

    public FakeSiteRepository() {
    }

    public static FakeSiteRepository getInstance() {
        if (instance == null) {
            return new FakeSiteRepository();
        }
        return instance;
    }

    @Override
    public long createNewSiteByGroup(String site, long groupId) {
        return 670;
    }

    @Override
    public boolean updateSiteByGroup(long siteId, String newSite, long groupId) {
	return true;
    }

    @Override
    public boolean deleteSiteByGroup(long siteId, long groupId) {
	return true;
    }
    
}
