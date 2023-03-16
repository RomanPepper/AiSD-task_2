package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    public String[][] readMatrixFromFile(File file) throws FileNotFoundException {
        FileReader fileReader = new FileReader(file);
        Scanner scanner = new Scanner(fileReader);

        List<List<String>> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String currentRow = scanner.nextLine();
            list.add(Arrays.stream(currentRow.split(", | ,| |,")).toList());
        }

        return ListUtils.stringListMatrixToArrayMatrix(list);
    }
}
