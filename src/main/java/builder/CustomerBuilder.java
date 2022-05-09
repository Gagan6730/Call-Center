package builder;

import model.Customer;

import java.io.*;
import java.util.*;

public class CustomerBuilder {
    public static Customer createCustomer(int customerId, String phNum)
    {
        System.out.printf("Creating customer with phone num %s\n",phNum);
        return Customer.builder()
                .customerId(customerId)
                .phoneNum(phNum)
                .build();
    }
}
