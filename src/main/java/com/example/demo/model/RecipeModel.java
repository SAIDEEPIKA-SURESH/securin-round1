package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRawValue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="recipes")
@JsonPropertyOrder({ "id", "title", "cuisine", "rating", "prep_time", "cook_time", "total_time", "description", "nutrients", "serves" })
public class RecipeModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="title")
	private String title;
	@Column(name="cuisine")
	private String cuisine;
	@Column(name="rating")
	private float rating;
	@JsonProperty("prep_time")
	@Column(name="prep_time")
	private int prepTime;
	@JsonProperty("cook_time")
	@Column(name="cook_time")
	private int cookTime;
	@JsonProperty("total_time")
	@Column(name="total_time")
	private int totalTime;
	@Column(name="description",columnDefinition="text")
	@JsonProperty("description")
	private String description;
	@Column(name="nutrients",columnDefinition="text")
	@JsonRawValue
	private String nutrients;
	@Column(name="serves")
	@JsonProperty("serves")
	private int serves;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCuisine() {
		return cuisine;
	}
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public int getPrepTime() {
		return prepTime;
	}
	public void setPrepTime(int prepTime) {
		this.prepTime = prepTime;
	}
	public int getCookTime() {
		return cookTime;
	}
	public void setCookTime(int cookTime) {
		this.cookTime = cookTime;
	}
	public int getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNutrients() {
		return nutrients;
	}
	public void setNutrients(String nutrients) {
		this.nutrients = nutrients;
	}
	public int getServes() {
		return serves;
	}
	public void setServes(int serves) {
		this.serves = serves;
	}
	
}
