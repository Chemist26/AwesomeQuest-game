package Main;

import Event.Event01;
import Event.Event02;

import java.net.URL;

public class GameManager {
    ActionHandler actionHandler = new ActionHandler(this);
    public UI ui = new UI(this);
    public Player player = new Player(this);
    public SceneChanger sceneChanger = new SceneChanger(this);
    Music music = new Music();
    SE se = new SE();

    public Event01 ev1 = new Event01(this);
    public Event02 ev2 = new Event02(this);


    public URL fieldMusic = getClass().getClassLoader().getResource("audio/acousticbreeze.wav");
    public URL fieldMusic2 = getClass().getClassLoader().getResource("audio/ofeliasdream.wav");
    public URL deathSound = getClass().getClassLoader().getResource("audio/death.wav");
    public URL hitSound = getClass().getClassLoader().getResource("audio/hitSound.wav");
    public URL healSound = getClass().getClassLoader().getResource("audio/healSound.wav");
    public URL itemSound = getClass().getClassLoader().getResource("audio/itemSound.wav");
    public URL enterSound = getClass().getClassLoader().getResource("audio/enterSound.wav");
    public URL talkNPCSound = getClass().getClassLoader().getResource("audio/talkNPC.wav");
    public URL attackNPCSound = getClass().getClassLoader().getResource("audio/attackNPC.wav");
    public URL defeatNPCSound = getClass().getClassLoader().getResource("audio/defeatNPC.wav");
    public URL leaveNPCSound = getClass().getClassLoader().getResource("audio/leaveNPC.wav");
    public URL currentMusic;


    public static void main(String[] args) {
        new GameManager();
    }

    public GameManager() {
        currentMusic = fieldMusic;
        playMusic(currentMusic);

        player.setPlayerDefaultStatus();
        sceneChanger.showScene1();
    }


    public void playSE(URL url) {

        se.setFile(url);
        se.play(url);
    }
    public void playMusic(URL url) {

        music.setFile(url);
        music.play(url);
        music.loop(url);
    }
    public void stopMusic(URL url) {

        music.stop(url);
    }
}
