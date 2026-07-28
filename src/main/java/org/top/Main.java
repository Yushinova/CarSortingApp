package org.top;

import org.top.builder.CarBuilder;
import org.top.comparator.CarComparator;
import org.top.config.GlobalSortConfig;
import org.top.model.Car;
import org.top.sorting.BubbleSorter;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //установка алгоритма сортировки
        GlobalSortConfig.getInstance().setSorter(new BubbleSorter<>());
    }
}