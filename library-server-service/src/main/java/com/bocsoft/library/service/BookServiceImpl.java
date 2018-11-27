package com.bocsoft.library.service;

import com.bocsoft.library.dao.domain.Book;
import com.bocsoft.library.dao.domain.BorrowInfo;
import com.bocsoft.library.dao.mapper.BookMapper;
import com.bocsoft.library.dao.mapper.BorrowInfoMapper;
import com.bocsoft.library.serviceI.BookService;
import com.bocsoft.library.serviceI.SearchBookConditionDTO;
import com.bocsoft.library.serviceI.SearchBookResultDTO;
import com.bocsoft.library.serviceI.UserDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service("BookService")
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;
    @Autowired
    BorrowInfoMapper borrowInfoMapper;

    public List<SearchBookResultDTO> searchBook(SearchBookConditionDTO searchBookConditionDTO) {
        Book book = new Book();
        if (searchBookConditionDTO.getAuthor() != null && !"".equals(searchBookConditionDTO.getAuthor())) {
            book.setAuthor("%" + fuzzyCondition(searchBookConditionDTO.getAuthor()) + "%");
        }
        if (searchBookConditionDTO.getBookName() != null && !"".equals(searchBookConditionDTO.getBookName())) {
            book.setBookName("%" + fuzzyCondition(searchBookConditionDTO.getBookName()) + "%");
        }
        if (searchBookConditionDTO.getPublisher() != null && !"".equals(searchBookConditionDTO.getPublisher())) {
            book.setPublisher("%" + fuzzyCondition(searchBookConditionDTO.getPublisher()) + "%");
        }
        if (searchBookConditionDTO.getOwner() != null && !"".equals(searchBookConditionDTO.getOwner())) {
            book.setOwner("%" + fuzzyCondition(searchBookConditionDTO.getOwner()) + "%");
        }
        if (searchBookConditionDTO.getType() != null && !"".equals(searchBookConditionDTO.getType())) {
            book.setType("%" + fuzzyCondition(searchBookConditionDTO.getType()) + "%");
        }
        book.setRest(searchBookConditionDTO.getRest());
        PageHelper.startPage(searchBookConditionDTO.getPageIndex(), searchBookConditionDTO.getLimit(), true);
        List<Book> list = bookMapper.searchBook(book);
        PageInfo<Book> pageInfo = new PageInfo<Book>(list);
        pageInfo.getTotal();
        List<SearchBookResultDTO> resultList = new ArrayList<SearchBookResultDTO>();
        for (Book tempBook : list) {
            SearchBookResultDTO searchBookResultDTO = new SearchBookResultDTO();
            BeanUtils.copyProperties(tempBook, searchBookResultDTO);
            searchBookResultDTO.setResults(pageInfo.getTotal());
            resultList.add(searchBookResultDTO);
        }
        return resultList;
    }

    public String fuzzyCondition(String oldCondition) {
        return oldCondition.replace("_", "/_").replace("%", "/%").replace("[", "[[").replace("]", "]]");
    }

    public synchronized int borrowBook(String bookId, UserDTO userDTO) {
        String userId = userDTO.getUserId();
        Book book = bookMapper.getBookById(bookId);
        if (book.getRest() <= 0) {
            //库存不足
            return -2;
        }
        int updateBookRestResult = bookMapper.updateBookRest(bookId);
        if (updateBookRestResult != 1) {
            // 更新数据库失败
            return -3;
        }
        BorrowInfo borrowInfo = new BorrowInfo();
        borrowInfo.setBookId(bookId);
        borrowInfo.setUserId(userId);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat returnDf = new SimpleDateFormat("yyyy-MM-dd");
        long borrowTime = System.currentTimeMillis();
        long returnTime = borrowTime + (30 * 24 * 60 * 60 * 1000L);
        borrowInfo.setBorrowTime(df.format(borrowTime));
        borrowInfo.setShouldReturnTime(returnDf.format(returnTime));

        borrowInfoMapper.insertBorrowInfo(borrowInfo);
        return 0;
    }

    public synchronized int returnBook(String borrowId) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String actualReturnTime = df.format(System.currentTimeMillis());
        BorrowInfo borrowInfo = borrowInfoMapper.getBorrowInfoByBorrowId(borrowId);
        if (borrowInfo.getReturned() == 1)  {
            //已还
            return -4;
        }
        int returnBookResult = borrowInfoMapper.returnBook(borrowId, actualReturnTime);
        String bookId = borrowInfo.getBookId();
        int addBookRestResult;
        if (returnBookResult == 1) {
            //将已还书returned字段置1成功，则将库存加1
            addBookRestResult = bookMapper.addBookRest(bookId);
        } else {
            //returned字段置1失败（异常），不操作库存，返回错误
            return -3;
        }
        if (addBookRestResult == 1) {
            // 库存添加成功
            return 0;
        } else {
            //库存添加失败，此处没有数据库事务控制，后果严重
            // TODO: 2018/11/23  增加数据库事务控制，防止还书记录与书库存出错
            return -2;
        }
    }

    public synchronized int renewBook(String borrowId) {
        String oldShouldReturnTime = borrowInfoMapper.getShouldReturnTime(borrowId);
        if (oldShouldReturnTime == null || "".equals(oldShouldReturnTime)) {
            //已经续借，无法再次续借
            return -2;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String shouldReturnTime = "";
        try {
            long should = df.parse(oldShouldReturnTime).getTime() + 30 * 24 * 60 * 60 * 1000L;
            shouldReturnTime = df.format(should);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int renewResult = borrowInfoMapper.renewBook(borrowId, shouldReturnTime);
        if (renewResult == 1) {
            return 0;
        } else {
            return -1;
        }
    }
}
