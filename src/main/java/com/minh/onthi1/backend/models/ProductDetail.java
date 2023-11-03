package com.minh.onthi1.backend.models;

import com.minh.onthi1.backend.converters.ColorConverter;
import com.minh.onthi1.backend.converters.SizeConverter;
import com.minh.onthi1.backend.enums.Color;
import com.minh.onthi1.backend.enums.Size;
import jakarta.persistence.*;

@Entity
@Table(name = "product_detail")
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productDetailId;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;
    @Convert(converter = ColorConverter.class)
    private Color color;
    @Convert(converter = SizeConverter.class)
    private Size size;
    @Column
    private int quantity;

    public ProductDetail() {
    }

    public ProductDetail(Long productDetailId, Product product, Color color, Size id, int quantity) {
        this.productDetailId = productDetailId;
        this.product = product;
        this.color = color;
        this.size = id;
        this.quantity = quantity;
    }

    public Long getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(Long productDetailId) {
        this.productDetailId = productDetailId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
