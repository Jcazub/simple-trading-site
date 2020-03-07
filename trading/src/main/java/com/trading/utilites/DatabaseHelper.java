package com.trading.utilites;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class DatabaseHelper {

    public static <T> T queryForNullableObject(JdbcTemplate jdbcTemplate, String sql, RowMapper<T> rowMapper, Object argument) throws DataAccessException {
        List<T> results = jdbcTemplate.query(sql, rowMapper, argument);

        if (results == null || results.isEmpty()) {
            return null;
        }
        else if (results.size() > 1) {
            throw new IncorrectResultSizeDataAccessException(1, results.size());
        }
        else{
            return results.iterator().next();
        }
    }
}
