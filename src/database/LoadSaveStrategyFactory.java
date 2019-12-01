package database;

import java.lang.reflect.InvocationTargetException;

public class LoadSaveStrategyFactory {
    public LoadSaveStrategy createArtikelDbStrategy(String name) {
        LoadSaveStrategy strategy;
        try{
            Class strategyClass = Class.forName("src.database."+name);
            Object strategyObject = strategyClass.getConstructor().newInstance();
            strategy = (LoadSaveStrategy) strategyObject;
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error trying to make strategy for "+name);
        }
        return strategy;
    }
}