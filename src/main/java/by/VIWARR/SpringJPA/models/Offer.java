package by.VIWARR.SpringJPA.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "price")
    private double price;

    public Offer() {
    }

    public Offer(Product product, double price) {
        this.product = product;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return id == offer.id && Double.compare(price, offer.price) == 0 && Objects.equals(product, offer.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, price);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", product=" + product +
                ", price=" + price +
                '}';
    }
}
