package br.com.erudio.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.repositories.BookRepository;
import br.com.erudio.services.BookServices;
import br.com.erudio.unittests.mapper.mocks.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

	MockBook input;

	@InjectMocks
	private BookServices service;

	@Mock
	BookRepository repositoy;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {

		var list = input.mockEntityList();

		when(repositoy.findAll()).thenReturn(list);

		var people = service.findAll();

		assertNotNull(people);
		assertEquals(14, people.size());

		var bookOne = people.get(1);

		System.out.println(bookOne.toString());

		assertTrue(bookOne.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Author Test1", bookOne.getAuthor());
		assertEquals(new Date(1640995200000L), bookOne.getLaunchDate());
		assertEquals("Title Test1", bookOne.getTitle());
		assertEquals(5.00, bookOne.getPrice());

		var bookFour = people.get(4);

		assertTrue(bookFour.toString().contains("links: [</api/book/v1/4>;rel=\"self\"]"));
		assertEquals("Author Test4", bookFour.getAuthor());
		assertEquals(new Date(1640995200000L), bookFour.getLaunchDate());
		assertEquals("Title Test4", bookFour.getTitle());
		assertEquals(15.50, bookFour.getPrice());

		var bookSeven = people.get(7);

		assertTrue(bookSeven.toString().contains("links: [</api/book/v1/7>;rel=\"self\"]"));
		assertEquals("Author Test7", bookSeven.getAuthor());
		assertEquals(new Date(1640995200000L), bookSeven.getLaunchDate());
		assertEquals("Title Test7", bookSeven.getTitle());
		assertEquals(5.00, bookSeven.getPrice());

	}

	@Test
	void testFindById() {

		var entity = input.mockEntity(1);
		entity.setId(1L);

		when(repositoy.findById(1L)).thenReturn(Optional.of(entity));

		var result = service.findById(1L);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));

		assertEquals("Author Test1", result.getAuthor());
		assertEquals(new Date(1640995200000L), result.getLaunchDate());
		assertEquals("Title Test1", result.getTitle());
		assertEquals(5.00, result.getPrice());
	}

	@Test
	void testCreate() {

		var entity = input.mockEntity(1);

		var persisted = entity;
		persisted.setId(1L);

		var dto = input.mockDTO(1);
		dto.setKey(1L);

		when(repositoy.save(entity)).thenReturn(persisted);

		var result = service.create(dto);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));

		assertEquals("Author Test1", result.getAuthor());
		assertEquals(new Date(1640995200000L), result.getLaunchDate());
		assertEquals("Title Test1", result.getTitle());
		assertEquals(5.00, result.getPrice());

	}

	@Test
	void testCreateWithNullBook() {

		Exception ex = assertThrows(RequiredObjectIsNullException.class, () -> {

			service.create(null);

		});

		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = ex.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testUpdate() {

		var entity = input.mockEntity(1);
		entity.setId(1L);

		var persisted = entity;
		persisted.setId(1L);

		var dto = input.mockDTO(1);
		dto.setKey(1L);

		when(repositoy.save(entity)).thenReturn(persisted);
		when(repositoy.findById(1L)).thenReturn(Optional.of(entity));

		var result = service.update(dto);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));

		assertEquals("Author Test1", result.getAuthor());
		assertEquals(new Date(1640995200000L), result.getLaunchDate());
		assertEquals("Title Test1", result.getTitle());
		assertEquals(5.00, result.getPrice());

	}

	@Test
	void testUpdateWithNullBook() {

		Exception ex = assertThrows(RequiredObjectIsNullException.class, () -> {

			service.update(null);

		});

		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = ex.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testDelete() {

		var entity = input.mockEntity(1);
		entity.setId(1L);

		when(repositoy.findById(1L)).thenReturn(Optional.of(entity));

		service.delete(1L);

	}

}
