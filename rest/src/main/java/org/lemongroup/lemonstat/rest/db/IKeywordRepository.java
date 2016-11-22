package org.lemongroup.lemonstat.rest.db;

public interface IKeywordRepository {

    public long createNewKeywordByGroup(long personId, String keyword, long groupId);
}
