package com.mercadolibre.challenge.model.weather;

import com.mercadolibre.challenge.model.entities.CelestialBody;
import com.mercadolibre.challenge.model.entities.Planet;
import com.mercadolibre.challenge.model.entities.Sun;
import com.mercadolibre.challenge.model.physics.Coordinate;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by costa on 17/9/2018.
 */
public class WeatherTests {

    private IWeather weather;
    private CelestialBody sun = new Sun();
    private List<CelestialBody> planets;
    private CelestialBody ferengi;
    private CelestialBody betasoide;
    private CelestialBody vulcano;
    private Coordinate ferengiCoorninate;
    private Coordinate vulcanoCoorninate;
    private Coordinate betasoideCoorninate;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        sun = new Sun();
        ferengiCoorninate = new Coordinate(500, 90);
        vulcanoCoorninate = new Coordinate(2000, 90);
        betasoideCoorninate = new Coordinate(1000, 90);
        vulcano = new Planet("vulcano",vulcanoCoorninate ,1);
        betasoide = new Planet("betasoide", betasoideCoorninate,-1);
        ferengi = new Planet("ferengi", ferengiCoorninate,1);
    }

    /***
     * All planets and sun are collinear
     */
    @Test
    public void checkIsDroughtDayAssertTrue() {
        planets = new ArrayList<>();
        planets.add(ferengi);
        planets.add(vulcano);
        planets.add(betasoide);
        weather = new Drought(planets, sun);

        assertTrue(weather.hasThisWeather());
    }


    /***
     * Vulcano Planet is not collinear.
     */
    @Test
    public void checkIsDroughtDayAssertFalse() {
        planets = new ArrayList<>();
        planets.add(ferengi);
        vulcano.getLocation(2);
        planets.add(vulcano);
        planets.add(betasoide);
        weather = new Drought(planets, sun);

        assertFalse(weather.hasThisWeather());
    }

    /***
     * All planets and sun are collinear
     */
    @Test
    public void checkIsNormalDayAssertTrue() {
        planets = new ArrayList<>();
        Coordinate c1 = new Coordinate(1.0,1.0);
        Coordinate c2 = new Coordinate(-1.0,1.0);
        Coordinate c3 = new Coordinate(-2.0, 1.0);
        vulcano = new Planet("ferengi",c1 ,1);
        betasoide = new Planet("ferengi", c2,-1);
        ferengi = new Planet("ferengi", c3,1);

        planets.add(ferengi);
        planets.add(vulcano);
        planets.add(betasoide);
        weather = new Normal(planets, sun);

        assertTrue(weather.hasThisWeather());
    }



}
