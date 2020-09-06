package com.mgiandia.library.util;


import java.time.LocalDate;

public class SystemDateStub {

    public static void setStub(LocalDate stub) {
        SystemDate.setStub(stub);
    }
    
    public static void reset() {
        SystemDate.removeStub();
    }
    
}
