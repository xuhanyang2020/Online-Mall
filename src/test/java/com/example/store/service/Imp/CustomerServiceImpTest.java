package com.example.store.service.Imp;

import com.example.store.dao.CustomerDao;
import com.example.store.dao.Imp.CustomerDaoImpJdbc;
import com.example.store.domain.Customer;
import com.example.store.service.CustomerService;
import com.example.store.service.ServiceException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImpTest {
    CustomerService customerService;
    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImp();
    }

    @AfterEach
    void tearDown() {
        customerService = null;
    }

    @Test
    @DisplayName("login successfully")
    void login1() {
        Customer customer = new Customer();
        customer.setId("1");
        customer.setPassword("12345");
        assertTrue(customerService.login(customer));
        assertNotNull(customer.getBirthday());
    }

    @Test
    @DisplayName("login unsuccessfully")
    void login2() {
        Customer customer = new Customer();
        customer.setId("1");
        customer.setPassword("1234");
        assertFalse(customerService.login(customer));
        assertNull(customer.getBirthday());
    }

    @Test
    @DisplayName("register successfully")
    void register1() throws ServiceException {

        Customer customer = new Customer();
        customer.setId("tom");
        customer.setPassword("try");
        customer.setBirthday(new Date(19980203L));
        customer.setName("duncan");
        customer.setPhone("10086");
        customer.setAddress("champaign");
        customerService.register(customer);

        CustomerDao customerDao = new CustomerDaoImpJdbc();
        Customer customer1 = customerDao.findByPk("1");
        assertNotNull(customer1);
        /*Customer customer1 = new Customer();
        customer1.setId("tom");
        customer1.setPassword("try");

        assertTrue(customerService.login(customer1));*/
    }

    @Test
    @DisplayName("register unsuccessfully")
    void register2() throws Exception {
        Customer customer = new Customer();
        customer.setId("1");
        customer.setPassword("try");
        customer.setBirthday(new Date(19980203));
        customer.setName("duncan");
        customer.setPhone("10086");
        customer.setAddress("champaign");
        customerService.register(customer);
    }
}