package org.example;
import java.util.ArrayList;

public  class Bot {

    public static Horse botPlay(ArrayList<Horse> playableHorse){
        int i = 0;
        while (i < playableHorse.size() && playableHorse.get(i).getAbsolutePosition() != -1){
            i++;
        }
        // If there's no horse to be moved from the starting position play randomly
        if (i == playableHorse.size()) {
            i = (int) (Math.random() * playableHorse.size());
        }
        return playableHorse.get(i);

    }
}
