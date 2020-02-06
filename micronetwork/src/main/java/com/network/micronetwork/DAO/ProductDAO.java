package com.network.micronetwork.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.network.micronetwork.Product ;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer>{

    public List<Product>findAll();
    public Product findById(int id);
    public Product save(Product product);
    public List<Product> findByPrixGreaterThan(int prixLimit);
    public List<Product> findByNomLike(String recherche);

    public void deleteById(int id);
    
}
