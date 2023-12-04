package org.example;
/**
 * В качестве задачи предлагаю вам реализовать код для демонстрации парадокса Монти Холла
 * (Парадокс Монти Холла — Википедия) и наглядно убедиться в верности парадокса
 * (запустить игру в цикле на 1000 и вывести итоговый счет).
 * Необходимо:
 * Создать свой Java Maven или Gradle проект;
 * Самостоятельно реализовать прикладную задачу;
 * Сохранить результат в HashMap<шаг теста, результат>;
 * Вывести на экран статистику по победам и поражениям.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    private static final Random rand = new Random();
    public static void main(String[] args) {
        Map<Integer, String> result = new HashMap<>(); // Учет результатов
        for(int i = 0; i < 1000; i++ ){
            int[] doors = {0,0,0}; // создаем игровое поле - 3 двери
            doors[rand.nextInt(3)] = 1; // Рандомно ставим машину за одну из дверей
            int choiceGamer = rand.nextInt(3); // Рандомно делаем выбор игрока
            int showDoor; // Дверь, которую будет открывать ведущий
            do{
                showDoor = rand.nextInt(3); // Рандомно выбираем дверь соблюдая условия игры
                // выбираем, какую дверь открыть, чтобы она не была выбрана игроком и в ней не было авто
            }while(doors[showDoor] == 1 || showDoor == choiceGamer);

            if (doors[choiceGamer] == 1) { // Если игрок угадал не меняя решения
                result.put(i, "Выиграл не меняя решения");
            } else { // В обратном случае
                result.put(i, "Выиграл изменив первичное решение");
            }
        }
        printResult(result);
    }
    private static void printResult(Map<Integer, String> res){
        int noSwitchChoice = 0;
        int switchChoice= 0;
        for (String result : res.values()) {
            if (result.equals("Выиграл не меняя решения")) {
                noSwitchChoice++;
            } else {
                switchChoice++;
            }
        }
        System.out.println("Всего игр сыграно: " + res.size());
        System.out.println("Выиграл, не меняя решения: " + noSwitchChoice + ", при этом проиграл: " + (res.size() - noSwitchChoice));
        System.out.println("Выиграл, изменив своё решение: " + switchChoice + ", при этом проиграл: " + (res.size() - switchChoice));
    }
}