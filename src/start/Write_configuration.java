package start;
/**
 * @author Krzysztof Zawadzki
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

/**
 *klasa Write zapisuje do pliku txt wartosci konfiguracyjne gry
 * klasa write za pomoca generatorów liczb losowych
 * tworzy pliki konfiguracyjne potrzebne do stworzenia planszy i logiki gry
 * klasa wykorzystywana wielokrotnie do tworzenia wielu map
 */

public class Write_configuration {
    /**   funkcja tworząca konfiigurację mapy */
    public void write(String data) throws FileNotFoundException {
        /**  tworzenie generatora liczb losowych */
        Random _int_generator = new Random();
        /**   zapisanie liczby szczytów obecnych w grze*/
        int mountain = _int_generator.nextInt(40);
        if (mountain < 15) {
            mountain = 15;
        }
        /**  tworzenie tablicy wartości położenia poziomego sczytów   */
        int[] moutain_x = new int[40];
        /**  tworzenie tablicy wartości położenia pionowego sczytów   */
        int[] moutain_y = new int[40];
        /**  tworzenie generatora liczb losowych  dla położenia poziomego sczytów */
        Random generator_x = new Random();
        /**  przypisanie położenia poziomego dla przestrzni do lonoowania */
        int landpad_x = _int_generator.nextInt(750);
        if (landpad_x < 200) {
            landpad_x = 200;
        }
        /**  pętla odpowiedzialna za wartości położenia poziomego sczytów */
        for (int i = 0; i < mountain; i++) {
            /**  przypisanie wartości położenia poziomego sczytów */
            moutain_x[i] = generator_x.nextInt(800);
            if (moutain_x[i] > landpad_x - 100 & moutain_x[i] < landpad_x + 50) {
                moutain_x[i] = 0;
            }
            ;

        }
        /**  tworzenie generatora liczb losowych  dla położenia poziomego sczytów */
        Random generator_y = new Random();
        /**  pętla odpowiedzialna za wartości położenia pionowego sczytów */
        for (int i = 0; i < mountain; i++) {
            /**  przypisanie wartości położenia pionowego sczytów */
            moutain_y[i] = 50 * generator_y.nextInt(25);
            if (moutain_y[i] < 400) {
                moutain_y[i] = 500;
            }
            ;
        }
        /**  tworzenie generatora liczb losowych  dla grawitacji na poziomie */
        Random generator_gravity = new Random();
        /**  stworzenie zmiennej grawtiacji*/
        int gravity;
        /** przypisanie zmiennej grawtiacji na danym poziomie*/
        gravity = generator_gravity.nextInt(10);
        /** ograniczenie zmiennej grawtiacji na danym poziomie*/
        if (gravity < 4) {
            gravity = 4;

        }
        /**  stworzenie zmiennej wymiarów przestrzeni do lądowania*/
        int landpad_size;
        /** przypisanie zmiennej wymiarów przestrzeni do lądowania na danym poziomie*/
        landpad_size = generator_gravity.nextInt(50);
        if (landpad_size < 20) {
            landpad_size = 15;
        }
        /**  stworzenie tablicy położenia poziomego przeciwników */
        int[] enemies_x = new int[3];

        /**  przypisanie położenia poziomego przeciwników */
        for (int i = 0; i < 3; i++) {
            enemies_x[i] = generator_x.nextInt(800);

        }
        /**  stworzenie tablicy położenia pionowego przeciwników */
        int[] enemies_y = new int[3];
        /**  przypisanie położenia poziomego przeciwników */
        for (int i = 0; i < 3; i++) {
            enemies_y[i] = generator_x.nextInt(380);
            if (enemies_y[i] < 70) {
                enemies_y[i] = 70;
            }

        }

        /**  otwarcie pliku i zapisanie zmiennych okrślający wygląd mapy i przbieg gry */
        PrintWriter createtxtfile = new PrintWriter(data); //tworzy lub otwiera plik konfiguracyjny
        createtxtfile.println("start enemy count easy= 1");
        createtxtfile.println("start enemy count medium= 2");
        createtxtfile.println("start enemy count hard =3");
        createtxtfile.println("score to level up easy = 3000");
        createtxtfile.println("score to level up medium = 4000");
        createtxtfile.println("score to level up hard = 5000");
        createtxtfile.println("gravity on  stage =" + gravity);
        createtxtfile.println("speed up = 1");
        createtxtfile.println("speed x = 2");
        createtxtfile.println("start position x=700");
        createtxtfile.println("start position y=50");
        createtxtfile.println("start pad position x=200");
        createtxtfile.println("start pad position y=300");
        createtxtfile.println("ground x=-500");
        createtxtfile.println("ground y=600");
        createtxtfile.println("ground width=2500");
        createtxtfile.println("ground height=300");
        createtxtfile.println("mountain width=100");
        createtxtfile.println("mountain height=700");
        createtxtfile.println("paint_width= 2000");
        createtxtfile.println("paint_height= 1000");
        createtxtfile.println("level to finish=5");
        createtxtfile.println("ship size=10");
        createtxtfile.println("game time =60");
        createtxtfile.println("land pad position x=" + landpad_x);
        createtxtfile.println("land pad position y=590");
        createtxtfile.println("land pad width = " + landpad_size);
        createtxtfile.println("enemy start x = " + enemies_x[0]);
        createtxtfile.println("first enemy  start x = " + enemies_x[1]);
        createtxtfile.println("second enemy  start x = " + enemies_x[2]);
        createtxtfile.println("third enemy  y = " + enemies_y[0]);
        createtxtfile.println("first enemy  y = " + enemies_y[1]);
        createtxtfile.println("second enemy  y = " + enemies_y[2]);
        createtxtfile.println("win points = 10000");
        createtxtfile.println("ship_destroyed points =1000 " );
        createtxtfile.println("third mountain count=" + mountain);

        for (int i = 0; i < mountain; i++) {
            createtxtfile.println("x_moutain=" + moutain_x[i]);
            createtxtfile.println("y_moutain=" + moutain_y[i]);
        }

        createtxtfile.close();

    }
}