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
    public Korting createKortingString(String mogelijk) {
        Korting korting;
        String name = mogelijk+"korting";
        try{
            Class cl = Class.forName("model.korting."+name);
            Object obj = cl.newInstance();
            korting=(Korting) obj;
        }catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            throw new IllegalArgumentException("Error trying to make korting for "+name);
        }
        return korting;
    }

    public Korting createKorting(Kortingsmogelijkheden mogelijk){
        return createKortingString(mogelijk.toString());
    }
}
