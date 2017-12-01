package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/svc")
@CrossOrigin
public class ProductSvc {
	List<Product> result;
	
	@RequestMapping(path="/products", produces="application/json")
	public Product[] getProducts() {
		if( result == null ) {
			result = new ArrayList<Product>();
			
			result.add( new Product(1, "Egg", "Meat", 2.59) );
			result.add( new Product(2, "Chips", "Cracker", 3.59) );
			result.add( new Product(3, "Hat", "Cloth", 12) );
			result.add( new Product(4, "Camera", "Electronic", 1250) );
		}
		
		return result.toArray(new Product[0]);
	}
	
	
}
