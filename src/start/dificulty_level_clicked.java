package start;
/**
 * @author Krzysztof Zawadzki
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Klasa odpowiadająca za wyswietlanie okna po wybraniu poziomu trudności
 * wyświetla dwa panele z których jeden jest odpowiedzialny za informacje
 * na temat gry takie jak czas punkty a drugi to panel gdzie odbywa się gra właściwa.
 * Odowiada też za odmieżanie czasu gry przeliczanie i zapis punktów jak też
 * związane z czasem warunki śmierci .
 *
 */
public class dificulty_level_clicked extends JFrame {
    /**liczba żyć statku */
    private int life_count;
    /**tablica przechowująca dane obecnej gry */
    private int[] game_data;
    /**tablica przechowująca najwyższe wyniki */
    private int[] highest_score;
    /**zmienna odpowiedzialna za odmieżanie czasu */
    private int game_time ;
    /**zmienna odpowiedzialna za obecny level*/
    private int current_level;
    /**zmienna odpowiedzialna za odliczanie punktów zdobytych w poziomie*/
    private int score;
    /**zmienna odpowiedzialna za odliczanie punktów zdobytych w sumie*/
    private int overal_score;
    /**zmienna odpowiedzialna za liczbę punktów za dotarcie do lądownika*/
    private int wins_score;
    /**zmienna odpowiedzialna za dodawnie punktów zdobytych za zniszczenie nieprzyjaznych statków*/
    private int ship_destroyed_score;
    /**zmienna odpowiedzialna za tymczasową ilość żyć*/
    private int current_life_temp;
    /**zmienna odpowiedzialna za liczbę punktów potrzebnych do przejścia na następny poziom*/
    private int score_to_levelup;
    /**zmienna odpowiedzialna za kontrole pauzy*/
    private int pause_count;
    /**zmienna odpowiedzialna kontrolę przekrocznia czasi*/
    private int time_count;
    /**wskaźnik na panel gry*/
    DrawingPanel game_panel_handler;

    /**tworzy podzielony panel na dwa podpanele*/
    private JSplitPane splitPaneH;
    /**główna funkcja odpowiedzialna za zachowanie panelu informacyjnego i okna podczas gry*/
    dificulty_level_clicked(int difficulty, int lives, int level) throws FileNotFoundException {


        current_life_temp = life_count;
        highest_score = new Read().read(8);
        if (level > 1) {
            try {
                game_data = new Read().read(9);

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

            overal_score = game_data[1];

        } else {
            overal_score = score;
        }

        current_level = level;
        life_count = lives;
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel info_panel = new JPanel();                    //panel z informacjami
        JPanel game_panel = new JPanel();                    //panel z grą


        game_panel.setLayout(new BorderLayout());
        game_panel.setPreferredSize(new Dimension(700, 600));
        game_panel.setMinimumSize(new Dimension(1, 1));

        info_panel.setLayout(new GridLayout(8, 1, 100, 50));
        info_panel.setMaximumSize(new Dimension(150, 1000));
        info_panel.setPreferredSize(new Dimension(100, 600));
        info_panel.setMinimumSize(new Dimension(100, 1));

        splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPaneH.setResizeWeight(1);
        add(splitPaneH);

        splitPaneH.setLeftComponent(game_panel);
        splitPaneH.setRightComponent(info_panel);
        setVisible(true);

        setSize(1000, 800);

        Font game_font = new Font("TimesRoman", Font.BOLD, 10);
        Font game_font2 = new Font("TimesRoman", Font.BOLD, 15);
        setFont(game_font2);

        JLabel score_label = new JLabel("score :");
        score_label.setFont(game_font);

        info_panel.add(score_label);

        JLabel best_score_label = new JLabel("best score");
        best_score_label.setFont(game_font);

        info_panel.add(best_score_label);

        JLabel time_label = new JLabel("time :" + String.valueOf(game_time));
        time_label.setFont(game_font);

        info_panel.add(time_label);


        JLabel level_label = new JLabel("level :" + current_level);
        level_label.setFont(game_font);
        info_panel.add(level_label);


        JButton exit_button = new JButton("exit");
        exit_button.addActionListener(new ActionListener() {                //zamykanie za pomocą przycisku exit

            public void actionPerformed(ActionEvent e) {
                try {

                    new Start_Window();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        info_panel.add(exit_button);

        JButton help_button = new JButton("help");                    //przycisk help
        help_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(game_panel,"sterowanie przyciski q,w,e,a,s,d  " +
                        " dotrzyj do lądownika unikaj wrogich statków i domków obcych" +
                        " ląduj z rozsądną prędkością :D" , "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
            }
        });

        info_panel.add(help_button);

        JButton restart_button = new JButton("restart");              //przycisk restart
        restart_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();

                try {
                    new dificulty_level_clicked(difficulty, 3, 1);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }


            }
        });

        info_panel.add(restart_button);


        if (difficulty == 1) {
            var drawing_panel = new DrawingPanel(1, life_count, current_level);                 //ładowanie planszy easy

            game_panel_handler = drawing_panel;

            game_panel.add(drawing_panel);                      //dodanie planszy easy
        }

        if (difficulty == 2) {
            var drawing_panel = new DrawingPanel(2, life_count, current_level);                 //ładowanie planszy medium
            game_panel_handler = drawing_panel;
            game_panel.add(drawing_panel);                      //dodanie planszy medium
        }

        if (difficulty == 3) {
            var drawing_panel = new DrawingPanel(3, life_count, current_level);                 //ładowanie  hard
            game_panel_handler = drawing_panel;
            game_panel.add(drawing_panel);                      //dodanie planszy hard

        }
        game_time=game_panel_handler.game_time;
        current_life_temp = game_panel_handler.life_count;
        wins_score = game_panel_handler.win_score;
        ship_destroyed_score = game_panel_handler.ship_destroyed_score;

        Timer timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                time_count++;
                life_count = game_panel_handler.life_count;
                pause_count = game_panel_handler.pause_count;

                if (game_panel_handler.death_flag == 1) {
                    game_time = 60;
                    game_panel_handler.death_flag = 0;
                    System.out.println(life_count);
                }
                if (game_time == 0) {
                    game_panel_handler.life_count = game_panel_handler.life_count - 1;
                    game_time = 60;
                }
                if (overal_score + score < score_to_levelup) {
                    game_panel_handler.life_count = game_panel_handler.life_count - 1;
                    game_time = 60;
                }
                if (life_count != current_life_temp) {
                    overal_score = overal_score - ship_destroyed_score;
                    current_life_temp = life_count;
                }


                if (game_panel_handler.ship_destroyed_flag == 1) {
                    overal_score = overal_score + ship_destroyed_score;
                    game_panel_handler.ship_destroyed_flag = 0;
                }
                if (time_count < 60) {

                    if (pause_count == 1) {

                    } else {
                        game_time = game_time - 1;
                    }
                    score = game_time * 100;
                    score_to_levelup = (game_panel_handler.drawing_data[difficulty + 2] * current_level + 10000 * (current_level - 1));
                    time_label.setText("time :" + String.valueOf(game_time));
                    score_label.setText("score :" + String.valueOf(overal_score + score) + "/" + score_to_levelup);
                    if (highest_score[0] <= overal_score + score) {
                        best_score_label.setText("score :" + String.valueOf(overal_score + score));


                    } else {
                        best_score_label.setText("score :" + String.valueOf(highest_score[0]));

                    }
                    if (overal_score + score < score_to_levelup) {
                        if (life_count != 0 & game_panel_handler.win_flag != 0) {
                            life_count = life_count - 1;
                        }
                    }

                    if (game_panel_handler.win_flag == 1) {
                        ((Timer) (e.getSource())).stop();

                        dispose();
                        game_time = 60;
                        PrintWriter createtxtfile = null; //tworzy lub otwiera plik konfiguracyjny
                        try {
                            createtxtfile = new PrintWriter("game_data.txt");
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        overal_score = overal_score + score + wins_score;
                        createtxtfile.println("score on current level:"+String.valueOf(score));
                        createtxtfile.println("your score =" + String.valueOf(overal_score));
                        createtxtfile.close();
                        try {
                            new you_won_window(game_panel_handler.difficulty_3, game_panel_handler.life_count, game_panel_handler.current_level, game_panel_handler.level_to_finish);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }

                } else {
                    ((Timer) (e.getSource())).stop();
                }
            }
        });
        timer.setInitialDelay(0);
        timer.start();


    }
}
