// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

public class TrainingRecordGUI extends JFrame implements ActionListener {

    private JTextField name = new JTextField(30);
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField hours = new JTextField(2);
    private JTextField mins = new JTextField(2);
    private JTextField secs = new JTextField(2);
    private JTextField dist = new JTextField(4);
    private JLabel labn = new JLabel(" Name:");
    private JLabel labd = new JLabel(" Day:");
    private JLabel labm = new JLabel(" Month:");
    private JLabel laby = new JLabel(" Year:");
    private JLabel labh = new JLabel(" Hours:");
    private JLabel labmm = new JLabel(" Mins:");
    private JLabel labs = new JLabel(" Secs:");
    private JLabel labdist = new JLabel(" Distance (km):");
    
    // Subclass attributes.
    /* I know there are more aesthetically pleasing and robust ways
       to deal with this problem, but I'm moving house soon--let's say I made
       an executive decision to prioritize an early software release. */
    private JTextField cycsur = new JTextField(4);
    private JTextField cyctmpo = new JTextField(4);
    private JTextField sprreps = new JTextField(4);
    private JTextField sprrec = new JTextField(4);
    private JTextField swmven = new JTextField(4);
    private JLabel labcycsur = new JLabel(" [Cycling] Surface:");
    private JLabel labcyctmpo = new JLabel(" Tempo:");
    private JLabel labsprreps = new JLabel(" [Sprinting] Reps:");
    private JLabel labsprrec = new JLabel(" Recovery:");
    private JLabel labswmven = new JLabel(" [Swimming] venue:");
    
    // Using an uneditable combo box rather than a menu or series of buttons.
    private JButton addA = new JButton("Add activity");
    private String[] activity = {"Running","Cycling","Sprinting","Swimming"};
    JComboBox actChoice = new JComboBox(activity);
    
    private JButton lookUpByDate = new JButton("Look Up");
    private JButton FindAllByDate = new JButton("Find All By Date");
    private JButton removeByDate = new JButton("Remove");

    private TrainingRecord myAthletes = new TrainingRecord();

    private JTextArea outputArea = new JTextArea(5, 50);

    public static void main(String[] args) {
        TrainingRecordGUI applic = new TrainingRecordGUI();
    } // main

    // set up the GUI 
    public TrainingRecordGUI() {
        super("Training Record");
        setLayout(new FlowLayout());
        
        add(actChoice);
        actChoice.setSelectedItem("Running");
        actChoice.addActionListener(this);
        
        // Gluing over a glitch where every field is visible on launch, even though "Running" is selected.
        labcycsur.setVisible(false); cycsur.setVisible(false);
        labcyctmpo.setVisible(false); cyctmpo.setVisible(false);
        labsprrec.setVisible(false); sprrec.setVisible(false);
        labsprreps.setVisible(false); sprreps.setVisible(false);
        labswmven.setVisible(false); swmven.setVisible(false);
        
        add(labn);
        add(name);
        name.setEditable(true);
        add(labd);
        add(day);
        day.setEditable(true);
        add(labm);
        add(month);
        month.setEditable(true);
        add(laby);
        add(year);
        year.setEditable(true);
        add(labh);
        add(hours);
        hours.setEditable(true);
        add(labmm);
        add(mins);
        mins.setEditable(true);
        add(labs);
        add(secs);
        secs.setEditable(true);
        add(labdist);
        add(dist);
        dist.setEditable(true);
        
        // Labels for subclasses
        add(labcycsur);
        add(cycsur);
        cycsur.setEditable(true);
        add(labcyctmpo);
        add(cyctmpo);
        cyctmpo.setEditable(true);
        add(labsprreps);
        add(sprreps);
        sprreps.setEditable(true);
        add(labsprrec);
        add(sprrec);
        sprrec.setEditable(true);
        add(labswmven);
        add(swmven);
        swmven.setEditable(true);
        
        // Buttons
        add(addA);
        addA.addActionListener(this);   
        add(lookUpByDate);
        lookUpByDate.addActionListener(this);
        add(FindAllByDate);
        FindAllByDate.addActionListener(this);
        add(removeByDate);
        removeByDate.addActionListener(this);
        add(outputArea);
        
        outputArea.setEditable(false);
        setSize(720, 200);
        setVisible(true);
        blankDisplay();
        
    } // constructor

    // listen for and respond to GUI events 
    public void actionPerformed(ActionEvent event) {
        String message = "";
        
        // Changing the visible fields based on what the activity is...
        if (actChoice.getSelectedItem() == "Cycling") {
            labcycsur.setVisible(true);
            cycsur.setVisible(true);
            labcyctmpo.setVisible(true);
            cyctmpo.setVisible(true);
        }
        if (actChoice.getSelectedItem() == "Sprinting") {
            labsprrec.setVisible(true);
            sprrec.setVisible(true);
            labsprreps.setVisible(true);
            sprreps.setVisible(true);
        }
        if (actChoice.getSelectedItem() == "Swimming") {
            labswmven.setVisible(true);
            swmven.setVisible(true);
        }
        
        // ... and what the activity isn't.
        if (actChoice.getSelectedItem() != "Cycling") {
            labcycsur.setVisible(false);
            cycsur.setVisible(false);
            labcyctmpo.setVisible(false);
            cyctmpo.setVisible(false);
        }
        if (actChoice.getSelectedItem() != "Sprinting") {
            labsprrec.setVisible(false);
            sprrec.setVisible(false);
            labsprreps.setVisible(false);
            sprreps.setVisible(false);
        }
        if (actChoice.getSelectedItem() != "Swimming") {
            labswmven.setVisible(false);
            swmven.setVisible(false);
        }
        
        // This try/catch statement protects the user from crashes after entering non-int or null values.
        // The user can try again afterwards.
        try {
            if (event.getSource() == addA) {
                String activityChoice = actChoice.getSelectedItem().toString();
                message = addEntry(activityChoice);
            }
            if (event.getSource() == lookUpByDate) {
                message = lookupEntry();
            }
            if (event.getSource() == FindAllByDate) {
                message = lookupEntries();
            }
            if (event.getSource() == removeByDate) {
                message = remove();
            }
        } catch (Exception NumberFormatException) {
            message = "Error: have you filled in the correct date?  Have you filled in the details of the correct sport?";
        }

        outputArea.setText(message);
        blankDisplay();
    } // actionPerformed

    public String addEntry(String what) {
        String message = "Record added\n";
        System.out.println("Adding "+what+" entry to the records");
        String n = name.getText();
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        float km = java.lang.Float.parseFloat(dist.getText());
        int h = Integer.parseInt(hours.getText());
        int mm = Integer.parseInt(mins.getText());
        int s = Integer.parseInt(secs.getText());
        
        // Declaring the subclasses' attributes here protects us from exceptions:
        // there is no need to fill in every box, only the relevant ones.
        
        if (what.equals("Running")) {
            Entry e = new Entry(n, d, m, y, h, mm, s, km);
            myAthletes.addEntry(e);
        } else if (what.equals("Cycling")) {
            String surface = cycsur.getText();
            String tempo = cyctmpo.getText();
            Entry e = new CycleEntry(n, d, m, y, h, mm, s, km, surface, tempo);
            myAthletes.addEntry(e);
        } else if (what.equals("Sprinting")) {
            int reps = Integer.parseInt(sprreps.getText());
            int recovery = Integer.parseInt(sprrec.getText());
            Entry e = new SprintEntry(n, d, m, y, h, mm, s, km, reps, recovery);
            myAthletes.addEntry(e);
        } else if (what.equals("Swimming")) {
            String venue = swmven.getText();
            Entry e = new SwimEntry(n, d, m, y, h, mm, s, km, venue);
            myAthletes.addEntry(e);
        }
        
        return message;
    }
    
    public String lookupEntry() {
        
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        
        outputArea.setText("looking up record ...");

        String message;
        message = myAthletes.lookupEntry(m, d, y);
        return message;
    }
        
    public String lookupEntries() {

        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());

        outputArea.setText("looking up record ...");

        //I'm using my own method, lookupEntries, which returns a string with multiple records.
        //lookupEntry is still available in TrainingRecord.
        String message = myAthletes.lookupEntries(m, d, y);
        return message;

    }
    
    public String remove() {
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("Looking up records to remove ...");
        
        String message = myAthletes.clearEntry(m,d,y);
        return message;
    }

    public void blankDisplay() {
        name.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        hours.setText("");
        mins.setText("");
        secs.setText("");
        dist.setText("");
        
        cycsur.setText("");
        cyctmpo.setText("");
        sprreps.setText("");
        sprrec.setText("");
        swmven.setText("");

    }// blankDisplay
    // Fills the input fields on the display for testing purposes only
    public void fillDisplay(Entry ent) {
        name.setText(ent.getName());
        day.setText(String.valueOf(ent.getDay()));
        month.setText(String.valueOf(ent.getMonth()));
        year.setText(String.valueOf(ent.getYear()));
        hours.setText(String.valueOf(ent.getHour()));
        mins.setText(String.valueOf(ent.getMin()));
        secs.setText(String.valueOf(ent.getSec()));
        dist.setText(String.valueOf(ent.getDistance()));
    }

} // TrainingRecordGUI

