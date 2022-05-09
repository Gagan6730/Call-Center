package publicInterface;

import handler.CallHandler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import model.Call;

import java.io.*;
import java.util.*;


@RequiredArgsConstructor
public class CallQueue {

    @NonNull
    private final Queue<Call> callQueue = new LinkedList<>();

    private final CallHandler callHandler;

    public void addNewCall(@NonNull final Call call)
    {
        callQueue.add(call);
        assignCall(call);
    }

    private void assignCall(@NonNull final Call call)
    {
        new Thread(() -> {
            try {
                callHandler.assignCall(call);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
