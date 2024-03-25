package by.VIWARR.SpringJPA.controllers;

import by.VIWARR.SpringJPA.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/products")
public class ProductControllers {

    private final ProductService productService;

    @Autowired
    public ProductControllers(ProductService productService) {
        this.productService = productService;
    }

    /*-------------------Отображение всех продуктов----------------------*/
    @GetMapping()
    public String showProductList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products/product_list";
    }


    /*---------------Отображение формы для нового продукта и сохранение сового продукта------------------*/
    @GetMapping("/new")
    public String newProductForm() {
        return "products/new_product_form";
    }

    @PostMapping()
    public String saveProductToDB(HttpServletRequest request) {
        String nameProduct = request.getParameter("nameProduct");
        productService.save(nameProduct);

        return "redirect:/products";
    }


    /*---------------Отображение продукта по Id----------*/
    @GetMapping("/id{id}")
    public String showProductById(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.findOne(id));

        return "products/show_product_by_id";
    }


    /*--------Форма для редактирования + редактирование продукта-------*/
    @GetMapping("/id{id}/edit")
    public String formEdit(@PathVariable ("id") int id, Model model) {
        model.addAttribute("product", productService.findOne(id));

        return "products/edit_form";
    }

    @PatchMapping("/id{id}")
    public String updateProduct(@PathVariable("id") int id,
                                HttpServletRequest request) {
        String newProductName = request.getParameter("nameProduct");
        productService.update(id, newProductName);

        return "redirect:/products";
    }


    /*------------Удаление продукта----------*/
    @DeleteMapping("/id{id}")
    public String delete(@PathVariable("id") int id) {
        productService.delete(id);

        return "redirect:/products";
    }
}
