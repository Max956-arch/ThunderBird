/******************************************************************************
 * Copyright (C) 2021 Eric Pogue.
 * 
 * This file and the ThunderbirdLite application is licensed under the 
 * BSD-3-Clause.
 * 
 * You may use any part of the file as long as you give credit in your 
 * source code.
 * 
 * This application utilizes the HttpRequest.java library developed by 
 * Eric Pogue
 * 
 *****************************************************************************/
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Container; 
import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import java.util.ArrayList;

class ContactTile extends JPanel {
    private int red, green, blue;
    private ThunderbirdContact contactInSeat = null;

    private Boolean isAnAisle = false;

    // MD: Sets a static color for both the Aisles and Seats.
    private Color seatColor = new Color(25,25,122);
    private Color aisleColor = new Color(70,70,70);

    public void setAisle() { isAnAisle = true; }

    ContactTile() {
        super();

        // Todo: Remove everything to do with random colors.
        // Todo: Implement visually appealing colors for aisles and seats.
    }

    ContactTile(ThunderbirdContact contactInSeatIn) {
        super();
        contactInSeat = contactInSeatIn;
    }

    final public void SetRandomValues() {
        red = GetNumberBetween(0,255);
        green = GetNumberBetween(0,255);
        blue = GetNumberBetween(0,255);
    }

    private static int GetNumberBetween(int min, int max) {
        Random myRandom = new Random();
        return min + myRandom.nextInt(max-min+1);
    }   

     public void paintComponent(Graphics g) {
        super.paintComponent(g); 

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // MD: Sets a static color for both the Aisles and Seats.
        if (isAnAisle) {
            g.setColor(aisleColor);
        } else {
            g.setColor(seatColor);
        }
        
        g.fillRect (10, 10, panelWidth-20, panelHeight-20);

        g.setColor(new Color(GetContrastingColor(red),GetContrastingColor(green),GetContrastingColor(blue)));

        final int fontSize=18;
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        int stringX = (panelWidth/2)-60;
        int stringY = (panelHeight/2)+30;
        if (contactInSeat != null) {

            // ToDo: Display preferred name instead of first and last name.
            // MD: This shows the Students preferred classroom name instead of the first/last name 
            String displayName = contactInSeat.getPreferredName();
            if(displayName == null || displayName.length() == 0){
                displayName =
                    contactInSeat.getFirstName() + " " +
                    contactInSeat.getLastName();
            }

            g.drawString(displayName, stringX, stringY);
        }
    }

    private static int GetContrastingColor(int colorIn) {
        return ((colorIn+128)%256);
    }
}

class ThunderbirdFrame extends JFrame implements ActionListener {
    private ArrayList<ContactTile> tileList;
    // MD: for the reverse button
    private boolean reversed = false;

    public ThunderbirdFrame() {
        setBounds(200,200,1200,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        JButton reverseView = new JButton("Reverse View");
        buttonPanel.add(reverseView);
        reverseView.addActionListener(this);

        // MD: This makes the classroom seats look better
        JPanel contactGridPanel = new JPanel();
        contentPane.add(contactGridPanel, BorderLayout.CENTER);
        contactGridPanel.setLayout(new GridLayout(4,8,10,10));

        ThunderbirdModel tbM = new ThunderbirdModel();
        tbM.LoadIndex();
        // MD: Leads all the contacts using multiple threads
        tbM.LoadContactsThreaded();

        // Todo: Review ThunderbirdModel in detail and implement a multithreaded version of loading contacts. 
        // Hint: Review LoadContact() and LoadContactsThreaded() in detail.

        System.out.println("Printing Model:");
        System.out.println(tbM);
        tbM.ValidateContacts();   


        tileList = new ArrayList<ContactTile>();

        // MD: creates an array list and adds three seat numbers to it.
        ArrayList<Integer> aisleSeats =
            new ArrayList <Integer>();

            aisleSeats.add(4);
            aisleSeats.add(12);
            aisleSeats.add(31);

        for(int i=1; i<33; i++) {
            ThunderbirdContact contactInSeat = tbM.findContactInSeat(i);
            if (contactInSeat != null) {
                System.out.println(contactInSeat);
            }

            ContactTile tile = new ContactTile(contactInSeat);

            // MD: When you click the seat it opens an additional information window.
            tile.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if(contactInSeat != null) {
                        new ContactDetailFrame(contactInSeat);
                    }
                }
            });

            // Todo: Place all the aisle seats in a array or an ArrayList instead of hard coding them. 
            // MD: Makes the aisle locations eaiser to chnage without modifying logic
            if (aisleSeats.contains(i)) {
                tile.setAisle();
            }

            tileList.add(tile);
            contactGridPanel.add(tile);
        }
    }

    public void actionPerformed(ActionEvent e) {
            // Todo: Remove randomization functionality and implement a visually appealing view of seats and aisles.
            reversed =!reversed;
            // MD: reverses the classroom when the button is clicked.
            if(reversed){
        java.util.Collections.reverse(tileList);
    }
    else{
        java.util.Collections.reverse(tileList);
    }
            // Todo: Implement reverse view where it looks like you are looking at the room from the back instead of the front 
            //     of the room. 

        repaint();
    }
}

// Todo: Rename the following class to Thunderbird.
// Hint: This will also require you to rename the Java file.
// MD: I made sure I renamed all of the classes and objects to Thunderbird
public class Thunderbird {
    public static void main(String[] args) {

        // MD: Searchs the command line arguments for HttpRequest, and then runs it
        for (int i = 0; i < args.length; i++) {
            if(args[i].equalsIgnoreCase("-HttpRequest")) {
                if(i + 1 < args.length) {
                    HTTPRequestFeature.Run(args[i + 1]);

                    return;
                }
            }
        }

        // Todo: Update the following line so that it reflects the name change to Thunderbird.
        System.out.println("Thunderbird Starting...");

        ThunderbirdFrame myThunderbirdFrame = new ThunderbirdFrame();
        myThunderbirdFrame.setVisible(true);
    }
}