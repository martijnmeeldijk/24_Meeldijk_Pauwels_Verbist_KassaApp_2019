package database.Factory;

import database.ArtikelDbStrat.ArtikelDbStrategy;

public class ArtikelDbStrategyFactory {
    //singleton
    private static ArtikelDbStrategyFactory unique= new ArtikelDbStrategyFactory ();
    private ArtikelDbStrategyFactory(){}

    public static ArtikelDbStrategyFactory getInstance(){
        return unique;
    }

    //maak
    public ArtikelDbStrategy createArtikelDbStrategy(String name) {
        ArtikelDbStrategy strategy;
        try{
            Class strategyClass = Class.forName("database.ArtikelDbStrat."+name);
            Object strategyObject = strategyClass.newInstance();
            strategy = (ArtikelDbStrategy) strategyObject;
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException("Error trying to make strategy for "+name);
        }
        return  strategy;
    }
}
