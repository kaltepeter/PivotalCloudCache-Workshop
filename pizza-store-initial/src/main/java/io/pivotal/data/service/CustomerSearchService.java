package io.pivotal.data.service;

import io.pivotal.data.domain.Customer;
import io.pivotal.data.jpa.repo.CustomerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CustomerSearchService {

    @Autowired
    CustomerJpaRepository customerJpaRepository;

    private volatile boolean cacheMiss = false;

    public boolean isCacheMiss() {
        boolean isCacheMiss = this.cacheMiss;
        this.cacheMiss = false;
        return isCacheMiss;
    }

    protected void setCacheMiss() {
        this.cacheMiss = true;
    }

    @Cacheable(value = "customer_kayla")
    public Customer getCustomerByEmail(String email) {

        setCacheMiss();

        Customer customer = customerJpaRepository.findByEmail(email);

        return customer;
    }

}