package builder;

import model.CustomerServiceRepresentative;

import java.io.*;
import java.util.*;

public class CustomerServiceRepresentativeBuilder {

    public static CustomerServiceRepresentative buildCustomerServiceRepresentative(int employeeId)
    {
        System.out.printf("Creating CSR with id %s\n",employeeId);
        return CustomerServiceRepresentative.builder()
                .employeeId(employeeId)
                .isFree(true)
                .build();
    }

}
