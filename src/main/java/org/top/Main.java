package org.top;

import org.top.builder.CarBuilder;
import org.top.model.Car;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Car car1 = new CarBuilder()
                .setBrand("BMW")
                .setModel("S300")
                .setColor("white")
                .setIsNew(true)
                .setPower(120)
                .setPrice(12000000.00)
                .setYear(2026)
                .build();
        System.out.println(car1);
    }
}