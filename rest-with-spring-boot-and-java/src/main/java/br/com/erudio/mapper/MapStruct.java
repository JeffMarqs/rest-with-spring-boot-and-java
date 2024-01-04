package br.com.erudio.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.erudio.data.dto.v1.BookDTO;
import br.com.erudio.data.dto.v1.PersonDTO;
import br.com.erudio.model.Book;
import br.com.erudio.model.Person;

@Mapper
public interface MapStruct {

	MapStruct INSTANCE = Mappers.getMapper(MapStruct.class);

	@Mapping(source = "id", target = "key")
	PersonDTO personToPersonDTO(Person person);

	@Mapping(source = "key", target = "id")
	Person personDTOToPerson(PersonDTO personDTO);

	List<PersonDTO> personListToPersonDTOlist(List<Person> people);

	List<Person> personDTOListToPersonlist(List<PersonDTO> peopleDTO);

	@Mapping(source = "id", target = "key")
	BookDTO bookToBookDTO(Book book);

	@Mapping(source = "key", target = "id")
	Book bookDTOToBook(BookDTO bookDTO);

	List<BookDTO> bookListToBookDTOlist(List<Book> books);

	List<Book> bookDTOListToBooklist(List<BookDTO> booksDTO);

}
