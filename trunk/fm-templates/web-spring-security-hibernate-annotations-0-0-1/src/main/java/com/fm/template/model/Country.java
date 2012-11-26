package com.fm.template.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "country")
@DynamicUpdate(true)
@NamedQueries({ @NamedQuery(name = "Country.getByName", query = "from Country c where name = :name") })
public class Country extends BaseObject {
	private static final long serialVersionUID = 2725094326377518739L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(nullable = false, length = 50, unique = true)
	private String name;
	@Column(nullable = false, length = 50, unique = true)
	private String description;
	@Column(nullable = false, length = 50, unique = true)
	private String foundation;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "country_city", joinColumns = { @JoinColumn(name = "country_id") }, inverseJoinColumns = { @JoinColumn(name = "city_id") })
	@NotEmpty(message = "{user.roles.notEmtpy}")
	private Set<City> cities;

	// @ManyToMany(fetch = FetchType.EAGER)
	// @JoinTable(name = "country_role", joinColumns = { @JoinColumn(name =
	// "country_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	// @NotEmpty(message = "{user.roles.notEmtpy}")
	// private Set<Role> roles;
	//
	// public Set<Role> getRoles() {
	// return roles;
	// }
	//
	// public void setRoles(Set<Role> roles) {
	// this.roles = roles;
	// }

	public Country() {
		this.cities = new HashSet<City>();
	}

	public void addCity(City city) {
		this.cities.add(city);
	}

	public String getFoundation() {
		return foundation;
	}

	public void setFoundation(String foundation) {
		this.foundation = foundation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<City> getCities() {
		return cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", description=" + description + ", cities=" + cities + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Country other = (Country) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
