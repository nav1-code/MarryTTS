package modules;

import marytts.LocalMaryInterface;
import marytts.util.data.audio.AudioPlayer;

public class Main {

	public static void main(String[] args) throws Exception {
        LocalMaryInterface mary = new LocalMaryInterface();
        mary.setVoice("dfki-poppy-hsmm");

        String input = "Hello, world!";
        AudioPlayer player = new AudioPlayer(mary.generateAudio(input));
        player.start();
        player.join();
    }

}
