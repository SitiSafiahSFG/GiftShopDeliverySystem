package projectdad; // backend processing

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.sql.Statement;

public class customer {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
    	Connection connection = null;
    	Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish a connection to the database for manage order
            String url = "jdbc:mysql://localhost:3306/giftshop";
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);

            // Create a statement to execute SQL queries
            statement = connection.createStatement();

            // Execute a query to retrieve data from the database
            String query = "SELECT customer_name, customer_email, bear_qty, choc_basket_qty, " +
                           "choc_bouquet_qty, flower_qty, total_items, total_cost FROM orders"; // Example query
            resultSet = statement.executeQuery(query);

            // Assuming the query returns only one row, retrieve the data
            while (resultSet.next()) {
                String customer_name = resultSet.getString("customer_name");
                String customer_email = resultSet.getString("customer_email");
                int bearQty = resultSet.getInt("bear_qty");
                int chocBasketQty = resultSet.getInt("choc_basket_qty");
                int chocBouquetQty = resultSet.getInt("choc_bouquet_qty");
                int flowerQty = resultSet.getInt("flower_qty");
                int totalItems = resultSet.getInt("total_items");
                double totalCost = resultSet.getDouble("total_cost");

                String params = "Name=" + customer_name + "\tEmail=" + customer_email +
                                "\tbear qty=" + bearQty + "\tchoc basket_qty=" + chocBasketQty +
                                "\tchoc bouquet_qty=" + chocBouquetQty + "\tflower_qty=" + flowerQty + 
                                "\ttotal cost=RM" + totalCost + "\ttotal items=" + totalItems;

                // Sending the data through a TCP socket for efficient order processing 
                Socket skt = new Socket("10.200.104.108", 9090);  // Connect to server 10.200.104.108
                DataOutputStream out = new DataOutputStream(skt.getOutputStream());
                out.writeUTF(params);
                // Send data to server
                System.out.println(params);
                out.flush();
                skt.close();  // Close the socket

                System.out.println("Data sent: " + params);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the ResultSet, Statement, and Connection
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    {
}
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    customer window = new customer();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public customer() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame(); //interface designed for customer interaction 
        frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
        frame.setBounds(100, 100, 926, 584);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("WELCOME TO STELLA GIFT SHOP");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setBounds(250, 28, 355, 25);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("NAME:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1.setBounds(39, 81, 45, 13);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("EMAIL:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_2.setBounds(440, 81, 45, 13);
        frame.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("GIFT");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_3.setBounds(452, 120, 57, 13);
        frame.getContentPane().add(lblNewLabel_3);

        textField = new JTextField();
        textField.setBounds(94, 79, 321, 19);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(495, 79, 253, 19);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        // Image and description labels
        JLabel lblBearImage = new JLabel("");
        ImageIcon bearImage = new ImageIcon(this.getClass().getResource("/Bear.jpg"));
        lblBearImage.setIcon(bearImage);
        lblBearImage.setBounds(39, 143, 200, 184);
        frame.getContentPane().add(lblBearImage);

        JLabel lblChocBasketImage = new JLabel("");
        ImageIcon chocBasketImage = new ImageIcon(this.getClass().getResource("/ChocBasket.jpg"));
        lblChocBasketImage.setIcon(chocBasketImage);
        lblChocBasketImage.setBounds(266, 143, 188, 184);
        frame.getContentPane().add(lblChocBasketImage);

        JLabel lblChocBouquetImage = new JLabel("");
        ImageIcon img2 = new ImageIcon(this.getClass().getResource("/FlowerChocBouquet.jpg"));
        lblChocBouquetImage.setIcon(img2);
        lblChocBouquetImage.setBounds(477, 143, 210, 184);
        frame.getContentPane().add(lblChocBouquetImage);

        JLabel lblFlowerImage = new JLabel("");
        ImageIcon img3 = new ImageIcon(this.getClass().getResource("/Flowerr.jpg"));
        lblFlowerImage.setIcon(img3);
        lblFlowerImage.setBounds(707, 143, 188, 184);
        frame.getContentPane().add(lblFlowerImage);

        JLabel lblBearBouquet = new JLabel("Flower Bear Bouquet");
        lblBearBouquet.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblBearBouquet.setBounds(83, 348, 120, 13);
        frame.getContentPane().add(lblBearBouquet);

        JLabel lblChocBasket = new JLabel("Chocolate Basket");
        lblChocBasket.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblChocBasket.setBounds(310, 348, 105, 13);
        frame.getContentPane().add(lblChocBasket);

        JLabel lblChocBouquet = new JLabel("Chocolate Bouquet");
        lblChocBouquet.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblChocBouquet.setBounds(519, 348, 136, 13);
        frame.getContentPane().add(lblChocBouquet);

        JLabel lblFlowerBouquet = new JLabel("Flower Bouquet");
        lblFlowerBouquet.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblFlowerBouquet.setBounds(754, 349, 105, 13);
        frame.getContentPane().add(lblFlowerBouquet);

        JLabel lblBearPrice = new JLabel("RM 80.00");
        lblBearPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblBearPrice.setBounds(109, 374, 57, 13);
        frame.getContentPane().add(lblBearPrice);

        JLabel lblChocBasketPrice = new JLabel("RM 130.00");
        lblChocBasketPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblChocBasketPrice.setBounds(320, 374, 62, 13);
        frame.getContentPane().add(lblChocBasketPrice);

        JLabel lblChocBouquetPrice = new JLabel("RM 150.00");
        lblChocBouquetPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblChocBouquetPrice.setBounds(541, 374, 76, 13);
        frame.getContentPane().add(lblChocBouquetPrice);

        JLabel lblFlowerPrice = new JLabel("RM 75.00");
        lblFlowerPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblFlowerPrice.setBounds(764, 375, 59, 13);
        frame.getContentPane().add(lblFlowerPrice);

        // Quantity label and JComboBox for Flower Bear Bouquet
        JLabel lblBearQuantity = new JLabel("Quantity:");
        lblBearQuantity.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblBearQuantity.setBounds(83, 412, 57, 13);
        frame.getContentPane().add(lblBearQuantity);

        JComboBox<Integer> comboBoxBear = new JComboBox<>();
        for (int i = 0; i <= 5; i++) {
            comboBoxBear.addItem(i);
        }
        comboBoxBear.setBounds(138, 407, 65, 25);
        frame.getContentPane().add(comboBoxBear);

        // Quantity label and JComboBox for Chocolate Basket
        JLabel lblChocBasketQuantity = new JLabel("Quantity:");
        lblChocBasketQuantity.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblChocBasketQuantity.setBounds(293, 413, 57, 13);
        frame.getContentPane().add(lblChocBasketQuantity);
        JComboBox<Integer> comboBoxChocBasket = new JComboBox<>();
        for (int i = 0; i <= 5; i++) {
            comboBoxChocBasket.addItem(i);
        }
        comboBoxChocBasket.setBounds(353, 407, 65, 25);
        frame.getContentPane().add(comboBoxChocBasket);

        // Quantity label and JComboBox for Chocolate Bouquet
        JLabel lblChocBouquetQuantity = new JLabel("Quantity:");
        lblChocBouquetQuantity.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblChocBouquetQuantity.setBounds(512, 412, 62, 13);
        frame.getContentPane().add(lblChocBouquetQuantity);
        JComboBox<Integer> comboBoxChocBouquet = new JComboBox<>();
        for (int i = 0; i <= 5; i++) {
            comboBoxChocBouquet.addItem(i);
        }
        comboBoxChocBouquet.setBounds(570, 407, 65, 25);
        frame.getContentPane().add(comboBoxChocBouquet);

        // Quantity label and JComboBox for Flower Bouquet
        JLabel lblFlowerQuantity = new JLabel("Quantity:");
        lblFlowerQuantity.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblFlowerQuantity.setBounds(707, 412, 57, 13);
        frame.getContentPane().add(lblFlowerQuantity);
        JComboBox<Integer> comboBoxFlower = new JComboBox<>();
        for (int i = 0; i <= 5; i++) {
            comboBoxFlower.addItem(i);
        }
        comboBoxFlower.setBounds(764, 407, 65, 25);
        frame.getContentPane().add(comboBoxFlower);
        
	//RESTFul web service, where the program constructs an HTTP POST request to send order details to a PHP script. 
        JButton btnCheckout = new JButton("Check Out");
        btnCheckout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the input values
                String name = textField.getText().trim();
                String email = textField_1.getText().trim();

                // Validate name, error handling during database operations. Validation is performed to ensure correct customer input
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Name field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!name.matches("[a-zA-Z ]+")) {
                    JOptionPane.showMessageDialog(frame, "Name can only contain letters and spaces.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validate email
                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Email field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
                    JOptionPane.showMessageDialog(frame, "Invalid email format.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (name.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill out your name and email.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Get the selected quantities
                int bearQty = (Integer) comboBoxBear.getSelectedItem();
                int chocBasketQty = (Integer) comboBoxChocBasket.getSelectedItem();
                int chocBouquetQty = (Integer) comboBoxChocBouquet.getSelectedItem();
                int flowerQty = (Integer) comboBoxFlower.getSelectedItem();
                
                // Prices for each item
                double bearPrice = 80.00;
                double chocBasketPrice = 130.00;
                double chocBouquetPrice = 150.00;
                double flowerPrice = 75.00;

                // Calculate the totals
                int totalItems = bearQty + chocBasketQty + chocBouquetQty + flowerQty;
                double totalCost = (bearQty * bearPrice) + (chocBasketQty * chocBasketPrice) +
                                   (chocBouquetQty * chocBouquetPrice) + (flowerQty * flowerPrice);
                
                // Display the totals
                textField_2.setText(String.valueOf(totalItems));
                textField_3.setText(String.format("RM %.2f", totalCost));
                
                JOptionPane.showMessageDialog(frame, 
                        "Name: " + name + "\nEmail: " + email + "\nBear Quantity: " + bearQty + 
                        "\nChocolate Basket Quantity: " + chocBasketQty + "\nChocolate Bouquet Quantity: " + chocBouquetQty +
                        "\nFlower Quantity: " + flowerQty + "\n\nTotal Items: " + totalItems + "\nTotal Cost: RM " + totalCost,
                        "Order Details", JOptionPane.INFORMATION_MESSAGE);
                
                // Create URL parameters [RESTful service part]
                String params = "customer_name=" + name + "&customer_email=" + email +
                                "&bear_qty=" + bearQty + "&choc_basket_qty=" + chocBasketQty +
                                "&choc_bouquet_qty=" + chocBouquetQty + "&flower_qty=" + flowerQty + 
                                "&total_items=" + totalItems + "&total_cost=" + totalCost;

                try {
                    @SuppressWarnings("deprecation")
					URL url = new URL("http://localhost/customer/giftShop.php"); //Our PHP File path
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    OutputStream os = conn.getOutputStream();
                    os.write(params.getBytes());
                    os.flush();
                    os.close();

                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                    	// Send order data to delivery system via socket
                        sendOrderData(name, email, bearQty, chocBasketQty, chocBouquetQty, flowerQty, totalItems, totalCost);
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();
                        JOptionPane.showMessageDialog(frame, response.toString(), "Info", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to place order", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

			private void sendOrderData(String name, String email, int bearQty, int chocBasketQty, int chocBouquetQty,
					int flowerQty, int totalItems, double totalCost) {
				// TODO Auto-generated method stub
				 String params = "Name=" + name + "\tEmail=" + email +
	                        "\tbear qty=" + bearQty + "\tchoc basket_qty=" + chocBasketQty +
	                        "\tchoc bouquet_qty=" + chocBouquetQty + "\tflower_qty=" + flowerQty + 
	                        "\ttotal cost=RM" + totalCost + "\ttotal items=" + totalItems;

	        try {
		    // The application sends order details to a server using TCP socket communication.
	            Socket socket = new Socket("localhost", 9090);  // Connect to delivery server 9090
	            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	            out.writeUTF(params);
	            out.flush();
	            socket.close();  // Close the socket
	            JOptionPane.showMessageDialog(frame, "Order details sent to delivery system.", "Info", JOptionPane.INFORMATION_MESSAGE);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(frame, "Ok: " + ex.getMessage());
	        }
	    }
				
			
        });
        btnCheckout.setBounds(752, 487, 106, 33);
        frame.getContentPane().add(btnCheckout);

        JLabel lblTotalItems = new JLabel("TOTAL ITEMS:");
        lblTotalItems.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTotalItems.setBounds(488, 499, 91, 13);
        frame.getContentPane().add(lblTotalItems);

        textField_2 = new JTextField();
        textField_2.setBounds(579, 497, 69, 19);
        frame.getContentPane().add(textField_2);
        textField_2.setColumns(10);

        JLabel lblTotalCost = new JLabel("TOTAL (RM):");
        lblTotalCost.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTotalCost.setBounds(39, 500, 91, 13);
        frame.getContentPane().add(lblTotalCost);

        textField_3 = new JTextField();
        textField_3.setBounds(140, 497, 105, 19);
        frame.getContentPane().add(textField_3);
        textField_3.setColumns(10);
    }
}
