package start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;


public class you_won_window extends JFrame {
    /**
     * zmienna odpowiedzialna za kontrolę ilości żyć
     */
    private int current_level;
    /**
     * tablica przechowująca dane gry
     */
    private int[] game_data;
    /**
     * tablica przechowująca najlepsze wyniki
     */
    private int[] highest_score_data;


    /**
     * tworzenie nowego panelu
     */
    JPanel you_won = new JPanel();

    /**
     * funkcja wyświetlająca okno po wygranym poziomie
     */
    you_won_window(int difficulty, int lives, int level, int level_to_finish) throws FileNotFoundException {
        /** ustawienie wymiarów okna */
        setSize(new Dimension(300, 200));
        /** ustawienie zamykania programi wraz z zamknięciem okna */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /** włączenie widzialności okna */
        setVisible(true);
        /** inkrementacja poziomu  */
        current_level = level + 1;
        try {
            /**  wczytanie obecnej wartości punktów */
            game_data = new Read().read(9);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        add(you_won);
        /** ustawienie rozmieszczenia elemetów*/
        you_won.setLayout(new GridLayout(4, 2, 300, 30));
        /** ustawienie wymiarów panelu */
        you_won.setSize(new Dimension(300, 200));
        /** stworzenie etykiety wyświetlającej punkty */
        JLabel score_label = new JLabel("you achieved next level:" + game_data[1]);
        you_won.add(score_label);
        /** stworzenie przycisku wyjścia */
        JButton exit_button = new JButton("exit");
        you_won.add(exit_button);
        /** funkcja otwierająca okno startowe z wciśnięciem przycisku wyjścia */
        exit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new Start_Window();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        /** stworzenie przycisku następnego poziomu */
        JButton next_level_button = new JButton("next level");              //przycisk restart
        you_won.add(next_level_button);
        /** funkcja otwierająca okno nowego poziomu gry z wciśnięciem przycisku następnego poziomu  */
        next_level_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    new dificulty_level_clicked(difficulty, lives, current_level);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        /** funkcja warunkowa zmieniająca wygląd okna wraz z wykryciem ostatniego poziomu  */
        if (current_level == level_to_finish) {
            /** usuwanie obecnych elementów panelu  */
            you_won.removeAll();
            /** tworzenie etykiety wyświtlającej zdobytą ilość punktów */
            JLabel end_label = new JLabel("you finished game with score:" + game_data[1]);
            you_won.add(end_label);
            /** tworzenie przycisku zagraj ponownie */
            JButton restart_game_button = new JButton("play again");
            you_won.add(restart_game_button);
            /** wczytywanie plików najlepsych wyników  */
            highest_score_data = new Read().read(8);
            /** zapisanie obecnej liczby punktów jako nadmiarowy wynik w liście najlepszych wyników */
            highest_score_data[10] = game_data[1];
            /**  sortowanie danych wyników od najmnieszego do największego*/
            Arrays.sort(highest_score_data, 0, 11);
            /**  zapisanie do pliku listy nowych najlepszych wyników */
            PrintWriter createtxtfile = new PrintWriter("Highest_score.txt");
            createtxtfile.println("score" + String.valueOf(highest_score_data[10]));
            createtxtfile.println("score" + String.valueOf(highest_score_data[9]));
            createtxtfile.println("score" + String.valueOf(highest_score_data[8]));
            createtxtfile.println("score" + String.valueOf(highest_score_data[7]));
            createtxtfile.println("score" + String.valueOf(highest_score_data[6]));
            createtxtfile.println("score" + String.valueOf(highest_score_data[5]));
            createtxtfile.println("score" + String.valueOf(highest_score_data[4]));
            createtxtfile.println("score" + String.valueOf(highest_score_data[3]));
            createtxtfile.println("score" + String.valueOf(highest_score_data[2]));
            createtxtfile.println("score" + String.valueOf(highest_score_data[1]));
            /** zamknięcie pliki zapisu  */
            createtxtfile.close();
            /**  zapisanie do pliku wyzerowania obecnego wyniku */
            PrintWriter createtxtfile2 = null;
            createtxtfile2 = new PrintWriter("game_data.txt");
            createtxtfile2.println("your game ended");
            createtxtfile2.println("your score =" + String.valueOf(0));
            createtxtfile2.close();
            /**  funkcja realizująca otwarcie nowej gry po wciśnięciu przycisku nowej gry*/
            restart_game_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    dispose();
                    try {
                        new dificulty_level_clicked(difficulty, 3, 1);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            you_won.add(exit_button);

        }
    }
}



