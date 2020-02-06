package com.network.micronetwork;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.network.micronetwork.DAO.ProductDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class ProductController {

	  @Autowired
	    private ProductDAO productDao;
	
	@RequestMapping(value="/Produits", method=RequestMethod.GET)
    public List<Product> listeProduits() {
		return productDao.findAll();
    }
	
	
	
	
	  @GetMapping(value ="/Produits/{id}")
	  public Product testeDeRequetes(@PathVariable int id) {
		  
		  return productDao.findById(id);
		  
	  }
	
	  
	  @GetMapping(value = "/Produits/prix/{prixLimit}")
	  public List<Product> testeDePrix(@PathVariable int prixLimit) {
	      return productDao.findByPrixGreaterThan(prixLimit);
	  }
	  
	  
	  @GetMapping(value = "/Produits/noms/{recherche}")
	    public List<Product> testeDeRequetes(@PathVariable String recherche) {
	        return productDao.findByNomLike("%"+recherche+"%");
	    }
  
	//ajouter un produit
	  @PutMapping (value = "/Produits")
	//  @PostMapping(value = "/Produits")
	    public ResponseEntity<Void> ajouterProduit(@RequestBody Product product) {

	        Product productAdded =  productDao.save(product);

	        if (productAdded == null)
	            return ResponseEntity.noContent().build();

	        URI location = ServletUriComponentsBuilder
	                .fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(productAdded.getId())
	                .toUri();

	        return ResponseEntity.created(location).build();
	    }

	
	  
	  @DeleteMapping (value = "/Produits/{id}")
	   public void supprimerProduit(@PathVariable int id) {

	       productDao.deleteById(id);
	   }
	  

	  @PostMapping(value = "/Produits")
	    public ResponseEntity<Void> ajouterProduitpos(@RequestBody Product product) {

	        Product productAdded =  productDao.save(product);

	        if (productAdded == null)
	            return ResponseEntity.noContent().build();

	        URI location = ServletUriComponentsBuilder
	                .fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(productAdded.getId())
	                .toUri();

	        return ResponseEntity.created(location).build();
	    }
}
