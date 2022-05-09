package com.book.readingIsGood.model.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
@AllArgsConstructor
@Getter
@Setter
public class Customer {

    @Id
    private String id;

    private String name;
    private String orders;

}
