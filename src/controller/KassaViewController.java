package controller;

import javafx.scene.control.Label;
import view.KassaView;

import java.awt.*;

public class KassaViewController implements Observer {
    private KassaView kassaView;
    private Label label= new Label("ja het werkt");

    public void setKassaView(KassaView kassaView) {
        this.kassaView = kassaView;
        kassaView.setLabel(label);
    }

    @Override
    public void update() {
    }
}
