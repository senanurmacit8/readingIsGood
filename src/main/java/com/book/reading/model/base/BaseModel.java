package com.book.reading.model.base;

import lombok.Data;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.MappedSuperclass;

@Document(collection = "book")
@Data
@MappedSuperclass
public class BaseModel {

    @Version
    private Integer version;

}
