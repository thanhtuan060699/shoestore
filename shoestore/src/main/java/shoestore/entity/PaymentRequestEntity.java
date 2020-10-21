package shoestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "paymentrequest")
public class PaymentRequestEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="purchase_amount")
	private Long purchaseAmount;
	
	@Column(name="ipaddress")
	private String ipAddress;
	
	@Column(name="secure_secret")
	private String secureScecret;
	
	@Column(name="vpc_locale")
	private String vpcLocale;
	
	@Column(name="vpc_version")
	private String vpcVersion;
	
	@Column(name="vpc_currency")
	private String vpcCurrency;
	
	@Column(name="vpc_command")
	private String vpcCommand;
	
	@Column(name="vpc_merchant")
	private String vpcMerchant;
	
	@Column(name="vpc_access_code")
	private String vpcAccessCode;
	
	@Column(name="url_request")
	private String urlRequest;
	
	@Column(name="return_url")
	private String returnUrl;
	
	@Column(name="secure_hash")
	private String secureHash;
	
	@Column(name="title")
	private String title;
	
	@OneToOne(mappedBy = "paymentRequestEntity",fetch = FetchType.LAZY)
	private OrderEntity orderEntity;

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

	public String getSecureScecret() {
		return secureScecret;
	}

	public void setSecureScecret(String secureScecret) {
		this.secureScecret = secureScecret;
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

	public String getSecureHash() {
		return secureHash;
	}

	public void setSecureHash(String secureHash) {
		this.secureHash = secureHash;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public OrderEntity getOrderEntity() {
		return orderEntity;
	}

	public void setOrderEntity(OrderEntity orderEntity) {
		this.orderEntity = orderEntity;
	}
	
	
}
