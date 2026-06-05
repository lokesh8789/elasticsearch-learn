package com.es.demo.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.es.demo.exceptions.IndexCreationException;
import com.es.demo.indices.BaseRollingIndex;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class IndexCreationService {
    private final ElasticsearchClient esClient;
    private final ElasticsearchOperations esOperations;

    public <T extends BaseRollingIndex> void createRollingIndexWithMappingAndAlias(T doc) {
        String alias = doc.getAlias();
        String rollingIndex = doc.getRollingIndex();
        try {
            IndexOperations ops = esOperations.indexOps(IndexCoordinates.of(rollingIndex));
            if (!ops.exists()) {
                log.info("Creating Index: {}", rollingIndex);
                ops.create();
                ops.putMapping(esOperations.indexOps(doc.getClass())
                        .createMapping());
                esClient.indices()
                        .putAlias(a -> a.index(rollingIndex)
                                .name(alias)
                        );
            }
        } catch (Exception e) {
            throw new IndexCreationException(rollingIndex);
        }
    }
}
