package org.lemongroup.lemonstat.rest.service;

import org.lemongroup.lemonstat.rest.db.IStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class StatService {

    @Autowired
    IStatRepository iStatRepository;

    public Long getOverStatByPersonBySite(long personId, long siteId){
        return iStatRepository.getOverStatByPersonBySite(personId, siteId);
    }

    public Long getDaylyStatByPersonBySiteByDay(long personId, long siteId, Date day){
        return iStatRepository.getDaylyStatByPersonBySiteByDay(personId, siteId, day);
    }
}
