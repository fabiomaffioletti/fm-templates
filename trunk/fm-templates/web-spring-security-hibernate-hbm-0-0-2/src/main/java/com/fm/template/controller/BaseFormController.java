package com.fm.template.controller;

public class BaseFormController {
	private String formView;
	private String successView;
	private String cancelView;

	public String getFormView() {
		return formView;
	}

	public void setFormView(String formView) {
		this.formView = formView;
	}

	public String getSuccessView() {
		return successView;
	}

	public void setSuccessView(String successView) {
		this.successView = successView;
	}

	public String getCancelView() {
		return cancelView;
	}

	public void setCancelView(String cancelView) {
		this.cancelView = cancelView;
	}

}
