package org.lemongroup.lemonstat.rest.db;

public class FakePersonRepository implements IPersonRepository {

    private static FakePersonRepository instance;
    private FakePersonRepository() {
    }

    public static FakePersonRepository getInstance(){
	if(instance == null){
	    return new FakePersonRepository();
	}
	return instance;
    }

    public long createNewPersonByGroup(String personName, long groupId){
//	System.out.println("Message from FakePersonRepository: newPerson " + personName + " GroupId " + groupId);
	return 345;
    }
}
