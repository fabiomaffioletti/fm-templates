package com.fm.template.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;

public class RequestUtils {

	public enum MessageType {
		SUCCESS("successMessage"), ERROR("errorMessage"), INFO("infoMessage"), WARNING("warningMessage");

		private String key;

		private MessageType(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

	}

	public static void message(MessageType messageType, MessageSource messageSource, String code, Object[] params, HttpServletRequest request) {
		request.getSession().setAttribute(messageType.getKey(), messageSource.getMessage(code, params, request.getLocale()));
	}

}
