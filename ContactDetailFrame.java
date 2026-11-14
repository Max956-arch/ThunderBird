import javax.swing.*;
import java.awt.*;

public class ContactDetailFrame extends JFrame {

    ContactDetailFrame(ThunderbirdContact contact) {

        setTitle("Contact Information");
        setSize(400,300);

        JTextArea details = new JTextArea();

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