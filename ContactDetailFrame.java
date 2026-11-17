import javax.swing.*;
import java.awt.*;

public class ContactDetailFrame extends JFrame {

    // MD: runs whenever you create a new contact detail frame
    ContactDetailFrame(ThunderbirdContact contact) {

        // MD: sets the title and the size of the window.
        setTitle("Contact Information");
        setSize(400,300);

        // MD: creates a text box that creats mutiple lines of text
        JTextArea details = new JTextArea();

        // MD: puts the information into the JTextArea 
        details.setText(
            "Preferred Name: " + contact.getPreferredName() +
            "\nFirst Name: " + contact.getFirstName() +
            "\nLast Name: " + contact.getLastName() +
            "\nEmail: " + contact.getEmailAddress() +
            "\nSeat: " + contact.getSeat()
        );

        add(details, BorderLayout.CENTER);
        
        setVisible(true);
    }
}