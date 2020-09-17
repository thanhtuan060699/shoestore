package shoestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="product_attribute")
public class ProductAttributeEntity extends BaseEntity{
	@Column(name="size")
	private Double size;
	
	@Column(name="price")
	private Long price;
	
	@Column(name="color")
	private String color;
	
	@Column(name="quantity")
	private Integer quantity;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product_id")
	private ProductEntity productEntity;

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
}
