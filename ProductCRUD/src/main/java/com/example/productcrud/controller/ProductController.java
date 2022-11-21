package com.example.productcrud.controller;

import com.example.productcrud.model.Product;
import com.example.productcrud.repository.CategoryRepository;
import com.example.productcrud.repository.ProductRepository;
import com.example.productcrud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private StorageService storageService;

    @Autowired private MessageSource messageSource;


    public ProductController() {
        productRepo = new ProductRepository();
    }

    @GetMapping("/")
    public String home(Model model)
    {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryRepo.getAllCategories());
        return "index";
    }

    @GetMapping("/listAllProducts")
    public String listAll(Model model) {
        List<Product> products = productRepo.getAllProducts();
        model.addAttribute("products", products);
        return "listAll";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable("id")int id, Model model){
        Optional<Product> product = productRepo.get(id);
        if(product.isPresent()){
            model.addAttribute("product",product.get());
            return "productInfo";
        }
        return "home";
    }

    @GetMapping("/create")
    public String showForm(Model model) {
        model.addAttribute("product", new Product());
        return "productForm";
    }

    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model) {
        Optional<Product> product = productRepo.get(id);
        if (product.isPresent()) {
            model.addAttribute("product", product);
        }
        return "productForm";
    }

    @PostMapping(value = "/post", consumes = {"multipart/form-data"})
    public String postInfo(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model) throws Exception {
        Locale locale = LocaleContextHolder.getLocale();
        if (product.getPhoto().getOriginalFilename().isEmpty()) {
            result.addError(new FieldError("product", "photo",
                    messageSource.getMessage("photo.required", null, "Photo required", locale)));
        }
        if (result.hasErrors()) {
            model.addAttribute("categories",categoryRepo.getAllCategories());
            return "index";
        }

        if (product.getId() > 0) {
            productRepo.edit(product);
        } else {
            productRepo.create(product);
        }
        storageService.uploadFile(product.getPhoto(), product.getId());
        model.addAttribute("products", productRepo.getAllProducts());
        return "redirect:/listAllProducts";
    }

    @GetMapping("/product/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        storageService.deleteFile(id);
        productRepo.deleteById(id);
        model.addAttribute("product", productRepo.getAllProducts());
        return "redirect:/listAllProducts";
    }

    @GetMapping("/search")
    public String searchProduct(HttpServletRequest request, Model model) {
        String name = request.getParameter("product");
        if (Objects.equals(name, "")) {
            model.addAttribute("product", productRepo.getAllProducts());
            return "redirect:/listAllProducts";
        } else {
            Product product = productRepo.search("name");
            model.addAttribute("products", product);
            return "listAllProducts";
        }
    }
}
