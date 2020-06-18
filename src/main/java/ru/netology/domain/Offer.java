package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Offer {
    private int id;
    private String flightFrom;
    private String flightTo;
    private int flightTime;
}
