package Event;

import Main.GameManager;
import Main.TTS;

public class Event01 {
    GameManager gm;
    Thread speechThread = new Thread();
    TTS tts = new TTS("kevin16");

    public Event01(GameManager gm) {
        this.gm = gm;
    }

    public void lookHut() {
        gm.ui.messageText.setText("This is your house.");
    }

    public void talkHut() {
        gm.ui.messageText.setText("Who are you talking to?");
    }

    public void restHut() {
        if (gm.player.playerLife != gm.player.playerMaxLife) {
            gm.ui.messageText.setText("You rest at the house. \n(Your life has recovered)");
            gm.player.playerLife++;
            gm.player.updatePlayerStatus();
            gm.playSE(gm.healSound);
        } else {
            gm.ui.messageText.setText("Your life is full.");
        }
    }

    public void lookNPC() {
        gm.ui.messageText.setText("A caveman is standing in front of you");
    }

    public void talkNPC() {
        gm.ui.messageText.setText("Caveman: Don't go any further without a weapon! \nYou should check the chests around here!");
        speechThread = new Thread(() -> tts.speak("Don't go any further without a weapon! \nYou should check the chests around here!"));
        speechThread.start();
//        gm.playSE(gm.talkNPCSound);
    }

    public void attackNPC() {
        final String FOOL_MESSAGE = "Caveman: what a fool.";
        final String HIT_BACK_MESSAGE = "Caveman: Hey, don't be stupid! I'm your friend!\n(The caveman hits you back and your life decreases by 1)";
        final String DEFEAT_MESSAGE = "Caveman: Oh, shit! \n(You have defeated the caveman and gotten his lantern!)";
        final String LEAVE_ALONE_MESSAGE = "Caveman: Just leave me alone.";

        if (gm.player.hasShield == 0) {
            if (gm.player.hasSword == 0) {
                if (gm.player.playerLife != 1) {
                    gm.ui.messageText.setText(HIT_BACK_MESSAGE);
                    gm.player.playerLife--;
                    speechThread = new Thread(() -> {tts.speak(HIT_BACK_MESSAGE.substring(9));});
                    speechThread.start();
//
//                    gm.playSE(gm.attackNPCSound);
                    gm.playSE(gm.hitSound);
                } else {
                    gm.ui.messageText.setText(FOOL_MESSAGE);
                    speechThread = new Thread(() -> {tts.speak(FOOL_MESSAGE.substring(9));});
                    speechThread.start();
                    gm.player.playerLife--;
                    gm.sceneChanger.showGameOverScreen(1);
                }
            } else if (gm.player.hasSword == 1) {
                if (gm.player.hasLantern == 0) { // Check if the player already has the lantern
                    gm.ui.messageText.setText(DEFEAT_MESSAGE);
                    gm.player.hasLantern = 1;

                    speechThread = new Thread(() -> {tts.speak(DEFEAT_MESSAGE.substring(9));});
                    speechThread.start();
//                    gm.playSE(gm.defeatNPCSound);
                    gm.playSE(gm.hitSound);
                } else {
                    gm.ui.messageText.setText(LEAVE_ALONE_MESSAGE);
                    speechThread = new Thread(() -> {tts.speak(LEAVE_ALONE_MESSAGE.substring(9));});
                    speechThread.start();
//                    gm.playSE(gm.leaveNPCSound);
                }
            }
            gm.player.updatePlayerStatus();
        } else {
            gm.ui.messageText.setText(LEAVE_ALONE_MESSAGE);
            speechThread = new Thread(() -> {tts.speak(LEAVE_ALONE_MESSAGE.substring(9));});
            speechThread.start();
//            gm.playSE(gm.leaveNPCSound);
        }
    }


    public void readSnowSign() {
        gm.ui.messageText.setText("Snow Sign Winter: Welcome to the Snowy Cave! Watch out for icy patches!");
    }

    public void lookChest() {
        gm.ui.messageText.setText("A chest is on the ground.");
    }

    public void talkChest() {
        gm.ui.messageText.setText("You talk to the chest but it says nothing.");
    }

    public void openChest() {
        if (gm.player.hasSword == 0) {
            gm.ui.messageText.setText("You open the chest and find a sword!");
            gm.player.hasSword = 1;
            gm.player.updatePlayerStatus();
            gm.playSE(gm.itemSound);
        } else {
            gm.ui.messageText.setText("There is nothing inside...");
        }
    }

    public void lookStars() {
        gm.ui.messageText.setText("You gaze at the twinkling stars in the night sky. It's a breathtaking sight.");
    }
}
