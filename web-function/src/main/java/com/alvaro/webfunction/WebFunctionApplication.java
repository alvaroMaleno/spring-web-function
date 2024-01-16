package com.alvaro.webfunction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class WebFunctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebFunctionApplication.class, args);
	}

	List<TollStation> tollStations;

	public WebFunctionApplication() {
		tollStations = new ArrayList<>();
		tollStations.add(new TollStation("101A", 112.4f, 2));
		tollStations.add(new TollStation("112C", 124.1f, 4));
		tollStations.add(new TollStation("221C", 126.2f, 2));
	}

	@Bean
	public Function<String, TollStation> retrieveStation(){
		return value -> {
			System.out.println("received request for station - " + value);
			return tollStations.stream()
					.filter(toll -> value.equals(toll.getStationId()))
					.findAny()
					.orElse(null);
		};
	}

	@Bean
	public Consumer<TollRecord> processTollRecord(){
		return  value -> {
			System.out.println("received toll for car with license plate - " + value.getLicensePlate());
		};
	}

	@Bean
	public Function<TollRecord, Mono<Void>> processTollRecordReactive(){
		return  value -> {
			System.out.println("received toll for car with license plate - " + value.getLicensePlate());
            return Mono.empty();
        };
	}

	@Bean
	public Consumer<Flux<TollRecord>> processListofTollRecordsReactive(){
		return value -> {
			value.subscribe(toll -> System.out.println(toll.getLicensePlate()));
		};
	}

	@Bean
	public Supplier<Flux<TollStation>> getTollStations(){
		return () -> Flux.fromIterable(tollStations);
	}
}
