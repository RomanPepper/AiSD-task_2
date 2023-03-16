package Utils;

import java.util.List;

public class ListUtils {
    public static String[][] stringListMatrixToArrayMatrix(List<List<String>> listMatrix) {
        String[][] arrayMatrix = new String[listMatrix.size()][listMatrix.get(0).size()];

        for(int i = 0; i < listMatrix.size(); i++) {
            for(int j = 0; j < listMatrix.get(0).size(); j++) {
                arrayMatrix[i][j] = listMatrix.get(i).get(j);
            }
        }

        return arrayMatrix;
    }
}
