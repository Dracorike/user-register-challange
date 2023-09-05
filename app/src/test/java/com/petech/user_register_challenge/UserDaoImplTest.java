package com.petech.user_register_challenge;


import static org.junit.Assert.assertEquals;

import android.util.Log;

import com.petech.user_register_challenge.data.dao.UserDaoUtil;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserDaoImplTest {

    @Test
    public void convert_local_date_to_string_iso86() {
        LocalDate date = LocalDate.parse("2023-09-04");
        String result = UserDaoUtil.convertDateToStringIso8601(date);

        assertEquals(result, "2023-09-04T00:00:01.000");
    }

    @Test
    public void convert_string_iso86_to_local_date() {
        LocalDate result = UserDaoUtil.convertStringIso8601ToLocalDate("2023-09-04T00:00:01.000");

        assertEquals(result, LocalDate.parse("2023-09-04"));
    }

    @Test
    public void test_converts_together() {
        LocalDate date = LocalDate.parse("2023-09-04");
        String localDateString = UserDaoUtil.convertDateToStringIso8601(date);
        LocalDate dateResult = UserDaoUtil.convertStringIso8601ToLocalDate(localDateString);

        assertEquals(dateResult, date);
    }

    @Test
    public void convertToDate() {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate local = LocalDate.parse("05/09/2023", formatters);

        assertEquals(local.toString(), "2023-09-05");
    }
}
