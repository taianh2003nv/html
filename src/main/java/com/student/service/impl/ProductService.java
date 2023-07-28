package com.student.service.impl;

import com.student.model.Product;
import com.student.model.ProductDto;

import java.util.List;
public interface ProductService {
  List<ProductDto> getProductDto ();
  void save(Product product);
  List<Product> findAll();
  void delete(int id);
  Product findByID( int id);
  List<Product> findByName(String name);
  List<Product> findByCategory(int cid);
  List<ProductDto> getInfor();
}
