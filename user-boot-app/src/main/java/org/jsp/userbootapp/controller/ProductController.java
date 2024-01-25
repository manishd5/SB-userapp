package org.jsp.userbootapp.controller;


import java.util.List;

import org.jsp.userbootapp.dto.Product;
import org.jsp.userbootapp.dto.ResponseStructure;
import org.jsp.userbootapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping("/products")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}

	@PutMapping("/products")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}

	@GetMapping("/products")
	public ResponseEntity<ResponseStructure<List<Product>>> findAll() {
		return productService.findAll();
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<ResponseStructure<Product>> findById(@PathVariable(name = "id") int id) {
		return productService.findById(id);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<ResponseStructure<Boolean>> deleteById(@PathVariable(name = "id") int id) {
		return productService.deleteById(id);
	}
	@GetMapping("/products/by-brand-category")
	public ResponseEntity<ResponseStructure<List<Product>>> find(@PathVariable String brand,@PathVariable String category) {
		return productService.find(brand, category);
	}
	@GetMapping("/products/by-brand/{brand}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(@PathVariable(name = "brand") String brand) {
		return productService.findByBrand(brand);
	}
	@GetMapping("/products/by-category")
	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(@RequestParam (name = "category") String category) {
		return productService.findByCategory(category);
	}


}
