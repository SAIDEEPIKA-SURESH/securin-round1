package com.example.demo.service;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.RecipeModel;
import com.example.demo.repository.RecipeRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class RecipeService {
	private RecipeRepository repo;
	public RecipeService(RecipeRepository repo) {
		this.repo = repo;
	}
	public String parseData() {
		try {
			String json = new String(Files.readAllBytes(Paths.get("C:\\Users\\Sai Deepika\\eclipse-workspace\\recipe_api_securin\\src\\main\\resources\\US_recipes_null.json")));
			ObjectMapper map = new ObjectMapper();
			JsonNode node = map.readTree(json);
			System.out.println("Node size: " + node.size());
			for (int i = 0; i < node.size(); i++) {
				String ind = Integer.toString(i);
				JsonNode obj = node.get(ind);
				System.out.println(obj.toString());
				RecipeModel recipe = new RecipeModel();
				if (obj.get("title") == null || obj.get("title").isNull()) {
				    recipe.setTitle(null);
				} else {
				    recipe.setTitle(obj.get("title").asText());
				}
				System.out.println("title updated");
				if (obj.get("cuisine") == null || obj.get("cuisine").isNull()) {
				    recipe.setCuisine(null);
				} else {
				    recipe.setCuisine(obj.get("cuisine").asText());
				}
				System.out.println("cuisine updated");
				if (obj.get("rating") == null || obj.get("rating").isNull()) {
				    recipe.setRating(0);
				} else {
				    recipe.setRating((float) obj.get("rating").asDouble());
				}
				System.out.println("rating updated");
				if (obj.get("prep_time") == null || obj.get("prep_time").isNull()) {
				    recipe.setPrepTime(0);
				} else {
				    recipe.setPrepTime(obj.get("prep_time").asInt());
				}
				System.out.println("prep_time updated");
				if (obj.get("cook_time") == null || obj.get("cook_time").isNull()) {
				    recipe.setCookTime(0);
				} else {
				    recipe.setCookTime(obj.get("cook_time").asInt());
				}
				System.out.println("cook_time updated");
				if (obj.get("total_time") == null || obj.get("total_time").isNull()) {
				    recipe.setTotalTime(0);
				} else {
				    recipe.setTotalTime(obj.get("total_time").asInt());
				}
				System.out.println("total_time updated");
				if (obj.get("description") == null || obj.get("description").isNull()) {
				    recipe.setDescription(null);
				} else {
				    recipe.setDescription(obj.get("description").asText());
				}
				System.out.println("description updated");
				JsonNode nutrientsNode = obj.get("nutrients");
				if (nutrientsNode != null && !nutrientsNode.isNull()) {
				    recipe.setNutrients(nutrientsNode.toString());
				} else {
				    recipe.setNutrients(null);
				}
				System.out.println("nutrients updated");
				if (obj.get("serves") == null || obj.get("serves").isNull()) {
				    recipe.setServes(0);
				} else {
				    recipe.setServes(obj.get("serves").asInt());
				}
				System.out.println("serves updated");
				repo.save(recipe);
			}
		        return "Data Saved Successfully!";
			}
			catch(Exception e)
			{
				e.printStackTrace();
		        return "Data not saved";
			}
		}
	 public RecipeModel createRecipe(RecipeModel recipe) {
	        if (recipe.getTitle() == null || recipe.getTitle().isBlank()) {
	            throw new RuntimeException("Title is required");
	        }
	        if (recipe.getCuisine() == null || recipe.getCuisine().isBlank()) {
	            throw new RuntimeException("Cuisine is required");
	        }
	        if (recipe.getPrepTime() == 0) {
	            throw new RuntimeException("Prep time is required");
	        }
	        if (recipe.getCookTime() == 0) {
	            throw new RuntimeException("Cook time is required");
	        }
	        recipe.setTotalTime(recipe.getPrepTime() + recipe.getCookTime());
	        return repo.save(recipe);
	 }
	 public List<RecipeModel> getTopRecipes(int limit) {
		    Pageable pageable = PageRequest.of(0, limit);
		    return repo.findAllByOrderByRatingDesc(pageable);
		}
	 public List<RecipeModel> getAllRecipes() {
		    return repo.findAll();
		}
}
