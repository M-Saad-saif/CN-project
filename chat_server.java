//declearing package
package chat;

//import chat.chat_server.stopServer.ClientReconnectManager;
//import chat.chat_server.stopServer.ClientReconnectManager;
//import chat.chat_server.stopServer.ClientReconnectManager;
import java.io.*;  //for input and outputs
import java.net.*;  //for networking sockects
import java.util.*;  //for list and collections (List<ClientHandler>, ArrayList<>)
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

// main class extending Jframe to create a GUI for the server (we build this for window based interface instead of console based system)
public class chat_server extends javax.swing.JFrame {

    static ServerSocket serverSocket; //server sockects to accpet clients
    static Map<String, ClientHandler> clients = Collections.synchronizedMap(new HashMap<>()); //thread safe map to store clients by name so that we can know who is active
    private Thread serverThread;
    private volatile boolean isRunning = false;
    private static Map<String, String> active_clientName_Map = new ConcurrentHashMap<>();

    // constructor for initializing the GUI
    public chat_server() {
        initComponents();

        DefaultCaret caret = (DefaultCaret) msg_area.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        msg_area.setEditable(false);
        jScrollPane1.setAutoscrolls(true);

        start_btn.addActionListener(evt -> startServer());
        stop_server.addActionListener(evt -> {
            try {
                stopServer();
            } catch (IOException ex) {
                System.getLogger(chat_server.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });

        // function of server starting btn
        start_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startServer();
            }
        });
        // function of server stopping btn
        stop_server.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    stopServer();
                } catch (IOException ex) {
                }
            }
        });

    }

    //    UI setup code which is auto generated
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        msg_send = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        msg_text = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        stop_server = new javax.swing.JButton();
        start_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        msg_area = new javax.swing.JTextArea();

        jButton1.setText("jButton1");

        msg_send.setBackground(new java.awt.Color(86, 86, 246));
        msg_send.setForeground(new java.awt.Color(255, 255, 255));
        msg_send.setText("Send");
        msg_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msg_sendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 204));
        setPreferredSize(new java.awt.Dimension(360, 797));
        setResizable(false);

        msg_text.setBackground(new java.awt.Color(167, 168, 220));
        msg_text.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 29)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Server");
        jLabel1.setAlignmentX(15.0F);
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        stop_server.setBackground(new java.awt.Color(255, 153, 153));
        stop_server.setForeground(new java.awt.Color(102, 0, 0));
        stop_server.setText("Stop");
        stop_server.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stop_serverActionPerformed(evt);
            }
        });

        start_btn.setBackground(new java.awt.Color(153, 153, 255));
        start_btn.setForeground(new java.awt.Color(0, 0, 102));
        start_btn.setText("Start");
        start_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                start_btnActionPerformed(evt);
            }
        });

        msg_area.setBackground(new java.awt.Color(255, 204, 204));
        msg_area.setColumns(20);
        msg_area.setRows(5);
        msg_area.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(msg_area);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(msg_text, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(start_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stop_server))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(stop_server)
                        .addComponent(start_btn))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116)
                .addComponent(msg_text, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void stop_serverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stop_serverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stop_serverActionPerformed

    private void start_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_start_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_start_btnActionPerformed

    private void msg_sendActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_msg_sendActionPerformed
        // TODO add your handling code here:
    }

    //       main method 
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new chat_server().setVisible(true);//creating and showing the GUI
            msg_area.setEditable(false); //preventing user to type inside the server GUI
        });
    }

    //starting the server socket thread 
    private void startServer() {
        if (isRunning) {
            msg_area.append("Server is already running.\n");
            return;
        }
        isRunning = true;
        serverThread = new Thread(() -> {
            try {
                serverSocket = new ServerSocket(1201); //starting server on port 1201

                SwingUtilities.invokeLater(()
                        -> msg_area.append("Server started. Waiting for clients...\n") // msg displayed after server started
                );

                new Thread(() -> {
                    try {
                        Thread.sleep(2000); // Wait a bit for server to stabilize
                        for (String clientName : ClientReconnectManager.getLastActiveClients()) {
                            // This is just a placeholder
                            msg_area.append("Attempting to notify client: " + clientName + "\n");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();

                while (isRunning) {
                    Socket socket = serverSocket.accept(); //using accpet() method for accepting new clients
                    // CREATING STreams for input and output
                    DataInputStream dtaINPUTstrm = new DataInputStream(socket.getInputStream());
                    DataOutputStream dtaOUTPUTstrm = new DataOutputStream(socket.getOutputStream());

                    String name = dtaINPUTstrm.readUTF(); // read the name of the client after inputing the name while running the cleint file 

                    SwingUtilities.invokeLater(()
                            -> msg_area.append("\nClient connected: " + name + "  /  " + socket.getInetAddress())
                    );

                    ClientHandler clientHandler = new ClientHandler(socket, name, dtaINPUTstrm, dtaOUTPUTstrm); //create handler to handle each client and broadcast their msg
                    clients.put(name, clientHandler); // this method add to clietns map
                    clientHandler.start(); //starting of client thread
                    Active_Clients(); //calling funtion to show the client who are active
                }
            } catch (IOException e) {
            }
        });
        serverThread.start();
    }

    private void stopServer() throws IOException {
        if (!isRunning) {
            return;
        }
        isRunning = false;

        //save client naem with thier IP Address
        synchronized (clients) {
            for (ClientHandler client : clients.values()) {
                active_clientName_Map.put(client.socket.getInetAddress().toString(), client.clientName);
            }
            // Send restart notification
            broadcastMessage("SERVER_RESTARTING", null, true);
            try {
                Thread.sleep(000); // Give clients time to receive the message
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // clean shutdown when server is closed
            broadcastMessage("Server has shut down.", null, true);  //all the client revecive this msg
            // Close connections
            for (ClientHandler client : clients.values()) {
                client.closeConnection();
            }
            clients.clear();
        }

        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Modify the client handling in startServer
        while (isRunning) {
            Socket socket = serverSocket.accept();// accpet the cleint
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

            // Check if this is a reconnecting client
            String clientIPadd = socket.getInetAddress().toString(); //get client IP    
            if (active_clientName_Map.containsKey(clientIPadd)) {
                // Reconnecting client - use saved name
                String name = active_clientName_Map.get(clientIPadd); //check IP exist in active list
                ClientHandler clientHandler = new ClientHandler(socket, name, dis, dout);
                clients.put(name, clientHandler);
                clientHandler.start();
            } else {
                // New client - ask for name
                String name = dis.readUTF(); // read the user nmae 
                active_clientName_Map.put(clientIPadd, name); //add that client in the active list
                ClientHandler clientHandler = new ClientHandler(socket, name, dis, dout); //start new handler thread
                clients.put(name, clientHandler);
                clientHandler.start(); //strat the thread

            }
        }
    }

    class ClientReconnectManager {

        private static List<String> lastActiveClients = new ArrayList<>(); //maintain a list of clients

        public static void setLastActiveClients(List<String> clients) {
            lastActiveClients = new ArrayList<>(clients); // update list of active clients
        }

        public static List<String> getLastActiveClients() {
            return new ArrayList<>(lastActiveClients); //retuirng of copy of new 
        }
    }

    //now here showing active client in the GUI of server and calling this function in the main method //now here showing active client in the GUI of server and calling this function in the main method
    private static void Active_Clients() {
        msg_area.append("\nActive clients: " + clients.keySet() + "\n");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea msg_area;
    private javax.swing.JButton msg_send;
    private javax.swing.JTextField msg_text;
    private javax.swing.JButton start_btn;
    private javax.swing.JButton stop_server;
    // End of variables declaration//GEN-END:variables

    //broadcast method to send msges to all the clients
    static void broadcastMessage(String message, ClientHandler sender, boolean logToServer) {
        synchronized (clients) {
            //using for eah loop to target each client
            for (ClientHandler client : clients.values()) {
                //broadcast msg to all client except the sender
                if (client != sender) {
                    client.sendMessage(message);
                }
            }
        }
        // if logTosevrer boolean is true the msg will append in msg area.
        if (logToServer) {
            msg_area.append(message + "\n");
        }
    }

    //clienthandler class that run each client in a thread
    static class ClientHandler extends Thread {

        private final Socket socket;
        private final String clientName;
        private final DataInputStream dtaINPUTstrm;
        private final DataOutputStream dtaOUPUTstrm;

        //constructor
        public ClientHandler(Socket socket, String clientName, DataInputStream dataIN, DataOutputStream dataOUT) {
            this.socket = socket;
            this.clientName = clientName;
            this.dtaINPUTstrm = dataIN;
            this.dtaOUPUTstrm = dataOUT;
        }

        //closing   all streams and socket related to client
        public void closeConnection() {
            try {
                if (dtaINPUTstrm != null) {
                    dtaINPUTstrm.close(); //closing input stream
                }
                if (dtaOUPUTstrm != null) {
                    dtaOUPUTstrm.close(); // closing output stream
                }
                if (socket != null && !socket.isClosed()) {
                    socket.close(); //closing the network connection
                }
            } catch (IOException e) {
                e.printStackTrace();  //throw the error if somthing goes wrong
            }
        }

        //this is the logic htat run in a client thread it is kind of a heart of server side logic
        public void run() {
            try {
                broadcastMessage("'"+clientName + "' joined the chat.", this, false); //send msg to all clients of joining
                String msg_input;
                while (true) {
                    msg_input = dtaINPUTstrm.readUTF(); //read msg from the client

                    //exit the chat if client press the exit btn
                    if (msg_input.equalsIgnoreCase("exit")) {
                        break;
                    }

                    //handle all the special characters and notify the client
                    if (msg_input.contains("@") || msg_input.contains("#") || msg_input.contains("$") || msg_input.contains("%") || msg_input.contains("^") || msg_input.contains("&") || msg_input.contains("*")) {
                        sendMessage("Server: ''Error donot use special characters''");
                        continue;
                    }
                    broadcastMessage(clientName + ": " + msg_input, this, true);  //breadcast the msg which client send to other clients
                }
            } catch (IOException e) {
                msg_area.append("\nConnection lost with '" + clientName + "'\n");//notify when client leave the chat

            } finally { //this block alway run
                closeConnection();  //closing of stream and socket
                clients.remove(clientName);  //removing of client from the list
                broadcastMessage("'"+clientName + "' left the chat.", this, true); //notify all the others
                Active_Clients(); //create a new list
            }
        }

        //sending msg to the spcific client
        void sendMessage(String message) {
            try {
                dtaOUPUTstrm.writeUTF(message);
            } catch (IOException e) {
                msg_area.append("Error sending to " + clientName + "\n");
            }
        }
    }
}
