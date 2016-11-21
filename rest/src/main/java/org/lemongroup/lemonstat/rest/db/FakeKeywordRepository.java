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
    public long createNewKeywordByGroup(long personId, String keywordName, long groupId) {
        return 150;
    }
}
