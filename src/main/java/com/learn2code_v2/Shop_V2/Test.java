package com.learn2code_v2.Shop_V2;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class Test {

    private JdbcTemplate jdbcTemplate;

    public Test(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcTemplate.execute("select * from customer");
    }
}
