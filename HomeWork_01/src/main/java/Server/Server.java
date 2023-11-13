package Server;
import Client.Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
public class Server extends JFrame {
    private static final int WIN_SERV_HEIGHT = 350; // Положение окна на экране
    private static final int WIN_SERV_WIDTH = 300;
    private static final int WIN_SERV_POSX = 600; // размер окна
    private static final int WIN_SERV_POSY = 500;
    private static final String LOG_PATH = "src/main/java/Server/log.txt";
    private static final TextArea panel = new TextArea();
    JButton btnStart = new JButton("Start"); // класс JButton отвечает за кнопки
    JButton btnExit = new JButton("Exit");
    private boolean serverIsWork = false;
    List<Client> list;
    JPanel panChatBtn;
    JPanel panBotton;
    JButton stopServer = new JButton("Stop");
    JButton exitChat = new JButton("Exit");
    public Server() {
        list = new ArrayList<>();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WIN_SERV_POSX, WIN_SERV_POSY); // установка позиции окна
        setSize(WIN_SERV_WIDTH, WIN_SERV_HEIGHT); // установка размера окна
        setTitle("Chat Server"); // название окна
        setResizable(false); // запрет на масштабируемость окна
        // Кнопка exit - выход из приложения
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.append("Server is stopped and program is closed\n");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });
        // Кнопка start - запуск чата
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverIsWork) {
                    panel.append("Server is run...\n");
                }else {
                    serverIsWork = true;
                    panel.append("Server is started!\n");
                    panBotton.setVisible(false);
                    btnServerIsActive();
                    panChatBtn.setVisible(true);
                }
            }
        });
        stopServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopServer();
                panBotton.setVisible(true);
            }
        });
        // Добавляем в панель кнопки, в низ
        panBotton = new JPanel(new GridLayout(1, 2));
        panBotton.add(btnStart);
        panBotton.add(btnExit);
        add(panel);
        add(panBotton, BorderLayout.SOUTH);
        setVisible(true); // установка видимости окна
    }
    public static TextArea getPanel() {
        return panel;
    }
    public boolean connectUser(Client client){
        if (serverIsWork) {
            list.add(client);
            return true;
        } else {
            return false;
        }
    }
    public List<Client> getList() {
        return list;
    }
    private void saveInLog(String message){
        try (FileWriter writer = new FileWriter(LOG_PATH, true)){
            writer.write(message);
            writer.write("\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void messageServ(String message){
        if(serverIsWork) {
            saveInLog(message);
            panel.append(message + "\n");
            for (Client cl: list) {
                cl.getChat().append(message + "\n");
            }
        }
    }
    private String readLog(){
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH);){
            int c;
            while ((c = reader.read()) != -1){
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public String getReadLog(){
        return readLog();
    }

    public boolean getServerIsWork() {
        return serverIsWork;
    }
    private Component btnServerIsActive() {
        if (serverIsWork){
            panChatBtn = new JPanel(new GridLayout(1, 2));
            panChatBtn.add(stopServer);
            panChatBtn.add(exitChat);
            add(panChatBtn, BorderLayout.SOUTH);
            return panChatBtn;
        }
        return null;
    }
    private void stopServer(){
        if (serverIsWork){
            serverIsWork = false;
            panel.append("Server was stopped\n");
            panChatBtn.setVisible(false);
            add(panBotton, BorderLayout.SOUTH);
            for (int i = list.size() - 1; i >= 0; i--) {
                list.get(i).getChat().append("Server is down... you are disconnect\n");
                list.get(i).disconnectServer();
                list.remove(i);
            }
        } else {
            panel.append("Server don't run\n");
        }
    }
}
