package com.fm.template.builder;

import com.fm.template.model.Config;
import com.fm.template.util.BaseBuilder;
import com.fm.template.util.FluentBuilder;

public class ConfigBuilder {
	public interface ConfigFluent extends BaseBuilder<Config> {
		ConfigFluent id(Integer v);

		ConfigFluent configName(String v);

		ConfigFluent configValue(String v);
	}

	public static ConfigFluent configBuilder() {
		Config instance = new Config();
		return FluentBuilder.builderFor(ConfigFluent.class, instance);
	}
}
