package database.Factory;

import database.LoadSaveStrategy;

import java.lang.reflect.InvocationTargetException;

public class LoadSaveStrategyFactory {
    private static LoadSaveStrategyFactory unique;

    private LoadSaveStrategyFactory() {
    }

    public static LoadSaveStrategyFactory getInstance(){
        if (unique == null) {
            unique = new LoadSaveStrategyFactory();
        }
        return unique;
    }

    public LoadSaveStrategy createArtikelDbStrategy(String name) {
        LoadSaveStrategy strategy;
        try{
            Class strategyClass = Class.forName("database."+name);
            Object strategyObject = strategyClass.getConstructor().newInstance();
            strategy = (LoadSaveStrategy) strategyObject;
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error trying to make strategy for "+name);
        }
        return strategy;
    }
}
