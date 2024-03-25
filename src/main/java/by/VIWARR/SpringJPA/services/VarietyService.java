package by.VIWARR.SpringJPA.services;

import by.VIWARR.SpringJPA.models.Product;
import by.VIWARR.SpringJPA.models.Variety;
import by.VIWARR.SpringJPA.repositories.VarietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class VarietyService {

    private final VarietyRepository varietyRepository;

    private final ProductService productService;

    @Autowired
    public VarietyService(VarietyRepository varietyRepository, ProductService productService) {
        this.varietyRepository = varietyRepository;
        this.productService = productService;
    }


    /*----------Отображение всех сортов-----------*/
    public List<Variety> findAll() {
        return varietyRepository.findAll();
    }


    /*-----------Сохранение сорта---------*/
    @Transactional
    public void save(String nameVariety, int idProduct) {
        Product product = productService.findOne(idProduct);
        Variety variety = new Variety(nameVariety, product);
        varietyRepository.save(variety);
    }
}
