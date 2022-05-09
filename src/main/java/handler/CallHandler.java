package handler;

import accessor.dao.EmployeeDAO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import model.Call;
import model.CustomerServiceRepresentative;

import java.io.*;
import java.util.*;

public class CallHandler {

    private static CallHandler callHandler;
    private CallHandler()
    {

    }

    public static CallHandler getCallHandlerInstance()
    {
        if(callHandler == null)
        {
            callHandler = new CallHandler();
        }
        return callHandler;
    }
    private final Queue<CustomerServiceRepresentative> customerServiceRepresentatives = new LinkedList<>();

    private final Map<Integer, CSRWorker> csrIdToCSRWorkerMap = new HashMap<>();

    public void populateCSR(@NonNull final EmployeeDAO employeeDAO)
    {
        customerServiceRepresentatives.addAll(employeeDAO.getAllCustomerServiceRepresentatives());
    }

    public synchronized void addFreeCSR(@NonNull final CustomerServiceRepresentative csr)
    {
        this.customerServiceRepresentatives.add(csr);
        this.notifyAll();
    }



    public synchronized void assignCall(@NonNull final Call call) throws InterruptedException {
        while(customerServiceRepresentatives.isEmpty())
        {
            this.wait();
        }

        CustomerServiceRepresentative csr = customerServiceRepresentatives.remove();
        int csrId = csr.getEmployeeId();
        if(!this.csrIdToCSRWorkerMap.containsKey(csrId))
        {
            //TODO : use a builder
            final CSRWorker csrWorker =  new CSRWorker(this, csr, call);
            this.csrIdToCSRWorkerMap.put(csrId, csrWorker);
        }

        final CSRWorker worker = this.csrIdToCSRWorkerMap.get(csrId);
        worker.setCall(call);
        new Thread(worker).start();



    }


}
