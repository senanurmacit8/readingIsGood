package com.book.reading.model.book;

import com.book.reading.model.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class Book extends BaseModel {

        @Id
        private String id;

        private String name;
        private String author;
        private String category;
        private double amount;
        private int count;

}
