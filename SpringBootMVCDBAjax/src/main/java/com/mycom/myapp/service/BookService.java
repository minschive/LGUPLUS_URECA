package com.mycom.myapp.service;

import com.mycom.myapp.dto.BookDto;

import java.util.List;

// service layer 는 실무에서 가장 복잡한 코드가 존재 <= Business logic 을 구현해놓기 때문에
// 단순 crud 는 controller 와 repository layer 중간에서 단순 pass
public interface BookService {

    List<BookDto> listBook();
    BookDto detailBook(int bookId);
    int insertBook(BookDto book);
    int updateBook(BookDto book);
    int deleteBook(int bookId);
}
