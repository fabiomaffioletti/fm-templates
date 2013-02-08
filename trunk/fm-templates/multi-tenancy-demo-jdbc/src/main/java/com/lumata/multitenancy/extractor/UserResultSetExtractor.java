package com.lumata.multitenancy.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.lumata.multitenancy.model.User;

public class UserResultSetExtractor implements ResultSetExtractor<User> {

	@Override
	public User extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		User user = new User();
		user.setId(rs.getInt(1));
		user.setName(rs.getString(2));
		return user;
	}

}
