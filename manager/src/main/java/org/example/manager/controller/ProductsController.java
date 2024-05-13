package org.example.manager.controller;

import lombok.RequiredArgsConstructor;
import org.example.manager.client.BadRequestException;
import org.example.manager.client.ProductsRestClient;
import org.example.manager.controller.payload.NewProductPayload;
import org.example.manager.entity.Product;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products")
public class ProductsController {

    private final ProductsRestClient productsRestClient;


    @GetMapping("list")
    public String getProductsList(Model model, @RequestParam(name = "filter", required = false)String filter){
        model.addAttribute("products",this.productsRestClient.findAllProducts(filter));
        model.addAttribute("filter",filter);
        return "catalogue/products/list";
    }

    @GetMapping("create")
    public String getNewProductPage(){
        return "catalogue/products/new_product";
    }

    @PostMapping("create")
    public String createProduct(NewProductPayload newProductPayload,
                                Model model){
        try {
            Product product = this.productsRestClient.createProduct(newProductPayload.title(), newProductPayload.details());
            return "redirect:/catalogue/products/%d".formatted(product.id());
        }catch (BadRequestException exception){
            model.addAttribute("payload", newProductPayload);
            model.addAttribute("errors", exception.getErrors());
            return "catalogue/products/new_product";
        }
    }


}
