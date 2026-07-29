package org.top.menu.service;

import java.io.IOException;

public interface FileExportService {
    void saveLastResultToFile(String filePath) throws IOException;
}
