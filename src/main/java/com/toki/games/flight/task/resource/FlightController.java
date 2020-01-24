package com.toki.games.flight.task.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.toki.games.flight.task.client.GetClient;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class FlightController {

  @Autowired
  GetClient CLIENT;

  @GetMapping(value = "/get/available-flights")
  public Flux<Object> getAvailableFights() {
    return CLIENT.callFlightProviders();
  }
}
