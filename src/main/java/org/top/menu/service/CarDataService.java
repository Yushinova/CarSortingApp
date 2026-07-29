package org.top.menu.service;

import java.io.IOException;

public interface CarDataService {
    void fillManually(int size);
    void fillRandomly(int size);
    void fillFromFile(String filePath) throws IOException;
}
