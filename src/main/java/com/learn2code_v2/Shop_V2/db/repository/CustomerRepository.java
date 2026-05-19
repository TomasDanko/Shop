package com.learn2code_v2.Shop_V2.db.repository;

import com.learn2code_v2.Shop_V2.db.mapper.CustomerRowMapper;
import com.learn2code_v2.Shop_V2.domain.Customer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.swing.plaf.basic.BasicTreeUI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Component
public class CustomerRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CustomerRowMapper customerRowMapper = new CustomerRowMapper();

    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Customer get(int id){
        final String sql = "SELECT * FROM customer WHERE customer.id = " + id;
        try{
            return jdbcTemplate.queryForObject(sql, customerRowMapper);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Integer add(Customer customer){
        final String sql = "INSERT INTO customer(name, surname, email, address, age, phone_number) VALUES(?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getSurname());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getAddress());

            if(customer.getAge() != null){
                ps.setInt(5, customer.getAge());
            } else {
                ps.setNull(5, Types.INTEGER);
            }

            ps.setString(6, customer.getPhoneNumber());
            return ps;
        }, keyHolder);

        return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : null;
    }

    public List<Customer> getAllCustomers(){
        final String sql  = "SELECT * FROM customer";
        return jdbcTemplate.query(sql, customerRowMapper);


    }
}
