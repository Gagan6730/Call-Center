package accessor.dao;

import lombok.Getter;
import model.CustomerServiceRepresentative;

import java.io.*;
import java.util.*;

public class EmployeeDAO {

    @Getter
    private final List<CustomerServiceRepresentative> allCustomerServiceRepresentatives = new ArrayList<>();

    public void addCustomerServiceRepresentatives(CustomerServiceRepresentative csr)
    {
        this.allCustomerServiceRepresentatives.add(csr);
    }
}

