package by.VIWARR.SpringJPA.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Variety")
public class Variety {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "name_variety")
    private String nameVariety;

    public Variety() {
    }

    public Variety(String nameVariety, Product product) {
        this.nameVariety = nameVariety;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getNameVariety() {
        return nameVariety;
    }

    public void setNameVariety(String nameVariety) {
        this.nameVariety = nameVariety;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variety variety = (Variety) o;
        return id == variety.id && Objects.equals(nameVariety, variety.nameVariety);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameVariety);
    }

    @Override
    public String toString() {
        return "Variety{" +
                "id=" + id +
                ", product=" + product +
                ", nameVariety='" + nameVariety + '\'' +
                '}';
    }
}
