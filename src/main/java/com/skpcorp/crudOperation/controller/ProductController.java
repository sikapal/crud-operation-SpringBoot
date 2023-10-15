package com.skpcorp.crudOperation.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.skpcorp.crudOperation.model.ProductDTO;
import com.skpcorp.crudOperation.service.ProductService;
import com.skpcorp.crudOperation.util.PaginationUtil;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    // to show products list page
    @GetMapping
    public String list(@PageableDefault(size = 3) final Pageable pageable,
            final Model model) {
    	Page<ProductDTO> page = productService.findAll(pageable);
        model.addAttribute("products", page);
        model.addAttribute("pageList", PaginationUtil.getPageList(page));
        return "product/list";
    }

 // To demonstrate pagination using thymeleaf-spring-data-dialect
    @GetMapping("pagination-2")
    public String list_2(@SortDefault("id") final Pageable pageable,
            final Model model) {
    	
    	Page<ProductDTO> page = productService.findAll(pageable);
        model.addAttribute("products", page);
        
        return "product/list-2";
    }
    
    
    // to show a page to add a product
    @GetMapping("/add")
    public String add(@ModelAttribute("product") final ProductDTO productDTO) {
        return "product/add";
    }
    

    // To save a product in DB
    @PostMapping("/add")
    public String add(@ModelAttribute("product") @Valid final ProductDTO productDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("name") && productService.nameExists(productDTO.getName())) {
            bindingResult.addError(new FieldError("product", "name",productDTO.getName(), false, null, null, "this product name already exists!"));
        }
        if (bindingResult.hasErrors()) {
            return "product/add";
        }
        productService.create(productDTO);
        redirectAttributes.addFlashAttribute("MSG_SUCCESS", "A new product succesfully created!");
        return "redirect:/products";
    }

    // To show edit product page with product details
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("product", productService.get(id));
        return "product/edit";
    }

    // For updating product to DB
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("product") @Valid final ProductDTO productDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        final ProductDTO currentProductDTO = productService.get(id);
        if (!bindingResult.hasFieldErrors("name") &&
                !productDTO.getName().equalsIgnoreCase(currentProductDTO.getName()) &&
                productService.nameExists(productDTO.getName())) {
        	bindingResult.addError(new FieldError("product", "name", productDTO.getName(), false, null, null, "this product name already exists!"));
        }
        if (bindingResult.hasErrors()) {
            return "product/edit";
        }
        productService.update(id, productDTO);
        redirectAttributes.addFlashAttribute("MSG_SUCCESS", "Product updated successfully!");
        return "redirect:/products";
    }

    // For deleting Product in DB
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        productService.delete(id);
        redirectAttributes.addFlashAttribute("MSG_INFO", "Product deleted succesful!");
        return "redirect:/products";
    }

}
