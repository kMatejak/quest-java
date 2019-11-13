package com.codecool.quest;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.MapLoader;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static com.codecool.quest.logic.actors.Actor.canFinishGame;
import static com.codecool.quest.logic.items.Key.keyNum;
import static com.codecool.quest.logic.items.RedKey.redKeyNum;
import static com.codecool.quest.logic.items.Sword.swordNum;


public class Main extends Application {
    String mapPath = checkWin(canFinishGame);
    GameMap map = MapLoader.loadMap(mapPath);
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Button pickUpButton = new Button();
    Label keyLabel = new Label();
    Label redKeyLabel = new Label();
    Label swordLabel = new Label();
    Label messageLabel = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    public static String checkWin(boolean win) {
        if (win) {
            return "/win_map.txt";
        }
        return "/win_map.txt";
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label(""),          0,0);
        ui.add(new Label("Health: "),  0,1);
        ui.add(healthLabel,                 1,1);

        ui.add(new Label(""),          1,2);
        ui.add(pickUpButton,                0,3);
        pickUpButton.setText("Pick up!");

        ui.add(new Label(""),          1,4);
        ui.add(new Label("Swords: "),  0,5);
        ui.add(swordLabel,                  1,5);

        ui.add(new Label("Keys: "),    0,6);
        ui.add(keyLabel,                    1,6);

        ui.add(new Label("Red Key: "), 0,7);
        ui.add(redKeyLabel,                 1,7);

        ui.add(new Label(""),          1,8);
        ui.add(messageLabel,                0,9);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("RocketCat. A Tale From The Tomb");
        primaryStage.show();

        pickUpButton.setOnAction(actionEvent ->
                map.pickUpItem()
        );
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (map.getActor("ghost") != null) {
            map.getActor("ghost").moveRandomly(map.getWidth(), map.getHeight());
            //map.getActor("ghost").move(1, 0);
            if (map.getActor("ghost").getHealth() < 0) {
                map.removeActor("ghost");
            }
        }
        if (map.getActor("bat") != null) {
            map.getActor("bat").move(0, 1);
            if (map.getActor("bat").getHealth() < 0) {
                map.removeActor("bat");
            }
        }

        map.moveMonsters();

        if (map.getActor("player") != null) {
            switch (keyEvent.getCode()) {
                case UP:
                    map.getActor("player").move(0, -1);
                    refresh();
                    break;
                case DOWN:
                    map.getActor("player").move(0, 1);
                    refresh();
                    break;
                case LEFT:
                    map.getActor("player").move(-1, 0);
                    refresh();
                    break;
                case RIGHT:
                    map.getActor("player").move(1, 0);
                    refresh();
                    break;
            }
            if (map.getActor("player").getHealth() < 0) {
                map.removeActor("player");
            }
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);

                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getActor("player").getHealth());
        swordLabel.setText("" + swordNum);
        keyLabel.setText("" + keyNum);
        redKeyLabel.setText("" + redKeyNum);
        messageLabel.setText(" ");
    }
}
