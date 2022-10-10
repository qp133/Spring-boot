package com.example.productcrud.repository;

import com.example.productcrud.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private List<Product> products = new ArrayList<>();

//    public ProductRepository() {
//        List<Product> list = List.of(
//            new Product()
//        );
//        for (Product product : list) {
//            products.add(product);
//        }
//    }

    public Optional<Product> get(int id) {
        return products.stream().filter(c -> c.getId()==id).findFirst();
    }

    public List<Product> getAllProducts() {
        return this.products;
    }

    public Product create(Product product) {
        int id;
        if (products.isEmpty()) {
            id = 1;
        } else {
            Product lastCustomer = products.get(products.size()-1);
            id = lastCustomer.getId() + 1;
        }
        product.setId(id);
        products.add(product);
        return product;
    }

    public Product edit(Product product) {
        get(product.getId()).ifPresent(existProduct -> {
            existProduct.setName(product.getName());
            existProduct.setDetail(product.getDetail());
            existProduct.setCategory(product.getCategory());
            existProduct.setPhoto(product.getPhoto());
        });
        return product;
    }

    public void deleteById(int id) {
        get(id).ifPresent(exist -> products.remove(exist));
    }

    public void delete(Product product) {
        deleteById(product.getId());
    }

    public Product search(String name) {
        return products.stream().filter(i -> i.getName().contains(name)).findAny().orElse(null);
    }
}
