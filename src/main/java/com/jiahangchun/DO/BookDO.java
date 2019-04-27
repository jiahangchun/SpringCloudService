package com.jiahangchun.DO;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "book_index")
public class BookDO {

    @Id
    @Field(type = FieldType.String, store = true)
    private String id;

    @Field(type = FieldType.String, store = true)
    private String imgSrc;

    @Field(type = FieldType.String, store = true)
    private String bookName;

    @Field(type = FieldType.String, store = true)
    private String intro;
}
