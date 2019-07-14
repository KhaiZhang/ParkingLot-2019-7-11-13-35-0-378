package com.thoughtworks.tdd.story6test;

import com.thoughtworks.tdd.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MangerTest {
    @Test
    public void should_return_car_when_manager_order_parkingBoy_to_fetch_car() throws Exception{
        //given
        Car car = new Car("C：12138");
        ParkingLot parkingLot = new ParkingLot(10);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Manager manager = new Manager(parkingLots);
        manager.addParkingBoy(parkingBoy);
        manager.addParkingBoy(smartParkingBoy);
        //when
        Ticket ticket = manager.orderParkingBoyParkCar(car,parkingBoy);
        Car actualCar = manager.orderParkingBoyFetchCar(ticket,parkingBoy);
        //then
        assertThat(car, is(actualCar));
    }

    @Test
    public void should_return_car_when_manager_fetch_car() throws Exception{
        //given
        Car car = new Car("C：12138");
        ParkingLot parkingLot = new ParkingLot(10);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        Manager manager = new Manager(parkingLots);
        //when
        Ticket ticket = manager.parkCar(car);
        Car actualCar = manager.fetchCar(ticket);
        Ticket wrongTicket = new Ticket();
        //then
        assertThat(car, is(actualCar));
        String message = Assertions.assertThrows(Exception.class, () -> manager.fetchCar(wrongTicket)).getMessage();
        Assertions.assertTrue(message.contains("Unrecognized parking ticket"));
    }
}
