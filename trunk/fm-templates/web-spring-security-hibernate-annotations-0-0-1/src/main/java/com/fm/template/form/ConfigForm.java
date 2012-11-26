package com.fm.template.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class ConfigForm {

	private Integer id;
	@NotBlank(message = "{configForm.configName.notBlank}")
	@Length(max = 45, message = "{configForm.configName.length}")
	private String configName;
	@NotBlank(message = "{configForm.configValue.notBlank}")
	@Length(max = 255, message = "{configForm.configValue.length}")
	private String configValue;

	public ConfigForm() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

}
