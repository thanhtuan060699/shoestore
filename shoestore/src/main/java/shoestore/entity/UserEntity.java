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
@Table(name="user")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="username")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="fullname")
	private String fullname;
	
	@Column(name="address")
	private  String address;
	
	@Column(name="province")
	private  String province;
	
	@Column(name="district")
	private  String district;
	
	@Column(name="ward")
	private  String ward;
	
	@Column(name="email")
	private  String email;
	
	@Column(name="status")
	private  Integer status;

	@OneToMany(mappedBy = "userEntity")
	private List<UserRoleEntity> userRoleEntities=new ArrayList<UserRoleEntity>();
	
	@OneToMany(mappedBy = "userEntity")
	private List<ContactEntity> contactEntities=new ArrayList<ContactEntity>();
	
	@OneToMany(mappedBy = "userEntity")
	private List<OrderEntity> orderEntities =new ArrayList<OrderEntity>();
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullname;
	}

	public void setFullName(String fullname) {
		this.fullname = fullname;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public List<UserRoleEntity> getUserRoleEntities() {
		return userRoleEntities;
	}

	public void setUserRoleEntities(List<UserRoleEntity> userRoleEntities) {
		this.userRoleEntities = userRoleEntities;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<OrderEntity> getOrderEntities() {
		return orderEntities;
	}

	public void setOrderEntities(List<OrderEntity> orderEntities) {
		this.orderEntities = orderEntities;
	}

	public List<ContactEntity> getContactEntities() {
		return contactEntities;
	}

	public void setContactEntities(List<ContactEntity> contactEntities) {
		this.contactEntities = contactEntities;
	}

	
	
	
}
