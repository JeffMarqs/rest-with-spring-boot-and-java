package br.com.erudio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.PersonDTO;
import br.com.erudio.data.vo.v2.PersonDTOV2;
import br.com.erudio.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonServices personServices;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonDTO findById(@PathVariable(value = "id") Long id) {
		return personServices.findById(id);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonDTO> findAll() {
		return personServices.findAll();
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonDTO create(@RequestBody PersonDTO person) {
		return personServices.create(person);
	}
	
	@PostMapping(value = "/v2", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonDTOV2 createV2(@RequestBody PersonDTOV2 person) {
		return personServices.createV2(person);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonDTO uptade(@RequestBody PersonDTO person) {
		return personServices.update(person);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		personServices.delete(id);
		return ResponseEntity.noContent().build();
	}

}
