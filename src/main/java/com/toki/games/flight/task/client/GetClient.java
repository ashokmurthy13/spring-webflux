package com.toki.games.flight.task.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import com.toki.games.flight.task.model.BusinessFlightData;
import com.toki.games.flight.task.model.BusinessFlights;
import com.toki.games.flight.task.model.CheapFlightData;
import com.toki.games.flight.task.model.CheapFlights;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GetClient {

  public Flux<Object> callFlightProviders() {

    Flux<CheapFlights> cheapFlightDetails = getWebClient().get().uri("api/flights/cheap")
        .accept(MediaType.APPLICATION_JSON).exchange().doOnNext(response -> {
          HttpStatus status = response.statusCode();
          if (status.is5xxServerError()) {
            throw new WebClientException("Server Error. Please try after sometime.") {
              private static final long serialVersionUID = 8792473915672552095L;
            };
          } else if (status.is4xxClientError()) {
            throw new WebClientException("Client error. Not data found or may be a bad request") {
              private static final long serialVersionUID = -225909161063664494L;
            };
          }
        })
        .doOnSuccess(clientResponse -> System.out
            .println("clientResponse.statusCode() = " + clientResponse.statusCode()))
        .flatMap(clientResponse -> clientResponse.bodyToMono(CheapFlightData.class))
        .flatMapMany(cheapFlightData -> {
          return Flux.fromIterable(cheapFlightData.getData());
        })
        .filter(cheapFlights -> cheapFlights.getDeparture() > (System.currentTimeMillis() / 1000));

    Flux<BusinessFlights> businessFlightDetails = getWebClient().get().uri("api/flights/business")
        .accept(MediaType.APPLICATION_JSON).exchange()
        .doOnSuccess(clientResponse -> System.out
            .println("clientResponse.statusCode() = " + clientResponse.statusCode()))
        .flatMap(clientResponse -> clientResponse.bodyToMono(BusinessFlightData.class))
        .flatMapMany(businessFlightData -> {
          return Flux.fromIterable(businessFlightData.getData());
        }).filter(businessFlights -> businessFlights
            .getDepartureTime() > (System.currentTimeMillis() / 1000));

    Flux<Object> availableFlights = Flux.merge(cheapFlightDetails, businessFlightDetails);

    return availableFlights.switchIfEmpty(Mono.just("No flights are available at this time."));
  }

  public WebClient getWebClient() {
    return WebClient.builder().baseUrl("https://tokigames-challenge.herokuapp.com/")
        .defaultHeader("X-Client-Id", "ashok-test").build();
  }

}
