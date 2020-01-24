package com.toki.games.flight.task.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CheapFlights {

  private String route;
  private long departure;
  private long arrival;
}
