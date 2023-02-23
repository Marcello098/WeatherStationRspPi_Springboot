package org.example.weatherstationspring.external.measurement.helpers;

import com.pi4j.context.Context;
import com.pi4j.context.ContextBuilder;

public class PI4JChild {
    public static ContextBuilder newContextBuilder() {
        return ContextBuilder.newInstance();
    }

    public static Context newContext() {
        return newContextBuilder().build();
    }

    public static Context newAutoContext() {
        return newContextBuilder().autoDetect().build();
    }
}

