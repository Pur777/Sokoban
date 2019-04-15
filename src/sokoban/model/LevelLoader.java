package sokoban.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LevelLoader {
    private Path levels;

    LevelLoader(Path levels) {
        this.levels = levels;
    }

    GameObjects getLevel(int level) {
        if (level > 60) {
            level %= 60;
            if (level == 0) {
                level = 60;
            }
        }
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;

        List<String> list = readLevelsFile(level);
        for (int i = 0; i < list.size(); i++) {
            char[] c = list.get(i).toCharArray();
            for (int j = 0; j < c.length; j++) {
                switch (c[j]) {
                    case 'X':
                        walls.add(new Wall(Model.FIELD_CELL_SIZE * j + Model.FIELD_CELL_SIZE/2, Model.FIELD_CELL_SIZE * i + Model.FIELD_CELL_SIZE/2));
                        break;
                    case '*':
                        boxes.add(new Box(Model.FIELD_CELL_SIZE * j + Model.FIELD_CELL_SIZE/2, Model.FIELD_CELL_SIZE * i + Model.FIELD_CELL_SIZE/2));
                        break;
                    case '.':
                        homes.add(new Home(Model.FIELD_CELL_SIZE * j + Model.FIELD_CELL_SIZE/2, Model.FIELD_CELL_SIZE * i + Model.FIELD_CELL_SIZE/2));
                        break;
                    case '&':
                        homes.add(new Home(Model.FIELD_CELL_SIZE * j + Model.FIELD_CELL_SIZE/2, Model.FIELD_CELL_SIZE * i + Model.FIELD_CELL_SIZE/2));
                        boxes.add(new Box(Model.FIELD_CELL_SIZE * j + Model.FIELD_CELL_SIZE/2, Model.FIELD_CELL_SIZE * i + Model.FIELD_CELL_SIZE/2));
                        break;
                    case '@':
                        player = new Player(Model.FIELD_CELL_SIZE * j + Model.FIELD_CELL_SIZE/2, Model.FIELD_CELL_SIZE * i + Model.FIELD_CELL_SIZE/2);
                        break;
                }
            }
        }
        return new GameObjects(walls, boxes, homes, player);
    }

    private List<String> readLevelsFile(int level) {
        List<String> list = new ArrayList<>();
        String needLevel = String.format("Maze: %d", level);
        String endSearch = "*************************************";
        try (BufferedReader bufferedReader = Files.newBufferedReader(levels)) {
            String s;
            boolean searchStart = false;
            while ((s = bufferedReader.readLine()) != null) {
                if (searchStart) {
                    if (s.equals(endSearch)) {
                        break;
                    }
                    list.add(s);
                }
                if (s.equals(needLevel)) {
                    list.add(s);
                    searchStart = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        list.subList(0, 7).clear();
        list.remove(list.size() - 1);
        return list;
    }
}