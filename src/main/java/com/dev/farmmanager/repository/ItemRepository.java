package com.dev.farmmanager.repository;

import com.dev.farmmanager.domain.entity.Item;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item> {

    @EntityGraph(attributePaths = "category")
    List<Item> findAllByUserId(Integer userId);

    @EntityGraph(attributePaths = "category")
    Optional<Item> findByIdAndUserId(Integer id, Integer userId);
}