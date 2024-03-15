package by.VIWARR.SpringJPA.controllers;

import by.VIWARR.SpringJPA.models.Product;
import by.VIWARR.SpringJPA.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController (ProductService productService) {
        this.productService = productService;
    }

    /*--------------------------------------------------------------------*/

    //вывод всех продуктов
    @GetMapping()
    public String getProductList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products/product_list";
    }

    //вывод продукта по id
    @GetMapping("/id{id}")
    public String showProductById(@PathVariable("id") int id, Model model) {
        Product product = productService.findOne(id);
        model.addAttribute("product", product);
        return "products/show_product_by_id";
    }

    /*--------------------------------------------------------------------*/

    //форма для добавления продукта
    @GetMapping("/new")
    public String formNewProduct() {
        return "products/new_product_form";
    }

    @PostMapping()
    public String createProduct(HttpServletRequest request, HttpServletResponse response) {
        String nameProduct = request.getParameter("name");
        productService.save(nameProduct);
        return "redirect:/product";
    }

    /*-----------------------------------------------------------------*/

    //форма для редактирования продукта
    @GetMapping("/id{id}/edit")
    public String formEditToUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.findOne(id));
        return "products/edit_form";
    }

    //обработка PATCH-запроса на обновление продукта
    @PatchMapping("/id{id}")
    public String updateProductById(@ModelAttribute("product") Product product,
                                    @PathVariable("id") int id) {
        productService.update(id, product);
        return "redirect:/product";
    }

    /*-------------------------------------------------------------------*/

    //удаление продукта
    @DeleteMapping("/id{id}")
    public String deleteProductById(@PathVariable("id") int id) {
        productService.delete(id);
        return "redirect:/product";
    }
}
