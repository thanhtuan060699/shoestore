package shoestore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="brand")
public class BrandEntity extends BaseEntity{
	
	@Column(name="name")
	private String name;
	
	@Column(name="status")
	private Integer status;
	
	@OneToMany(mappedBy = "brandEntity")
	private List<ProductEntity> producEntities=new ArrayList<ProductEntity>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<ProductEntity> getProducEntities() {
		return producEntities;
	}

	public void setProducEntities(List<ProductEntity> producEntities) {
		this.producEntities = producEntities;
	}
	
	
}
