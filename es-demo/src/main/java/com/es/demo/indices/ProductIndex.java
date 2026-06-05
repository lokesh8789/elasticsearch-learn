package com.es.demo.indices;

import com.es.demo.util.ElasticUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;

import java.time.Instant;
import java.util.Date;

import static com.es.demo.util.Indices.PRODUCT_INDEX;

@Document(indexName = PRODUCT_INDEX, createIndex = false)
@Mapping(mappingPath = "/elastic/mappings/products-mapping.json")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductIndex implements BaseRollingIndex {

    @Id
    private String id;
    private String name;
    private String category;
    private Double price;
    private String description;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Kolkata")
    private Date createdOn;

    @Override
    public String getAlias() {
        return PRODUCT_INDEX;
    }
}
