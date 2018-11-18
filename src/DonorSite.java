import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class DonorSite {
    public JButton makeDonationButton  = new JButton("Make Donation");
    public JButton logoutButton = new JButton("Logout");
    public JPanel panel1 = new JPanel(null);
    
        public DonorSite() {
            makeDonationButton.setPreferredSize(new Dimension(150, 50));
            logoutButton.setPreferredSize(new Dimension(150, 50));
            panel1.setPreferredSize(new Dimension(400,80));
            panel1.setLayout(new FlowLayout());
            panel1.add(makeDonationButton);
            panel1.add(logoutButton);
            
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame = new JFrame("Login");
                frame.setContentPane(new Login().panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        makeDonationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
					//Configure email message
                	String message = "Thank you for your dontation!" + '\n';
                	int recIndex = 0;
                	if(Login.recievers.get(recIndex).getReport().isEmpty()) {
                		message += "Unfortunately, your recipient has not submitted any expense reports yet. Please check back at a later date. \n Thank You, \nThe Refugee Centre";
                		
                	}
                	else {
                		message += "The most recent expense report for your recipient is shown below" + '\n' + Login.recievers.get(recIndex).toString() + "\n Thank You, \n The Refugee Centre";
                	}
                	
                	//Send to donate
                	MainFrame.sendSomewhere("https://www.paypal.me/therefugeecentre");
                	
                	//Send email
                	String emailToSend = Login.donors.get(Login.index).getEmail();
                	System.out.println(emailToSend);
                	SendEmail.sendEmail(emailToSend, message);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }
}
