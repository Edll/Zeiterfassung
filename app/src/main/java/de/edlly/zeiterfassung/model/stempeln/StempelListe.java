package de.edlly.zeiterfassung.model.stempeln;

import java.util.ArrayList;
import java.util.List;

public class StempelListe {
    List<Stempel> list = new ArrayList<Stempel>();
    IStempelRegel regel = new StempelRegel();

    public void stempeln(Stempel stempel) throws StempelException {
        if (list.size() > 0) {

            regel.checkBefore(list.get(list.size() - 1).getStatus(), stempel.getStatus());
        } else {
            regel.checkFirst(stempel.getStatus());
        }

        list.add(stempel);
    }

    public Stempel getStempel(int id) {
        return list.get(id);
    }

}
