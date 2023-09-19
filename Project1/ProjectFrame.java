/*
Vincent Testagrossa
Project 1: Expression Converter
28May2020

Requirements: Dependent on the SwitchFix class.

A swing application consisting of 3 JPanels, 2 JButtons, 2 JLabels, 2 JTextFields. Uses BoxLayout for the primary
placement on the JFrame, and each panel uses FlowLayout to attach the components. Implements ActionListener to call
the SwitchFix GetPrefix() and GetPostfix() methods.
*/
package Project1;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProjectFrame extends JFrame implements ActionListener {
    private JPanel topPanel, centerPanel, bottomPanel;
    private JButton postfixButton, prefixButton;
    private JLabel label1, label2;
    private JTextField inputField, outputField;
    private BoxLayout layout;

    ProjectFrame(){
        //instantiate the Boxlayout and set the axis to Y. Instantiate the panels to be added to the frame using the layout.
        layout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        topPanel = new JPanel();
        centerPanel = new JPanel();
        bottomPanel = new JPanel();

        //set the close operation to exit, set the title and set the layout for the frame.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Prefix/Postfix Converter");
        this.setLayout(layout);

        //create the first label and the input field.
        label1 = new JLabel("Enter Expression");
        inputField = new JTextField("");
        inputField.setColumns(20);

        //create the buttons
        postfixButton = new JButton("Prefix to Postfix");
        postfixButton.addActionListener(this);
        prefixButton = new JButton("Postfix to Prefix");
        prefixButton.addActionListener(this);
        
        //create the second label and the output field. Make the output field not editable.
        label2 = new JLabel("Result");
        outputField = new JTextField("");
        outputField.setColumns(20);
        outputField.setEditable(false);
        
        //set the top panel layout to be a flowLayout, then add the top panel to the frame and add the
        //first label + input field to the top panel.
        topPanel.setLayout(new FlowLayout());
        this.add(topPanel);
        topPanel.add(label1);
        topPanel.add(inputField);
        
        //set the center panel layout to be a flowlayout, then add the center panel to the frame and add the
        //two buttons to the center panel.
        centerPanel.setLayout(new FlowLayout());
        this.add(centerPanel);
        centerPanel.add(postfixButton);
        centerPanel.add(prefixButton);

        //Set the layout for the bottom panel to be a flowlayout, then add the bottom panel to the frame and add
        //the second label + output field to the bottom panel.
        bottomPanel.setLayout(new FlowLayout());
        this.add(bottomPanel);
        bottomPanel.add(label2);
        bottomPanel.add(outputField);

        //Pack the frame to fit to the width of the panels/components. Disable resizing, and set the frame to visible.
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }
    @Override 
    public void actionPerformed(ActionEvent e){
        /*Overrides actionPerformed to handle button events.

        Depending on which button is pressed, creates a new SwitchFix object, passes the inputField in the constructor and then
        sets the output field with the appropriate method. Catches SyntaxErrors and opens a JOptionPane with the error message.
        */
        if (e.getSource() == postfixButton){
            try{
                SwitchFix prefix = new SwitchFix(inputField.getText());
                outputField.setText(prefix.getPostfix());
            }
            catch (SyntaxError ex){
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == prefixButton){
            try{
                SwitchFix postfix = new SwitchFix(inputField.getText());
                outputField.setText(postfix.getPrefix());
            }
            catch (SyntaxError ex){
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}