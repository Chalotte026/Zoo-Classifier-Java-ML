import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import weka.classifiers.trees.LMT;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class zooWithGUI extends JFrame implements ActionListener {
	 private final JComboBox<String> animalComboBox;
	 private final JTextField hairSpinner;
	 private final JTextField feathersSpinner;
	 private final JTextField eggsSpinner;
	 private final JTextField milkSpinner;
	 private final JTextField airborneSpinner;
	 private final JTextField aquaticSpinner;
	 private final JTextField predatorSpinner;
	 private final JTextField toothedSpinner;
	 private final JTextField backboneSpinner;
	 private final JTextField breathesSpinner;
	 private final JTextField venomousSpinner;
	 private final JTextField finsSpinner;
	 private final JTextField legsSpinner;
	 private final JTextField tailSpinner;
	 private final JTextField domesticSpinner;
	 private final JTextField catsizeSpinner;
	 private final JLabel resultLabel;
	 private final JButton classifyButton;
	 /*
	 String[] animalNames = {"aardvark", "antelope", "bass", "bear", "boar", "buffalo", "calf", "carp", "catfish", "cavy", "cheetah",
		        "chicken", "chub", "clam", "crab", "crayfish", "crow", "deer", "dogfish", "dolphin", "dove", "duck",
		        "elephant", "flamingo", "flea", "frog", "frog", "fruitbat", "giraffe", "girl", "gnat", "goat", "gorilla",
		        "gull", "haddock", "hamster", "hare", "hawk", "herring", "honeybee", "housefly", "kiwi", "ladybird", 
		        "lark", "leopard", "lion", "lobster", "lynx", "mink", "mole", "mongoose", "moth", "newt", "octopus", 
		        "opossum", "oryx", "ostrich", "parakeet", "penguin", "pheasant", "pike", "piranha", "pitviper", 
		        "platypus", "polecat", "pony", "porpoise", "puma", "pussycat", "raccoon", "reindeer", "rhea", 
		        "scorpion", "seahorse", "seal", "sealion", "seasnake", "seawasp", "skimmer", "skua", "slowworm", 
		        "slug", "sole", "sparrow", "squirrel", "starfish", "stingray", "swan", "termite", "toad", "tortoise",
		        "tuatara", "tuna", "vampire", "vole", "vulture", "wallaby", "wasp", "wolf", "worm", "wren"};*/
	 
	 String[] animalNames = {"1. aardvark", "2. antelope", "3. bass", "4. bear", "5. boar", "6. buffalo", "7. calf", "8. carp", "9. catfish", "10. cavy", "11. cheetah",
             "12. chicken", "13. chub", "14. clam", "15. crab", "16. crayfish", "17. crow", "18. deer", "19. dogfish", "20. dolphin", "21. dove", "22. duck",
             "23. elephant", "24. flamingo", "25. flea", "26. frog", "27. frog", "28. fruitbat", "29. giraffe", "30. girl", "31. gnat", "32. goat", "33. gorilla",
             "34. gull", "35. haddock", "36. hamster", "37. hare", "38. hawk", "39. herring", "40. honeybee", "41. housefly", "42. kiwi", "43. ladybird", 
             "44. lark", "45. leopard", "46. lion", "47. lobster", "48. lynx", "49. mink", "50. mole", "51. mongoose", "52. moth", "53. newt", "54. octopus", 
             "55. opossum", "56. oryx", "57. ostrich", "58. parakeet", "59. penguin", "60. pheasant", "61. pike", "62. piranha", "63. pitviper", 
             "64. platypus", "65. polecat", "66. pony", "67. porpoise", "68. puma", "69. pussycat", "70. raccoon", "71. reindeer", "72. rhea", 
             "73. scorpion", "74. seahorse", "75. seal", "76. sealion", "77. seasnake", "78. seawasp", "79. skimmer", "80. skua", "81. slowworm", 
             "82. slug", "83. sole", "84. sparrow", "85. squirrel", "86. starfish", "87. stingray", "88. swan", "89. termite", "90. toad", "91. tortoise",
             "92. tuatara", "93. tuna", "94. vampire", "95. vole", "96. vulture", "97. wallaby", "98. wasp", "99. wolf", "100. worm", "101. wren"};

	 public zooWithGUI() throws Exception {
		 super("Test LMT Model");
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 animalComboBox = new JComboBox<>(animalNames);
		 hairSpinner = new JTextField(5);
		 feathersSpinner = new JTextField(5);
		 eggsSpinner = new JTextField(5);
		 milkSpinner = new JTextField(5);
		 airborneSpinner = new JTextField(5);
		 aquaticSpinner = new JTextField(5);
		 predatorSpinner = new JTextField(5);
		 toothedSpinner = new JTextField(5);
		 backboneSpinner = new JTextField(5);
		 breathesSpinner = new JTextField(5);
		 venomousSpinner = new JTextField(5);
		 finsSpinner = new JTextField(5);
		 legsSpinner = new JTextField(5);
		 tailSpinner = new JTextField(5);
		 domesticSpinner = new JTextField(5);
		 catsizeSpinner = new JTextField(5);
		 classifyButton = new JButton("Classify");		 
	     classifyButton.addActionListener(this);
	     
	     resultLabel = new JLabel("");
	     JPanel panel = new JPanel(new GridLayout(19, 2));
	     JLabel label1 = new JLabel("  Animal in ZOO");
	     label1.setFont(new Font("Arial", Font.PLAIN, 20));

	     JLabel label2 = new JLabel("Animal Detail: (false = 0/true = 1)");	
	     
	     JLabel label3 = new JLabel("Animal Name : ");
	     label3.setHorizontalAlignment(SwingConstants.RIGHT);
	     JLabel label4 = new JLabel("Hair : ");
	     label4.setHorizontalAlignment(SwingConstants.RIGHT);
	     JLabel label5 = new JLabel("Feathers : ");
	     label5.setHorizontalAlignment(SwingConstants.RIGHT);
	     JLabel label6 = new JLabel("Eggs : ");
	     label6.setHorizontalAlignment(SwingConstants.RIGHT);
	     JLabel label7 = new JLabel("Milk : ");
	     label7.setHorizontalAlignment(SwingConstants.RIGHT);
	     JLabel label8 = new JLabel("Airborne : ");
	     label8.setHorizontalAlignment(SwingConstants.RIGHT);
	     JLabel label9 = new JLabel("Aquatic : ");
	     label9.setHorizontalAlignment(SwingConstants.RIGHT);
	     JLabel label10 = new JLabel("Predator : ");
	     label10.setHorizontalAlignment(SwingConstants.RIGHT);
	     JLabel label11 = new JLabel("Toothed : ");
	     label11.setHorizontalAlignment(SwingConstants.RIGHT);
	     JLabel label12 = new JLabel("Backbone : ");
	     label12.setHorizontalAlignment(SwingConstants.RIGHT);
	     JLabel label13 = new JLabel("Breathes : ");
	     label13.setHorizontalAlignment(SwingConstants.RIGHT);
	     JLabel label14 = new JLabel("Venomous : ");
	     label14.setHorizontalAlignment(SwingConstants.RIGHT);
	     JLabel label15 = new JLabel("Fins : ");
	     label15.setHorizontalAlignment(SwingConstants.RIGHT);
	     JLabel label16 = new JLabel("Number of Legs : ");
	     label16.setHorizontalAlignment(SwingConstants.RIGHT);
	     JLabel label17 = new JLabel("Has a Tail : ");
	     label17.setHorizontalAlignment(SwingConstants.RIGHT);
	     JLabel label18 = new JLabel("Domestic : ");
	     label18.setHorizontalAlignment(SwingConstants.RIGHT);
	     JLabel label19 = new JLabel("Cat Size : ");
	     label19.setHorizontalAlignment(SwingConstants.RIGHT);
	     
	     
	     panel.add(label1);
	     panel.add(label2);	     
	     panel.add(label3);
	     panel.add(animalComboBox);
	     panel.add(label4);
	     panel.add(hairSpinner);
	     panel.add(label5);
	     panel.add(feathersSpinner);
	     panel.add(label6);
	     panel.add(eggsSpinner);
	     panel.add(label7);
	     panel.add(milkSpinner);
	     panel.add(label8);
	     panel.add(airborneSpinner);
	     panel.add(label9);
	     panel.add(aquaticSpinner);
	     panel.add(label10);
	     panel.add(predatorSpinner);
	     panel.add(label11);
	     panel.add(toothedSpinner);
	     panel.add(label12);
	     panel.add(backboneSpinner);
	     panel.add(label13);
	     panel.add(breathesSpinner);
	     panel.add(label14);
	     panel.add(venomousSpinner);
	     panel.add(label15);
	     panel.add(finsSpinner);
	     panel.add(label16);
	     panel.add(legsSpinner);
	     panel.add(label17);
	     panel.add(tailSpinner);
	     panel.add(label18);
	     panel.add(domesticSpinner);
	     panel.add(label19);
	     panel.add(catsizeSpinner);
	     panel.add(classifyButton);
	     panel.add(resultLabel);
	     
	     setContentPane(panel);

	     // Set the frame size and make it visible
	     setSize(400, 600);
	     setVisible(true);
	     getContentPane().add(panel, BorderLayout.CENTER);
	        pack();
	        setVisible(true);     
	 }
	 public static void main(String[] args) throws Exception {
	     new zooWithGUI();
	 }
	 public void actionPerformed(ActionEvent event) {
		 try {
			 String animal = (String) animalComboBox.getSelectedItem();		
			 int index = Arrays.asList(animalNames).indexOf(animal);
			 String animalName = animal.replaceAll("^\\d+\\.\\s*", "");
	      // System.out.println(animalNames[0]);
			 
		  // Load the ARFF dataset
	         BufferedReader reader = new BufferedReader(new FileReader("arff/zoo.arff"));
	         Instances data = new Instances(reader);
	      	 reader.close();
	         
	      // Set the class index to the last attribute (class)
	         data.setClassIndex(data.numAttributes() - 1);
	         
	         LMT model = (LMT) weka.core.SerializationHelper.read("model/zoo.model");
	         
	         Instance instance = new DenseInstance(data.numAttributes());
	         instance.setValue(0, (int) index);
	         instance.setValue(1, (double) Double.parseDouble(hairSpinner.getText()));
	         instance.setValue(2, (double) Double.parseDouble(feathersSpinner.getText()));
	         instance.setValue(3, (double) Double.parseDouble(eggsSpinner.getText()));
	         instance.setValue(4, (double) Double.parseDouble(milkSpinner.getText()));
	         instance.setValue(5, (double) Double.parseDouble(airborneSpinner.getText()));
	         instance.setValue(6, (double) Double.parseDouble(aquaticSpinner.getText()));
	         instance.setValue(7, (double) Double.parseDouble(predatorSpinner.getText()));
	         instance.setValue(8, (double) Double.parseDouble(toothedSpinner.getText()));
	         instance.setValue(9, (double) Double.parseDouble(backboneSpinner.getText()));
	         instance.setValue(10, (double) Double.parseDouble(breathesSpinner.getText()));
	         instance.setValue(11, (double) Double.parseDouble(venomousSpinner.getText()));
	         instance.setValue(12, (double) Double.parseDouble(finsSpinner.getText()));
	         instance.setValue(13, (double) Double.parseDouble(legsSpinner.getText()));
	         instance.setValue(14, (double) Double.parseDouble(tailSpinner.getText()));
	         instance.setValue(15, (double) Double.parseDouble(domesticSpinner.getText()));
	         instance.setValue(16, (double) Double.parseDouble(catsizeSpinner.getText()));      
	         instance.setDataset(data);
	         double result = model.classifyInstance(instance);
	         resultLabel.setText("<html><font color='black'>  Type of animal is: </font><font color='blue'>" + data.classAttribute().value((int)result) + "</font></html>");

	         JPanel panel = new JPanel();
	         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	         // create a JLabel for the image
	         JLabel imageLabel = new JLabel();
	         ImageIcon imageIcon = new ImageIcon("image/"+ data.classAttribute().value((int)result) + ".jpg"); // replace ".jpg" with the file extension of your image file
	         Image img = imageIcon.getImage().getScaledInstance(700, 600, Image.SCALE_DEFAULT); // adjust the width and height as needed
	         imageIcon = new ImageIcon(img);
	         imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	         imageLabel.setIcon(imageIcon);
	         // create a JLabel for the text
	         JLabel textLabel = new JLabel("This type animal '"+animalName+"' is in "+data.classAttribute().value((int)result));
	         textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	         // add the components to the panel
	         panel.add(textLabel);
	         panel.add(Box.createVerticalStrut(10)); // add some vertical space between the image and text
	         panel.add(imageLabel);
	         
	         JOptionPane.showMessageDialog(null, panel, " Image "+ data.classAttribute().value((int)result), JOptionPane.PLAIN_MESSAGE);
		 }catch (Exception ex) {
	         JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	     }
	 }
}



