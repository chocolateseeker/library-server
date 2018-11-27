package com.bocsoft.library.serviceI;

import java.util.List;

public interface BorrowInfoService {
    public List<BorrowInfoHistoryDTO> getBorrowInfoHistory(Object user, int pageIndex, int limit);
    public List<BookRankDTO> getBookRank(String timeLimit);
    public List<UserRankDTO> getUserRank(String timeLimit);

}
