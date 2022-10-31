package com.book.reading.dto.book;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class BookInfo {

    @NotEmpty(message = "The name is required.")
    private String name;
    @NotEmpty(message = "The author is required.")
    private String author;
    @NotEmpty(message = "The category is required.")
    private String category;
    @NotEmpty(message = "The amount is required.")
    private double amount;
    @NotEmpty(message = "The count is required.")
    @Size(min = 30, max = 160,message = "The book count have to range of 30-160!")
    private int count;

    private Integer version;

}
