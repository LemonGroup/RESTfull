package org.lemongroup.lemonstat.rest.db;

public class FakePersonRepository implements IPersonRepository {

    private static FakePersonRepository instance;

    private FakePersonRepository() {
    }

    public static FakePersonRepository getInstance() {
        if (instance == null) {
            return new FakePersonRepository();
        }
        return instance;
    }

    @Override
    public long createNewPersonByGroup(String personName, long groupId) {
//	System.out.println("Message from FakePersonRepository: newPerson " + personName + " GroupId " + groupId);
        return 345;
    }
    @Override
    public boolean updatePersonByGroup(long personId, String newPersonName, long groupId) {
        return true;
    }
    @Override
    public boolean deletePersonByGroup(long personId, long groupId) {
        return true;
    }
}
