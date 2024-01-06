package Main;

public class SceneChanger {
    GameManager gm;

    public SceneChanger(GameManager gm) {
        this.gm = gm;
    }

    public void showScene1(){
        gm.ui.bgPanel[1].setVisible(true);
        gm.ui.bgPanel[2].setVisible(false);
        gm.ui.messageText.setText("Lets defeat the Dungeon Master and save the world!");

        gm.stopMusic(gm.currentMusic);
        gm.currentMusic = gm.fieldMusic;
        gm.playMusic(gm.currentMusic);
    }

    public void showScene2(){
        gm.ui.bgPanel[1].setVisible(false);
        gm.ui.bgPanel[2].setVisible(true);
        gm.ui.bgPanel[3].setVisible(false);
        gm.ui.messageText.setText("");

        gm.stopMusic(gm.currentMusic);
        gm.currentMusic = gm.fieldMusic2;
        gm.playMusic(gm.currentMusic);
    }

    public void showScene3(){
        gm.playSE(gm.enterSound);
        gm.ui.bgPanel[2].setVisible(false);
        gm.ui.bgPanel[3].setVisible(true);
        gm.ui.messageText.setText("You enter the cave. What is waiting for you inside...\n\n"
        + "*** This is the end of the demo. Thank you for playing!!! ***");
    }

    public void showGameOverScreen(int currentBgNum) {
        gm.ui.bgPanel[currentBgNum].setVisible(false);
        gm.ui.titleLabel.setVisible(true);
        gm.ui.titleLabel.setText("YOU DIED");
        gm.ui.restartButton.setVisible(true);
        gm.ui.restartButton.setText("Click here to restart");

        gm.stopMusic(gm.currentMusic);
        gm.playSE(gm.deathSound);
    }

    public void exitGameOverScreen() {
        gm.ui.titleLabel.setVisible(false);
        gm.ui.restartButton.setVisible(false);
        gm.player.setPlayerDefaultStatus();
    }

}
