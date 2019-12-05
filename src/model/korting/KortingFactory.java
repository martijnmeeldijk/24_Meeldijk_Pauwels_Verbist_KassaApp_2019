package model.korting;

import java.lang.reflect.InvocationTargetException;

public class KortingFactory {
    //singleton
    private static KortingFactory unique = new KortingFactory();
    private KortingFactory(){}

    public static KortingFactory getInstance(){
        return unique;
    }

    //maak
    public Korting createKorting(Kortingsmogelijkheden mogelijk) {
        Korting korting;
        String name = String.valueOf(mogelijk)+"korting";
        try{
            Class cl = Class.forName("model.korting."+name);
            Object obj = cl.newInstance();
            korting=(Korting) obj;
        }catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            throw new IllegalArgumentException("Error trying to make korting for "+name);
        }
        return korting;
    }
}
