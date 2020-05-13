package br.edu.unicsul.agendatelefonica.models;

import java.util.ArrayList;
import java.util.List;

import br.edu.unicsul.agendatelefonica.R;

public class Developer {
    private String name;
    private String rgm;
    private int avatarId;

    public Developer(String name, String rgm, int avatarId) {
        this.name = name;
        this.rgm = rgm;
        this.avatarId = avatarId;
    }

    public String getName() {
        return name;
    }

    public String getRgm() {
        return rgm;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public static List<Developer>GetProjectDevs(){
        List<Developer> devlist = new ArrayList<>();


        devlist.add(new Developer("Alline Andric da Silva", "1752483-1", R.drawable.alline));
        devlist.add(new Developer("Caio Lima Uno So", "1742084-9", R.drawable.caio));
        devlist.add(new Developer("Diego Oliveira Andrade Rafael", "1784337-5", R.drawable.diego));
        devlist.add(new Developer("Yuri Estevão", "1793074-0", R.drawable.yuri));

        return devlist;
    }
}
