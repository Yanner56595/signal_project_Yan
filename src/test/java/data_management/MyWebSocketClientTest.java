package data_management;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.WebSocketServer.MyWebSocketServer;
import com.cardio_generator.outputs.WebSocketOutputStrategy;
import com.data_management.DataStorage;
import com.data_management.MyWebSocketClient;
import com.data_management.PatientRecord;

import java.net.URI;
import java.util.List;

public class MyWebSocketClientTest {

    @Test
    void test1() {
        // Try to successfully connect
        MyWebSocketServer server = new MyWebSocketServer(8887); // Port 8887
        server.start(); // Start the server
        DataStorage storage = DataStorage.getInstance();
        try {
            URI serverUri = new URI("ws://localhost:8887");
            MyWebSocketClient webClient = new MyWebSocketClient(serverUri, storage);
            webClient.connectBlocking(); // Wait until connected
            return;
        }
        catch (Exception ex) {
            fail("Error to connect");
        }
    }

    /* 
    @Test
    void test2() {
        // Try to add data
        WebSocketOutputStrategy webOutput = new WebSocketOutputStrategy(8887);
        DataStorage storage = DataStorage.getInstance();
        try {
            URI serverUri = new URI("ws://localhost:8887");
            MyWebSocketClient webClient = new MyWebSocketClient(serverUri, storage);
            webClient.connectBlocking(); // Wait until connected
            webOutput.output(1, 1L, "Type 1", "3.0");
            PatientRecord record = new PatientRecord(1, 3.0, "Type 1", 1L);

            List<PatientRecord> records = storage.getRecords(1, 1L, 2L);
            assertEquals(1, records.size());
            assertEquals(record, records.get(0));
        }
        catch (Exception ex) {
            fail("Error to connect");
        }
    }
        */

    @Test
    void test3() {
        // Test duplicates
        DataStorage storage = DataStorage.getInstance();
        storage.addPatientData(1, 3.0, "Type 1", 1L);
        storage.addPatientData(1, 3.0, "Type 1", 1L);
        assertEquals(storage.getRecords(1, 1L, 2L).size(), 1);
        
    }
    
}
