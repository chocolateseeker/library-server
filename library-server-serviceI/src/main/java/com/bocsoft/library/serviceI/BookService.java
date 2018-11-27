package com.bocsoft.library.serviceI;

import java.util.List;

public interface BookService {
    List<SearchBookResultDTO> searchBook(SearchBookConditionDTO searchBookConditionDTO);

    int borrowBook(String bookId, UserDTO userDTO);

    int returnBook(String borrowId);

    int renewBook(String borrowId);
}
