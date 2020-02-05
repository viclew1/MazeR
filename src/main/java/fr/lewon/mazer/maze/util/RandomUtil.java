package fr.lewon.mazer.maze.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class RandomUtil {

    private static final Random rdm = new Random();

    public static <T> T getRandomElement(Collection<T> elements) {
        List<T> list = new ArrayList<>(elements);
        return list.get(rdm.nextInt(list.size()));
    }

}
