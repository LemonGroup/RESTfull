package org.lemongroup.lemonstat.rest.db;

import org.lemongroup.lemonstat.rest.datamodel.Keyword;

import java.util.List;

public interface IKeywordRepository {

    public long createNewKeywordByGroup(long personId, String keyword, long groupId);
    public boolean updateKeywordByGroup(long keywordId, String newKeyword, long groupId);
    public boolean deleteKeywordByGroup(long keywordId, long groupId);
    public List<Keyword> getAllKeywordsByGroup(long groupId);
}
