package Event;

import Main.GameManager;

public class Event02 {
    public GameManager gm;

    public Event02(GameManager gm) {
        this.gm = gm;
    }

    public void lookCave() {
        gm.ui.messageText.setText("It's a cave!");
    }

    public void talkCave() {
        gm.ui.messageText.setText("You hear the echo of your voice.");
    }

    public void enterCave(){
        if (gm.player.hasLantern == 0) {
            gm.ui.messageText.setText("It's too dark to enter.");
        }
        else {
            gm.sceneChanger.showScene3();
        }
    }
}
