package start;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * @author Krzysztof Zawadzki
 */

/**
 * Klasa start window odpowiada za elementy GUI interfejsu uzytkownika menu głównego.
 * Z wykorzystaniem bibliotek swing i awt  udało się stworzyć gui odpowiadajace
 * temu zadanemu w projekcie .Za pomocą intrukcji action listener otwieraja sie za
 * pomocą kliknięcia przycisku okna odpowiadające funkcjonalnościom menu.
 */

public class Start_Window {

    /**
     * Główna ramka w kórej jest wyświetlany interefejs
     */
    JFrame f;
    /**
     * tablica najwyższych wyników
     */
    private int[] highest_score_data;
    /**
     * flaga czy przycisk został naciśnięty
     */
    private int sound_on;
    /**
     * wskaźnik na obiekt odpowiadający za muzykę
     */
    public music m1;
    /**
     * flaga czy muzyka jest włączona
     */
    public int music_on;

    /**
     * główna funkcja klasy odpowiadająca z wyświetlenie i logike menu startowego
     */
    Start_Window() throws FileNotFoundException {

        if (music_on == 0) {
            /**stworzenie obiektu odpowiedzialnego za muzykę  */
            m1 = new music();
            music_on = 1;
        }
        /**tworzenie okna startowego */
        f = new JFrame();

        try {
            /**  uruchomienie funkcji zawierającej elementy okna i ich zachowanie*/
            start_window_open();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        /**  ustawienie tytułu */
        f.setTitle("          Lunar Lander");
        /** ustawienie rozmiaru okna */
        f.setSize(800, 600);
        /** okno zostaje "pokazane" */
        f.setVisible(true);
        /** ustawienie końca programu wraz z zamknięciem okna  */
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * funkcji zawierającej elementy okna i ich zachowanie
     */
    private void start_window_open() throws FileNotFoundException {
        /** usuwanie poprzednich elementów okna*/
        f.getContentPane().removeAll();
        /**  ustawienie rozmieszczenia elementów w oknie*/
        f.setLayout(new GridLayout(5, 2, 300, 30));
        /**stworzenie i określenie etykiety tytułu  */
        JLabel Title_label = new JLabel("                         Lunar Lander");
        /** określenie czcionki */
        Font font = new Font("TimesRoman", Font.BOLD, 40);
        /** ustawienie czcionki dla tytułu */
        Title_label.setFont(font);
        /**  dodanie tytułu do okna*/
        f.add(Title_label);
        /**  stworzenie przycisku start*/
        JButton start_button = new JButton("start");
        f.add(start_button);
        /** funkcja odpowiada za uruchomienie okna wyboru poziomu z wciśnięciem przycisku start */
        start_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start_clicked();

            }
        });
        /**  stworzenie przycisku highest_score*/
        JButton highest_score_button = new JButton("highest score");

        f.add(highest_score_button);
        /** funkcja odpowiada za uruchomienie okna z najwyższymi wynikami z wciśnięciem przycisku highest score */
        highest_score_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    highest_score_clicked();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        /**  stworzenie przycisku opcji*/
        JButton settings_button = new JButton("settings");                //tworzenie i przypisanie funkcji przycisku settings

        f.add(settings_button);
        /** funkcja odpowiada za uruchomienie okna opcji z wciśnięciem przycisku settings */
        settings_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                settings_clicked();
            }
        });
        /**  stworzenie przycisku wyjście*/
        JButton exit_button = new JButton("exit");                        //tworzenie i przypisanie funkcji przycisku exit
        /** funkcja odpowiada za zamknięcie programu z wciśnięciem przycisku exit */
        exit_button.addActionListener(new ActionListener() {                //zamykanie za pomocą przycisku exit
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        f.add(exit_button);
        /** upewnienie się że dane są aktualne */
        f.revalidate();
        /** zmiana wyglądu okna */
        f.repaint();
    }

    /**
     * funkcja odpowiedzialna za elementy i funkje okna wyboru poziommu trudności
     */
    private void start_clicked() {
        /** usuwanie poprzednich elementów okna*/
        f.getContentPane().removeAll();
        /** ustawienie nowego ułożenia elementów w oknie */
        f.setLayout(new GridLayout(4, 2, 300, 30));
        /** tworzenie przycisku powrotu do poprzedniego okna */
        JButton back_button = new JButton("back");
        /** funkcja  powrotu do poprzedniego okna za przyciśnięciem przycisku  */
        back_button.addActionListener(new ActionListener() {
                                          public void actionPerformed(ActionEvent e) {
                                              try {
                                                  start_window_open();
                                              } catch (FileNotFoundException ex) {
                                                  ex.printStackTrace();
                                              }
                                          }
                                      }
        );
        /** tworzenie przycisku wyboru poziomu łatwy */
        JButton easy_button = new JButton("easy");
        /**funkcja otwierająca okno gry poziomu łatwego */
        easy_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new dificulty_level_clicked(1, 3, 1);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                f.dispose();

            }
        });
        /** tworzenie przycisku wyboru poziomu średni */
        JButton medium_button = new JButton("medium");
        /**funkcja otwierająca okno gry poziomu średniego */
        medium_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new dificulty_level_clicked(2, 3, 1);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                f.dispose();


            }
        });
        /** tworzenie przycisku wyboru poziomu trudny */
        JButton hard_button = new JButton("hard");
        /**funkcja otwierająca okno gry poziomu trudnego */
        hard_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new dificulty_level_clicked(3, 3, 1);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                f.dispose();


            }
        });

        f.add(easy_button);
        f.add(medium_button);
        f.add(hard_button);
        f.add(back_button);
        /** upewnienie się że dane są aktualne */
        f.revalidate();
        /** zmiana wyglądu okna */
        f.repaint();
    }

    /**
     * funkcja otwierająca okno zawierające listę najwyższych wyników
     */
    private void highest_score_clicked() throws FileNotFoundException {
        /** usuwanie poprzednich elementów okna*/
        f.getContentPane().removeAll();
        /** ustawienie nowego ułożenia elementów w oknie */
        f.setLayout(new GridLayout(12, 2, 30, 30));
        /** wczytanie listy najlepszych wyników */
        highest_score_data = new Read().read(8);
        JLabel highscore_label_1 = new JLabel("score:" + String.valueOf(highest_score_data[0]));
        f.add(highscore_label_1);
        JLabel highscore_label_2 = new JLabel("score:" + String.valueOf(highest_score_data[1]));
        f.add(highscore_label_2);
        JLabel highscore_label_3 = new JLabel("score:" + String.valueOf(highest_score_data[2]));
        f.add(highscore_label_3);
        JLabel highscore_label_4 = new JLabel("score:" + String.valueOf(highest_score_data[3]));
        f.add(highscore_label_4);
        JLabel highscore_label_5 = new JLabel("score:" + String.valueOf(highest_score_data[4]));
        f.add(highscore_label_5);
        JLabel highscore_label_6 = new JLabel("score:" + String.valueOf(highest_score_data[5]));
        f.add(highscore_label_6);
        JLabel highscore_label_7 = new JLabel("score:" + String.valueOf(highest_score_data[6]));
        f.add(highscore_label_7);
        JLabel highscore_label_8 = new JLabel("score:" + String.valueOf(highest_score_data[7]));
        f.add(highscore_label_8);
        JLabel highscore_label_9 = new JLabel("score:" + String.valueOf(highest_score_data[8]));
        f.add(highscore_label_9);
        JLabel highscore_label_10 = new JLabel("score:" + String.valueOf(highest_score_data[9]));
        f.add(highscore_label_10);

        /** tworzenie przycisku powrotu do poprzedniego okna */
        JButton back_button = new JButton("back");
        /** funkcja  powrotu do poprzedniego okna za przyciśnięciem przycisku  */
        back_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    start_window_open();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        f.getContentPane().add(back_button);
        f.revalidate();
        f.repaint();
    }
     /** funkcja otwierająca okno zawierające okno opcji*/
    private void settings_clicked() {
        /** usuwanie poprzednich elementów okna*/
        f.getContentPane().removeAll();
        /** ustawienie nowego ułożenia elementów w oknie */
        f.setLayout(new GridLayout(4, 2, 300, 30));
        /** tworzenie przycisku powrotu do poprzedniego okna */
        JButton back_button = new JButton("back");
        /** funkcja  powrotu do poprzedniego okna za przyciśnięciem przycisku  */
        back_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    start_window_open();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        /** tworzenie przycisku odpowiadającego za odtwarzanie muzyki */
        JButton sound_control_button = new JButton("sound on/of");
        /** funkcja  włączenia lub wyłączenia muzyki za przyciśnięciem przycisku  */
        sound_control_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (sound_on == 0) {
                    try {
                        /** włączenie pliku odtwarzania*/
                        m1.music();
                        /** uruchamianie odtwarzania */
                        m1.clip.start();
                        /** ustawienie flagi aby w natępnej iteracji włączenia przycisku został wciśnięty stop*/
                        sound_on = 2;
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (UnsupportedAudioFileException ex) {
                        ex.printStackTrace();
                    } catch (LineUnavailableException ex) {
                        ex.printStackTrace();
                    }
                }

                if (sound_on == 1) {
                    /** zatrzymuje odtwarzanie muzyki */
                    m1.clip.stop();
                    sound_on = 0;

                }
                if (sound_on == 2) {
                    sound_on = 1;
                }

            }
        });


        f.getContentPane().add(sound_control_button);
        f.getContentPane().add(back_button);
        /** upewnienie się że dane są aktualne */
        f.revalidate();
        /** zmiana wyglądu okna */
        f.repaint();
    }


}

