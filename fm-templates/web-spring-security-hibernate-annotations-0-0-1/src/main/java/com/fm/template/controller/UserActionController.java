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
import com.fm.template.manager.UserActionManager;
import com.fm.template.model.UserAction;
import com.fm.template.util.AdminConstants;
import com.fm.template.util.FilterObject;

@Controller
public class UserActionController extends BaseController {
	@Autowired
	private UserActionManager userActionManager;
	@Autowired
	private ConfigManager configManager;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "admin-search-user-action.html", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView filter(HttpServletRequest request, Map model) {
		String performedBy = request.getParameter("performedBy");
		request.getSession().setAttribute("performedBy", performedBy);
		return users(request, model);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "admin-list-user-actions.html", method = RequestMethod.GET)
	public ModelAndView users(HttpServletRequest request, Map model) {
		Integer page = 0;
		Integer defaultUserActionsPageSize = 0;
		if (!StringUtils.isBlank(request.getParameter("resultsPerPage"))) {
			defaultUserActionsPageSize = Integer.parseInt(request.getParameter("resultsPerPage"));
		} else if ((Integer) request.getSession().getAttribute(AdminConstants.DEFAULT_USER_ACTIONS_PAGE_SIZE_CONFIG_NAME) != null) {
			defaultUserActionsPageSize = (Integer) request.getSession().getAttribute(AdminConstants.DEFAULT_USER_ACTIONS_PAGE_SIZE_CONFIG_NAME);
		} else {
			defaultUserActionsPageSize = Integer.parseInt(configManager.filter(AdminConstants.CONFIG_NAME, AdminConstants.DEFAULT_USER_ACTIONS_PAGE_SIZE_CONFIG_NAME).getConfigValue());
		}
		String sortColumn = AdminConstants.DEFAULT_USER_ACTIONS_SORT_COLUMN;
		String sortOrder = AdminConstants.DEFAULT_USER_ACTIONS_SORT_ORDER;
		String performedBy = (String) request.getParameter("performedBy");
		request.getSession().setAttribute(AdminConstants.DEFAULT_USER_ACTIONS_PAGE_SIZE_CONFIG_NAME, defaultUserActionsPageSize);

		if (request.getParameter(new ParamEncoder("userAction").encodeParameterName(TableTagParameters.PARAMETER_SORT)) != null) {
			sortColumn = request.getParameter(new ParamEncoder("userAction").encodeParameterName(TableTagParameters.PARAMETER_SORT));
			sortOrder = request.getParameter(new ParamEncoder("userAction").encodeParameterName(TableTagParameters.PARAMETER_ORDER));
			if (sortOrder.equals(AdminConstants.DISPLAYTAG_SORT_ORDER_ASC)) {
				sortOrder = AdminConstants.ORDER_ASC;
			} else {
				sortOrder = AdminConstants.ORDER_DESC;
			}
		}

		if (request.getParameter((new ParamEncoder("userAction").encodeParameterName(TableTagParameters.PARAMETER_PAGE))) != null) {
			page = (Integer.parseInt(request.getParameter((new ParamEncoder("userAction").encodeParameterName(TableTagParameters.PARAMETER_PAGE)))) - 1) * defaultUserActionsPageSize;
		}
		
		List<FilterObject> filters = new ArrayList<FilterObject>();
		if(!StringUtils.isBlank(performedBy)) {
			filters.add(new FilterObject("performedBy", Integer.parseInt(performedBy), true));
		}
		List<UserAction> items = userActionManager.filter(page, defaultUserActionsPageSize, filters, sortColumn, sortOrder);
		model.put("userActions", items);
		model.put("resultsPerPage", defaultUserActionsPageSize);
		model.put("resultSize", userActionManager.count(filters).intValue());
		return new ModelAndView("admin/admin-list-user-actions");
	}

}