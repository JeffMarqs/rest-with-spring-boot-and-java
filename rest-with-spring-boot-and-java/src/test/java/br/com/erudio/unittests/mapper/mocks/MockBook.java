package br.com.erudio.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.erudio.data.dto.v1.BookDTO;
import br.com.erudio.model.Book;

public class MockBook {


    public Book mockEntity() {
        return mockEntity(0);
    }
    
    public BookDTO mockDTO() {
        return mockDTO(0);
    }
    
    public List<Book> mockEntityList() {
        List<Book> book = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            book.add(mockEntity(i));
        }
        return book;
    }

    public List<BookDTO> mockDTOList() {
        List<BookDTO> book = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            book.add(mockDTO(i));
        }
        return book;
    }
    
    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setAuthor("Author Test" + number);
        book.setLaunchDate(new Date(1640995200000L));
        book.setPrice(((number % 2)==0) ? 15.50 : 5.00);
        book.setId(number.longValue());
        book.setTitle("Title Test" + number);
        return book;
    }

    public BookDTO mockDTO(Integer number) {
        BookDTO book = new BookDTO();
        book.setAuthor("Author Test" + number);
        book.setLaunchDate(new Date(1640995200000L));
        book.setPrice(((number % 2)==0) ? 15.50 : 5.00);
        book.setKey(number.longValue());
        book.setTitle("Title Test" + number);
        return book;
    }

}
