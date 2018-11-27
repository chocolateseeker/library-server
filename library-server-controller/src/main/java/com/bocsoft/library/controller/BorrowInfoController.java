package com.bocsoft.library.controller;

import com.bocsoft.library.serviceI.BookRankDTO;
import com.bocsoft.library.serviceI.BorrowInfoHistoryDTO;
import com.bocsoft.library.serviceI.BorrowInfoService;
import com.bocsoft.library.serviceI.UserRankDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/library/borrowInfo")
public class BorrowInfoController {
    @Autowired
    BorrowInfoService borrowInfoService;

    @ResponseBody
    @RequestMapping(value = "/getHistory")
    public List<BorrowInfoHistoryVO> getBorrowInfoHistory(@RequestBody HistoryConditionVO historyConditionVO, HttpServletRequest request) {
        Object user = request.getSession().getAttribute("user");
        int pageIndex = historyConditionVO.getPageIndex();
        int limit = historyConditionVO.getLimit();
        List<BorrowInfoHistoryDTO> list = borrowInfoService.getBorrowInfoHistory(user, pageIndex, limit);
        List<BorrowInfoHistoryVO> resultList = new ArrayList<BorrowInfoHistoryVO>();
        for (BorrowInfoHistoryDTO dto : list) {
            BorrowInfoHistoryVO vo = new BorrowInfoHistoryVO();
            BeanUtils.copyProperties(dto, vo);
            vo.setResults(dto.getResults());
            resultList.add(vo);
        }
        return resultList;
    }

    @ResponseBody
    @RequestMapping(value = "/getRank")
    public RankResultVO getBookRank(@RequestParam("timeLimit") String timeLimit) {
        RankResultVO resultVO = new RankResultVO();
        if (timeLimit == null || "".equals(timeLimit)) {
            log.info("查询排行榜，时间段：" + timeLimit);
            return resultVO;
        }
        List<BookRankDTO> bookRankDTOList = borrowInfoService.getBookRank(timeLimit);
        List<UserRankDTO> userRankDTOList = borrowInfoService.getUserRank(timeLimit);
        List<BookRankVO> bookRankVOList = new ArrayList<BookRankVO>();
        List<UserRankVO> userRankVOList = new ArrayList<UserRankVO>();
        for (BookRankDTO bookRankDTO : bookRankDTOList) {
            BookRankVO bookRankVO = new BookRankVO();
            BeanUtils.copyProperties(bookRankDTO, bookRankVO);
            bookRankVOList.add(bookRankVO);
        }
        for (UserRankDTO userRankDTO : userRankDTOList) {
            UserRankVO userRankVO = new UserRankVO();
            BeanUtils.copyProperties(userRankDTO,userRankVO);
            userRankVOList.add(userRankVO);
        }
        resultVO.setBookList(bookRankVOList);
        resultVO.setUserList(userRankVOList);
        return resultVO;
    }
}
