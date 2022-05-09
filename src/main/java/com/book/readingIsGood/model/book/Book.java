package com.book.readingIsGood.model.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book")
@AllArgsConstructor
@Getter
@Setter
public class Book {

        @Id
        private String id;

        private String name;
        private String author;
        private String category;
        private double amount;
        private int count;

}
