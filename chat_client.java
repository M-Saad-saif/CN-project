//delcreaing package
package chat;
//import

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class chat_client extends javax.swing.JFrame {

    //networking components
    static Socket socket; //socket used to connect to the server
    static DataInputStream dtaINPUTstrm;  //Input stream for receiving data from the server.
    static DataOutputStream dtaOUTPUTstrm;
    private static String savedUserName = null;

    //consturctor that initializes the GUI
    public chat_client() {
        initComponents();
    }

    //auto generated code
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        msg_area = new javax.swing.JTextArea();
        msg_text = new javax.swing.JTextField();
        msg_send = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));
        setPreferredSize(new java.awt.Dimension(350, 365));
        setResizable(false);

        msg_area.setBackground(new java.awt.Color(203, 203, 222));
        msg_area.setColumns(20);
        msg_area.setRows(5);
        jScrollPane1.setViewportView(msg_area);

        msg_text.setBackground(new java.awt.Color(167, 168, 220));
        msg_text.setForeground(new java.awt.Color(0, 0, 0));

        msg_send.setBackground(new java.awt.Color(86, 86, 246));
        msg_send.setForeground(new java.awt.Color(255, 255, 255));
        msg_send.setText("Send");
        msg_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msg_sendActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 29)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Client");

        jLabel3.setBackground(new java.awt.Color(51, 51, 255));
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("Welcome to chat Group");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(msg_send, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(msg_text, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(143, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(msg_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(msg_send)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //btn handling when the send btn is clicked
    private void msg_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msg_sendActionPerformed
        // TODO add your handling code here:
        try {
            String msges = "";
            msges = msg_text.getText();  //.getText() method get msg from input feild
            dtaOUTPUTstrm.writeUTF(msges); // passing our msges in .writeUTF() mthod so that msg send to the server and server read
            msg_text.setText(""); // clearing the input field after the msg is send to the server
        } catch (IOException e) {
            //    handle Exception
        }
    }//GEN-LAST:event_msg_sendActionPerformed

    //main method and entry point of the client app
    public static void main(String args[]) throws InterruptedException {
        chat_client clientGUI = new chat_client(); // create GUI window for the client
        java.awt.EventQueue.invokeLater(() -> clientGUI.setVisible(true));
        msg_area.setEditable(false); //make the chat area only read-able not editable
        connectToServer(clientGUI);
    }

    private static void connectToServer(chat_client clientGUI) throws InterruptedException {
        try {
            socket = new Socket("127.0.0.1", 1201);
            dtaINPUTstrm = new DataInputStream(socket.getInputStream());
            dtaOUTPUTstrm = new DataOutputStream(socket.getOutputStream());

            if (savedUserName == null) {
                savedUserName = JOptionPane.showInputDialog("Enter your name:");
                dtaOUTPUTstrm.writeUTF(savedUserName);
                SwingUtilities.invokeLater(() -> {
                    msg_area.append("SERVER: ''Connected to server for the first time!''\n");
                });
            } else {
                dtaOUTPUTstrm.writeUTF(savedUserName);
                // Add this line for reconnection message
                SwingUtilities.invokeLater(() -> {
                    msg_area.append("SERVER: ''Reconnected to server successfully!''\n");
                });
            }
            clientGUI.jLabel1.setText(savedUserName);

            String message_input = "";
            while (true) {
                try {
                    message_input = dtaINPUTstrm.readUTF(); //send username to the server

                    // Check for server restart message
                    if (message_input.equals("SERVER: ''SERVER_RESTARTING''")) {
                        msg_area.append("SERVER: ''Server restarting - reconnecting...''\n");
                        closeConnection();
                        Thread.sleep(2000); // Wait before reconnecting
                        connectToServer(clientGUI);
                        return;
                    }

                    final String msg = message_input;
                    SwingUtilities.invokeLater(() -> {
                        msg_area.append(msg + "\n");
                        msg_area.setCaretPosition(msg_area.getDocument().getLength());
                    });

                } catch (Exception e) {
                    msg_area.append("SERVER: ''Connection lost - retrying...''\n");
                    try {
                        Thread.sleep(3000); //Pauses the thread for 3 sec before attempting to reconnect
                        connectToServer(clientGUI);  // try to reconnect to server
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        } catch (IOException e) {

            attemptReconnect(clientGUI);

        }
    }

    private static void attemptReconnect(chat_client clientGUI) {
        int attempts = 0;
        while (attempts < 1) { // Try 1 times

            try {
                Thread.sleep(1000); // Wait 3 seconds between attempts
                connectToServer(clientGUI); //connect to server
                msg_area.append("\nSEVRER: ''Reconnected to server'' \n");

                return; // if successfully connected it exit the method
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                return;
            } catch (Exception e) {
            }
            attempts++;
        }

    }

    private static void closeConnection() {
        try {
            //closes the output stream
            if (dtaOUTPUTstrm != null) {
                dtaOUTPUTstrm.close();
            }
            //closes the input stream
            if (dtaINPUTstrm != null) {
                dtaINPUTstrm.close();
            }
            //closes the socket connection
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace(); //detect error that occur during closing
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private static javax.swing.JTextArea msg_area;
    private javax.swing.JButton msg_send;
    private javax.swing.JTextField msg_text;
    // End of variables declaration//GEN-END:variables
}
