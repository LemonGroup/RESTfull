package org.lemongroup.lemonstat.rest.service;

import org.lemongroup.lemonstat.rest.db.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    IPersonRepository iPersonRepository;

    public long createNewPersonByGroup(String personName, long groupId){
       return iPersonRepository.createNewPersonByGroup(personName,groupId);
    }

    public boolean updatePersonByGroup(long personId, String newPersonName, long groupId) {
        return iPersonRepository.updatePersonByGroup(personId,newPersonName,groupId);
    }

    public boolean deletePersonByGroup(long personId, long groupId) {
        return iPersonRepository.deletePersonByGroup(personId,groupId);
    }

}
