package com.smile.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-24 11:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "blog", type = "docs", shards = 1, replicas = 0)
public class Blog {

    @Id
    private String bid;

    @Field(type = FieldType.Keyword)
    private String title;

    @Field(index = false, type = FieldType.Keyword)
    private String images; // 图片地址

    private Integer clickCount;

    private Integer discussCount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
