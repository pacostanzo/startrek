package com.mercadolibre.challenge.repository;

import com.mercadolibre.challenge.model.weather.report.WeatherReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by costa on 20/9/2018.
 */
@Component
public class DatabaseSeeder implements CommandLineRunner{
    @Autowired
    WeatherPredictionRepository repository;

    @Override
    public void run(String... strings) throws Exception {

        WeatherReport report = new WeatherReport(repository);
        report.execute();
    }
}