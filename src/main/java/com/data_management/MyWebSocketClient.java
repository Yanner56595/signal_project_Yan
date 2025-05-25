package com.data_management;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class MyWebSocketClient extends WebSocketClient {
    
    private DataStorage dataStorage;
    private DataParser dataParser = new DataParser(null);

    /**
     * Constructs a new instance of DataStorage, initializing the underlying storage
     * structure.
     */
    public MyWebSocketClient(URI serverUri, DataStorage dataStorage) {
        super(serverUri);
        this.dataStorage = dataStorage;
    }

    // Called when a client connects
    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("Connected to server");
        //conn.send("Welcome client!");

    }

    // Called when a client disconnects
    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Disconnected");
    }

    /**
     * Receives message about patient and add it to the dataStorage
     * 
     * @param message data about one patient
     */
    @Override
    public void onMessage(String message) {
        // If many messages separated by \n
        String[] messages = message.split("\n");
        for (int i = 0; i < messages.length; i++) {
            try {
                parseMessage(messages[i]);
            }
            catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
        System.out.println("Written successfully!");
    }

    /**
     * Parses data about patient and adds it to the dataStorage
     * 
     * @param message string info about patient
     * @throws Exception with message what's incorrect
     */
    public void parseMessage(String message) throws Exception {
            String[] values = dataParser.parseString(message);
            int patientId = 0;
            long timestamp = 0;
            double measurementValue = 0;
            if (values.length != 4) {
                throw new Exception("Wrong number of variables");
            }
            try {
                patientId = Integer.parseInt(values[0]);
            }
            catch (NumberFormatException e) {
                throw new Exception("Wrong Id of the patient");
            }
            try{
                timestamp = Long.parseLong(values[1]);
            }
            catch (NumberFormatException e) {
                throw new Exception("Wrong timestamp of the patient");
            }
            String label = values[2];
            try {
                measurementValue = Double.parseDouble(values[3]);
            }
            catch (NumberFormatException e) {
                throw new Exception("Wrong measurement value of the patient");
            }
            dataStorage.addPatientData(patientId, measurementValue, label, timestamp);
    }

    /**
     * Writes message when connection lost and 
     * tries to reconnect
     * 
     * @param conn connection
     * @param ex Exception messsage
     */
    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        tryReconnect();
    }

    /**
     * Tries to reconnect with server 
     * in case of error with connection
     */
    public void tryReconnect() {
        // Try to reconnect 3 times
        int tries = 3;
        for (int i = 0; i < tries; i++) {
            try {
                //Wait till reconnect
                Thread.sleep(3000);
                this.reconnectBlocking();
                System.out.println("Reconnected!");
            } 
            catch (InterruptedException | IllegalStateException e) {
                System.err.println("Reconnect attempt failed: " + e.getMessage());
            }
        }
    }
}
