package com.es.demo.service;

import com.es.demo.exceptions.BusinessException;
import com.es.demo.indices.ProductIndex;
import com.es.demo.repo.ProductIndexRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;
import org.springframework.data.elasticsearch.core.query.UpdateResponse;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {
    private final ProductIndexRepo productIndexRepo;
    private final ElasticsearchOperations esOperations;
    private final IndexCreationService indexCreationService;

    public ProductIndex createProduct(ProductIndex product) {
        product.setId(UUID.randomUUID().toString());
        product.setCreatedOn(new Date());
        indexCreationService.createRollingIndexWithMappingAndAlias(product);
        return esOperations.save(product, product.getIndexCoordinates());
    }

    public List<ProductIndex> findAll() {
        return Streamable.of(productIndexRepo.findAll())
                .toList();
    }

    public ProductIndex getById(String id) {
        NativeQuery query = NativeQuery.builder()
                .withQuery(qb -> qb.ids(b -> b.values(id)))
                .build();
        return Optional.ofNullable(esOperations.searchOne(query, ProductIndex.class))
                .map(SearchHit::getContent)
                .orElseThrow(() -> new BusinessException("Document with id: " + id + " is not found"));

//        this ElasticsearchRepository does not work as we are using rolling index.
//        return productIndexRepo.findById(id)
//                .orElseThrow(() -> new BusinessException("Document with id: " + id + " is not found"));
    }

    public void deleteById(String id) {
        NativeQuery query = NativeQuery.builder()
                .withQuery(qb -> qb.ids(b -> b.values(id)))
                .build();
        DeleteQuery deleteQuery = DeleteQuery.builder(query)
                .build();
        esOperations.delete(deleteQuery, ProductIndex.class);
//        this ElasticsearchRepository does not work as we are using rolling index.
//        productIndexRepo.deleteById(id);
    }

    public void deleteAll() {
        productIndexRepo.deleteAll();
    }

    public UpdateResponse updateProduct(String id, ProductIndex product) {
        ProductIndex productIndex = getById(id);
        productIndex.setDescription(product.getDescription());
        productIndex.setPrice(product.getPrice());
        return esOperations.update(productIndex, productIndex.getIndexCoordinates());
    }
}
