package shoestore.dto;

public class PaymentResponseDTO {
	private Long id;
	
	private Long purchaseAmount;
	
	private String responseCode;
	
	private String orderInfo;
	
	private String secureHash;
	
	private String typeCard;
	
	private String message;
	
	private String transStatus;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(Long purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public String getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSecureHash() {
		return secureHash;
	}

	public void setSecureHash(String secureHash) {
		this.secureHash = secureHash;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getTypeCard() {
		return typeCard;
	}

	public void setTypeCard(String typeCard) {
		this.typeCard = typeCard;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	
	
}
