package org.top.thread;

import java.util.List;

import org.top.collection.CustomList;

public class ThreadManager {
    public static <T> int multiThreadCounter(List<T> list, T element, int numberThreads) {
        if (list == null || list.isEmpty() || numberThreads <= 0)
            return 0;

        int size = list.size();
        if (numberThreads > size)
            numberThreads = size;

        List<Thread> threads = new CustomList<>();
        List<ElementCounter<T>> workers = new CustomList<>();
        int arrayThreadSize = size / numberThreads;
        int remainder = size % numberThreads;
        int currentStart = 0;

        for (int i = 0; i < numberThreads; i++) {
            int currentEnd = currentStart + arrayThreadSize + (i < remainder ? 1 : 0);

            ElementCounter<T> worker = new ElementCounter<T>(list, currentStart, currentEnd, element);
            workers.add(worker);
            threads.add(new Thread(worker));

            currentStart = currentEnd;
        }

        for (Thread t : threads)
            t.start();

        try {
            for (Thread t : threads)
                t.join();
        } catch (InterruptedException e) {
            System.err.println("Поток прерван: " + e.getMessage());
            Thread.currentThread().interrupt();
            return -1;
        }

        int totalCount = 0;
        for (ElementCounter<T> worker : workers)
            totalCount += worker.getCount();

        return totalCount;
    }
}
