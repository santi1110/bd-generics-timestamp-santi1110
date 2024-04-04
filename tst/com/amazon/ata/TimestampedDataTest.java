package com.amazon.ata;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TimestampedDataTest {
    private static final ZonedDateTime SAMPLE_TIMESTAMP = ZonedDateTime.parse("2019-07-31T08:00:19Z[UTC]");


    @Test
    void dataAndTimestampConstructor_withNonNullValues_constructsInstance() {
        // GIVEN - valid data, timestamp
        String data = "My message";
        ZonedDateTime timestamp = SAMPLE_TIMESTAMP;

        // WHEN - construct TimestampedData
        TimestampedData<String> timestampedData = new TimestampedData<>(data, timestamp);

        // THEN
        // PARTICIPANTS: data is correct
/*        fail("Replace with an assertion that data stored in timestampedData is what we expect");*/
        assertEquals(data, timestampedData.getData(), "Expected data to match");
        // timestamp is correct
        assertEquals(timestamp, timestampedData.getTimestamp(), "Expected timestamp to match");
    }

    @Test
    void dataAndTimestampConstructor_withNullData_constructsInstance() {
        // GIVEN - null data, valid timestamp
        Double data = null;
        ZonedDateTime timestamp = SAMPLE_TIMESTAMP;

        // WHEN - construct TimestampedData
        TimestampedData<Double> timestampedData = new TimestampedData<>(data, timestamp);

        // THEN
        // PARTICIPANTS: data is null
        /*fail("Replace with an assertion that data stored in timestampedData is null");*/
        assertEquals(data, timestampedData.getData(), "Expected data to be null");
        // timestamp is correct
        assertEquals(timestamp, timestampedData.getTimestamp(), "Expected timestamp to match");
    }

    @Test
    void dataAndTimestampConstructor_withNullTimestamp_throwsException() {
        // GIVEN - valid data, null timestamp
        ZonedDateTime data = ZonedDateTime.now();
        ZonedDateTime timestamp = null;

        // WHEN - construct TimestampedData
        // THEN - throw exception
        assertThrows(IllegalArgumentException.class,
                     () -> new TimestampedData<>(data, timestamp),
                     "Expected IllegalArgumentException when passing in null timestamp");
    }

    @Test
    void dataConstructor_withData_defaultsTimestampToNow() {
        // GIVEN - valid data
        List<String> data = new ArrayList<>();
        data.add("Hello");
        data.add("there!");

        // WHEN - construct TimestampedData
        TimestampedData<List<String>> timestampedData = new TimestampedData<>(data);

        // THEN
        // PARTICIPANTS - data is correct

       /* fail("Replace with an assertion that data stored in timestampedData is what we expect");*/
        assertEquals(data, timestampedData.getData(), "Expected data to match");
        // timestamp is close to now (within 5 seconds)
        Duration durationBetweenTimestampAndNow = Duration.between(timestampedData.getTimestamp(), ZonedDateTime.now());
        assertTrue(durationBetweenTimestampAndNow.toMillis() < 5_000);
    }

    // PARTICIPANTS: Implement this test
    @Test
    void dataConstructor_withNullData_constructsInstance() {

        Double data = null;
        // GIVEN - null data
        TimestampedData<Double> timestampedData = new TimestampedData<>(data);
        // WHEN - construct Timestamped data

        // THEN
        // data is null
        assertEquals(data, timestampedData.getData(), "Expected data to be null");
        // timestamp is close to now (within 5 seconds)
        Duration durationBetweenTimestampAndNow = Duration.between(timestampedData.getTimestamp(), ZonedDateTime.now());
        assertTrue(durationBetweenTimestampAndNow.toMillis() < 5_000);
       /* fail("Remove after implementing this test.");*/

    }
}
