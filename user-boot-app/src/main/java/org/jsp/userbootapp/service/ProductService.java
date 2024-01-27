package org.jsp.userbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dao.ProductDao;
import org.jsp.userbootapp.dao.UserDao;
import org.jsp.userbootapp.dto.Product;
import org.jsp.userbootapp.dto.ResponseStructure;
import org.jsp.userbootapp.dto.User;
import org.jsp.userbootapp.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product,int user_id) {
		Optional<User> recUser=userDao.findById(user_id);
		if(recUser.isPresent()) {
			User u =recUser.get();
			u.getProducts().add(product);
			userDao.saveUser(u);
			product.setUser(u);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		structure.setData(productDao.saveProduct(product));
		structure.setMessage("Product Saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Product> recProduct = productDao.findById(product.getId());
		if (recProduct.isPresent()) {
			Product dbProduct = recProduct.get();
			dbProduct.setName(product.getName());
			dbProduct.setBrand(product.getBrand());
			dbProduct.setDescription(product.getDescription());
			dbProduct.setCategory(product.getCategory());
			dbProduct.setCost(product.getCost());
			structure.setData(productDao.saveProduct(dbProduct));
			structure.setMessage("Product Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);
		}
		structure.setData(null);
		structure.setMessage("Product not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findAll() {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setData(productDao.findAll());
		structure.setMessage("Product List");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure ,HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Product>> findById(int id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Product> dbproduct = productDao.findById(id);
		if (dbproduct.isPresent()) {
			structure.setData(dbproduct.get());
			structure.setMessage("Product Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		}
		structure.setData(null);
		structure.setMessage("Product not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<Boolean>> deleteById(int id) {
		ResponseStructure<Boolean> structure = new ResponseStructure<>();
		Optional<Product> dbproduct = productDao.findById(id);
		if (dbproduct.isPresent()) {
			structure.setData(true);
			structure.setMessage("Product Deleted");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			productDao.deleteById(id);
			return new ResponseEntity<ResponseStructure<Boolean>>(structure, HttpStatus.ACCEPTED);
		}

		structure.setData(false);
		structure.setMessage("Cannot delete Product ID is invalid");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<Boolean>>(structure, HttpStatus.NOT_FOUND);
	}
	public ResponseEntity<ResponseStructure<List<Product>>>  find(String brand, String category) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setData(productDao.find(brand,category));
		structure.setMessage("Products Found->Brand:"+brand+"Category:"+category);
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Product>>>  findByBrand(String brand) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
			structure.setData(productDao.findByBrand(brand));
			structure.setMessage("Products Found->Brand:"+brand);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}

	public ResponseEntity<ResponseStructure<List<Product>>>  findByCategory(String category) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setData(productDao.findByCategory(category));
		structure.setMessage("Products Found->Category:"+category);
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		
	}


}
