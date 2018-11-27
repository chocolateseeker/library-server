package com.bocsoft.library.service;

import com.bocsoft.library.dao.domain.BookRank;
import com.bocsoft.library.dao.domain.BorrowInfoHistory;
import com.bocsoft.library.dao.domain.UserRank;
import com.bocsoft.library.dao.mapper.BorrowInfoMapper;
import com.bocsoft.library.serviceI.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service("BorrowInfoService")
public class BorrowInfoServiceImpl implements BorrowInfoService {
    @Autowired
    BorrowInfoMapper borrowInfoMapper;

    public List<BorrowInfoHistoryDTO> getBorrowInfoHistory(Object user, int pageIndex, int limit) {
        UserDTO userDTO = (UserDTO) user;
        String userId = userDTO.getUserId();
        PageHelper.startPage(pageIndex, limit, true);
        List<BorrowInfoHistory> list = borrowInfoMapper.getBorrowInfoHistory(userId);
        PageInfo<BorrowInfoHistory> pageInfo = new PageInfo<BorrowInfoHistory>(list);
        List<BorrowInfoHistoryDTO> resultList = new ArrayList<BorrowInfoHistoryDTO>();
        for (BorrowInfoHistory borrowInfoHistory : list) {
            BorrowInfoHistoryDTO borrowInfoHistoryDTO = new BorrowInfoHistoryDTO();
            BeanUtils.copyProperties(borrowInfoHistory, borrowInfoHistoryDTO);
            borrowInfoHistoryDTO.setResults(pageInfo.getTotal());
            resultList.add(borrowInfoHistoryDTO);
        }
        return resultList;
    }

    public List<BookRankDTO> getBookRank(String timeLimit) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long currentTime = System.currentTimeMillis();
        long startTime = 0;
        if ("oneMonth".equals(timeLimit)) {
            startTime = currentTime - 30 * 24 * 60 * 60 * 1000L;
        }
        if ("threeMonths".equals(timeLimit)) {
            startTime = currentTime - 3 * 30 * 24 * 60 * 60 * 1000L;
        }
        if ("oneYear".equals(timeLimit)) {
            startTime = currentTime - 12 * 30 * 24 * 60 * 60 * 1000L;
        }
        if ("all".equals(timeLimit)) {
            startTime = 0;
        }
        String startTimeStr = df.format(startTime);
        List<BookRank> bookRankList = borrowInfoMapper.getBookRank(startTimeStr);
        List<BookRankDTO> bookRankDTOList = new ArrayList<BookRankDTO>();
        for (BookRank bookRank : bookRankList) {
            BookRankDTO bookRankDTO = new BookRankDTO();
            BeanUtils.copyProperties(bookRank, bookRankDTO);
            bookRankDTOList.add(bookRankDTO);
        }
        return bookRankDTOList;
    }

    public List<UserRankDTO> getUserRank(String timeLimit) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long currentTime = System.currentTimeMillis();
        long startTime = 0;
        if ("oneMonth".equals(timeLimit)) {
            startTime = currentTime - 30 * 24 * 60 * 60 * 1000L;
        }
        if ("threeMonths".equals(timeLimit)) {
            startTime = currentTime - 3 * 30 * 24 * 60 * 60 * 1000L;
        }
        if ("oneYear".equals(timeLimit)) {
            startTime = currentTime - 12 * 30 * 24 * 60 * 60 * 1000L;
        }
        if ("all".equals(timeLimit)) {
            startTime = 0;
        }
        String startTimeStr = df.format(startTime);
        List<UserRank> userRankList = borrowInfoMapper.getUserRank(startTimeStr);
        List<UserRankDTO> userRankDTOList = new ArrayList<UserRankDTO>();
        for (UserRank userRank : userRankList) {
            UserRankDTO userRankDTO = new UserRankDTO();
            BeanUtils.copyProperties(userRank, userRankDTO);
            userRankDTOList.add(userRankDTO);
        }
        return userRankDTOList;
    }
}
