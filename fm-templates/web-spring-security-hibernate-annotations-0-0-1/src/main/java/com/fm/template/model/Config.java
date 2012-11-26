package com.fm.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "config")
@DynamicUpdate(true)
public class Config extends BaseObject {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "config_name")
	private String configName;
	@Column(name = "config_value")
	private String configValue;

	public Config() {

	}

	public Config(String configName, String configValue) {
		super();
		this.configName = configName;
		this.configValue = configValue;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public String getConfigName() {
		return configName;
	}

	public String getConfigValue() {
		return configValue;
	}

	@Override
	public String toString() {
		return "Config [id=" + id + ", configName=" + configName + ", configValue=" + configValue + ", modifiedBy=" + modifiedBy + ", modifiedAt=" + modifiedAt + ", createdBy=" + createdBy + ", createdAt=" + createdAt + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Config other = (Config) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}