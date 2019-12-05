package database.Factory;

import database.LoadSaveStrat.LoadSaveStrategy;

public class LoadSaveStrategyFactory {
    private static LoadSaveStrategyFactory unique= new LoadSaveStrategyFactory();

    private LoadSaveStrategyFactory() {
    }

    public static LoadSaveStrategyFactory getInstance(){
        return unique;
    }

    public LoadSaveStrategy createArtikelDbStrategy(String name) {
        LoadSaveStrategy strategy;
        try{
            Class strategyClass = Class.forName("database.LoadSaveStrat."+name);
            Object strategyObject = strategyClass.newInstance();
            strategy = (LoadSaveStrategy) strategyObject;
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException("Error trying to make strategy for "+name);
        }
        return strategy;
    }
}
