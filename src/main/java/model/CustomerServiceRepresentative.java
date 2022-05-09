package model;

import lombok.*;

import java.io.*;
import java.util.*;

@Builder
@Getter
public class CustomerServiceRepresentative{

    @NonNull
    private final int employeeId;

    @NonNull
    @Setter
    private boolean isFree = true;


}
