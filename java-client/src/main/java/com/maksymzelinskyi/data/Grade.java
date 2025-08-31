package com.maksymzelinskyi.data;

import java.time.LocalDate;

public record Grade(int id, int grade, int outOf, int defaultOutOf, LocalDate date, String comment, Period period) {

}
