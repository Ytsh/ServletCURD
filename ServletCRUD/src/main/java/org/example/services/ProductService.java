package org.example.services;

import org.example.model.Product;
import org.example.model.dto.ProductDTO;
import org.example.repository.ProductRepository;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    ProductRepository productRepository = new ProductRepository();
    public List<ProductDTO> getAllProduct(int category) throws SQLException {
        return this.productRepository.getAllProductForCategory(category);
    }

    public ProductDTO addProduct(Product product) throws SQLException {
        return this.productRepository.addProduct(product);
    }

    public ProductDTO updateProduct(Product product, int productId) throws SQLException {
        return this.productRepository.updateProduct(product,productId);
    }

    public void deleteProduct(int productId) throws SQLException {
        this.productRepository.deleteProduct(productId);
    }

    public List<ProductDTO> getAllProducts() throws SQLException {
        return this.productRepository.getAllProducts();
    }
}
