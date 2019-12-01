package controller;

import javafx.scene.control.Label;
import view.KassaView;
import view.panels.KassaOverviewPane;

import java.awt.*;

public class KassaViewController implements Observer {
    private KassaOverviewPane kassaOverviewPane;
    private Label label= new Label("ja het werkt");

    public void setKassaView(KassaOverviewPane kassaOverviewPane) {
        this.kassaOverviewPane = kassaOverviewPane;
        //kassaOverviewPane.setLabel(label);
    }

    @Override
    public void update() {
    }
}
