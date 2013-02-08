package com.lumata.multitenancy.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lumata.multitenancy.dao.UserDAO;
import com.lumata.multitenancy.mapper.UserRowMapper;
import com.lumata.multitenancy.model.User;

@Repository(value="userDAO")
public class UserDAOImpl implements UserDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public List<User> all() {
		return jdbcTemplate.query("select * from user", new UserRowMapper());
	}

	@Override
	@Transactional
	public void save(final User user) {
		jdbcTemplate.update("insert into user (name) values(?)", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getName());
			}
		});
	}
	
	@Override
	@Transactional
	public void saveRollback(final User user) {
		jdbcTemplate.update("insert into user (name) values(?)", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getName());
			}
		});
		throw new RuntimeException("unexpected exception...");
	}

	@Override
	@Transactional
	public void delete(final User user) {
		jdbcTemplate.update("delete from user where id = ?", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, user.getId());
			}
		});
	}

	@Override
	@Transactional
	public User getByName(String name) {
		return jdbcTemplate.query("select * from user where name = ?", new Object[]{name}, new UserRowMapper()).get(0);
	}

}
