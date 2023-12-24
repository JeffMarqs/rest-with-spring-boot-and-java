package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.PersonDTO;
import br.com.erudio.data.vo.v2.PersonDTOV2;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.Mapper;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonServices {

	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper customerMapper;

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	public List<PersonDTO> findAll() {

		logger.info("Finding all people!");

		return Mapper.parseListObjects(repository.findAll(), PersonDTO.class);
	}

	public PersonDTO findById(Long id) {

		logger.info("Finding one Person!");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found this ID!"));

		return Mapper.parseObject(entity, PersonDTO.class);
	}

	public PersonDTO create(PersonDTO personDTO) {

		logger.info("Creating one person!");

		var entity = repository.save(Mapper.parseObject(personDTO, Person.class));
		var entityDTO = Mapper.parseObject(entity, PersonDTO.class);

		return entityDTO;
	}

	public PersonDTO update(PersonDTO person) {

		logger.info("Updating one person!");

		var entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found this ID!"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		repository.save(entity);

		return Mapper.parseObject(entity, PersonDTO.class);
	}

	public void delete(Long id) {

		logger.info("Deleting one person!");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found this ID!"));

		repository.delete(entity);
	}

	public PersonDTOV2 createV2(PersonDTOV2 personDTO) {
		
		logger.info("Creating one person with V2!");

		var entity = repository.save(customerMapper.convertDTOToEntity(personDTO));
		var entityDTO = customerMapper.convertEntityToDTO(entity);
		
		return entityDTO;
	}

}
