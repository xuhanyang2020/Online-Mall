package com.example.store.dao.Imp;

import com.example.store.dao.CustomerDao;
import com.example.store.domain.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.PolicySpi;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDaoImpJdbcTest {

    CustomerDao dao;

    @BeforeEach
    void setUp(){
        dao  = new CustomerDaoImpJdbc();
    }

    @AfterEach
    void tearDown(){
        dao = null;
    }

    @Test
    void findByPk() {
        Customer customer = dao.findByPk("1");
        assertEquals(customer.getName(), "xhy");
        assertEquals(1L,customer.getBirthday().getTime());
    }

    @Test
    void findAll() {
        List<Customer> l = dao.findAll();
        System.out.println(l.toString());
        assertEquals(1,l.size());
    }

    @Test
    void create() {
        Customer customer = new Customer();
        customer.setId("5");
        customer.setName("sti3");
        customer.setPassword("789");
        customer.setPhone("13331389822");
        customer.setBirthday(new Date(19980823));
        dao.create(customer);
    }

    @Test
    void modify() {
        Customer customer = new Customer();
        customer.setId("2");
        customer.setName("stick");
        customer.setPassword("345");
        customer.setPhone("13331389867");
        customer.setBirthday(new Date(19980823));
        customer.setAddress("sjz");
        dao.modify(customer);
    }

    @Test
    void remove() {
        dao.remove("2");
    }
}