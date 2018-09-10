package GUI;

import java.util.ArrayList;
import java.util.List;

public class Round {

    List standings = new ArrayList<Standing>();

    List fieldInfos = new ArrayList<FieldInfo>();

    public List getStandings() {
        return standings;
    }

    public void addStandings(Standing standing) {
        this.standings.add(standings);
    }

    public List getFieldInfos() {
        return fieldInfos;
    }

    public void addFieldInfos(FieldInfo fieldInfo) {
        this.fieldInfos.add(fieldInfo);
    }
}
