package shoestore.dto;

public class PointDTO {
	private Long id;
	
	private Integer value;
	
	private Integer xValue;
	
	private Integer yValue;
	
	private String url;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
