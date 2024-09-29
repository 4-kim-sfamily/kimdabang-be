package com.kimdabang.kdbserver.review.review.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "reviews")
public class ReviewDocument {

    @Id
    private String id;
    @Field(type = FieldType.Text)
    private Long purchaseCode;
    @Field(type = FieldType.Text)
    private Long reviewCode;
    @Field(type = FieldType.Text)
    private String productCode;
    @Field(type = FieldType.Text)
    private String options;
    @Field(type = FieldType.Text)
    private Date creationDate;
    @Field(type = FieldType.Text)
    private Integer rating;
    @Field(type = FieldType.Text)
    private String nickname;
    @Field(type = FieldType.Text)
    private String text;
}
