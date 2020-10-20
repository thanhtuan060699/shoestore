package shoestore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="")
public class ScreenEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="url")
	private String url;
	
	@OneToMany(mappedBy = "screenEntity")
	private List<PointEntity> pointEntities=new ArrayList<PointEntity>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<PointEntity> getPointEntities() {
		return pointEntities;
	}

	public void setPointEntities(List<PointEntity> pointEntities) {
		this.pointEntities = pointEntities;
	}
	
	
	
}
