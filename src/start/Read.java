package start;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *klasa read odczytuje z pliku txt wartosci konfiguracyjne gry
 * za pomoca petli while liczby w formie Stringa zmieniane sa do inta i zapisane w tablicy
 */

public class Read {
    public int[] read(int file_number) throws FileNotFoundException {

        File file ;
        if(file_number==0) {
            file = new File("score.txt");             //odczyt pliku 1
        }

        if(file_number==1) {
            file = new File("game_data_easy.txt");             //odczyt pliku 1
        }

        else if(file_number==2){
            file = new File("game_data_medium.txt");             //odczyt pliku 2
        }

        else if(file_number==3){
            file = new File("game_data_hard.txt");             //odczyt pliku 3
        }

        else if(file_number==4){
            file = new File("map1.txt");             //odczyt pliku 4
        }

        else if(file_number==5){
            file = new File("map2.txt");             //odczyt pliku 5
        }

        else if(file_number==6) {
            file = new File("map3.txt");             //odczyt pliku 6
        }

        else if(file_number==7) {
            file = new File("map4.txt");             //odczyt pliku 7

        }
        else if(file_number==8) {
            file = new File("Highest_score.txt");             //odczyt pliku 8

        }
        else if(file_number==9) {
            file = new File("game_data.txt");             //odczyt pliku 9

        }
            else {file = new File("map4.txt");

            System.exit(1);
        }

        Scanner scanner = new Scanner(file);
        int[] table_of_data = new int[200];              //tablica z danymi
        int count=0;        // zmienna iterująca tablice

        while (scanner.hasNext()) {                         //pętla czyszcząca dane i zapisująca je w tablicy

            String line = scanner.nextLine();
            String cleanint = line.replaceAll("\\D+",""); // usunięcie wszystkich informacji oprócz liczb
            int clean1=Integer.parseInt(cleanint);                //zamiana string na int
            table_of_data[count]=clean1;                        //zapis do tablicy danych
            count=count+1;
        }


        return table_of_data;}}



