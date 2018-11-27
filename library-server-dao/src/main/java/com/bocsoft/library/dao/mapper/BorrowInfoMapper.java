package com.bocsoft.library.dao.mapper;

import com.bocsoft.library.dao.domain.BookRank;
import com.bocsoft.library.dao.domain.BorrowInfo;
import com.bocsoft.library.dao.domain.BorrowInfoHistory;
import com.bocsoft.library.dao.domain.UserRank;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BorrowInfoMapper {
    void insertBorrowInfo(BorrowInfo borrowInfo);

    List<BorrowInfoHistory> getBorrowInfoHistory(String userId);

    int returnBook(@Param("borrowId") String borrowId, @Param("actualReturnTime") String actualReturnTime);

    int renewBook(@Param("borrowId") String borrowId, @Param("shouldReturnTime") String shouldReturnTime);

    String getShouldReturnTime(@Param("borrowId") String borrowId);

    List<BookRank> getBookRank(String timeLimit);

    List<UserRank> getUserRank(String timeLimit);

    String getBookIdByBorrowId(String borrowId);

    BorrowInfo getBorrowInfoByBorrowId(String borrowId);
}
