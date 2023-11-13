package Client;

import Server.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import java.io.Serializable;
import java.util.List;

public class Client extends JFrame {
    private static final int WIN_CL_HEIGHT = 350; // Положение окна на экране
    private static final int WIN_CL_WIDTH = 300;
    private static final int WIN_CL_POSX = 200; // размер окна
    private static final int WIN_CL_POSY = 500;
    JButton btnJoin = new JButton("Join");
    JButton btnSend = new JButton("Send");
    JTextField ip = new JTextField("127.0.0.2");
    JTextField port = new JTextField("8189");
    JTextField messChat;

    JLabel empty = new JLabel();
    JTextField name = new JTextField("");
    JTextField pass = new JTextField("******");
    JTextArea chat;
    Server serv;
    JPanel panBottom;
    JPanel connectBottom;
    private static boolean connected = false;

    public Client(Server server) {
        this.serv = server;
        messChat = new JTextField();
        setLocation(WIN_CL_POSX, WIN_CL_POSY);
        setSize(WIN_CL_WIDTH, WIN_CL_HEIGHT);
        setTitle("Chat client");
        setResizable(false);
        // Кнопка подключения к серверу
        btnJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectedToServer();
            }
        });
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messages();
            }
        });
        panBottom = new JPanel(new GridLayout(2, 3));
        panBottom.add(ip);
        panBottom.add(port);
        panBottom.add(empty);
        panBottom.add(name);
        panBottom.add(pass);
        panBottom.add(btnJoin);
        add(scrollMessChat());
        add(panBottom, BorderLayout.NORTH);
        connectBottom = new JPanel(new GridLayout(1, 2));
        connectBottom.add(messChat);
        connectBottom.add(btnSend);
        add(connectBottom, BorderLayout.SOUTH);
        connectBottom.setVisible(false);
        if (connected) {
            panBottom.setVisible(false);
            connectBottom.setVisible(true);
        }

        setVisible(true);
    }

    @Override
    public String getName() {
        return this.name.getText();
    }
    private void connectedToServer() {
        if (serv.getServerIsWork()) {
            serv.connectUser(this);
            chat.append("You join to server\n");
            String history = serv.getReadLog();
            if (!history.isEmpty()) {
                chat.append(history + "\n");
            }
            Server.getPanel().append(name.getText() + " join to server\n");
            connected = true;
            panBottom.setVisible(false);
            connectBottom.setVisible(true);
            setTitle("Chat client " + name.getText());
        } else {
            chat.append("Server is not run\n");
        }
    }
    public void disconnectServer() {
        connectBottom.setVisible(false);
        panBottom.setVisible(true);
    }
    private void messages() {
        if(connected){
            if (!messChat.getText().isEmpty()){
                String message = name.getText() + ": " + messChat.getText();
                serv.messageServ(message);
                messChat.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if (e.getKeyChar() == KeyEvent.VK_ENTER){
                            messages();
                        } /*else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){ // Так и не смог понять, почему не работает
                            chat.append("You disconnect to server");
                            disconnectUser(Client.this);
                        }*/
                    }
                });
                messChat.setText("");
            }
        }else {
        }
    }
    public JTextArea getChat() {
        return chat;
    }
    private Component scrollMessChat(){
        chat = new JTextArea("Enter your name\n");
        chat.setEditable(false);
        return new JScrollPane(chat);
    }
    private void disconnectUser() {
        if (connected){
            connected = false;
            connectBottom.setVisible(false);
            panBottom.setVisible(true);
            serv.messageServ("Client \"" + name.getText() + "\" disconnect from server");
        }
    }
    @Override
    protected void processWindowEvent(WindowEvent e){
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING){
            disconnectUser();
        }
    }
}
