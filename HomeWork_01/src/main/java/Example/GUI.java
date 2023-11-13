package Example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    // Создание кнопки, название кнопки "Press"
    private JButton button = new JButton("Press");
    // Создание текстового поля, надписи не будет (""), 5 колонок
    private JTextField input = new JTextField("", 5);
    // Добавляем надпись для "input"
    private JLabel label = new JLabel("Input:"); // Без кнопки, без объекта, просто надпись в панели
    // Создание выбора кругляшки
    private JRadioButton radio1 = new JRadioButton("Select this");
    private JRadioButton radio2 = new JRadioButton("Select that");
    // Создание галочки в квадрате, по умолчанию будет false (без галочки)
    private JCheckBox check = new JCheckBox("Check", false);

    public GUI() {
        super("Simple example"); // Передаем название формы в конструктор
        // Указываем положение и размер окна
        this.setBounds(100, 100, 250, 100);
        // Закрытие на крестик
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создание контейнера, той формы, где будут все кнопки
        Container container = this.getContentPane();
        // Создаем в контейнере поле кнопок = 3 строки и 2 колонки, и по 2 отступа по вертикали и горизонтали
        container.setLayout(new GridLayout(3, 2,2 , 2));
        // Помещаем в контейнер кнопки
        container.add(label); // поместили надпись для input
        container.add(input); // поместили input
        // группируем JRadiobutton, чтобы при нажатии на radio1, галочка с radio2 пропадала
        ButtonGroup group = new ButtonGroup(); // Если не группировать, то они будут менять своё значение самостоятельно
        group.add(radio1);
        group.add(radio2);
        // После этого, добавляем в контейнер кнопки radio
        container.add(radio1);
        radio1.setSelected(true); // Установили галочку по умолчанию на первую кнопку
        container.add(radio2);
        // Добавляем check
        container.add(check);
        // Прописываем кнопку и ее действие
        // addActionListener позволяет слушать кнопку и вызывать класс при нажатии - будет выполнять действие
        button.addActionListener(new ButtonEventListener()); // создаем отдельно класс - действие
        // Добавляем в контейнер кнопку
        container.add(button);
    }

    /**
     * Создаем отдельный класс для ButtonEventListener и реализуем интерфейс ActionListener,
     * он будет выполнять олин метод, который мы переопределим
     */
    class ButtonEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String message = "";
            message += "button was pressed\n";
            message += "Text is " + input.getText() + "\n"; // добавляем текст из нашего input
            message += (radio1.isSelected() ? "radio1" : "radio2") + " is selected\n"; // Если выбрано radio1, то пишем выбрано radio1
            message += "CheckBox is " + ((check.isSelected()) ? "checked" : "unchecked"); // так же указываем для check
            // null - не свой собственный компонент, то есть нет свое панели или родительской панели, куда он выводит сообщение
            // message - то, что мы выводим
            // output - будет название таблички
            // JOptionPane.PLAIN_MESSAGE - простое сообщение(суть всей строки: новым окном - новое сообщение)
            JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
