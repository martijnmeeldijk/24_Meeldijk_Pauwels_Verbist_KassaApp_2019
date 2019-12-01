package database;

import java.lang.reflect.InvocationTargetException;

public class ArtikelDbStrategyFactory {
    private static ArtikelDbStrategyFactory unique = new ArtikelDbStrategyFactory();

    private ArtikelDbStrategyFactory(){}

    public static ArtikelDbStrategyFactory getInstance(){
        return unique;
    }

    public ArtikelDbStrategy createArtikelDbStrategy(String name) {
        ArtikelDbStrategy strategy;
        try{
            Class strategyClass = Class.forName("src.database."+name);
            Object strategyObject = strategyClass.getConstructor().newInstance();
            strategy = (ArtikelDbStrategy) strategyObject;
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error trying to make strategy for "+name);
        }
        return  strategy;
    }
}