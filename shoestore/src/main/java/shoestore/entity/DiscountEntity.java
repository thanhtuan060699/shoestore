package shoestore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="discount")
public class DiscountEntity extends BaseEntity {
	
	@Column(name="code")
	private String code;
	
	@Column(name="discount_percent")
	private String discountPercent;
	
	@Column(name="expire_day")
	private Date expireDay;
	
	@Column(name="quanlity")
	private Integer image;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product_id")
	private ProductEntity productEntity;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(String discountPercent) {
		this.discountPercent = discountPercent;
	}

	public Date getExpireDay() {
		return expireDay;
	}

	public void setExpireDay(Date expireDay) {
		this.expireDay = expireDay;
	}

	public Integer getImage() {
		return image;
	}

	public void setImage(Integer image) {
		this.image = image;
	}

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

	
	
	
}
