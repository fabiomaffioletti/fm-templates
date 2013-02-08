package com.fm.template.multitenancy.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fm.template.multitenancy.extractor.UserResultSetExtractor;
import com.fm.template.multitenancy.model.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int line) throws SQLException {
		UserResultSetExtractor extractor = new UserResultSetExtractor();
	    return extractor.extractData(rs);
	  }
	
}
