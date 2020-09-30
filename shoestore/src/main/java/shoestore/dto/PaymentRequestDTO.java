package shoestore.dto;

public class PaymentRequestDTO {
	 private Long id;
	 
	 private Long purchaseAmount;
	 
	 private String ipAddress;
	 
	 private String secureSecret;
	 
	 private String vpcLocale;
	 
	 private String vpcVersion;
	 
	 private String vpcCurrency;
	 
	 private String vpcCommand;
	 
	 private String vpcMerchant;
	 
	 private String vpcAccessCode;
	 
	 private String urlRequest;
	 
	 private String returnUrl;
	 
	 private String secure_Hash;
	 
	 private String title;

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

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getSecureSecret() {
		return secureSecret;
	}

	public void setSecureSecret(String secureSecret) {
		this.secureSecret = secureSecret;
	}

	public String getVpcLocale() {
		return vpcLocale;
	}

	public void setVpcLocale(String vpcLocale) {
		this.vpcLocale = vpcLocale;
	}

	public String getVpcVersion() {
		return vpcVersion;
	}

	public void setVpcVersion(String vpcVersion) {
		this.vpcVersion = vpcVersion;
	}

	public String getVpcCurrency() {
		return vpcCurrency;
	}

	public void setVpcCurrency(String vpcCurrency) {
		this.vpcCurrency = vpcCurrency;
	}

	public String getVpcCommand() {
		return vpcCommand;
	}

	public void setVpcCommand(String vpcCommand) {
		this.vpcCommand = vpcCommand;
	}

	public String getVpcMerchant() {
		return vpcMerchant;
	}

	public void setVpcMerchant(String vpcMerchant) {
		this.vpcMerchant = vpcMerchant;
	}

	public String getVpcAccessCode() {
		return vpcAccessCode;
	}

	public void setVpcAccessCode(String vpcAccessCode) {
		this.vpcAccessCode = vpcAccessCode;
	}

	public String getUrlRequest() {
		return urlRequest;
	}

	public void setUrlRequest(String urlRequest) {
		this.urlRequest = urlRequest;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getSecure_Hash() {
		return secure_Hash;
	}

	public void setSecure_Hash(String secure_Hash) {
		this.secure_Hash = secure_Hash;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	 
	 
}
