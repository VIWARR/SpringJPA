package by.VIWARR.SpringJPA.controllers;

import by.VIWARR.SpringJPA.models.Product;
import by.VIWARR.SpringJPA.models.Variety;
import by.VIWARR.SpringJPA.services.ProductService;
import by.VIWARR.SpringJPA.services.VarietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/variety")
public class VarietyControllers {

    private final VarietyService varietyService;

    private final ProductService productService;

    @Autowired
    public VarietyControllers(VarietyService varietyService,  ProductService productService) {
        this.varietyService = varietyService;
        this.productService = productService;
    }

    /*---------------VarietyList------------*/
    @GetMapping()
    public String showVarietyList(Model model) {
        model.addAttribute("varieties", varietyService.findAll());

        return "variety/variety_list";
    }

    /*--------------NewVariety---------*/
    @GetMapping("/new")
    public String newVarietyForm(Model model) {
        model.addAttribute("products", productService.findAll());

        return "variety/new_variety_form";
    }

    /*-----------Create variety---------*/
    @PostMapping()
    public String create(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("product"));
        String name = request.getParameter("nameVariety");
        varietyService.save(name, id);

        return "redirect:/variety";
    }

    /*------------отображение сорта по Id------------*/
    @GetMapping("/id{id}")
    public String showVarietyById(Model model, @PathVariable("id") int id) {
        model.addAttribute("variety", varietyService.findOne(id));

        return "variety/show_variety_by_id";
    }

    /*----------форма редактирования----------*/
    @GetMapping("/id{id}/edit")
    public String editForm(Model model, @PathVariable("id") int id) {
        Variety variety = varietyService.findOne(id);
        List<Product> products = productService.findAll();
        products.remove(variety.getProduct());

        model.addAttribute("variety", variety);
        model.addAttribute("products", products);

        return "variety/edit_form";
    }

    @PatchMapping("/id{id}")
    public String editVariety(HttpServletRequest request, @PathVariable("id") int id) {
        String newNameVariety = request.getParameter("nameVariety");
        int newVarietyId = Integer.parseInt(request.getParameter("product"));
        varietyService.update(id, newNameVariety, newVarietyId);

        return "redirect:/variety";
    }

    @DeleteMapping("/id{id}")
    public String delete(@PathVariable("id") int id) {
        productService.delete(id);

        return "redirect:/variety";
    }
}
