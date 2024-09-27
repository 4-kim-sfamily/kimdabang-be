package com.kimdabang.kdbserver.product.product.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "products")
public class ProductPartialDocument {

    @Field(type = FieldType.Text)
    private String productCode;
    @Field(type = FieldType.Text)
    private String productName;
    @Field(type = FieldType.Text)
    private Long categoryId;

}
