package com.bocsoft.library.dao.mapper;

import com.bocsoft.library.dao.domain.Book;

import java.util.List;

public interface BookMapper {
    List<Book> searchBook(Book book);

    int updateBookRest(String bookId);

    Book getBookById(String bookId);

    int addBookRest(String bookId);

}
