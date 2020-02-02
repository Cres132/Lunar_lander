package start;
/**
 * @author Krzysztof Zawadzki
 */
/**
 *
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**Klasa odpowiadająca za cały wyląd panelu gry.
 * Zaczyna od  inicjacji zmiennych gry i  pobrania danych planszy z pliku .
 * następnie ładowne do bufora są pliki textur i rysowane jest okno z grą .
 * klasa odpowiada za ruch przeciwników i statku.
 * sprawdzane w tej klasie są śmierć statku, zniszczenie przeciwnika,wygrana.
 * obsłużona jest w tej klasie obsługa myszki i klawiatury.
 *
 * */
class DrawingPanel extends JPanel implements ActionListener, KeyListener, MouseListener {
    /**tablica przechowująca dane odczytane z pliku txt wyznaczającego wygląd i logikę gry */
    public int[] drawing_data;
    /** timer kontrolujący zmianę programu w czasie*/
    public Timer tm = new Timer(10, this);
    /**  zmienna mówiąca o przemieszczeniu poziomym statku kierowanego przez gracza*/
    private int x;
    /**  zmienna mówiąca o przemieszczeniu pionowym statku kierowanego przez gracza*/
    private int y;
    /**  zmienna mówiąca o kroku przemieszczenia poziomego statku kierowanego przez gracza*/
    private int velx;
    /**  zmienna mówiąca o kroku przemieszczenia pionowego statku kierowanego przez gracza*/
    private int vely;
    /**  zmienna przechowująca teksrure uzywaną do rysowania powierzchni planety*/
    private BufferedImage slate;
    /**  zmienna przechowująca teksrure uzywaną do tła "kosmos"*/
    private BufferedImage space;
    /**  zmienna przechowująca teksrure uzywaną do rysowania ekranu śmierci*/
    private BufferedImage death;
    /**  zmienna przechowująca przechowująca teksrure uzywaną do rysowania nierówności  na powierzchni planety"kosmicznych domków"*/
    private BufferedImage alienhouse;
    /**  zmienna przechowująca wiadomość o poziome trudności planszy*/
    public int difficulty_3;
    /**  zmienna przechowująca wiadomość o grawitacji planszy*/
    private int gravity;
    /**  zmienna przechowująca wiadomość o prędkości w dół statku*/
    private int speed_down;
    /**  zmienna przechowująca wiadomość o prędkości w górę statku*/
    private int speed_up;
    /**  zmienna przechowująca wiadomość o prędkości w poziomie statku*/
    private int speed_x;
    /**  zmienna przechowująca wiadomość położeniu statku w płaszczyźnie poziomej*/
    private int start_position_x;
    /**  zmienna przechowująca wiadomość położeniu statku w płaszczyźnie pionowej*/
    private int start_position_y;
    /**  zmienna przechowująca wiadomość położeniu przestrzeni lądowania w płaszczyźnie poziomej*/
    private int landpad_position_x;
    /**  zmienna przechowująca wiadomość położeniu przestrzeni lądowania w płaszczyźnie pioneowej*/
    private int landpad_position_y;
    /**  zmienna  przechowującawiadomość szerokości textur rysujących */
    private int paint_width;
    /**  zmienna  przechowująca wiadomość wysokości textur rysujących */
    private int paint_height;
    /**  zmienna przechowująca  wiadomość położeniu poziomego punktu startowego rysowania powierzchni ziemi */
    private int ground_x;
    /**  zmienna przechowująca  wiadomość położeniu pionowego punktu startowego rysowania powierzchni ziemi */
    private int ground_y;
    /**  zmienna  przechowująca wiadomość szerokości rysowania powierzchni ziemi */
    private int ground_width;
    /**  zmienna  przechowująca wiadomość wysokości rysowania powierzchni ziemi */
    private int ground_height;
    /**  zmienna  przechowująca wiadomość wysokości rysowania powierzchni nierówności na powierzchni planety */
    private int mountain_height;
    /**  zmienna  przechowująca wiadomość szerokości rysowania powierzchni nierówności na powierzchni planety */
    private int mountain_width;
    /**  zmienna  przechowująca wiadomość o punkcie poziomym przestrzeni startowej */
    private int startpad_x;
    /**  zmienna  przechowująca wiadomość o punkcie pionowej przestrzeni startowej */
    private int startpad_y;
    /**  zmienna  przechowująca wiadomość o wielkości statku*/
    private int ship_size;
    /**  zmienna tymczasowa  przechowująca wiadomość o wartości grawitacji używana przy jej zmianie  */
    private int temp_gravity;
    /**  zmienna tymczasowa  przechowująca wiadomość o wartości prędkości w dół statku używana przy jej zmianie  */
    private int temp_speed_down;
    /**  zmienna  przechowująca wiadomość o  ilości żyć */
    public int life_count;
    /**  zmienna  tymczasowa przechowująca wiadomość o  ilości żyć używana do wykrycia zmiany  */
    private int life_count_temp;
    /**  zmienna  przechowująca wiadomość o ile poziomów pokonał gracz*/
    public int current_level;
    /**  zmienna  przechowująca wiadomość o wygranej przez gracza */
    public int win_flag;
    /**  zmienna  przechowująca wiadomość o  obecnej szerokości  panelu gry*/
    private double scale_x;
    /**  zmienna  przechowująca wiadomość o  obecnej wysokości panelu gry*/
    private double scale_y;
    /**  zmienna  przechowująca wiadomość o  wielkości przestrzeni ląowania */
    private int landpad_size;
    /**  zmienna  przechowująca wiadomość pierwotnej szerokości okna */
    private int panel_start_size_x;
    /**  zmienna  przechowująca wiadomość pierwotnej wysokości okna */
    private int panel_start_size_y;
    /**  zmienna tymczasowa  przechowująca wiadomość o prędkości w dół statku  */
    private double temp_vely;
    /**  zmienna  przechowująca wiadomość  o wartości przyśpieszenia w dół statku   */
    private double gravity_double;
    /**  zmienna  przechowująca wiadomość  o wielkości statków przeciwników  */
    private int enemy_ship_size;
    /**  zmienna  przechowująca wiadomość  o prędkości statków przeciwników  */
    private int enemy_ship_move;
    /**  zmienna  przechowująca wiadomość  o punkcie startowym  poziomym statku przeciwnika 1 */
    private int enemy_ship_1_x;
    /**  zmienna  przechowująca wiadomość  o punkcie startowym  poziomym statku przeciwnika 2 */
    private int enemy_ship_2_x;
    /**  zmienna  przechowująca wiadomość  o punkcie startowym poziomym statku przeciwnika 3 */
    private int enemy_ship_3_x;
    /**  zmienna  przechowująca wiadomość  o kierunku poziomym statku przeciwnika 1 */
    private int enemy_ship_1_flag;
    /**  zmienna  przechowująca wiadomość  o kierunku poziomym  statku przeciwnika 2*/
    private int enemy_ship_2_flag;
    /**  zmienna  przechowująca wiadomość  o kierunku poziomym  statku przeciwnika 3 */
    private int enemy_ship_3_flag;
    /**  zmienna  przechowująca wiadomość  o istnieniu statku przeciwnika 1 */
    private int enemy_ship_1_exist;
    /**  zmienna  przechowująca wiadomość  o istnieniu statku przeciwnika 2 */
    private int enemy_ship_2_exist;
    /**  zmienna  przechowująca wiadomość  o istnieniu statku przeciwnika 3 */
    private int enemy_ship_3_exist;
    /**  zmienna  przechowująca wiadomość  o punkcie startowym  pionowym statku przeciwnika 1 */
    private int enemy_ship_1_y;
    /**  zmienna  przechowująca wiadomość  o punkcie startowym  pionowym statku przeciwnika 2 */
    private int enemy_ship_2_y;
    /**  zmienna  przechowująca wiadomość  o punkcie startowym  pionowym statku przeciwnika 3 */
    private int enemy_ship_3_y;
    /**  zmienna  przechowująca wiadomość  o liczbie przeciwników*/
    private int enemy_count;
    /**  zmienna  przechowująca wiadomość  o zniszczeniu swojego statku */
    public int death_flag;
    /**  zmienna  przechowująca wiadomość  o położenie poziomym myszki  */
    private int mouse_x;
    /**  zmienna  przechowująca wiadomość  o  położeniu pionowym myszki */
    private int mouse_y;
    /**  zmienna  przechowująca wiadomość  o zniszczeniu jakiegokolwiek statku   */
    public int ship_destroyed_flag;
    /**  zmienna  przechowująca wiadomość  o wartości pauzy   */
    public int pause_count;
    /**  zmienna  przechowująca wiadomość  o ilości poziomów do końca  */
    public int level_to_finish;
    /**  zmienna  przechowująca wiadomość  o śmierci statku używana do blokowania nakładania się śmierci   */
    private int death_flag2;
    /**  zmienna  przechowująca wiadomość  o położeniu punktów mówiacych o ilości żyć   */
    private int life_position;
    /**  zmienna  przechowująca wiadomość  o czasie gry  */
    public int game_time;
    /**  zmienna  przechowująca wiadomość  o ilośći punktów za przejście poziomu   */
    public int win_score;
    /**  zmienna  przechowująca wiadomość  o ilości punktów za zniszczenie statku  */
    public int ship_destroyed_score;

    /**  główna funkcja zajmująca się wczytaniem i inicjacią zmiennych
     * a także wczytaniem textur i uruchomieniem rysowania */
    public DrawingPanel(int difficulty_2, int lives, int level) {
        /**  inicacja zmiennych logiki gry nie wymagających wczyatania*/
        enemy_ship_1_exist = 1;
        enemy_ship_2_exist = 1;
        enemy_ship_3_exist = 1;
        win_flag = 0;
        life_count_temp = lives;
        life_count = lives;
        current_level = level;
        enemy_ship_1_flag = 0;
        enemy_ship_2_flag = 1;
        enemy_ship_3_flag = 0;

        difficulty_3 = difficulty_2;
        /** wczytanie plików konfiguracyjnych gry  */
        try {
            if (current_level == 1) {

                drawing_data = new Read().read(difficulty_3);
            } else {
                drawing_data = new Read().read(current_level + 1);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        /** inicjacja zmiennych z pomocą wczytanych danych */
        if (difficulty_2 == 1)
            enemy_count = drawing_data[0];
        if (difficulty_2 == 2)
            enemy_count = drawing_data[1];
        if (difficulty_2 == 3)
            enemy_count = drawing_data[2];

        enemy_ship_move = difficulty_3 + 1;
        temp_gravity = gravity = drawing_data[6];
        gravity_double = gravity * 0.1;
        speed_up = drawing_data[7];
        speed_x = drawing_data[8];
        start_position_x = drawing_data[9];
        start_position_y = drawing_data[10];
        ground_x = -drawing_data[13];
        ground_y = drawing_data[14];
        ground_width = drawing_data[15];
        ground_height = drawing_data[16];
        mountain_width = drawing_data[17];
        mountain_height = drawing_data[18];
        paint_width = drawing_data[19];
        paint_height = drawing_data[20];
        level_to_finish = drawing_data[21];
        ship_size = drawing_data[22];
        game_time = drawing_data[23];
        life_position = ship_size;
        startpad_x = start_position_x + 2 * ship_size;
        startpad_y = start_position_y - ship_size;
        landpad_position_x = drawing_data[24];
        landpad_position_y = drawing_data[25];
        landpad_size = drawing_data[26];
        enemy_ship_1_x = drawing_data[27];
        enemy_ship_2_x = drawing_data[28];
        enemy_ship_3_x = drawing_data[29];
        enemy_ship_1_y = drawing_data[30];
        enemy_ship_2_y = drawing_data[31];
        enemy_ship_3_y = drawing_data[32];
        win_score = drawing_data[33];
        ship_destroyed_score = drawing_data[34];
        temp_speed_down = speed_down = gravity + 1;
        enemy_ship_size = 2 * ship_size;
        panel_start_size_x = 872;
        panel_start_size_y = 759;

        /** załadowanie obrazów */
        loadImages();
        /**  rozpoczęcie pracy zegara */
        tm.start();
        /**  przypisanie słuchaczy zdarzeń to panelu gry*/
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    /** funkcja sprawdzająca czy statek gracza został zniszczony   */
    private void check_death(int x, int y) {
        int check_death_x = startpad_x + ship_size + x;
        int check_death_y = start_position_y + y;

        if (check_death_x > enemy_ship_1_x & check_death_x < enemy_ship_1_x + enemy_ship_size + 10 & enemy_count >= 1 & enemy_ship_1_exist == 1 & death_flag == 0) {
            if (check_death_y > enemy_ship_1_y & check_death_y < enemy_ship_1_y + enemy_ship_size) {
                death_flag = 1;
                death_flag2 = 1;
            }
        }
        if (check_death_x > enemy_ship_2_x & check_death_x < enemy_ship_2_x + enemy_ship_size + 10 & enemy_count >= 2 & enemy_ship_2_exist == 1 & death_flag == 0) {
            if (check_death_y > enemy_ship_2_y & check_death_y < enemy_ship_2_y + enemy_ship_size) {
                death_flag = 1;
                death_flag2 = 1;
            }
        }
        if (check_death_x > enemy_ship_3_x & check_death_x < enemy_ship_3_x + enemy_ship_size + 10 & enemy_count >= 3 & enemy_ship_3_exist == 1 & death_flag == 0) {
            if (check_death_y > enemy_ship_3_y & check_death_y < enemy_ship_3_y + enemy_ship_size) {
                death_flag = 1;
                death_flag2 = 1;
            }
        }

        for (int i = 0; i < drawing_data[35]; i++) {


            if (check_death_x > drawing_data[36 + 2 * i] & check_death_x < drawing_data[36 + 2 * i] + mountain_width + 10) {
                if (check_death_y > drawing_data[i * 2 + 37]) {
                    death_flag2 = 1;
                    death_flag = 1;
                }


            }
            if (check_death_y > ground_y & death_flag == 0 & win_flag == 0) {
                death_flag2 = 1;
                death_flag = 1;
            }

        }
    }

    /** funkcja sprawdzająca czy statek przeciwnika został zniszczony   */
    private void check_enemy_destroyed(int x, int y) {

        if (x > enemy_ship_1_x & x < enemy_ship_1_x + enemy_ship_size + 10 & enemy_count >= 1) {
            if (y > enemy_ship_1_y & y < enemy_ship_1_y + enemy_ship_size) {
                enemy_ship_1_exist = 0;
                ship_destroyed_flag = 1;
            }
        }
        if (x > enemy_ship_2_x & x < enemy_ship_2_x + enemy_ship_size + 10 & enemy_count >= 2) {
            if (y > enemy_ship_2_y & y < enemy_ship_2_y + enemy_ship_size) {
                enemy_ship_2_exist = 0;
                ship_destroyed_flag = 1;

            }
        }
        if (x > enemy_ship_3_x & x < enemy_ship_3_x + enemy_ship_size + 10 & enemy_count >= 3) {
            if (y > enemy_ship_3_y & y < enemy_ship_3_y + enemy_ship_size) {
                enemy_ship_3_exist = 0;
                ship_destroyed_flag = 1;

            }
        }

    }

    /** funkcja odpowiadająca z wczytanie i zbuforowanie textur  */
    private void loadImages() {


        try {
            alienhouse = ImageIO.read(new File("alienhause.jpg"));
            space = ImageIO.read(new File("space.jpg"));
            slate = ImageIO.read(new File("texture.jpg"));
            death = ImageIO.read(new File("youdied.jpg"));

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(this,
                    "Could not load images", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    /** funkcja odpowiadająca rysowanieu co takt zegara planszy gry jak i statku gracza
     * a także przeciwników i ilości pozostałych żyć
     * skaluje wyświetalny rysunek wraz z zmianą rozmiaru okna */
    private void doDrawing(Graphics g) throws FileNotFoundException {
        /** tworzy przestrzeń wyświetlającą grę */
        var g2d = (Graphics2D) g.create();
        /** buforowanie tekstur  */
        var space_paint = new TexturePaint(space, new Rectangle(0, 0, paint_width, paint_height));
        var slateTp = new TexturePaint(slate, new Rectangle(0, 0, paint_width, paint_height));
        var alientp = new TexturePaint(alienhouse, new Rectangle(0, 0, paint_width, paint_height));

        /** określenie obecnych wymiarów okna */
        scale_x = getWidth();
        scale_y = getHeight();
        /** skalownie panelu */
        g2d.scale(scale_x / panel_start_size_x, scale_y / panel_start_size_y);
        g2d.setPaint(space_paint);
        g2d.fillRect(-100, -100, paint_width, paint_height);

        /** rysownie mapy  */
        for (int i = 0; i < drawing_data[35]; i++)
        {
            g2d.drawRect(drawing_data[36 + 2 * i], drawing_data[i * 2 + 37], mountain_width, mountain_height);
            g2d.setPaint(alientp);
            g2d.fillRect(drawing_data[36 + i * 2], drawing_data[i * 2 + 37], mountain_width, mountain_height);

        }
        if (death_flag2 == 1) {
            life_count = life_count - 1;
            death_flag2 = 0;
        }


        if (life_count != life_count_temp) {
            x = 0;
            y = 0;
            life_count_temp = life_count;

        }

        /** rysowanie ilości żyć i statku  */
        g2d.setColor(Color.red);
        g2d.drawRect(startpad_x + x, startpad_y + y, ship_size, ship_size);
        g2d.fillRect(startpad_x + x, startpad_y + y, ship_size, ship_size);
        if (life_count > 0) {
            g2d.drawRect(life_position, life_position, ship_size, ship_size);
            g2d.fillRect(life_position, life_position, ship_size, ship_size);

        }
        if (life_count > 1) {
            g2d.drawRect(3 * life_position, life_position, ship_size, ship_size);
            g2d.fillRect(3 * life_position, life_position, ship_size, ship_size);
        }
        if (life_count > 2) {
            g2d.drawRect(5 * life_position, life_position, ship_size, ship_size);
            g2d.fillRect(5 * life_position, life_position, ship_size, ship_size);
        }

        if (enemy_count > 0 & enemy_ship_1_exist == 1) {
            g2d.setColor(Color.yellow);

            g2d.drawRect(enemy_ship_1_x, enemy_ship_1_y, enemy_ship_size, enemy_ship_size);
            g2d.fillRect(enemy_ship_1_x, enemy_ship_1_y, enemy_ship_size, enemy_ship_size);


        }
        /** rysowanie przeciwników */
        if (enemy_count > 1 & enemy_ship_2_exist == 1) {

            g2d.setColor(Color.yellow);
            g2d.drawRect(enemy_ship_2_x, enemy_ship_2_y, enemy_ship_size, enemy_ship_size);
            g2d.fillRect(enemy_ship_2_x, enemy_ship_2_y, enemy_ship_size, enemy_ship_size);
        }

        if (enemy_count > 2 & enemy_ship_3_exist == 1) {

            g2d.setColor(Color.yellow);
            g2d.drawRect(enemy_ship_3_x, enemy_ship_3_y, enemy_ship_size, enemy_ship_size);
            g2d.fillRect(enemy_ship_3_x, enemy_ship_3_y, enemy_ship_size, enemy_ship_size);
        }

        /** tworzenie miejsca startowego */
        g2d.setColor(new Color(126, 75, 41));
        g2d.drawRect(start_position_x, start_position_y, 5*ship_size, 2*ship_size);
        g2d.fillRect(start_position_x, start_position_y, 5*ship_size, 2*ship_size);
        /** tworzenie powierzchni planety*/
        g2d.drawOval(ground_x, ground_y, ground_width, ground_height);
        g2d.setPaint(slateTp);
        g2d.fillOval(ground_x, ground_y, ground_width, ground_height);
        g2d.setColor(new Color(126, 75, 41));
        /** tworzenie miejsca do lądowania */
        g2d.drawRect(landpad_position_x, landpad_position_y, landpad_size, 2*ship_size);
        g2d.fillRect(landpad_position_x, landpad_position_y, landpad_size, 2*ship_size);

        /** wyświetalnie obrazu śmierci gdy  liczba żyć równa się 0 */
        if (life_count <= 0) {
            var death_slate = new TexturePaint(death, new Rectangle(0, 0, paint_width - 1100, paint_height - 100));
            g2d.setPaint(death_slate);
            g2d.fillRect(-100, -100, paint_width - 1000, paint_height - 100);
        }

        g2d.dispose();
    }

    /** implementacja obsługi klawiszy  */
    public void actionPerformed(ActionEvent e)
    {
        /** realizacja lądowania  */
        if (landpad_position_x - 10 <= startpad_x + x) {
            if (landpad_position_x + 50 >= startpad_x + x) {
                if (startpad_y + y <= landpad_position_y + 10) {
                    if (startpad_y + y >= landpad_position_y - 10) {
                        speed_down = 0;

                        if (vely <= speed_up & velx <= 0) {
                            vely = 0;
                            velx = 0;
                            win_flag = 1;
                        } else {
                            if (win_flag == 0) {

                                death_flag = 1;
                                death_flag2 = 1;
                            }

                        }
                    } else {
                        gravity = temp_gravity;
                        speed_down = temp_speed_down;
                    }
                } else {
                    gravity = temp_gravity;
                    speed_down = temp_speed_down;
                }
            } else {
                gravity = temp_gravity;
                speed_down = temp_speed_down;
            }
        } else {
            gravity = temp_gravity;
            speed_down = temp_speed_down;
        }

        /** realizacja startu */
        if (start_position_x - 10 <= startpad_x + x) {
            if (start_position_x + 50 >= startpad_x + x) {
                if (startpad_y + y <= start_position_y + 10) {
                    if (startpad_y + y >= start_position_y - 10) {
                        speed_down = 0;
                        if (vely > 0) {
                            vely = 0;

                        }


                    } else {
                        gravity = temp_gravity;
                        speed_down = temp_speed_down;
                    }
                } else {
                    gravity = temp_gravity;
                    speed_down = temp_speed_down;
                }
            } else {
                gravity = temp_gravity;
                speed_down = temp_speed_down;
            }
        } else {
            gravity = temp_gravity;
            speed_down = temp_speed_down;

        }

        /** sprawdzenie zniszczenia statku i śmierci  */
        check_enemy_destroyed(mouse_x, mouse_y);
        check_death(x, y);
        if (enemy_ship_1_x > scale_x) {
            enemy_ship_1_flag = 1;
        }
        if (enemy_ship_1_x < 2) {
            enemy_ship_1_flag = 0;
        }
        if (enemy_ship_2_x > scale_x) {
            enemy_ship_2_flag = 1;
        }
        if (enemy_ship_2_x < 2) {
            enemy_ship_2_flag = 0;
        }
        if (enemy_ship_3_x > scale_x) {
            enemy_ship_3_flag = 1;
        }
        if (enemy_ship_3_x < 2) {
            enemy_ship_3_flag = 0;
        }
        if (enemy_ship_1_flag == 0) {
            enemy_ship_1_x = enemy_ship_1_x + enemy_ship_move;
        } else {
            enemy_ship_1_x = enemy_ship_1_x - enemy_ship_move;
        }
        if (enemy_ship_2_flag == 0) {
            enemy_ship_2_x = enemy_ship_2_x + enemy_ship_move;
        } else {
            enemy_ship_2_x = enemy_ship_2_x - enemy_ship_move;
        }
        if (enemy_ship_3_flag == 0) {
            enemy_ship_3_x = enemy_ship_3_x + enemy_ship_move;
        } else {
            enemy_ship_3_x = enemy_ship_3_x - enemy_ship_move;
        }

        /** realizacja zmiany położenia statku z różnymi krokami   */
        x += velx;
        y += vely;
        repaint();

    }
    /** funkcja realizująca zmiane prędkości po wciśnięciu przycisku w górę  */
    public void up() {
        temp_vely = 0;
        vely = speed_up;
        velx = 0;
    }
    /** funkcja realizująca zmiane prędkości po wciśnięciu przycisku w dół */
    public void down() {
        if (vely < gravity + speed_down) {
            temp_vely = temp_vely + gravity_double;
            vely = (int) (Math.round(temp_vely)) + speed_down;
        } else
            vely = gravity + speed_down;
        velx = 0;
    }
    /** funkcja realizująca zmiane prędkości po wciśnięciu przycisku w lewo */
    public void left() {

        velx = -speed_x;

        if (vely < gravity) {
            temp_vely = temp_vely + gravity_double;
            vely = (int) (Math.round(temp_vely));
        } else
            vely = gravity;


    }
    /** funkcja realizująca zmiane prędkości po wciśnięciu przycisku w prawo */
    public void right() {
        temp_vely = temp_vely + gravity_double;
        if (vely < gravity) {
            temp_vely = temp_vely + gravity_double;
            vely = (int) (Math.round(temp_vely));
        } else
            vely = gravity;
        velx = speed_x;
    }
    /** funkcja realizująca zmiane prędkości po wciśnięciu przycisku w po skosie górę ,w prawo */
    public void rightup() {

        vely = speed_up;
        velx = speed_x;
        temp_vely = 0;
    }
    /** funkcja realizująca zmiane prędkości po wciśnięciu przycisku w po skosie górę ,w lewo */
    public void leftup() {


        vely = speed_up;
        velx = -speed_x;
        temp_vely = 0;
    }
    /** funkcja realizująca zmiane prędkości po wciśnięciu przycisku puszczeniu przycisku */
    public void stop() {


        vely = gravity;
        velx = 0;


    }
    /** funkcja realizująca pauze zegar na panelu informacyjnym jest zatrzymywany a wartości ruchu wyzerowane
     * po ponownym naciśniećiu przycisku wszystko wraca do stanu przed wciścnięciem */
    public void pause(int pause) {
        if (pause == 0) {
            gravity = 0;
            temp_gravity = 0;
            speed_up = 0;
            speed_down = 0;
            speed_x = 0;
            velx = 0;
            vely = 0;
            enemy_ship_move = 0;
            pause_count = 1;
        }
        if (pause == 1) {
            gravity = temp_gravity = drawing_data[6];
            speed_up = drawing_data[7];
            speed_x = drawing_data[8];
            speed_down = gravity - 1;

            enemy_ship_move = difficulty_3 + 1;
            pause_count = 0;
        }


    }

    /** funkcja nieużywana wymagana w logice zdarzenia*/
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /** funkcja wykonująca odpowiednie przypisanie prędkości wraz z naciśnięciem odpowiedniego*/
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_SPACE) {
            pause(pause_count);
        }

        if (code == KeyEvent.VK_W) {

            up();
        } else if (code == KeyEvent.VK_S) {

            down();
        } else if (code == KeyEvent.VK_A) {

            left();
        } else if (code == KeyEvent.VK_D) {

            right();
        } else if (code == KeyEvent.VK_Q) {
            ;
            leftup();
        } else if (code == KeyEvent.VK_E) {

            rightup();
        } else {
            stop();
        }

    }

    /** funkcja wykonująca odpowiednie przypisanie prędkości puszczeniem przycisku*/
    public void keyReleased(KeyEvent e) {
        stop();
    }

    /** funkcja malująca panel wobec wcześniej określonych parametrów*/
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            doDrawing(g);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /** funkcja nieużywana wymagana w logice zdarzenia*/
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /** funkcja zbiera obecne położeni kliknięcia myszki wykorzystywane przy niszczeniu wrogich statków*/
    @Override
    public void mousePressed(MouseEvent e) {
        mouse_x = (int) this.getMousePosition().getX();
        mouse_y = (int) this.getMousePosition().getY();
    }
    /** funkcja nieużywana wymagana w logice zdarzenia*/
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    /** funkcja nieużywana wymagana w logice zdarzenia*/
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    /** funkcja nieużywana wymagana w logice zdarzenia*/
    @Override
    public void mouseExited(MouseEvent e) {

    }
}


