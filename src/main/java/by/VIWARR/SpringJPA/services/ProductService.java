package by.VIWARR.SpringJPA.services;

import by.VIWARR.SpringJPA.models.Product;
import by.VIWARR.SpringJPA.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /*----------------Отображение всех продуктов------------*/
    public List<Product> findAll() {
        return productRepository.findAll();
    }


    /*-------------Сохранение продукта в БД------------*/
    @Transactional
    public void save(String nameProduct) {
        Product product = new Product(nameProduct.toLowerCase());
        productRepository.save(product);
    }


    /*---------------Поиск продукта по Id-----------*/
    public Product findOne(int id){
        Optional<Product> foundProduct = productRepository.findById(id);
        return foundProduct.orElse(null);
    }


    /*--------------Редактирование продукта-------------*/
    @Transactional
    public void update(int id, String newProductName) {
        Product updateProduct = findOne(id);
        updateProduct.setNameProduct(newProductName);
        productRepository.save(updateProduct);
    }

    /*-----------Удаление продукта------------*/
    @Transactional
    public void delete(int id) {
        productRepository.deleteById(id);
    }
}
