package shoestore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity{

	@Column(name="totalprice")
	private Long totalPrice;
	
	@Column(name="amount")
	private Integer amount;
	
	@Column(name="province")
	private String province;
	
	@Column(name="district")
	private String district;
	
	@Column(name="ward")
	private String ward;
	
	@Column(name="phonenumber")
	private String phonenumber;

	@Column(name="address")
	private String address;
	
	@Column(name="email")
	private String email;
	
	@Column(name="customerstatus")
	private Integer customerStatus;
	
	@Column(name="adminstatus")
	private Integer adminStatus;
	
	@Column(name="message")
	private String message;
	
	@Column(name="fullname")
	private String fullName;
	
	@Column(name="methodPayment")
	private String methodPayment;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private UserEntity userEntity;
	
	@OneToMany(mappedBy = "orderEntity")
	private List<OrderDetailEntity> orderDetailEntities =new ArrayList<OrderDetailEntity>();
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="onepayRequestID")
    private PaymentRequestEntity paymentRequestEntity;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="onepayResponseID")
    private PaymentResponseEntity paymentResponseEntity;

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(Integer customerStatus) {
		this.customerStatus = customerStatus;
	}

	public Integer getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(Integer adminStatus) {
		this.adminStatus = adminStatus;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public List<OrderDetailEntity> getOrderDetailEntities() {
		return orderDetailEntities;
	}

	public void setOrderDetailEntities(List<OrderDetailEntity> orderDetailEntities) {
		this.orderDetailEntities = orderDetailEntities;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public PaymentRequestEntity getPaymentRequestEntity() {
		return paymentRequestEntity;
	}

	public void setPaymentRequestEntity(PaymentRequestEntity paymentRequestEntity) {
		this.paymentRequestEntity = paymentRequestEntity;
	}

	public PaymentResponseEntity getPaymentResponseEntity() {
		return paymentResponseEntity;
	}

	public void setPaymentResponseEntity(PaymentResponseEntity paymentResponseEntity) {
		this.paymentResponseEntity = paymentResponseEntity;
	}

	public String getMethodPayment() {
		return methodPayment;
	}

	public void setMethodPayment(String methodPayment) {
		this.methodPayment = methodPayment;
	}
	
	
}
