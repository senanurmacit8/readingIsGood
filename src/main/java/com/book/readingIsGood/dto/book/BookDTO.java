package com.book.readingIsGood.dto.book;

import lombok.Data;

@Data
public class BookDTO {

    private String name;
    private String author;
    private String category;
    private double amount;
    private int count;
}
