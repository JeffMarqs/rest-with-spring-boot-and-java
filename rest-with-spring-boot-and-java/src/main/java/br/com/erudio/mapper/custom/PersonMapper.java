package br.com.erudio.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v2.PersonDTOV2;
import br.com.erudio.model.Person;

@Service
public class PersonMapper {
	
	public PersonDTOV2 convertEntityToDTO(Person person) {
		PersonDTOV2 dto = new PersonDTOV2();
		
		dto.setId(person.getId());
		dto.setAddress(person.getAddress());
		dto.setFirstName(person.getFirstName());
		dto.setLastName(person.getLastName());
		dto.setGender(person.getGender());
		dto.setBirthDay(new Date());
		
		return dto;
	}
	
	public Person convertDTOToEntity(PersonDTOV2 dto) {
		Person person = new Person();
		
		person.setId(dto.getId());
		person.setAddress(dto.getAddress());
		person.setFirstName(dto.getFirstName());
		person.setLastName(dto.getLastName());
		person.setGender(dto.getGender());
		//person.setBirthDay(new Date());
		
		return person;
	}

}
