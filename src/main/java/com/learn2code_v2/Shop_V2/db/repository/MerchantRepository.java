package com.learn2code_v2.Shop_V2.db.repository;

import com.learn2code_v2.Shop_V2.db.mapper.MerchantRowMapper;
import com.learn2code_v2.Shop_V2.domain.Merchant;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
public class MerchantRepository {
    private final JdbcTemplate jdbcTemplate;
    private final MerchantRowMapper merchantRowMapper = new MerchantRowMapper();

    public MerchantRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Merchant get(int id){
        final String sql = "SELECT * FROM merchant WHERE merchant.id =  " + id;
        try{
            return jdbcTemplate.queryForObject(sql, merchantRowMapper);
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public List<Merchant> getAll(){
        final String sql = "SELECT * FROM merchant";
        return jdbcTemplate.query(sql,merchantRowMapper);
    }

    public Integer add(Merchant merchant) {
        final String sql = "INSERT INTO merchant(name, email, address) VALUES(?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    sql,
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, merchant.getName());
            ps.setString(2, merchant.getEmail());
            ps.setString(3, merchant.getAddress());
            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys != null && keys.containsKey("id")) {
            return ((Number) keys.get("id")).intValue();
        }

        return null;
    }
    }

