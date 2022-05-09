package model;

import lombok.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor
public class Call {

    public enum CallStatus{
        WAITING,
        STARTED,
        ENDED
    }

    @NonNull
    @Getter
    private final Customer callingCustomer;

    @Setter
    private CustomerServiceRepresentative customerServiceRepresentative;

    @Getter
    private AtomicLong startTime;

    @Getter
    private AtomicLong endTime;

    @NonNull
    private final AtomicLong calledTime = new AtomicLong(System.currentTimeMillis());

    private CallStatus callStatus = CallStatus.WAITING;


    public void startCall(@NonNull final CustomerServiceRepresentative csr)
    {
        if(this.startTime == null)
        {
            this.startTime = new AtomicLong();
        }
        this.customerServiceRepresentative = csr;
        this.customerServiceRepresentative.setFree(false);
        this.startTime.set(System.currentTimeMillis());
        this.callStatus = CallStatus.STARTED;
    }

    public void endCall()
    {
        if(this.endTime == null)
        {
            this.endTime = new AtomicLong();
        }
        this.endTime.set(System.currentTimeMillis());
        this.callStatus = CallStatus.ENDED;
        this.customerServiceRepresentative.setFree(true);
    }

    public synchronized long getCallTime()
    {
        return this.endTime.longValue()-this.startTime.longValue();
    }
}
