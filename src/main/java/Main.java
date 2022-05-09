import accessor.dao.EmployeeDAO;
import builder.CustomerBuilder;
import builder.CustomerServiceRepresentativeBuilder;
import handler.CallHandler;
import model.Call;
import model.CustomerServiceRepresentative;
import publicInterface.CallQueue;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        CallHandler callHandler = CallHandler.getCallHandlerInstance();
        CallQueue callQueue = new CallQueue(callHandler);
        EmployeeDAO employeeDAO = new EmployeeDAO();

        employeeDAO.addCustomerServiceRepresentatives(CustomerServiceRepresentativeBuilder.
                buildCustomerServiceRepresentative(1));
//        employeeDAO.addCustomerServiceRepresentatives(CustomerServiceRepresentativeBuilder.
//                buildCustomerServiceRepresentative(2));
//        employeeDAO.addCustomerServiceRepresentatives(CustomerServiceRepresentativeBuilder.
//                buildCustomerServiceRepresentative(3));
//        employeeDAO.addCustomerServiceRepresentatives(CustomerServiceRepresentativeBuilder.
//                buildCustomerServiceRepresentative(UUID.randomUUID()));
//        employeeDAO.addCustomerServiceRepresentatives(CustomerServiceRepresentativeBuilder.
//                buildCustomerServiceRepresentative(UUID.randomUUID()));
        callHandler.populateCSR(employeeDAO);

        Call call1 = new Call(CustomerBuilder.createCustomer(1, "1"));
        Call call2 = new Call(CustomerBuilder.createCustomer(2, "2"));
        Call call3 = new Call(CustomerBuilder.createCustomer(6, "6"));
        Call call4 = new Call(CustomerBuilder.createCustomer(3, "3"));
        Call call5 = new Call(CustomerBuilder.createCustomer(4, "4"));
        Call call6 = new Call(CustomerBuilder.createCustomer(5, "5"));

        long start = System.currentTimeMillis();
        callQueue.addNewCall(call1);
        callQueue.addNewCall(call2);
        callQueue.addNewCall(call3);
        callQueue.addNewCall(call4);
        callQueue.addNewCall(call5);
        callQueue.addNewCall(call6);

//        Thread.currentThread().join();
        System.out.println("Total Time = " + (System.currentTimeMillis()-start)+"ms");
    }
}
