package GUI;

import java.util.ArrayList;
import java.util.List;

public class Round {

    private List standings = new ArrayList<Standing>();

    private List fieldInfos = new ArrayList<FieldInfo>();

    public List getStandings() {
        return standings;
    }

    public List getFieldInfos() {
        return fieldInfos;
    }

    public void addFieldInfos(FieldInfo fieldInfo) {
        this.fieldInfos.add(fieldInfo);
    }

    public void addStandings(Standing standing){this.standings.add(standing);}

}
