package com.fm.template.multitenancy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tenant")
public class Tenant extends BaseObject {
	private static final long serialVersionUID = 2421631115991447479L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "key_name", unique = true, nullable = false)
	private String keyName;
	@Column(name = "jndi_name", nullable = false)
	private String jndiName;

	public Tenant() {

	}

	public Tenant(String keyName, String jndiName) {
		super();
		this.keyName = keyName;
		this.jndiName = jndiName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getJndiName() {
		return jndiName;
	}

	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}

	@Override
	public String toString() {
		return "Tenant [id=" + id + ", keyName=" + keyName + ", jndiName=" + jndiName + "]";
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
