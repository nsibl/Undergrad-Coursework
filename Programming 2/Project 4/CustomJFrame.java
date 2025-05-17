import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


//Begins to create the GUI
@SuppressWarnings("serial")
public class CustomJFrame extends JFrame{

    //Declaring variables here
    public static String data = "";
    private JLabel headingLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel phoneNumberLabel;
    private JLabel emailLabel;
    private JLabel dietaryLabel;
    private JLabel genderLabel;
    private JLabel waterLabel;
    private JLabel mealsLabel;
    private JLabel checkBoxLabel;
    private JLabel walkLabel;
    private JLabel weightLabel;
    private static JTextField firstNameTextField;
    private static JTextField lastNameTextField;
    private static JTextField phoneNumberTextField;
    private static JTextField emailTextField;
    private static JRadioButton maleRadioButton;
    private static JRadioButton femaleRadioButton;
    private JRadioButton preferenceRadioButton;
    private static ButtonGroup radioButtonGroup;
    private static JSpinner waterIntakeSpinner;
    private static JSlider mealSlider;
    private static JCheckBox wheatCheckBox;
    private static JCheckBox sugarCheckBox;
    private static JCheckBox dairyCheckBox;
    @SuppressWarnings("rawtypes")
    private static JComboBox walkComboBox;
    private String[] walkOptions = {"Less than 1 Mile                                             ", "More than 1 mile but less than 2 miles", "More than 2 miles but less than 3 miles", "More than 3 miles"};
    private static JFormattedTextField weightFormattedTextField;
    private static JButton clearButton;
    private static JButton submitButton;
    private FileHandler fileHandler = new FileHandler();
    GridBagConstraints gbc = new GridBagConstraints();

    //Creating the window
    public CustomJFrame() {

        setLayout(new GridBagLayout());

        //Personal Info
        headingLabel = new JLabel("Personal Information");
        gbc.insets = new Insets(10, -15, 6, 0);
        add(headingLabel, gbc);

        //First name
        firstNameLabel = new JLabel("First Name:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(firstNameLabel, gbc);
        setFirstNameTextField(new JTextField("", 15));
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(getFirstNameTextField(), gbc);

        //Last name
        lastNameLabel = new JLabel("Last Name:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lastNameLabel, gbc);
        setLastnameTextField(new JTextField("",15));
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(getLastNameTextField(), gbc);

        //Phone number
        phoneNumberLabel = new JLabel("Phone Number:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(phoneNumberLabel, gbc);
        setPhoneNumberTextField(new JTextField("", 15));
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(getPhoneNumberTextField(), gbc);

        //Email
        emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(emailLabel, gbc);
        setEmailTextField(new JTextField("", 15));
        gbc.gridx = 1;
        gbc.gridy = 8;
        add(getEmailTextField(), gbc);

        //Gender
        genderLabel = new JLabel("Gender:");
        gbc.gridx = 0;
        gbc.gridy = 10;
        add(genderLabel, gbc);
        setMaleRadioButton(new JRadioButton("Male"));
        setFemaleRadioButton(new JRadioButton("Female"));
        preferenceRadioButton = new JRadioButton("Prefer not to say");
        setRadioButtonGroup(new ButtonGroup());
        getRadioButtonGroup().add(getMaleRadioButton());
        getRadioButtonGroup().add(getFemaleRadioButton());
        getRadioButtonGroup().add(preferenceRadioButton);
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.insets = new Insets(-3, -152, -10, 0);
        add(getMaleRadioButton(), gbc);
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.insets = new Insets(-3, -137, 0, 0);
        add(getFemaleRadioButton(), gbc);
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.insets = new Insets(-3, -85, 0, 0);
        add(preferenceRadioButton, gbc);

        //Diet Questions
        dietaryLabel = new JLabel("Dietary Questions");
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.insets = new Insets(10, -15, 6, 0);
        add(dietaryLabel, gbc);

        //Water Question
        waterLabel = new JLabel("How many cups of water on average do you drink a day?");
        gbc.gridx = 1;
        gbc.gridy = 16;
        gbc.insets = new Insets(10, -110, 6, 0);
        add(waterLabel, gbc);
        SpinnerNumberModel spinner = new SpinnerNumberModel(15, 0, 50, 1);
        setWaterIntakeSpinner(new JSpinner(spinner));
        gbc.gridx = 1;
        gbc.gridy = 18;
        gbc.insets = new Insets(10, -110, 2, 0);
        add(getWaterIntakeSpinner(), gbc);

        //Food Question
        mealsLabel = new JLabel("How many meals on average do you eat a day?");
        gbc.gridx = 1;
        gbc.gridy = 20;
        gbc.insets = new Insets(10, -110, 10, 0);
        add(mealsLabel, gbc);
        setMealSlider(new JSlider(JSlider.HORIZONTAL, 0, 10, 3));
        getMealSlider().setPaintTicks(true);
        getMealSlider().setPaintLabels(true);
        getMealSlider().setSnapToTicks(true);
        getMealSlider().setMajorTickSpacing(1);
        gbc.gridx = 1;
        gbc.gridy = 22;
        gbc.insets = new Insets(10, -110, 2, 0);
        add(getMealSlider(), gbc);

        //Check boxes
        checkBoxLabel = new JLabel("Do any of these meals regularly contain:");
        gbc.insets = new Insets(10, -120, 4, 0);
        gbc.gridx = 1;
        gbc.gridy = 23;
        add(checkBoxLabel, gbc);
        setDairyCheckBox(new JCheckBox("Dairy"));
        gbc.insets = new Insets(10, -270, 4, 0);
        gbc.gridx = 1;
        gbc.gridy = 24;
        add(getDairyCheckBox(), gbc);
        setWheatCheckBox(new JCheckBox("Wheat"));
        gbc.insets = new Insets(10, -140, 4, 0);
        gbc.gridx = 1;
        gbc.gridy = 24;
        add(getWheatCheckBox(), gbc);
        setSugarCheckBox(new JCheckBox("Sugar"));
        gbc.insets = new Insets(10, -10, 4, 0);
        gbc.gridx = 1;
        gbc.gridy = 24;
        add(getSugarCheckBox(), gbc);

        //Walking Question
        walkLabel = new JLabel("On average how many miles do you walk in a day?");
        gbc.insets = new Insets(10, -110, 4, 0);
        gbc.gridx = 1;
        gbc.gridy = 25;
        add(walkLabel, gbc);
        setWalkComboBox(new JComboBox(walkOptions));
        gbc.gridx = 1;
        gbc.gridy = 26;
        gbc.insets = new Insets(10, -110, 4, 0);
        add(getWalkComboBox(), gbc);

        //Weight Question
        weightLabel = new JLabel("How much do you weigh?");
        gbc.insets = new Insets(10, -110, 4, 0);
        gbc.gridx = 1;
        gbc.gridy = 27;
        add(weightLabel, gbc);
        setWeightFormattedTextField(new JFormattedTextField("                                                           "));
        gbc.gridx = 1;
        gbc.gridy = 28;
        gbc.insets = new Insets(10, -110, 4, 0);
        add(getWeightFormattedTextField(), gbc);

        //Clear
        setClearButton(new JButton("Clear"));
        gbc.gridx = 0;
        gbc.gridy = 29;
        Color yellow = new Color(255, 255, 0);
        getClearButton().setBackground(yellow);
        gbc.insets = new Insets(20, -70, 0, 0);
        getClearButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InnerActionListener innerActionListener = new InnerActionListener();
                innerActionListener.actionPerformed(e);
            }
        });
        add(getClearButton(), gbc);

        //Submit
        setSubmitButton(new JButton("Submit"));
        Color green = new Color(0, 255, 0);
        getSubmitButton().setBackground(green);
        gbc.gridx = 1;
        gbc.gridy = 29;
        gbc.insets = new Insets(20, 0, 0, -160);

        getSubmitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InnerActionListener innerActionListener = new InnerActionListener();
                innerActionListener.actionPerformed(e);
                fileHandler.writeResults(CustomJFrame.data);
            }
        });
        add(getSubmitButton(), gbc);



    }

    //Getter and setter methods for all the fields
    public static JTextField getFirstNameTextField() {
        return firstNameTextField;
    }

    public static JCheckBox getDairyCheckBox() {
        return dairyCheckBox;
    }

    public void setFirstNameTextField(JTextField firstNameTextField) {
        this.firstNameTextField = firstNameTextField;
    }

    public static JTextField getPhoneNumberTextField() {
        return phoneNumberTextField;
    }

    public void setPhoneNumberTextField(JTextField phoneNumberTextField) {
        this.phoneNumberTextField = phoneNumberTextField;
    }

    public static JTextField getLastNameTextField() {
        return lastNameTextField;
    }

    public void setLastnameTextField(JTextField lastNameTextField) {
        this.lastNameTextField = lastNameTextField;
    }

    public static JTextField getEmailTextField() {
        return emailTextField;
    }

    public void setEmailTextField(JTextField emailTextField) {
        this.emailTextField = emailTextField;
    }

    public static JRadioButton getFemaleRadioButton() {
        return femaleRadioButton;
    }

    public void setFemaleRadioButton(JRadioButton femaleRadioButton) {
        this.femaleRadioButton = femaleRadioButton;
    }

    public static JRadioButton getMaleRadioButton() {
        return maleRadioButton;
    }

    public void setMaleRadioButton(JRadioButton maleRadioButton) {
        this.maleRadioButton = maleRadioButton;
    }

    public static JSpinner getWaterIntakeSpinner() {
        return waterIntakeSpinner;
    }

    public void setWaterIntakeSpinner(JSpinner waterIntakeSpinner) {
        this.waterIntakeSpinner = waterIntakeSpinner;
    }

    public static ButtonGroup getRadioButtonGroup() {
        return radioButtonGroup;
    }

    public static void setRadioButtonGroup(ButtonGroup radioButtonGroup) {
        CustomJFrame.radioButtonGroup = radioButtonGroup;
    }

    public static JCheckBox getWheatCheckBox() {
        return wheatCheckBox;
    }

    public static void setWheatCheckBox(JCheckBox wheatCheckBox) {
        CustomJFrame.wheatCheckBox = wheatCheckBox;
    }

    public static JCheckBox getSugarCheckBox() {
        return sugarCheckBox;
    }

    public static void setSugarCheckBox(JCheckBox sugarCheckBox) {
        CustomJFrame.sugarCheckBox = sugarCheckBox;
    }

    public static JCheckBox getDairyCheckbox() {
        return dairyCheckBox;
    }

    public static void setDairyCheckBox(JCheckBox dairyCheckBox) {
        CustomJFrame.dairyCheckBox = dairyCheckBox;
    }

    public static JFormattedTextField getWeightFormattedTextField(){
        return weightFormattedTextField;
    }

    public static void setWeightFormattedTextField(JFormattedTextField weightFormattedTextField) {
        CustomJFrame.weightFormattedTextField = weightFormattedTextField;
    }

    public static JSlider getMealSlider() {
        return mealSlider;
    }

    public void setMealSlider(JSlider mealSlider) {
        this.mealSlider = mealSlider;
    }

    public static JComboBox getWalkComboBox() {
        return walkComboBox;
    }

    public void setWalkComboBox(JComboBox walkComboBox) {
        this.walkComboBox = walkComboBox;
    }

    public static JButton getClearButton() {
        return clearButton;
    }

    public void setClearButton(JButton clearButton) {
        this.clearButton = clearButton;
    }

    public static JButton getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(JButton submitButton) {
        this.submitButton = submitButton;
    }


}

//performs action when submit or clear is pressed
class InnerActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(CustomJFrame.getClearButton().getModel().isArmed()) {
            clearForm();
        }
        else if(CustomJFrame.getSubmitButton().getModel().isArmed()) {
            String firstName = CustomJFrame.getFirstNameTextField().getText() ;
            String lastName = CustomJFrame.getLastNameTextField().getText() ;
            String phoneNumber = CustomJFrame.getPhoneNumberTextField().getText();
            String email = CustomJFrame.getEmailTextField().getText();
            String sex = "";
            if(CustomJFrame.getMaleRadioButton().isSelected()) {
                sex = "Male";
            }else if(CustomJFrame.getFemaleRadioButton().isSelected()) {
                sex = "Female";
            }else {
                sex = "Prefer not to say";
            }
            int waterNumber = (Integer) CustomJFrame.getWaterIntakeSpinner().getValue();
            String waterNumberString = String.valueOf(waterNumber);
            int mealNumber = (Integer) CustomJFrame.getMealSlider().getValue();
            String mealNumberString = String.valueOf(mealNumber);
            String dairy = "";
            if(CustomJFrame.getDairyCheckBox().isSelected()) {
                dairy = "TRUE";
            }else {
                dairy = "FALSE";
            }
            String wheat = "";
            if(CustomJFrame.getWheatCheckBox().isSelected()) {
                wheat = "TRUE";
            }else {
                wheat = "FALSE";
            }
            String sugar = "";
            if(CustomJFrame.getSugarCheckBox().isSelected()) {
                sugar = "TRUE";
            }else {
                sugar = "FALSE";
            }
            String miles = CustomJFrame.getWalkComboBox().getSelectedItem().toString();
            String milesFinal = "";
            if(miles.contains("Less than 1 Mile")) {
                milesFinal = "Less than 1 Mile";
            }else if(miles.contains("More than 1 mile but less than 2 miles")) {
                milesFinal = "More than 1 mile but less than 2 miles";
            }else if(miles.contains("More than 2 mile")) {
                milesFinal = "More than 2 miles but less than 3 miles";
            }else if(miles.contains("More than 3 mile")) {
                milesFinal = "More than 3 miles";
            }

            //declares weight variable
            int weightNumber;
            String weight = CustomJFrame.getWeightFormattedTextField().getText().replaceAll("\\s", "");
            try {

                weightNumber = Integer.parseInt(weight);
                CustomJFrame.data = firstName + "," + lastName + "," + phoneNumber + "," + email + "," + sex + "," + waterNumberString + "," + mealNumberString + "," + wheat + "," + sugar + "," + dairy + "," + milesFinal + "," + weightNumber;
            }catch(Exception m){

                weightNumber = 0;
                CustomJFrame.data = firstName + "," + lastName + "," + phoneNumber + "," + email + "," + sex + "," + waterNumberString + "," + mealNumberString + "," + wheat + "," + sugar + "," + dairy + "," + milesFinal + "," + "null";
            }


            clearForm();
        }
    }

    //Clears forms
    private void clearForm() {
        CustomJFrame.getFirstNameTextField().setText("");
        CustomJFrame.getLastNameTextField().setText("");
        CustomJFrame.getPhoneNumberTextField().setText("");
        CustomJFrame.getEmailTextField().setText("");
        CustomJFrame.getRadioButtonGroup().clearSelection();
        CustomJFrame.getWaterIntakeSpinner().setValue(0);
        CustomJFrame.getWheatCheckBox().setSelected(false);
        CustomJFrame.getDairyCheckBox().setSelected(false);
        CustomJFrame.getSugarCheckBox().setSelected(false);
        CustomJFrame.getWeightFormattedTextField().setText("                                                  ");

    }
}


