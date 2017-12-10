package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/svc")
@CrossOrigin
public class ProductSvc {
	private List<Product> result;
	private int seq = 5;
	
	@RequestMapping(path="/products", produces="application/json", method=RequestMethod.GET)
	public Product[] getProducts() {
		if( result == null ) {
			result = new ArrayList<Product>();
			
			result.add( new Product(1, "Egg", "Meat", 2.59) );
			result.add( new Product(2, "Chips", "Cracker", 3.59) );
			result.add( new Product(3, "Hat", "Cloth", 12.78) );
			result.add( new Product(4, "Camera", "Electronic", 1250) );
		}
		
		return result.toArray(new Product[0]);
	}
	
	@RequestMapping(path="/products", method=RequestMethod.POST)
	public Product savProduct(@RequestBody Product newProduct) {
		newProduct.id = seq++;
		getResult().add(newProduct);
		
		return newProduct;
	}
	
	@RequestMapping(path="/products", method=RequestMethod.PUT)
	public int updtProduct(@RequestBody Product product) {
		for(int i=0; i<getResult().size(); i++) {
			if( result.get(i).id == product.id ) {
				result.set(i, product);
				return 1;
			}
		}

		return 0;
	}	
	
	@RequestMapping(path="/products/{id}", method=RequestMethod.DELETE)
	public int delProduct(@PathVariable(name="id") int pId) {
		for(int i=0; i<getResult().size(); i++) {
			if( result.get(i).id == pId ) {
				result.remove(i);
				return 1;
			}
		}
		
		return 0;
	}

	public List<Product> getResult() {
		if( result == null ) {
			getProducts();
		}
		return result;
	}	
	
}
