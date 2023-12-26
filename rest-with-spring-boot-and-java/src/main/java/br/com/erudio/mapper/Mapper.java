package br.com.erudio.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.erudio.data.dto.v1.PersonDTO;
import br.com.erudio.model.Person;

public class Mapper {
	
	private static ModelMapper mapper = new ModelMapper();
	
	static {
		mapper.createTypeMap(Person.class, PersonDTO.class).addMapping(Person::getId, PersonDTO::setKey);
		mapper.createTypeMap(PersonDTO.class, Person.class).addMapping(PersonDTO::getKey, Person::setId);
	}
	
	public static <O, D> D parseObject(O origin, Class<D> destination) {
		return mapper.map(origin, destination);
	}
	
	public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
		
		List<D> destinationObjects = new ArrayList<D>();
		
		origin.stream().forEach(o -> {
			destinationObjects.add(mapper.map(o, destination));
		});
		
		return destinationObjects;
	}

}
