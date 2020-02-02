package start;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Krzysztof Zawadzki
 */

/**klasa odpowiadająca za odtwarzanie muzyki */
public class music {
    /** wskaźnik na odtwarzanie pliku*/
    public Clip clip;
    /** zmienna odpowiadająca za zatrzymanie odtwarzania */
    public int stop;

    /**funkcja odpowiadająca za odtwarzanie muzyki */
    public void music() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        /** przypisanie funkcjonalności odtwarzania muzyki */
        clip = AudioSystem.getClip();
        /**pobranie pliku muzycznego */
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("m.wav").getAbsoluteFile());
        /** otworzenie pliku muzycznego */
        clip.open(audioIn);
        /** wystartowanie odtwarzania pliku muzycznego */
        clip.start();
        if (stop == 1) {
            /** zatrzymanie odtwarzania pliku muzycznego */
            clip.stop();
        }


    }
}
