package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.RecipeModel;
import com.example.demo.service.RecipeService;
@RestController
@RequestMapping("/api")
public class RecipeController {
	private RecipeService rs;
	public RecipeController(RecipeService rs) {
		this.rs = rs;
	}
	@GetMapping("/import")
	public String importData() {
		return rs.parseData();
	}
	@PostMapping("/recipes")
	public ResponseEntity<RecipeModel> addRecipe(@RequestBody RecipeModel recipe) {
	    RecipeModel savedRecipe = rs.createRecipe(recipe);
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedRecipe);
	}
	@GetMapping("/recipes/top")
	public ResponseEntity<Map<String, List<RecipeModel>>> getTopRecipes(
	        @RequestParam(defaultValue = "5") int limit) {
	    List<RecipeModel> recipes = rs.getTopRecipes(limit);
	    Map<String, List<RecipeModel>> response = new HashMap<>();
	    response.put("data", recipes);
	    return ResponseEntity.ok(response);
	}
	@GetMapping("/allrecipes")
	public ResponseEntity<List<RecipeModel>> getAllRecipes() {
	    List<RecipeModel> recipes = rs.getAllRecipes();
	    return ResponseEntity.ok(recipes);
	}
}
