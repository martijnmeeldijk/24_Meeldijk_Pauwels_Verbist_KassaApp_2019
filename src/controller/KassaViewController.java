package controller;

import view.KassaView;

public class KassaViewController implements Observer {
    private KassaView kassaView;

    public void setKassaView(KassaView kassaView) {
        this.kassaView = kassaView;
    }

    @Override
    public void update() {


    }
}
