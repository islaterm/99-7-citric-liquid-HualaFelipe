package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricjuice.model.unit.AbstractNonPlayerUnit;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.TypeCombat;
import com.github.cc3002.citricjuice.model.unit.Unit;
import com.github.cc3002.citricliquid.controller.gameflow.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Random;

public class BattlePhaseScene extends InGameScene {
    VBox vBoxAttacker;
    VBox vBoxCenter;
    VBox vBoxReceiver;
    Text textAttacker;
    Text textReceiver;
    Text nAttacker;
    Text nReceiver;
    Button rollAttackButton;
    Button rollDefButton;
    Button fightButton1;
    Button rollCounterAttackButton;
    Button rollCounterDefButton;
    Button rollEvdButton;
    Button rollCounterEvdButton;
    Button fightButton2;
    Button endBattleButton;
    Unit attacker;
    Unit receiver;


    /**
     * Constructor for a BattlePhaseScene
     * @param gameController
     */
    public BattlePhaseScene(GameController gameController) {
        super(gameController);
        create();
    }

    /**
     * Create Method
     */
    private void create() {

        vBoxAttacker = new VBox();
        vBoxCenter = new VBox();
        vBoxReceiver = new VBox();

        attacker = gameController.getTurnOwner();


        receiver = gameController.getTurnController().getState().getReceiver();

        textAttacker = new Text("Attacker: \n" + attacker.getName());
        textReceiver = new Text ("Receiver: \n" + receiver.getName());

        nAttacker = new Text();
        nAttacker.setStyle("    -fx-font-size: 20px;\n"+
                "-fx-text-fill: #ab022e;"
                );

        nReceiver= new Text();
        nReceiver.setStyle("    -fx-font-size: 20px;\n"+
                "-fx-text-fill: #008098;"
        );




        vBoxAttacker.setPrefWidth(200);
        vBoxCenter.setPrefWidth(300);
        vBoxReceiver.setPrefWidth(200);

        vBoxAttacker.setAlignment(Pos.CENTER_RIGHT);
        vBoxCenter.setAlignment(Pos.CENTER);
        vBoxReceiver.setAlignment(Pos.CENTER_LEFT);

        vBoxAttacker.getChildren().addAll(textAttacker, nAttacker);
        vBoxReceiver.getChildren().addAll(textReceiver,nReceiver);

        top.getChildren().addAll(vBoxAttacker,vBoxCenter,vBoxReceiver);

        rollAttackButton = new Button("Roll Attack");
        rollAttackButton.setPrefSize(160, 20);
        rollAttackButton.setStyle("-fx-background-color: #ab022e; \n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 10 20 10 20;");

        rollAttackButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.getTurnController().rollAtkAttack();
                vBoxCenter.getChildren().remove(rollAttackButton);

                if(receiver instanceof AbstractNonPlayerUnit){
                    gameController.getTurnController().rollDefAttack();
                    TypeCombat typeCombat;
                    Random r = new Random();
                    int random = r.nextInt(1);
                    if(random == 1 ) {
                        typeCombat=TypeCombat.DEFEND;
                    } else{
                        typeCombat=TypeCombat.EVADE;
                    }
                    gameController.getTurnController().selectTypeCombat(typeCombat);

                    nReceiver.setText(String.valueOf(receiver.getRollDef()));
                    vBoxCenter.getChildren().add(fightButton1);
                }else{
                    vBoxCenter.getChildren().add(rollDefButton);
                    vBoxCenter.getChildren().add(rollEvdButton);
                }





                nAttacker.setText(String.valueOf(attacker.getRollAtk()));
                reloadPlayerPanel();

            }
        });
        vBoxCenter.getChildren().add(rollAttackButton);

        rollDefButton = new Button("Roll Defense");
        rollDefButton.setPrefSize(160, 20);
        rollDefButton.setStyle("-fx-background-color: #008098;\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 10 20 10 20;");

        rollDefButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.getTurnController().rollDefAttack();
                vBoxCenter.getChildren().remove(rollDefButton);
                vBoxCenter.getChildren().remove(rollEvdButton);
                gameController.getTurnController().selectTypeCombat(TypeCombat.DEFEND);
                vBoxCenter.getChildren().add(fightButton1);
                nReceiver.setText(String.valueOf(receiver.getRollDef()));
                reloadPlayerPanel();
            }
        });

        rollEvdButton = new Button("Roll Evade");
        rollEvdButton.setPrefSize(160, 20);
        rollEvdButton.setStyle("-fx-background-color: #008098;\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 10 20 10 20;");

        rollEvdButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.getTurnController().rollDefAttack();
                vBoxCenter.getChildren().remove(rollDefButton);
                vBoxCenter.getChildren().remove(rollEvdButton);
                gameController.getTurnController().selectTypeCombat(TypeCombat.EVADE);
                vBoxCenter.getChildren().add(fightButton1);
                nReceiver.setText(String.valueOf(receiver.getRollDef()));
                reloadPlayerPanel();
            }
        });

        fightButton1 = new Button("Fight");
        fightButton1.setPrefSize(160, 20);
        fightButton1.setStyle("-fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 10 20 10 20;");

        fightButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.getTurnController().rollDefAttack();
                vBoxCenter.getChildren().remove(fightButton1);


                if(receiver instanceof AbstractNonPlayerUnit){
                    gameController.getTurnController().rollAtkContraAttack();
                    vBoxCenter.getChildren().add(rollCounterDefButton);
                    vBoxCenter.getChildren().add(rollCounterEvdButton);
                    nReceiver.setText(String.valueOf(receiver.getRollAtk()));

                }else{
                    vBoxCenter.getChildren().add(rollCounterAttackButton);
                    nReceiver.setText("");
                }


                nAttacker.setText("");

                reloadPlayerPanel();

            }
        });

        rollCounterAttackButton = new Button("Roll Attack");
        rollCounterAttackButton.setPrefSize(160, 20);
        rollCounterAttackButton.setStyle("-fx-background-color: #008098;\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 10 20 10 20;");

        rollCounterAttackButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.getTurnController().rollAtkContraAttack();
                vBoxCenter.getChildren().remove(rollCounterAttackButton);
                vBoxCenter.getChildren().add(rollCounterDefButton);
                vBoxCenter.getChildren().add(rollCounterEvdButton);
                nReceiver.setText(String.valueOf(receiver.getRollAtk()));
                reloadPlayerPanel();
            }
        });

        rollCounterDefButton = new Button("Roll Defense");
        rollCounterDefButton.setPrefSize(160, 20);
        rollCounterDefButton.setStyle("-fx-background-color: #ab022e;\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 10 20 10 20;");

        rollCounterDefButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.getTurnController().rollDefContraAttack();
                gameController.getTurnController().selectTypeCombat(TypeCombat.DEFEND);
                vBoxCenter.getChildren().remove(rollCounterDefButton);
                vBoxCenter.getChildren().remove(rollCounterEvdButton);
                vBoxCenter.getChildren().add(fightButton2);
                nAttacker.setText(String.valueOf(attacker.getRollDef()));
                reloadPlayerPanel();
            }
        });

        rollCounterEvdButton = new Button("Roll Evade");
        rollCounterEvdButton.setPrefSize(160, 20);
        rollCounterEvdButton.setStyle("-fx-background-color: #ab022e;\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 10 20 10 20;");

        rollCounterEvdButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.getTurnController().rollDefContraAttack();
                gameController.getTurnController().selectTypeCombat(TypeCombat.EVADE);
                vBoxCenter.getChildren().remove(rollCounterDefButton);
                vBoxCenter.getChildren().remove(rollCounterEvdButton);
                vBoxCenter.getChildren().add(fightButton2);
                nAttacker.setText(String.valueOf(attacker.getRollDef()));
                reloadPlayerPanel();
            }
        });

        fightButton2 = new Button("Fight");
        fightButton2.setPrefSize(160, 20);
        fightButton2.setStyle("-fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 10 20 10 20;");

        fightButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.getTurnController().contraAttack();
                vBoxCenter.getChildren().remove(fightButton2);
                vBoxCenter.getChildren().add(endBattleButton);
                reloadPlayerPanel();
            }
        });

        endBattleButton = new Button("End Battle");
        endBattleButton.setPrefSize(160, 20);
        endBattleButton.setStyle("-fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 10 20 10 20;");

        endBattleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.getTurnController().endBattle();
                gameController.getGameGUI().setGameScene(gameController.getTurnController().getState().getPhaseScene());
                reloadPlayerPanel();
            }
        });




    }

}
