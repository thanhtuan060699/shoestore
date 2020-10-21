package shoestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="point")
public class PointEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="xValue")
	private Integer xValue;
	
	@Column(name="yValue")
	private Integer yValue;
	
	@Column(name="value")
	private Integer value;
	
	@Column(name="type")
	private String type;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="screenId")
	private ScreenEntity screenEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getxValue() {
		return xValue;
	}

	public void setxValue(Integer xValue) {
		this.xValue = xValue;
	}

	public Integer getyValue() {
		return yValue;
	}

	public void setyValue(Integer yValue) {
		this.yValue = yValue;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public ScreenEntity getScreenEntity() {
		return screenEntity;
	}

	public void setScreenEntity(ScreenEntity screenEntity) {
		this.screenEntity = screenEntity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
