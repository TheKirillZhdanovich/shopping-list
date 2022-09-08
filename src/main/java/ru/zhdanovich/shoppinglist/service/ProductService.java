package ru.zhdanovich.shoppinglist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zhdanovich.shoppinglist.product.Product;
import ru.zhdanovich.shoppinglist.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> showAllProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(Long id, Product product) {
        Product productToUpdate = productRepository.findProductById(id);
        productToUpdate.setName(product.getName());
        productRepository.save(productToUpdate);
    }

    public Product getProductById(Long id) {
        return productRepository.findProductById(id);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
