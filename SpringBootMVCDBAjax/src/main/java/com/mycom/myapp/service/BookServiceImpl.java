package com.mycom.myapp.service;

import com.mycom.myapp.dao.BookDao;
import com.mycom.myapp.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

//    @Autowired
//    BookDao bookDao;

    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<BookDto> listBook() {
        return bookDao.listBook();
    }

    @Override
    public BookDto detailBook(int bookId) {
        return bookDao.detailBook(bookId);
    }

    @Override
    public int insertBook(BookDto book) {
        return bookDao.insertBook(book);
    }

    @Override
    public int updateBook(BookDto book) {
        return bookDao.updateBook(book);
    }

    @Override
    public int deleteBook(int bookId) {
        return bookDao.deleteBook(bookId);
    }
}
