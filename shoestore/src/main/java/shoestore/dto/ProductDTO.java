package shoestore.dto;

public class ProductDTO {
	private Long id;
	
	private String name;
	
	private Integer limit;
	
	private Integer totalPage;
	
	private Integer page;
	
	private String color;
	
	private Double size;
	
	private Long price;
	
	private Integer quantity;
	
	private Long productAttributeId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getProductAttributeId() {
		return productAttributeId;
	}

	public void setProductAttributeId(Long productAttributeId) {
		this.productAttributeId = productAttributeId;
	}
	
	
}
