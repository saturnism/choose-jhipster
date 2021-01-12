package com.okta.developer.blog.repository;

import com.okta.developer.blog.domain.Post;
import org.neo4j.springframework.data.repository.ReactiveNeo4jRepository;
import org.neo4j.springframework.data.repository.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data Neo4j reactive repository for the Post entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PostRepository extends ReactiveNeo4jRepository<Post, String> {
    Flux<Post> findAllBy(Pageable pageable);

    @Query("MATCH (n:Post)<-[]-(m) RETURN n,m")
    Flux<Post> findAllWithEagerRelationships(Pageable pageable);

    @Query("MATCH (n:Post)<-[]-(m) RETURN n,m")
    Flux<Post> findAllWithEagerRelationships();

    @Query("MATCH (e:Post {id: $id}) RETURN e")
    Mono<Post> findOneWithEagerRelationships(String id);
}
