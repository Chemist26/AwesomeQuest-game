package Main;

public class Player {
    GameManager gm;

    public int playerMaxLife;
    public int playerLife;

    public int hasSword;
    public int hasShield;
    public int hasLantern;


    public Player(GameManager gm) {
        this.gm = gm;
    }

    public void setPlayerDefaultStatus() {
        playerMaxLife = 5;
        playerLife = 3;
        hasSword = 0;
        hasShield = 0;
        hasLantern = 0;

        updatePlayerStatus();
    }

    public void updatePlayerStatus() {
        if (gm == null || gm.ui == null || gm.ui.lifeLabel == null) {
            System.out.println("GameManager, UI, or lifeLabel is null.");
            return;
        }

        // REMOVE ALL LIFE ICON
        int i = 1;
        while (i <= 5 && i < gm.ui.lifeLabel.length) {
            if (gm.ui.lifeLabel[i] != null) {
                gm.ui.lifeLabel[i].setVisible(false);
            }
            i++;
        }

        // DISPLAY HEARTS UP TO playerLife
        int lifeCount = playerLife;
        while (lifeCount > 0 && lifeCount <= gm.ui.lifeLabel.length) {
            if (gm.ui.lifeLabel[lifeCount] != null) {
                gm.ui.lifeLabel[lifeCount].setVisible(true);
            }
            lifeCount--;
        }




        // CHECK PLAYER ITEMS
        if (hasSword == 0 && gm.ui.swordLabel != null) {
            gm.ui.swordLabel.setVisible(false);
        }
        if (hasSword == 1 && gm.ui.swordLabel != null) {
            gm.ui.swordLabel.setVisible(true);
        }
        if (hasShield == 0 && gm.ui.shieldLabel != null) {
            gm.ui.shieldLabel.setVisible(false);
        }
        if (hasShield == 1 && gm.ui.shieldLabel != null) {
            gm.ui.shieldLabel.setVisible(true);
        }
        if (hasLantern == 0 && gm.ui.lanternLabel != null) {
            gm.ui.lanternLabel.setVisible(false);
        }
        if (hasLantern == 1 && gm.ui.lanternLabel != null) {
            gm.ui.lanternLabel.setVisible(true);
        }
    }


}
