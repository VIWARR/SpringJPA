package by.VIWARR.SpringJPA.services;

import by.VIWARR.SpringJPA.models.Product;
import by.VIWARR.SpringJPA.models.Variety;
import by.VIWARR.SpringJPA.repositories.VarietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        return varietyRepository.findAll(Sort.by("nameVariety"));
    }


    /*-----------Сохранение сорта---------*/
    @Transactional
    public void save(String nameVariety, int idProduct) {
        Product product = productService.findOne(idProduct);
        Variety variety = new Variety(nameVariety, product);
        varietyRepository.save(variety);
    }

    /*---------Поиск сорта в Id----------*/
    public Variety findOne(int id) {
        Optional<Variety> variety = varietyRepository.findById(id);
        return variety.orElse(null);
    }

    @Transactional
    public void update(int id, String newVarietyName, int newVarietyId) {
        Variety variety = findOne(id);
        variety.setNameVariety(newVarietyName);

        Product product = productService.findOne(newVarietyId);
        variety.setProduct(product);

        varietyRepository.save(variety);
    }

    @Transactional
    public void delete(int id) {
        varietyRepository.deleteById(id);
    }
}
