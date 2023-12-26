package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.erudio.controllers.PersonController;
import br.com.erudio.data.dto.v1.PersonDTO;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.Mapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	PersonRepository repository;

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	public List<PersonDTO> findAll() {

		logger.info("Finding all people!");
		
		var personsDto =  Mapper.parseListObjects(repository.findAll(), PersonDTO.class);
		
		personsDto.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		
		return personsDto;
	}

	public PersonDTO findById(Long id) {

		logger.info("Finding one Person!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found this ID!"));

		var dto = Mapper.parseObject(entity, PersonDTO.class);
		dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		
		return dto;
	}

	public PersonDTO create(PersonDTO personDTO) {
		
		logger.info("Creating one person!");
		
		var entity = repository.save(Mapper.parseObject(personDTO, Person.class));
		var dto = Mapper.parseObject(entity, PersonDTO.class);
		
		dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
		
		return dto;
	}
	
	public PersonDTO update(PersonDTO person) {
		
		logger.info("Updating one person!");
		
		var entity = repository.findById(person.getKey())
		.orElseThrow(() -> new ResourceNotFoundException("No records found this ID!"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		repository.save(entity);
		
		var dto = Mapper.parseObject(entity, PersonDTO.class);
		
		dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
		
		return dto;
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one person!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found this ID!"));
		
		repository.delete(entity);
	}

}
