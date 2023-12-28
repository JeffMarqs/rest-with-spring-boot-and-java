package br.com.erudio.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.controllers.PersonController;
import br.com.erudio.data.dto.v1.BookDTO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.MapStruct;
import br.com.erudio.repositories.BookRepository;

@Service
public class BookServices {

	@Autowired
	BookRepository repository;

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	public List<BookDTO> findAll() {

		logger.info("Finding all books!");

		var bookListDto = MapStruct.INSTANCE.bookListToBookDTOlist(repository.findAll());

		bookListDto.stream()
				.forEach(b -> b.add(linkTo(methodOn(PersonController.class).findById(b.getKey())).withSelfRel()));

		return bookListDto;
	}

	public BookDTO findById(Long id) {

		logger.info("Finding one Book!");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found this ID!"));

		var dto = MapStruct.INSTANCE.bookToBookDTO(entity);
		dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

		return dto;
	}

	public BookDTO create(BookDTO bookDTO) {

		if (bookDTO == null)
			throw new RequiredObjectIsNullException();

		logger.info("Creating a new book!");

		var entity = repository.save(MapStruct.INSTANCE.bookDTOToBook(bookDTO));

		var dto = MapStruct.INSTANCE.bookToBookDTO(entity);

		dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());

		return dto;
	}

	public BookDTO update(BookDTO bookDTO) {

		if (bookDTO == null)
			throw new RequiredObjectIsNullException();

		logger.info("Updating one book!");

		var entity = repository.findById(bookDTO.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found this ID!"));

		entity.setAuthor(bookDTO.getAuthor());
		entity.setLaunchDate(bookDTO.getLaunchDate());
		entity.setPrice(bookDTO.getPrice());
		entity.setTitle(bookDTO.getTitle());

		repository.save(entity);

		var dto = MapStruct.INSTANCE.bookToBookDTO(entity);

		dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());

		return dto;
	}

	public void delete(Long id) {

		logger.info("Deleting one book!");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found this ID!"));

		repository.delete(entity);
	}

}
