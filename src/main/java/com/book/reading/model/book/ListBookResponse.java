package com.book.reading.model.book;

import com.book.reading.dto.book.BookInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListBookResponse {
     List<BookInfo> bookInfoList;
}
