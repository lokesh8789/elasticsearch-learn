package com.es.demo;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.InfoResponse;
import com.es.demo.indices.ProductIndex;
import com.es.demo.repo.ProductIndexRepo;
import com.es.demo.service.IndexCreationService;
import com.es.demo.util.ElasticUtil;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static com.es.demo.util.Indices.PRODUCT_INDEX;

@SpringBootApplication
public class EsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsDemoApplication.class, args);
	}

	@Bean
	public ApplicationRunner init(
			ElasticsearchClient client,
			ElasticsearchOperations operations,
			ProductIndexRepo productIndexRepo,
			IndexCreationService indexCreationService) {
		return args -> {
			InfoResponse info = client.info();
			System.out.println(info.name());
			System.out.println(info.clusterName());
			System.out.println(info.clusterUuid());
			System.out.println(info.version().luceneVersion());

//			productIndexRepo.deleteAll(RefreshPolicy.IMMEDIATE);
//			ProductIndex product = ProductIndex.builder()
//					.id(UUID.randomUUID().toString())
//					.name("iPhone X")
//					.category("Apple")
//					.price(105099d)
//					.description("iPhone x the best")
//					.createdOn(Instant.now().minus(90, ChronoUnit.DAYS))
//					.build();
//			productIndexRepo.save(product);
//			indexCreationService.createRollingIndexWithMappingAndAlias(product);
//			operations.save(product, product.getIndexCoordinates());
//			operations.indexOps(ProductIndex.class)
//					.putMapping();
		};
	}

}
