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

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findOne(int id) {
        Optional<Product> foundProduct = productRepository.findById(id); //Optional - т.к. продукт может быть или не может
        return foundProduct.orElse(null); //если найдет то проудкт, если нет null
    }

    @Transactional //т.к. записываем, то убираем readOnly = true
    public void save(String nameProduct) {
        Product product = new Product(nameProduct);
        productRepository.save(product);
    }

    @Transactional
    public void update(int id, Product updateProduct) {
        //в repository для обновления сущности и сохранения сущности используется один метод save
        //поэтому мы вызываем setId, чтобы repository понял, что такой продукт существует
        //продукт не будет сохраняться, он будет редактироваться
        updateProduct.setId(id);
        productRepository.save(updateProduct);
    }

    @Transactional
    public void delete(int id) {
        productRepository.deleteById(id);
    }
}
