package br.com.erudio.controllers.port;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.erudio.controllers.PersonController;
import br.com.erudio.data.dto.v1.PersonDTO;
import br.com.erudio.services.PersonServices;
import br.com.erudio.util.MediaType;

@Controller
@RequestMapping("/api/person/v1")
public class PersonControllerImpl implements PersonController {

	@Autowired
	private PersonServices personServices;

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML })
	public ResponseEntity<PersonDTO> findById(@PathVariable(value = "id") Long id) {
		var person = personServices.findById(id);
		return ResponseEntity.ok().body(person);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	public ResponseEntity<List<PersonDTO>> findAll() {
		var listPeople = personServices.findAll();
		return ResponseEntity.ok().body(listPeople);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML }, produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML })
	public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO person) {
		var people = personServices.create(person);
		return ResponseEntity.ok().body(people);
	}

	@PutMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML }, produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML })
	public ResponseEntity<PersonDTO> uptade(@RequestBody PersonDTO person) {
		var people = personServices.update(person);
		return ResponseEntity.ok().body(people);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		personServices.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML })
	public ResponseEntity<PersonDTO> disablePerson(@PathVariable(value = "id") Long id) {
		var person = personServices.disablePerson(id);
		return ResponseEntity.ok().body(person);
	}

}
