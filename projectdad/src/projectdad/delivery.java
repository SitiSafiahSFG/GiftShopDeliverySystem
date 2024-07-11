package projectdad;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class delivery extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PORT = 9090;
    private JTextArea infoArea;

    public delivery() {
        initialize();
        startServer();
    }

    private void initialize() {
    	getContentPane().setBackground(new Color(233, 233, 233));
        setBounds(100, 100, 923, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);


        JLabel lblNewLabel = new JLabel("COURIER APPLICATION");
        lblNewLabel.setBounds(311, 30, 336, 51);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        getContentPane().add(lblNewLabel);

        infoArea = new JTextArea();
        infoArea.setFont(new Font("Calibri", Font.PLAIN, 12));
        infoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setBounds(89, 145, 692, 313);
        getContentPane().add(scrollPane);
        
        JLabel lblNewLabel_1 = new JLabel("ORDER INFO FOR DELIVERY: ");
        lblNewLabel_1.setBounds(89, 106, 347, 28);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        getContentPane().add(lblNewLabel_1);
        
        JButton btnClear = new JButton("CLEAR");
        btnClear.setBounds(791, 393, 109, 28);
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearInfoArea();
            }
        });
        getContentPane().add(btnClear);

        
        JButton btnClose = new JButton("CLOSE");
        btnClose.setBounds(791, 431, 109, 27);
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        getContentPane().add(btnClose);
        
    }

    private void startServer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try (ServerSocket serverSocket = new ServerSocket(PORT)) {
           
                    System.out.println("Server is listening on port 9090...");
                    while (true) {
                        Socket clientSocket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
                        StringBuilder dataBuilder = new StringBuilder();
                        String line;
                        while ((line = in.readLine()) != null) {
                            dataBuilder.append(line).append("\n");
                        	  
                        }
                        displayMessage(dataBuilder.toString());
                        clientSocket.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
      }
			
			 private void clearInfoArea() {
			        SwingUtilities.invokeLater(new Runnable() {
			            @Override
			            public void run() {
			                infoArea.setText(""); // This clears the JTextArea
			            }
			        });
			 }
			 
			 private void displayMessage(final String message) {
			        SwingUtilities.invokeLater(new Runnable() {
			            @Override
			            public void run() {
			                infoArea.append(message); // Display data in database
			                infoArea.append("\n------------------------\n");
			            }
			        });
			    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new delivery().setVisible(true);
            }
        });
    }
}
