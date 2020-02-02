package start;
/**
 * @author Krzysztof Zawadzki
 */

import java.io.FileNotFoundException;
/**  program startowy odpowiadająca za stworzenie plików konfiguracyjnych*/
public class Configuration_file_maker {
    /**  funkcja tworzy kolejne losowo genrowane mapy do kolejnych plików txt
     * przez wielokrotne wyłowanie metody klasy Write_configuration  write */
    public static void main(String[] args) throws FileNotFoundException {

        new Write_configuration().write("game_data_easy.txt");
        new Write_configuration().write("game_data_medium.txt");
        new Write_configuration().write("game_data_hard.txt");
        new Write_configuration().write("map1.txt");
        new Write_configuration().write("map2.txt");
        new Write_configuration().write("map3.txt");
        new Write_configuration().write("map4.txt");

    }
}
