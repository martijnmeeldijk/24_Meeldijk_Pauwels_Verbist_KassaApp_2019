package database.Factory;

import database.ArtikelDbStrategy;

import java.lang.reflect.InvocationTargetException;

public class ArtikelDbStrategyFactory {
    private static ArtikelDbStrategyFactory unique;

    private ArtikelDbStrategyFactory(){}

    public static ArtikelDbStrategyFactory getInstance(){
        if (unique == null) {
            unique = new ArtikelDbStrategyFactory ();
        }
        return unique;
    }

    public ArtikelDbStrategy createArtikelDbStrategy(String name) {
        ArtikelDbStrategy strategy;
        try{
            Class strategyClass = Class.forName("database."+name);
            Object strategyObject = strategyClass.getConstructor().newInstance();
            strategy = (ArtikelDbStrategy) strategyObject;
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            System.out.println(e.getCause());
            throw new IllegalArgumentException("Error trying to make strategy for "+name);
        }
        return  strategy;
    }
}
