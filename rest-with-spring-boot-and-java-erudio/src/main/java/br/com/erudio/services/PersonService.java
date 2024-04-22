package br.com.erudio.services;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.handler.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
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
    public PersonVO findById(Long id){
        logger.info("Finding one person!");
        var entity = personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }
    public List<PersonVO> findAll(){
        logger.info("Finding all people!");
        return DozerMapper.parseListObject(personRepository.findAll(), PersonVO.class);
    }
    public PersonVO create(Person person){
        logger.info("Creating one person!");
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);

        return vo;
    }
    public PersonVO update(Person person){
        logger.info("Update person!");
        var entity = personRepository.findById(person.getId()).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        return vo;
    }
    public void delete(Long id){
        var entity = personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
        personRepository.delete(entity);
    }
}
