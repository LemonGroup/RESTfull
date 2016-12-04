package org.lemongroup.lemonstat.rest.db;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lemongroup.lemonstat.rest.config.DatabaseConfig;
import org.lemongroup.lemonstat.rest.datamodel.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {DatabaseConfig.class})
public class DataSiteRepositoryTest {

    @Autowired
    private ISiteRepository dataSiteRepository;

    @Test
    @Transactional
    @Rollback
    public void createNewSiteByGroupTest() throws Exception {
        Site site = new Site("yandex.ru", 777);
        dataSiteRepository.createNewSiteByGroup(site.getSite(), site.getGroupId());
        Site returnSite = dataSiteRepository.getSiteByGroupIdAndSiteName(site.getSite(), site.getGroupId());
        Assert.assertEquals(site, returnSite);
    }

}