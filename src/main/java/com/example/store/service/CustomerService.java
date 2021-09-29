package com.example.store.service;

import com.example.store.domain.Customer;

public interface CustomerService {
    /**
     *  processing login
     * @param customer
     * @return
     */
    boolean login(Customer customer);

    /**
     *
     * @param customer
     * @throws Exception
     */
    void register(Customer customer) throws ServiceException;


}
