package com.book.reading.model.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "customer")
@AllArgsConstructor
@Getter
@Setter
public class Customer {

    @Id
    private String id;

    @Field(value = "orderId")
    @Lazy
    private String order;
    private String name;
}
