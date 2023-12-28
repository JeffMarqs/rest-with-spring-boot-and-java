package br.com.erudio.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.controllers.PersonController;
import br.com.erudio.data.dto.v1.PersonDTO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.MapStruct;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	PersonRepository repository;

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	public List<PersonDTO> findAll() {

		logger.info("Finding all people!");
		
		var personsDto = MapStruct.INSTANCE.personListToPersonDTOlist(repository.findAll());
		
		personsDto.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		
		return personsDto;
	}

	public PersonDTO findById(Long id) {

		logger.info("Finding one Person!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found this ID!"));

		var dto = MapStruct.INSTANCE.personToPersonDTO(entity);
		dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		
		return dto;
	}

	public PersonDTO create(PersonDTO personDTO) {
		
		if (personDTO == null)
			throw new RequiredObjectIsNullException();
		
		logger.info("Creating one person!");
		
		var entity = repository.save(MapStruct.INSTANCE.personDTOToPerson(personDTO));
		
		var dto = MapStruct.INSTANCE.personToPersonDTO(entity);
		
		dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
		
		return dto;
	}
	
	public PersonDTO update(PersonDTO personDTO) {
		
		if (personDTO == null)
			throw new RequiredObjectIsNullException();
		
		logger.info("Updating one person!");
		
		var entity = repository.findById(personDTO.getKey())
		.orElseThrow(() -> new ResourceNotFoundException("No records found this ID!"));
		
		entity.setFirstName(personDTO.getFirstName());
		entity.setLastName(personDTO.getLastName());
		entity.setAddress(personDTO.getAddress());
		entity.setGender(personDTO.getGender());
		
		repository.save(entity);
		
		var dto = MapStruct.INSTANCE.personToPersonDTO(entity);
		
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
