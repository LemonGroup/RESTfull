package org.lemongroup.lemonstat.rest.service;

import org.lemongroup.lemonstat.rest.datamodel.Keyword;
import org.lemongroup.lemonstat.rest.db.IKeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeywordService {

    private IKeywordRepository keywordRepository;

    @Autowired
    public KeywordService (IKeywordRepository keywordRepository){
        this.keywordRepository = keywordRepository;
    }

    public long createNewKeywordByGroup(long personId, String keyword, long groupId) {
      return keywordRepository.createNewKeywordByGroup(personId,keyword,groupId);
    }

    public boolean updateKeywordByGroup(long keywordId, String newKeyword, long groupId) {
        return keywordRepository.updateKeywordByGroup(keywordId,newKeyword,groupId);
    }

    public boolean deleteKeywordByGroup(long keywordId, long groupId){
        return keywordRepository.deleteKeywordByGroup(keywordId,groupId);
    }

}
