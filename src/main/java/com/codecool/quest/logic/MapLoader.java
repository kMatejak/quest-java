package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Bat;
import com.codecool.quest.logic.actors.Ghost;
import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.actors.Skeleton;
import com.codecool.quest.logic.items.Key;
import com.codecool.quest.logic.items.RedKey;
import com.codecool.quest.logic.items.Sword;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String mapPath) {
        InputStream is = MapLoader.class.getResourceAsStream(mapPath);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'D':
                            cell.setType(CellType.CLOSED_DOORS);
                            break;
                        case 'O':
                            cell.setType(CellType.OPENED_DOORS);
                            break;
                        case '$':
                            cell.setType(CellType.FIRE);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setActor(new Player(cell));
                            break;
                        case 'g':
                            cell.setType(CellType.FLOOR);
                            map.setActor(new Ghost(cell));
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            map.setActor(new Bat(cell));
                            break;
                        case '/':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case 'r':
                            cell.setType(CellType.FLOOR);
                            new RedKey(cell);
                            break;
                        case '1':
                            cell.setType(CellType.STONES_LEFT_UP);
                            break;
                        case '2':
                            cell.setType(CellType.STONES_UP);
                            break;
                        case '3':
                            cell.setType(CellType.STONES_RIGHT_UP);
                            break;
                        case '4':
                            cell.setType(CellType.STONES_LEFT);
                            break;
                        case '5':
                            cell.setType(CellType.STONES_CENTER);
                            break;
                        case '6':
                            cell.setType(CellType.STONES_RIGHT);
                            break;
                        case '7':
                            cell.setType(CellType.STONES_LEFT_DOWN);
                            break;
                        case '8':
                            cell.setType(CellType.STONES_DOWN);
                            break;
                        case '9':
                            cell.setType(CellType.STONES_RIGHT_DOWN);
                            break;
                        case '^':
                            cell.setType(CellType.ROCKET1);
                            break;
                        case '|':
                            cell.setType(CellType.ROCKET2);
                            break;
                        case '*':
                            cell.setType(CellType.ROCKET3);
                            break;
                        case '-':
                            cell.setType(CellType.ROCKET4);
                            break;
                        case 'R':
                            cell.setType(CellType.RED_GATE);
                            break;
                        case 'H':
                            cell.setType(CellType.RED_PLATFORM);
                            break;
                        case 'y':
                            cell.setType(CellType.LETTER_Y);
                            break;
                        case 'o':
                            cell.setType(CellType.LETTER_O);
                            break;
                        case 'u':
                            cell.setType(CellType.LETTER_U);
                            break;
                        case 'w':
                            cell.setType(CellType.LETTER_W);
                            break;
                        case 'i':
                            cell.setType(CellType.LETTER_I);
                            break;
                        case 'n':
                            cell.setType(CellType.LETTER_N);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
