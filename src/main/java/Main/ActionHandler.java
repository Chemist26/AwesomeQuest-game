package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener {

    GameManager gm;

    public ActionHandler(GameManager gm) {
        this.gm = gm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String yourChoice = e.getActionCommand();

        // Perform actions based on the choice
        switch (yourChoice) {
            // SCENE 1
            case "lookHut" : gm.ev1.lookHut(); break;
            case "talkHut" : gm.ev1.talkHut(); break;
            case "restHut" : gm.ev1.restHut(); break;
            case "lookNPC" : gm.ev1.lookNPC(); break;
            case "talkNPC" : gm.ev1.talkNPC(); break;
            case "attackNPC" : gm.ev1.attackNPC(); break;
            // SCENE 2
            case "readSnowSign" : gm.ev1.readSnowSign(); break;
            case "lookChest" : gm.ev1.lookChest(); break;
            case "talkChest" : gm.ev1.talkChest(); break;
            case "openChest" : gm.ev1.openChest(); break;
            case "lookStars" : gm.ev1.lookStars(); break;
            case "lookCave" : gm.ev2.lookCave(); break;
            case "talkCave" : gm.ev2.talkCave(); break;
            case "enterCave" : gm.ev2.enterCave(); break;

            // Change scenes
            case "goScene1" : gm.sceneChanger.showScene1(); break;
            case "goScene2" : gm.sceneChanger.showScene2(); break;
            // OTHERS
            case "restart" : gm.sceneChanger.exitGameOverScreen(); gm.sceneChanger.showScene1(); break;
        }
    }
}
