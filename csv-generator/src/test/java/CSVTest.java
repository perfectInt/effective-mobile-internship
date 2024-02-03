import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.junit.jupiter.api.Test;
import ru.effectivemobile.BusinessObject;
import ru.effectivemobile.CustomCSVWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CSVTest {

    String filePath = "C:/temp/file.csv";
    List<BusinessObject> data = new ArrayList<>();

    @Test
    public void writeToFile() {
        CustomCSVWriter<BusinessObject> csvWriter = new CustomCSVWriter<>();
        data.add(new BusinessObject(1, "Item 1"));
        data.add(new BusinessObject(2, "Item 2"));

        try {
            csvWriter.writeToFile(data, filePath);

            File file = new File(filePath);
            assertTrue(file.exists());
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            System.err.println(e.getMessage());
        }
    }
}
