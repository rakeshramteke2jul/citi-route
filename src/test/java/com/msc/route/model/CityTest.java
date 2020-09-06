package com.msc.route.model;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class CityTest {
	

    @Test
    public void build() {
        City city = City.build("Boston");
        Assert.assertEquals("BOSTON", city.getName());
    }

    @Test
    public void buildWithNeighbours() {
        City city = City.build("Boston");
        city.addNearby(City.build("Newark"))
                .addNearby(City.build("Philadelpia"));

        Set<City> nearby = city.getNearby();
        Assert.assertEquals(2, nearby.size());
        Assert.assertTrue(nearby.contains(City.build("Newark")));
    }


    @Test
    public void addNearby() {
        City city = City.build("Boston");
           city.addNearby(City.build("Newark"))
                .addNearby(City.build("Philadelphia"));

        Assert.assertEquals(2, city.getNearby().size());
    }


}
