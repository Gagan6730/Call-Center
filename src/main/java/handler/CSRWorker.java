package handler;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import model.Call;
import model.CustomerServiceRepresentative;

import java.io.*;
import java.util.*;

@RequiredArgsConstructor
public class CSRWorker implements Runnable{

    @NonNull private CallHandler callHandler;
    @NonNull
    private final CustomerServiceRepresentative csr;

    @NonNull
    @Setter
    private Call call;

    @Override
    public void run() {
        synchronized (call)
        {
            call.startCall(csr);
            System.out.printf("CSR ID = %d, Call with call id %s started at time %f\n",  csr.getEmployeeId(), call.getCallingCustomer().getPhoneNum(),
                    call.getStartTime().doubleValue());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            call.endCall();
            System.out.printf("CSR ID = %d, Call with ph num %s ended at time %f\n",  csr.getEmployeeId(),call.getCallingCustomer().getPhoneNum(),
                    call.getStartTime().doubleValue());
            System.out.printf("CSR ID = %d, Call Time = %d\n",csr.getEmployeeId(),call.getCallTime());
            callHandler.addFreeCSR(csr);

        }
    }
}
