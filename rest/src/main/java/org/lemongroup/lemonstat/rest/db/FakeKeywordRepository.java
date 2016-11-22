package org.lemongroup.lemonstat.rest.db;

public class FakeKeywordRepository implements IKeywordRepository {

    private static FakeKeywordRepository instance;

    public FakeKeywordRepository() {
    }

    public static FakeKeywordRepository getInstance() {
        if (instance == null) {
            return new FakeKeywordRepository();
        }
        return instance;
    }

    @Override
    public long createNewKeywordByGroup(long personId, String keyword, long groupId) {
        return 150;
    }

    @Override
    public boolean updateKeywordByGroup(long keywordId, String newKeyword, long groupId) {
	return true;
    }

    @Override
    public boolean deleteKeywordByGroup(long keywordId, long groupId){
	return true;
    }
}
