package com.student.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.model.Category;
import com.student.model.Product;
import com.student.model.ProductDto;
import com.student.model.Student;
import com.student.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    private final EntityManagerFactory factory;
    @Autowired
    private ProductRepository productRepository;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    public ProductServiceImpl(EntityManagerFactory factory) {
        this.factory = factory;
        entityManager = factory.createEntityManager();
    }

    @Override
    public List<ProductDto> getProductDto() {
//        Product product = entityManager.find(Product.class, 1);
//        Category category = entityManager.find(Category.class, product.getCid());
//        ProductDto dto = new ProductDto();
//        dto.setCid(product.getCid());
//        dto.setCategory(category);
//        dto.setPname(product.getPname());
//        dto.setPid(product.getPid());
//        List<ProductDto> dtos = new ArrayList<>();
//        dtos.add(dto);
        return null;
    }

    @Override
    @Transactional
    public void save(Product product) {
        productRepository.save(product);

    }

    @Override
    public List<ProductDto> getInfor() {
        List<Map<String, Objects>> mapList = productRepository.findInfor();
        ObjectMapper mapper = new ObjectMapper();
        List<ProductDto> products = new ArrayList<>();
        for (Map m : mapList) {
            ProductDto dto = mapper.convertValue(m,ProductDto.class);
            products.add(dto);
        }
        return products;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findByID(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new RuntimeException();
        }
        return product.get();
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findAllByPname(name);
    }

    @Override
    public List<Product> findByCategory(int cid) {
        return productRepository.findAllByCid(cid);
    }


}
