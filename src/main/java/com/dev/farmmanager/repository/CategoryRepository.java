package com.dev.farmmanager.repository;

import com.dev.farmmanager.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findAllByUserIdOrUserIdIsNull(Integer userId);

    Optional<Category> getByIdAndUserId(Integer id, Integer userId);
}
