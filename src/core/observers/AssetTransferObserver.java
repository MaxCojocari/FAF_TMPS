package core.observers;

import java.io.FileWriter;
import java.io.IOException;

public class AssetTransferObserver implements Observer {
    private String logFilePath;

    public AssetTransferObserver(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public void update(String eventType, String data) {
        try (FileWriter writer = new FileWriter(logFilePath, true)) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
