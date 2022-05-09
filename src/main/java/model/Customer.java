package model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.util.*;

@RequiredArgsConstructor
@Getter
@Builder
public class Customer {

    @NonNull
    private final int customerId;

    @NonNull
    private final String phoneNum;

}
