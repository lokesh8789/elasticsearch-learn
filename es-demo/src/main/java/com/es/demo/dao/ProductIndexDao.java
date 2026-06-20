package com.es.demo.dao;

import com.es.demo.indices.ProductIndex;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.UpdateResponse;

import java.util.Optional;

public interface ProductIndexDao {
    ProductIndex save(ProductIndex index, IndexCoordinates indexCoordinates);
    Optional<ProductIndex> getById(String id);
    boolean delete(String id);
    UpdateResponse update(ProductIndex index, IndexCoordinates indexCoordinates);
}
