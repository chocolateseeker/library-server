package com.bocsoft.library.controller;

import com.bocsoft.library.serviceI.BookService;
import com.bocsoft.library.serviceI.SearchBookConditionDTO;
import com.bocsoft.library.serviceI.SearchBookResultDTO;
import com.bocsoft.library.serviceI.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping(value = "/library/book")
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping("/searchBook")
    public SearchBookResultVO searchBook(@RequestBody SearchBookConditionVO searchBookConditionVO) {
        SearchBookConditionDTO searchBookConditionDTO = new SearchBookConditionDTO();

        searchBookConditionDTO.setBookName(searchBookConditionVO.getBookName());
        searchBookConditionDTO.setAuthor(searchBookConditionVO.getAuthor());
        searchBookConditionDTO.setPublisher(searchBookConditionVO.getPublisher());
        searchBookConditionDTO.setType(searchBookConditionVO.getType());
        searchBookConditionDTO.setOwner(searchBookConditionVO.getOwner());
        searchBookConditionDTO.setRest(searchBookConditionVO.getRest());

        searchBookConditionDTO.setPageIndex(searchBookConditionVO.getPageIndex());
        searchBookConditionDTO.setLimit(searchBookConditionVO.getLimit());

        List<SearchBookResultDTO> list = bookService.searchBook(searchBookConditionDTO);

        SearchBookResultVO searchBookResultVO = new SearchBookResultVO();
        searchBookResultVO.setBooks(list);
        if (list.size() > 0 && list.get(0) != null) {
            searchBookResultVO.setResults(list.get(0).getResults());
        } else {
            searchBookResultVO.setResults(0);
        }
        searchBookResultVO.setPageIndex(searchBookConditionVO.getPageIndex());
        searchBookResultVO.setLimit(searchBookConditionVO.getLimit());
        return searchBookResultVO;
    }

    @RequestMapping(value = "/borrowBook")
    public int borrowBook(@RequestParam("bookId") String bookId, HttpServletRequest httpServletRequest) {
        if (bookId == null || "".equals(bookId)) {
            return -1;
        }
        HttpSession session = httpServletRequest.getSession();
        UserDTO userDto = (UserDTO) session.getAttribute("user");
        return bookService.borrowBook(bookId, userDto);
    }

    @RequestMapping("/returnBook")
    public int returnBook(@RequestParam String borrowId) {
        return bookService.returnBook(borrowId);
    }

    @RequestMapping("/renewBook")
    public int renewBook(@RequestParam String borrowId) {
        return bookService.renewBook(borrowId);
    }
}
