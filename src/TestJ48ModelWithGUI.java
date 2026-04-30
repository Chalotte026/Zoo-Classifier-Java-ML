import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class TestJ48ModelWithGUI extends JFrame implements ActionListener {
    private final JTextField pregField;
    private final JTextField plasField;
    private final JTextField presureField;
    private final JTextField skinField;
    private final JTextField insuField;
    private final JTextField bmiField;
    private final JTextField pediField;
    private final JTextField ageField;
    private final JComboBox<Integer> ageComboBox;
    private final JLabel resultLabel;
    private final JButton classifyButton;

    public TestJ48ModelWithGUI() throws Exception {
        super("Test J48 Model");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the GUI components
        pregField = new JTextField(5);
        plasField = new JTextField(5);
        presureField = new JTextField(5);
        skinField = new JTextField(5);
        insuField = new JTextField(5);
        bmiField = new JTextField(5);
        pediField = new JTextField(5);
        // ageField = new JTextField(5);
        // ageField = new JSpinner(new SpinnerNumberModel(25, 0, 150, 1));
        ageField = new JTextField(5);
        resultLabel = new JLabel();
        classifyButton = new JButton("Classify");
        classifyButton.addActionListener(this);

        // Add the GUI components to a panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2));
        panel.add(new JLabel(" preg: "));
        panel.add(pregField);
        panel.add(new JLabel(" plas: "));
        panel.add(plasField);
        panel.add(new JLabel(" presure: "));
        panel.add(presureField);
        panel.add(new JLabel(" skin: "));
        panel.add(skinField);
        panel.add(new JLabel(" insu: "));
        panel.add(insuField);
        panel.add(new JLabel(" bmi: "));
        panel.add(bmiField);
        panel.add(new JLabel(" pedi: "));
        panel.add(pediField);
        //panel.add(new JLabel(" age: "));
        //panel.add(new JLabel(" age (years): "));
        //panel.add(ageField);
        Integer[] ageValues = new Integer[101];
        for (int i = 0; i <= 100; i++) {
            ageValues[i] = i;
        }
        ageComboBox = new JComboBox<>(ageValues);
        panel.add(new JLabel(" age: "));
        panel.add(ageComboBox);
        panel.add(new JLabel(" "));
        panel.add(classifyButton);
        panel.add(new JLabel(" Result: "));
        panel.add(resultLabel);

        // Add the panel to the frame and show the GUI
        getContentPane().add(panel, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        new TestJ48ModelWithGUI();
    }

    public void actionPerformed(ActionEvent event) {
        try {
            // Get the user input values
            double preg = Double.parseDouble(pregField.getText());
            double plas = Double.parseDouble(plasField.getText());
            double presure = Double.parseDouble(presureField.getText());
            double skin = Double.parseDouble(skinField.getText());
            double insu = Double.parseDouble(insuField.getText());
            double bmi = Double.parseDouble(bmiField.getText());
            double pedi = Double.parseDouble(pediField.getText());
            // double age = Double.parseDouble(ageField.getText());
            //double age = (double) ageField.getValue();
            double age = (Integer) ageComboBox.getSelectedItem();
            
         // Load the ARFF dataset
            BufferedReader reader = new BufferedReader(new FileReader("arff/diabetes.arff"));
            Instances data = new Instances(reader);
            reader.close();

            // Set the class index to the last attribute (class)
            data.setClassIndex(data.numAttributes() - 1);

            // Load the J48 model from file
            J48 model = (J48) weka.core.SerializationHelper.read("model/diabetes.model");

            // Create an instance with the user input values
            Instance instance = new DenseInstance(data.numAttributes());
            instance.setValue(0, preg);
            instance.setValue(1, plas);
            instance.setValue(2, presure);
            instance.setValue(3, skin);
            instance.setValue(4, insu);
            instance.setValue(5, bmi);
            instance.setValue(6, pedi);
            instance.setValue(7, age);
            instance.setDataset(data);

            // Classify the instance
            double result = model.classifyInstance(instance);

            // Set the result label to the predicted class value
            resultLabel.setText(data.classAttribute().value((int)result));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
