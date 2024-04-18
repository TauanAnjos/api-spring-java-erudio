package br.com.erudio.services;

import br.com.erudio.exceptions.handler.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;
@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());
    @Autowired
    PersonRepository personRepository;
    public Person findById(Long id){
        logger.info("Finding one person!");
        return personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
    }
    public List<Person> findAll(){
        logger.info("Finding all people!");
        return  personRepository.findAll();
    }
    public Person create(Person person){
        logger.info("Creating one person!");
        return personRepository.save(person);
    }
    public Person update(Person person){
        logger.info("Update person!");
        var entity = personRepository.findById(person.getId()).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return personRepository.save(person);
    }
    public void delete(Long id){
        var entity = personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
        personRepository.delete(entity);
    }
}
