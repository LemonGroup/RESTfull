package org.lemongroup.lemonstat.rest.db;

public interface IPersonRepository {

    public long createNewPersonByGroup(String personName, long groupId);
    public boolean updatePersonByGroup(long personId, String newPersonName, long groupId);
    public boolean deletePersonByGroup(long personId, long groupId);
}

