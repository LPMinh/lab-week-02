package com.minh.onthi1.backend.services;

import com.minh.onthi1.backend.models.Product;
import com.minh.onthi1.backend.models.ProductPrice;
import com.minh.onthi1.backend.models.ProductStatus;
import com.minh.onthi1.backend.repositories.ProductRepository;

import java.util.List;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService() {
        productRepository = new ProductRepository();
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProduct();
    }

    public boolean insertProduct(Product product) {
        return productRepository.insertProduct(product);
    }

    public boolean removeProduct(long productID) {
        return productRepository.removeProduct(productID);
    }

    public boolean updateProduct(Product product) {
        return productRepository.update(product);
    }

    public boolean setStatus(Product product, ProductStatus status) {
        return productRepository.setStatus(product, status);
    }

    public Double findPriceByProductID(long productID) {
        return productRepository.getPriceByID(productID);
    }

    public Product findProductByID(long productID) {
        return productRepository.findProductById(productID).get();
    }

    public boolean addProductPrice(ProductPrice productPrice) {
        return productRepository.addProductPrice(productPrice);
    }

    public List<Product> getAllProductsByStauts(ProductStatus status) {
        return productRepository.getAllProductByStatus(status);
    }
    public List<Product> filterProduct(int productStatus,String sort){
        return productRepository.filterProduct(productStatus,sort);
    }
    public List<Product> searchProduct(String query){
        return productRepository.searchProduct(query);
    }
}
