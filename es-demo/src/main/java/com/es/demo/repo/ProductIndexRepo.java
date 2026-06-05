package com.es.demo.repo;

import com.es.demo.indices.ProductIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductIndexRepo extends ElasticsearchRepository<ProductIndex, String> {
}
