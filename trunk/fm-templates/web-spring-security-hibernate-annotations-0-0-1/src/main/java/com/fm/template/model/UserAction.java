package com.fm.template.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "user_action")
@DynamicUpdate(true)
@SuppressWarnings("serial")
public class UserAction extends BaseObject {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "performed_by")
	private Integer performedBy;
	@Column(name = "performed_on")
	private Integer performedOn;
	@Column(name = "performed_by_username")
	private String performedByUsername;
	@Column(name = "performed_on_username")
	private String performedOnUsername;
	@Column(name = "action_description")
	private String actionDescription;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "performed_at")
	private Date performedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(Integer performedBy) {
		this.performedBy = performedBy;
	}

	public Integer getPerformedOn() {
		return performedOn;
	}

	public void setPerformedOn(Integer performedOn) {
		this.performedOn = performedOn;
	}

	public String getPerformedByUsername() {
		return performedByUsername;
	}

	public void setPerformedByUsername(String performedByUsername) {
		this.performedByUsername = performedByUsername;
	}

	public String getPerformedOnUsername() {
		return performedOnUsername;
	}

	public void setPerformedOnUsername(String performedOnUsername) {
		this.performedOnUsername = performedOnUsername;
	}

	public String getActionDescription() {
		return actionDescription;
	}

	public void setActionDescription(String actionDescription) {
		this.actionDescription = actionDescription;
	}

	public Date getPerformedAt() {
		return performedAt;
	}

	public void setPerformedAt(Date performedAt) {
		this.performedAt = performedAt;
	}

	@Override
	public String toString() {
		return "UserAction [id=" + id + ", performedBy=" + performedBy + ", actionDescription=" + actionDescription + ", performedAt=" + performedAt + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((performedAt == null) ? 0 : performedAt.hashCode());
		result = prime * result + ((performedBy == null) ? 0 : performedBy.hashCode());
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
		UserAction other = (UserAction) obj;
		if (performedAt == null) {
			if (other.performedAt != null)
				return false;
		} else if (!performedAt.equals(other.performedAt))
			return false;
		if (performedBy == null) {
			if (other.performedBy != null)
				return false;
		} else if (!performedBy.equals(other.performedBy))
			return false;
		return true;
	}

}
