package ru.zhdanovich.shoppinglist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zhdanovich.shoppinglist.product.Product;
import ru.zhdanovich.shoppinglist.service.ProductService;

@Controller
@RequestMapping("/shopping-list")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String showProducts(Model model) {
        model.addAttribute("products", productService.showAllProducts());
        return "main-page";
    }

    @GetMapping("/new")
    public String createProducts(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "create-product";
    }

    @PostMapping()
    public String addProduct(@ModelAttribute("product") Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create-product";
        }

        productService.addProduct(product);
        return "redirect:/shopping-list";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(Model model, @PathVariable("id") Long id) {
        model.addAttribute("product", productService.getProductById(id));
        return "update-product";
    }

    @PostMapping("/{id}")
    public String updateProduct(@ModelAttribute("product") Product product, @PathVariable("id") Long id,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update-product";
        }

        productService.updateProduct(id, product);
        return "redirect:/shopping-list";
    }

    @GetMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/shopping-list";
    }

}
