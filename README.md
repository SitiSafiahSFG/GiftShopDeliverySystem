Gift Shop Application

- How many apps involved 
  This system consists of two applications which are the Delivery application (delivery.java) and the Customer application (customer.java). 

- Brief explanation each apps  
  
  Customer application (customer.java) 
  The purpose of the customer application is to give clients a graphical user interface via which they can make orders at Stella Gift Shop. 
  Order information are sent to the delivery programme, and it processes and verifies user input as well as computes the final cost. 
  The application configures event listeners and initializes the GUI elements. 
  When the "Check Out" button is hit, ActionListener will be added to manage the checkout procedure. 
  It determines the total items and cost. uses an HTTP POST request to provide order details to a local server. 
  Through a socket connection, TCP Socket transmits the identical order data to the delivery application and Link to a MySQL database in order to obtain order information. 

  Delivery application (delivery.java) 
  The delivery application serves as a server, receiving orders from the customer application and displaying them for delivery processing. 
  The Server Socket receives incoming connections on port 9090. 
  Customer (customer application) communication is managed by the Socket. 
  To handle the corresponding actions of the "CLEAR" and "CLOSE" buttons, an ActionListener is attached to them. 

- Architecture/Layer diagram for each of the apps including the middleware 

![WhatsApp Image 2024-07-11 at 10 06 15 PM](https://github.com/SitiSafiahSFG/GiftShopDeliverySystem/assets/149215963/73bbe146-8099-4783-9738-8961f1daa0f8)

- List of URL end points middleware RESTful/SOAP/Socket  

  REST API Endpoint:

  URL: http://localhost/customer/giftShop.php
  Purpose: Customer information, item quantities, total cost, and total number of items are sent via HTTP POST 
           from this endpoint to a local PHP script (giftShop.php).

Socket Endpoint:

  IP Address: 10.200.104.108
  Port: 9090
  Purpose: To send order details to a delivery system using bidirectional communication over TCP/IP

- Functions/Features in the middleware 
  
  RESTful API (HTTP):

  Function: Connect with RESTful API endpoint to send the order details.
  Key Operations: POST Request (creates POST parameters that have order quantity and customer information)
                  HTTP Connection (makes a connection over HTTP to transfer data to the API endpoint)
                  Response Handling (controls HTTP response codes and shows success or failure messages)

  Socket (for Database Connection):

  Function: Communicate with a delivery server to send order details from the client (customer system) to the server (delivery system).
  Key Operations: Socket Creation (listening on port 9090 to connect to the delivery server)
                  Data Transmission (converts order details into UTF-encoded strings using a DataOutputStream)
                  Real-time Communication (immediate data transfer between the server (delivery system) and client (customer system))

  Database Connectivity (JDBC):

  Function: Retrieves order data by interacting with the giftshop MySQL database.
  Key Operations: JDBC Setup (connects to a MySQL database using Connector/J library)
                  SQL Query Execution (makes a Statement object and runs a SQL SELECT query)
                  Data Processing (retrieves and processes order data by looping over the ResultSet)

- The database and tables involve in the projects 

Database name : giftshop 

 Table name : orders 

 Attributes : 1. customer_name (customer name) 
              2. customer_email (customer email) 
              3. bear_qty (flower bear bouquet quantity) 
              4. choc_basket_quantity (chocolate basket quantity)
              5. choc_bouquet_quantity (chocolate bouquet quantity) 
              6. flower_qty (flower bouquet quantity)  
              7. total_item (total item purchase) 
              8. total_cost (total price)

![dddddddddddddddddd](https://github.com/SitiSafiahSFG/GiftShopDeliverySystem/assets/149215963/8882133f-695e-42c5-b6c2-3270f3e773c9)

- Teams link project presentation
  https://utemedu.sharepoint.com/sites/GROUP18_DAD/_layouts/15/stream.aspx?id=%2Fsites%2FGROUP18%5FDAD%2FShared%20Documents%2FGeneral%2FRecordings%2FDAD%20Project%2D20240713%5F013951%2DMeeting%20Recording%2Emp4&referrer=StreamWebApp%2EWeb&referrerScenario=AddressBarCopied%2Eview%2Ed83cb940%2D0698%2D4c53%2Da6d2%2D11be9ad1a503

- Canva slide presentation
  https://www.canva.com/design/DAGKwENgH4Y/yPx1kEe4NUVws-_LIZBUTA/edit?utm_content=DAGKwENgH4Y&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton




