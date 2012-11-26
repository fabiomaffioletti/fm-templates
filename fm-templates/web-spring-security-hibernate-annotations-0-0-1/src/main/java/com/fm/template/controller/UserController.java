package com.fm.template.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fm.template.manager.ConfigManager;
import com.fm.template.manager.UserManager;
import com.fm.template.model.User;
import com.fm.template.util.AdminConstants;
import com.fm.template.util.FilterObject;
import com.fm.template.util.RequestUtils;
import com.fm.template.util.RequestUtils.MessageType;

@Controller
public class UserController extends BaseController {
	@Autowired
	private UserManager userManager;
	@Autowired
	private ConfigManager configManager;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "admin-search-user.html", method = RequestMethod.POST)
	public ModelAndView filter(HttpServletRequest request, Map model) {
		String filter = request.getParameter("filter");
		request.getSession().setAttribute("filter", filter);
		return users(request, model);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "admin-list-users.html", method = RequestMethod.GET)
	public ModelAndView users(HttpServletRequest request, Map model) {
		Integer page = 0;
		Integer defaultUsersPageSize = 0;
		if (!StringUtils.isBlank(request.getParameter("resultsPerPage"))) {
			defaultUsersPageSize = Integer.parseInt(request.getParameter("resultsPerPage"));
		} else if ((Integer) request.getSession().getAttribute(AdminConstants.DEFAULT_USERS_PAGE_SIZE_CONFIG_NAME) != null) {
			defaultUsersPageSize = (Integer) request.getSession().getAttribute(AdminConstants.DEFAULT_USERS_PAGE_SIZE_CONFIG_NAME);
		} else {
			defaultUsersPageSize = Integer.parseInt(configManager.filter(AdminConstants.CONFIG_NAME, AdminConstants.DEFAULT_USERS_PAGE_SIZE_CONFIG_NAME).getConfigValue());
		}
		String sortColumn = AdminConstants.DEFAULT_ADMIN_USERS_SORT_COLUMN;
		String sortOrder = AdminConstants.DEFAULT_ADMIN_USERS_SORT_ORDER;
		String filter = (String) request.getSession().getAttribute("filter");
		request.getSession().setAttribute(AdminConstants.DEFAULT_USERS_PAGE_SIZE_CONFIG_NAME, defaultUsersPageSize);

		if (request.getParameter(new ParamEncoder("user").encodeParameterName(TableTagParameters.PARAMETER_SORT)) != null) {
			sortColumn = request.getParameter(new ParamEncoder("user").encodeParameterName(TableTagParameters.PARAMETER_SORT));
			sortOrder = request.getParameter(new ParamEncoder("user").encodeParameterName(TableTagParameters.PARAMETER_ORDER));
			if (sortOrder.equals(AdminConstants.DISPLAYTAG_SORT_ORDER_ASC)) {
				sortOrder = AdminConstants.ORDER_ASC;
			} else {
				sortOrder = AdminConstants.ORDER_DESC;
			}
		}

		if (request.getParameter((new ParamEncoder("user").encodeParameterName(TableTagParameters.PARAMETER_PAGE))) != null) {
			page = (Integer.parseInt(request.getParameter((new ParamEncoder("user").encodeParameterName(TableTagParameters.PARAMETER_PAGE)))) - 1) * defaultUsersPageSize;
		}

		List<FilterObject> filters = new ArrayList<FilterObject>();
		filters.add(new FilterObject("username", filter, false));
		List<User> users = userManager.filter(page, defaultUsersPageSize, filters, sortColumn, sortOrder);
		model.put("users", users);
		model.put("resultsPerPage", defaultUsersPageSize);
		model.put("resultSize", userManager.count(filters).intValue());
		return new ModelAndView("admin/admin-list-users");
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "admin-reset-users.html", method = RequestMethod.POST)
	public ModelAndView reset(Integer[] userId, HttpServletRequest request, Map model) {
		try {
			for (Integer id : userId) {
				userManager.reset(userManager.get(id));
			}
			RequestUtils.message(MessageType.SUCCESS, messageSource, "users.reset", null, request);
		} catch (Exception e) {
			log.error(e);
			RequestUtils.message(MessageType.ERROR, messageSource, "error.generic", null, request);
		}
		return users(request, model);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "admin-delete-users.html", method = RequestMethod.POST)
	public ModelAndView delete(Integer[] userId, HttpServletRequest request, Map model) {
		try {
			for (Integer id : userId) {
				userManager.remove(userManager.get(id));
			}
			RequestUtils.message(MessageType.SUCCESS, messageSource, "users.deleted", null, request);
		} catch (Exception e) {
			log.error(e);
			RequestUtils.message(MessageType.ERROR, messageSource, "error.generic", null, request);
		}
		return users(request, model);
	}
}