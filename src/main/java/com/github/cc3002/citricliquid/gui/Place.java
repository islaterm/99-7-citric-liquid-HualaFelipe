package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricjuice.model.board.NullPanel;
import com.github.cc3002.citricjuice.model.board.Panel;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * Represents the view of a single place on the tictactoe.BoardGame.
 * An instance of PlaceListener forwards mouse clicks to a tictactoe.Player.
 * Here we assume that Players have the mark 'X' or 'O'.
 */
public class Place extends Group {
    private final int col;
    private final int row;
    private final String imageDir = "file:src/main/java/com/github/cc3002/citricliquid/gui/images";
    private Image image;
    private ImageView imageView;
    private Panel panel;
    StackPane pane;
    Text inftx;
    Text inftx2;


    /**
     * A Place knows its row and column number.
     * It also knows how to display either an X or O image
     * when its state changed.
     */
    public Place(int myCol, int myRow) {
        col = myCol;
        row = myRow;
        panel = new NullPanel();
        imageView = new ImageView();
        inftx = new Text();
        inftx2 = new Text();
        //imageView.setFitWidth(GameGUI.CELL_SIZE);
        imageView.setFitWidth(45);
        imageView.setPreserveRatio(true);
        pane = new StackPane();
        this.getChildren().add(pane);
        pane.getChildren().add(imageView);
        pane.getChildren().add(inftx);
        inftx.setTranslateX(-15);
        inftx.setTranslateY(-15);
        inftx.setStyle("    -fx-font-size: 9px;\n");
        pane.getChildren().add(inftx2);
        inftx.setStyle("    -fx-font-size: 10px;\n");
        this.clearImage();
    }

    public void setImage(Image anImage) {
        imageView.setImage(anImage);
    }



    /**
     * Load an image from a given file name
     * @param imageFile
     * @return the loaded image
     */
    private Image getImage(String imageFile){
        return new Image(imageFile, 40, 40, false, false);
    }


    /**
     * To clear the image viewer
     */
    public void clearImage() {

        this.setImage(null);
    }


    /**
     * Set the panel of the place
     * @param panel
     */
    public void setPanel(Panel panel) {
        this.panel = panel;
        String urlImage = imageDir + panel.getImageUrl();
        setImage(getImage(urlImage));
        inftx.setText(String.valueOf(panel.getId()));
        if(panel.getPlayers().size()>0){
            inftx2.setText(panel.getPlayers().get(0).getName());
        }
    }
}

