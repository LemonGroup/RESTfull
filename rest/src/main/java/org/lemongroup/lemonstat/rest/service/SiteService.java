package org.lemongroup.lemonstat.rest.service;

import org.lemongroup.lemonstat.rest.db.IPersonRepository;
import org.lemongroup.lemonstat.rest.db.ISiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SiteService {

    @Autowired
    ISiteRepository iSiteRepository;

    public Collection getAllSites(long groupId){
        return iSiteRepository.getAllSites(groupId);
    }

    public long createNewSiteByGroup(String site, long groupId){
        return iSiteRepository.createNewSiteByGroup(site, groupId);
    }

    public boolean updateSiteByGroup(long siteId, String newSite, long groupId) {
        return iSiteRepository.updateSiteByGroup(siteId, newSite, groupId);
    }

    public boolean deleteSiteByGroup(long siteId, long groupId) {
        return iSiteRepository.deleteSiteByGroup(siteId, groupId);
    }
}
