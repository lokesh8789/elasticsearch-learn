package com.es.demo.dao;

import com.es.demo.exceptions.BusinessException;
import com.es.demo.indices.ProductIndex;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.ByQueryResponse;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;
import org.springframework.data.elasticsearch.core.query.UpdateResponse;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductIndexDaoImpl implements ProductIndexDao {

    private final ElasticsearchOperations esOperations;

    @Override
    public ProductIndex save(ProductIndex product, IndexCoordinates indexCoordinates) {
        return esOperations.save(product, indexCoordinates);
    }

    @Override
    public Optional<ProductIndex> getById(String id) {
        NativeQuery query = NativeQuery.builder()
                .withQuery(qb -> qb.ids(b -> b.values(id)))
                .build();
        return Optional.ofNullable(esOperations.searchOne(query, ProductIndex.class))
                .map(SearchHit::getContent);
    }

    @Override
    public boolean delete(String id) {
        NativeQuery query = NativeQuery.builder()
                .withQuery(qb -> qb.ids(b -> b.values(id)))
                .build();
        DeleteQuery deleteQuery = DeleteQuery.builder(query)
                .build();
        ByQueryResponse response = esOperations.delete(deleteQuery, ProductIndex.class);
        return response.getDeleted() > 0;
    }

    @Override
    public UpdateResponse update(ProductIndex index, IndexCoordinates indexCoordinates) {
        return esOperations.update(index, indexCoordinates);
    }


}
