package com.mycom.myapp.dao;

import com.mycom.myapp.dto.BookDto;

import java.util.List;

public interface BookDao {

    List<BookDto> listBook();
    BookDto detailBook(int bookid);
    int insertBook(BookDto book);
    int updateBook(BookDto book);
    int deleteBook(int bookId);

}
