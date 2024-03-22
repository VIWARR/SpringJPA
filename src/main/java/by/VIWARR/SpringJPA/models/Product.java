package by.VIWARR.SpringJPA.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_product")
    private String nameProduct;

    @OneToMany(mappedBy = "product")
    private List<Variety> varieties;

    public Product(){
    }

    public Product(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public List<Variety> getVarieties() {
        return varieties;
    }

    public void setVarieties(List<Variety> varieties) {
        this.varieties = varieties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Objects.equals(nameProduct, product.nameProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameProduct);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nameProduct='" + nameProduct + '\'' +
                ", varieties=" + varieties +
                '}';
    }
}
