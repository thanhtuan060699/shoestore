package shoestore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class ProductEntity extends BaseEntity{
	
	@Column(name="name")
	private String name;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="brand_id")
	private BrandEntity brandEntity;
	
	@OneToMany(mappedBy = "productEntity")
	private List<ProductAttributeEntity> productAttributeEntities=new ArrayList<ProductAttributeEntity>();
	
	@OneToMany(mappedBy = "productEntity")
	private List<ImageEntity> imageEntities=new ArrayList<ImageEntity>();
	
	@OneToMany(mappedBy = "productEntity")
	private List<DiscountEntity>discountEntities =new ArrayList<DiscountEntity>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BrandEntity getBrandEntity() {
		return brandEntity;
	}

	public void setBrandEntity(BrandEntity brandEntity) {
		this.brandEntity = brandEntity;
	}

	public List<ProductAttributeEntity> getProductAttributeEntities() {
		return productAttributeEntities;
	}

	public void setProductAttributeEntities(List<ProductAttributeEntity> productAttributeEntities) {
		this.productAttributeEntities = productAttributeEntities;
	}

	public List<ImageEntity> getImageEntities() {
		return imageEntities;
	}

	public void setImageEntities(List<ImageEntity> imageEntities) {
		this.imageEntities = imageEntities;
	}

	public List<DiscountEntity> getDiscountEntities() {
		return discountEntities;
	}

	public void setDiscountEntities(List<DiscountEntity> discountEntities) {
		this.discountEntities = discountEntities;
	}
	
	
}
