package com.example.demo.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.RecipeModel;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeModel,Integer> {
	List<RecipeModel> findAllByOrderByRatingDesc(Pageable pageable);
}
