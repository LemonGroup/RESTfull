package org.lemongroup.lemonstat.rest.db;

public interface IKeywordRepository {

    public long createNewKeywordByGroup(long personId, String keywordName, long groupId);
}
