package by.VIWARR.SpringJPA.controllers;

import by.VIWARR.SpringJPA.services.ProductService;
import by.VIWARR.SpringJPA.services.VarietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/new")
    public String newVarietyForm(Model model) {
        model.addAttribute("products", productService.findAll());

        return "variety/new_variety_form";
    }

    @PostMapping()
    public String create(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("product"));
        String name = request.getParameter("nameVariety");
        varietyService.save(name, id);

        return "redirect:/variety";
    }
}
