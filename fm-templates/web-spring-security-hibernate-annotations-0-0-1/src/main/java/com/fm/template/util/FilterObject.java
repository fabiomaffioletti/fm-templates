package com.fm.template.util;

public class FilterObject {

	private String property;
	private Object value;
	private boolean strict;

	public FilterObject(String property, Object value, boolean strict) {
		super();
		this.property = property;
		this.value = value;
		this.strict = strict;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public boolean isStrict() {
		return strict;
	}

	public void setStrict(boolean strict) {
		this.strict = strict;
	}

}
