package com.toki.games.flight.task.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BusinessFlights {

  private String departure;
  private String arrival;
  private long departureTime;
  private long arrivalTime;
}
