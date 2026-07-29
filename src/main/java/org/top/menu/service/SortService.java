package org.top.menu.service;

import java.util.List;

import org.top.menu.util.SortOrder;

public interface SortService {
    void executeSort(List<SortOrder> sortOrders);
    void executeEvenOddSort(int fieldId);
}
