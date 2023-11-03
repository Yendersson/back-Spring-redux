package com.api.rest.RestService.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Product {
	
	private Long id;
	private String title;
	private double price;
	private String description;
	private Category category;
	
	public Product() {
		
	}

	public Product(Long id, String title, double price, String description, Category category) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.description = description;
		this.category = category;
	}

	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, description, price, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(category, other.category) && Objects.equals(description, other.description)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Product [title=" + title + ", price=" + price + ", description=" + description + "]";
	}
	
	
	
	
	
}
