package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricliquid.controller.gameflow.GameController;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class GameScene {
    protected Scene scene;
    protected GameController gameController;
    protected int width;
    protected int height;
    protected VBox root;
    protected HBox top;
    protected HBox center;
    protected HBox bottom;

    protected Place[][] places;

    protected Text playerName;
    protected Text infoPlayer2;
    protected Text infoPlayer3;

    VBox leftBottom;
    VBox centerBottom;
    VBox rightBottom;

    /**
     * Creates a GameScene
     * @param gameController
     */
    public GameScene(GameController gameController) {
        this.gameController = gameController;
        width = 700;
        height = 700;
        createBase();
    }

    /**
     * Creates the Base of the front for all th GameScenes
     */
    private void createBase(){
        root = new VBox();
        top = new HBox(15);
        center = new HBox(15);
        bottom = new HBox(15);

        top.setPrefHeight(100);
        center.setPrefHeight(500);
        bottom.setPrefHeight(100);

        top.setAlignment(Pos.CENTER);
        center.setAlignment(Pos.CENTER);


        root.getChildren().addAll(top,center,bottom);

        this.scene = new Scene(root,this.width,this.height);
    }

    /**
     * Set GameController
     * @param gameController
     */
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Get GameController
     * @return
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * Get Scene
     * @return
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Set Scene
     * @param scene
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Creates de player panel viewer
     */
    public  void createPlayerPanel(){
        String actualPanel = gameController.getTurnOwner().getPanel().getClass().getSimpleName();
        if( gameController.getTurnOwner().getPanel().getId() == gameController.getTurnOwner().getHomePanel().getId())
            actualPanel = "My "+gameController.getTurnOwner().getPanel().getClass().getSimpleName();


        playerName = new Text("Player: "+ gameController.getTurnOwner().getName()
                +"\nNorma Goal: " + gameController.getTurnOwner().getNormaGoal()
                +"\nActual Panel: " + actualPanel
                +"\nPanel Number: " + gameController.getTurnOwner().getPanel().getId()
                +"\nIs "+gameController.getTurnOwner().getLifeState().getClass().getSimpleName()
        );


        infoPlayer2 = new Text("Victories: "+gameController.getTurnOwner().getVictories()+
                "\nStars: "+gameController.getTurnOwner().getStars()+
                "\nTurn Phase: "+gameController.getTurnController().getState().getNamePhase()+
                "\nChapter: "+gameController.getChapter()

        );



        infoPlayer3 = new Text("HP: " + gameController.getTurnOwner().getCurrentHP()+
                "\nMAX HP: "+gameController.getTurnOwner().getMaxHP()+
                "\nATK: "+gameController.getTurnOwner().getAtk()+
                "\nDEF: "+gameController.getTurnOwner().getDef()+
                "\nEVD: "+gameController.getTurnOwner().getDef()
        );

        leftBottom = new VBox(playerName);
        centerBottom = new VBox(infoPlayer2);
        rightBottom = new VBox(infoPlayer3);

        leftBottom.setPrefWidth(200);
        centerBottom.setPrefWidth(300);
        rightBottom.setPrefWidth(200);

        leftBottom.setAlignment(Pos.CENTER_LEFT);
        centerBottom.setAlignment(Pos.CENTER);
        rightBottom.setAlignment(Pos.CENTER_RIGHT);

        rightBottom.setTranslateX(-20);
        leftBottom.setTranslateX(20);

        bottom.getChildren().addAll(leftBottom,centerBottom,rightBottom);
    }

    /**
     * Reloads the player panel
     */
    protected void reloadPlayerPanel(){
        String actualPanel = gameController.getTurnOwner().getPanel().getClass().getSimpleName();
        if( gameController.getTurnOwner().getPanel().getId() == gameController.getTurnOwner().getHomePanel().getId())
            actualPanel = "My "+gameController.getTurnOwner().getPanel().getClass().getSimpleName();

        bottom.getChildren().removeAll(leftBottom,centerBottom,rightBottom);

        playerName = new Text("Player: "+ gameController.getTurnOwner().getName()
                +"\nNorma Goal: " + gameController.getTurnOwner().getNormaGoal()
                +"\nActual Panel: " + actualPanel
                +"\nPanel Number: " + gameController.getTurnOwner().getPanel().getId()
                +"\nIs "+gameController.getTurnOwner().getLifeState().getClass().getSimpleName()
        );


        infoPlayer2 = new Text("Victories: "+gameController.getTurnOwner().getVictories()+
                "\nStars: "+gameController.getTurnOwner().getStars()+
                "\nTurn Phase: "+gameController.getTurnController().getState().getNamePhase()+
                "\nChapter: "+gameController.getChapter()

        );



        infoPlayer3 = new Text("HP: " + gameController.getTurnOwner().getCurrentHP()+
                "\nMAX HP: "+gameController.getTurnOwner().getMaxHP()+
                "\nATK: "+gameController.getTurnOwner().getAtk()+
                "\nDEF: "+gameController.getTurnOwner().getDef()+
                "\nEVD: "+gameController.getTurnOwner().getDef()
        );

        leftBottom = new VBox(playerName);
        centerBottom = new VBox(infoPlayer2);
        rightBottom = new VBox(infoPlayer3);

        leftBottom.setPrefWidth(200);
        centerBottom.setPrefWidth(300);
        rightBottom.setPrefWidth(200);

        leftBottom.setAlignment(Pos.CENTER_LEFT);
        centerBottom.setAlignment(Pos.CENTER);
        rightBottom.setAlignment(Pos.CENTER_RIGHT);

        rightBottom.setTranslateX(-20);
        leftBottom.setTranslateX(20);

        bottom.getChildren().addAll(leftBottom,centerBottom,rightBottom);

    }

    /**
     * Create a map
     */
    protected void createMap() {
        int cols = gameController.getGameGUI().getMapColPlaces();
        int rows = gameController.getGameGUI().getMapRowPlaces();
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);

        places = new Place[cols][rows];
        for (int row = rows - 1; row >= 0; row--) {
            for (int col = 0; col < cols; col++) {
                Place p = new Place(col, row);
                grid.add(p, col, row);
                places[col][row] = p;
            }
        }

        loadMap();



        center.getChildren().add(grid);
    }

    /**
     * Load a map width the panels
     */
    public void loadMap(){
        places[0][0].setPanel(gameController.getPanels().get(0));
        places[1][0].setPanel(gameController.getPanels().get(1));
        places[2][0].setPanel(gameController.getPanels().get(2));
        places[3][0].setPanel(gameController.getPanels().get(3));
        places[4][0].setPanel(gameController.getPanels().get(4));
        places[5][0].setPanel(gameController.getPanels().get(5));
        places[6][0].setPanel(gameController.getPanels().get(6));
        places[7][0].setPanel(gameController.getPanels().get(7));
        places[8][0].setPanel(gameController.getPanels().get(8));

        places[0][1].setPanel(gameController.getPanels().get(9));
        places[4][1].setPanel(gameController.getPanels().get(10));
        places[8][1].setPanel(gameController.getPanels().get(11));

        places[0][2].setPanel(gameController.getPanels().get(12));
        places[4][2].setPanel(gameController.getPanels().get(13));
        places[8][2].setPanel(gameController.getPanels().get(14));

        places[0][3].setPanel(gameController.getPanels().get(15));
        places[4][3].setPanel(gameController.getPanels().get(16));
        places[8][3].setPanel(gameController.getPanels().get(17));


        places[0][4].setPanel(gameController.getPanels().get(18));
        places[1][4].setPanel(gameController.getPanels().get(19));
        places[2][4].setPanel(gameController.getPanels().get(20));
        places[3][4].setPanel(gameController.getPanels().get(21));
        places[4][4].setPanel(gameController.getPanels().get(22));
        places[5][4].setPanel(gameController.getPanels().get(23));
        places[6][4].setPanel(gameController.getPanels().get(24));
        places[7][4].setPanel(gameController.getPanels().get(25));
        places[8][4].setPanel(gameController.getPanels().get(26));

        places[0][5].setPanel(gameController.getPanels().get(27));
        places[4][5].setPanel(gameController.getPanels().get(28));
        places[8][5].setPanel(gameController.getPanels().get(29));

        places[0][6].setPanel(gameController.getPanels().get(30));
        places[4][6].setPanel(gameController.getPanels().get(31));
        places[8][6].setPanel(gameController.getPanels().get(32));

        places[0][7].setPanel(gameController.getPanels().get(33));
        places[4][7].setPanel(gameController.getPanels().get(34));
        places[8][7].setPanel(gameController.getPanels().get(35));

        places[0][8].setPanel(gameController.getPanels().get(36));
        places[1][8].setPanel(gameController.getPanels().get(37));
        places[2][8].setPanel(gameController.getPanels().get(38));
        places[3][8].setPanel(gameController.getPanels().get(39));
        places[4][8].setPanel(gameController.getPanels().get(40));
        places[5][8].setPanel(gameController.getPanels().get(41));
        places[6][8].setPanel(gameController.getPanels().get(42));
        places[7][8].setPanel(gameController.getPanels().get(43));
        places[8][8].setPanel(gameController.getPanels().get(44));
    }

}
