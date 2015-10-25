package de.edlly.zeiterfassung.model.stempeln;

import java.util.ArrayList;
import java.util.List;

public class StempelOrt implements IStempelOrt {
    private int id;
    private String name;

    private List<IStempelOrt> ortList = new ArrayList<IStempelOrt>();

    public StempelOrt() {

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<IStempelOrt> getOrtList() {
        return ortList;
    }

    @Override
    public void setOrtList(List<IStempelOrt> ortList) {
        this.ortList = ortList;
    }

    /**
     * Erstellen der Ort Liste @TODO: auslesen aus Datenbank oder anderer Quelle
     */

    @Override
    public void createOrtList() {
        StempelOrt ort = new StempelOrt();
        List<IStempelOrt> list = new ArrayList<IStempelOrt>();

        ort.setId(1);
        ort.setName("Firma");

        list.add(ort);

        ort.setId(2);
        ort.setName("Home Office");

        list.add(ort);

        ort.setId(3);
        ort.setName("Montage");

        list.add(ort);

        setOrtList(list);

    }

}
