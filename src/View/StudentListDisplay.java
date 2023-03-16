package View;

import Model.Student;
import Model.StudentLinkedList;
import Utils.FileHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;


//Класс отображения объекта StudentLinkedList
public class StudentListDisplay extends JFrame {
    private JPanel mainPanel;
    private JButton addRowButton;
    private JButton deleteRowButton;
    private JTable mainTable;
    private JButton formatButton;
    private JButton chooseFileButton;
    DefaultTableModel tableModel;
    StudentLinkedList studentLinkedList;
    FileHandler fileHandler = new FileHandler();

    public StudentListDisplay(StudentLinkedList studentLinkedList) {
        this.setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(550, 500));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setName("Bebra-IT: AiSD Task #2");

        this.studentLinkedList = studentLinkedList;

        //Зададим таблице необходимые настройки
        tableModel = new DefaultTableModel(2, 2);
        tableModel.setColumnIdentifiers(new Object[]{"Name", "Age"});
        mainTable.setModel(tableModel);

        addRowButton.addActionListener(e -> {
            tableModel.setRowCount(tableModel.getRowCount() + 1);
        });

        deleteRowButton.addActionListener(e -> {
            if (tableModel.getColumnCount() > 1) {
                tableModel.setRowCount(tableModel.getRowCount() - 1);
            }
        });

        chooseFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
            fileChooser.setName("Choose input file:");
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    this.fillJTableFromFile(fileChooser.getSelectedFile());
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        formatButton.addActionListener(e -> {
            Map<String, Integer> tableInfo = this.getTableInfo();
            studentLinkedList.updateFromList(Student.parseMapToList(tableInfo));
            studentLinkedList.format();
            this.updateUI();
        });

        this.pack();
        this.setVisible(true);
    }

    public void updateUI() {
        tableModel.setRowCount(studentLinkedList.getSize());

        for(int i = 0; i < tableModel.getRowCount(); i++) {
            tableModel.setValueAt(studentLinkedList.get(i).getName(), i, 0);
            tableModel.setValueAt(studentLinkedList.get(i).getAge(), i, 1);
        }
    }

    public void fillJTableFromFile(File file) throws FileNotFoundException {
        String[][] matrix = fileHandler.readMatrixFromFile(file);

        tableModel.setRowCount(matrix.length);
        tableModel.setColumnCount(matrix[0].length);

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                tableModel.setValueAt(matrix[i][j], i, j);
            }
        }
    }

    public Map<String, Integer> getTableInfo() {
        Map<String, Integer> infoDict = new HashMap<>();

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String name = null;
            Integer age = null;

            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                if (j == 0){
                    name = String.valueOf(tableModel.getValueAt(i, j));
                }
                if (j == 1){
                    age = Integer.parseInt(String.valueOf(tableModel.getValueAt(i, j)));
                }
            }

            infoDict.put(name, age);
        }

        return infoDict;
    }
}
