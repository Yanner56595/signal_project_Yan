package data_management;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.data_management.DataParser;
import com.data_management.DataStorage;

public class DataParsesTest {
    
    @Test
    void testParsingString() {
        DataParser parser = new DataParser("");

        assertArrayEquals(new String[] {"19", "1748117308859", "Cholesterol", "186.61616620768294"},
            parser.parseString("Patient ID: 19, Timestamp: 1748117308859, Label: Cholesterol, Data: 186.61616620768294"));
        assertArrayEquals(new String[] {"25", "1748117300107", "Saturation", "99.0"},
            parser.parseString("Patient ID: 25, Timestamp: 1748117300107, Label: Saturation, Data: 99.0%")); 
    }

    @Test
    void testReadData() {
        DataParser parser = new DataParser("testData1.txt");
        DataStorage dataStorage = DataStorage.getInstance();

        //Edge case
        try {
            parser.readData(dataStorage);
            assertEquals(dataStorage.getAllPatients().size(), 0); //Check nothing is added
        }
        catch (IOException e) {
            fail("Couldn't read file");
        }


        parser = new DataParser("testData2.txt");
        try {
            parser.readData(dataStorage);
            assertEquals(dataStorage.getAllPatients().size(), 2); //Check 2 patients
            assertEquals(
                dataStorage.getRecords(19, 1748117308859L, 1748117308859L).size(), 
                1); //Check if info about first patient is really saved
            assertEquals(
            dataStorage.getRecords(27, 1748117250111L, 1748117250113L).size(), 
            2); //Check if info about second patient is really saved
            
        }
        catch (IOException e) {
            fail("Couldn't read file");
        }
    }
}
