package com.example.weatherstationspring.external.measurement.temporaryFakeAdapter;

import com.example.weatherstationspring.domain.measurement.ValuesMeasurement;
import com.example.weatherstationspring.domain.measurement.model.Measurement;

import java.time.OffsetDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class FakeMeasurementAdapter implements ValuesMeasurement {
    @Override
    public Measurement measureValue() {
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        return new Measurement(55L, offsetDateTime, 10.0F,
                10.1F, 15.5F, 10, 11, 15,
                "Good", 51.773, 19.77702);
    }

    public static Float generateRandomFloat(Float min, Float max) {
        if (min >= max)
            throw new IllegalArgumentException("max must be greater than min");
        float result = ThreadLocalRandom.current().nextFloat() * (max - min) + min;
        if (result >= max) // correct for rounding
            result = Float.intBitsToFloat(Float.floatToIntBits(max) - 1);
        return result;
    }
}