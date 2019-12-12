package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.artikel.LogObject;
import model.Winkel;
import view.panels.LogviewPane;

public class LogViewController implements Observer {
    private LogviewPane logviewPane;
    private Winkel winkel;

    public LogViewController(Winkel winkel) {
        this.winkel = winkel;
        winkel.add(this);
    }

    public void setLogviewPane(LogviewPane logviewPane) {
        this.logviewPane = logviewPane;
    }

    public ObservableList<LogObject> getList() {

        ObservableList<LogObject>logObjecten= FXCollections.observableArrayList();
        for(LogObject logObject:winkel.getLog()){
            logObjecten.add(logObject);
        }
        return logObjecten;
    }

    public void setTextLog(){
        String logText="";
        for(LogObject logObject:getList()){
            logText+=logObject.toString();
        }

        logviewPane.setTextlog(logText);
    }

    @Override
    public void update() {
        if(logviewPane!=null){
            setTextLog();
        }
    }
}
