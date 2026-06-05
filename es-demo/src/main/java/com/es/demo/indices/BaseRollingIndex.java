package com.es.demo.indices;

import com.es.demo.util.ElasticUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public interface BaseRollingIndex {
    @JsonIgnore
    String getAlias();

    Date getCreatedOn();

    @JsonIgnore
    default String getRollingIndex() {
        Objects.requireNonNull(getCreatedOn(), "createdOn cannot be null");
        return ElasticUtil.getRollingIndex(getAlias(), getCreatedOn());
    }

    @JsonIgnore
    default IndexCoordinates getIndexCoordinates() {
        return IndexCoordinates.of(getRollingIndex());
    }
}
