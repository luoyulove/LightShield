package luoyu.lightshield;

public class Api {
    public static float ClientShieldAmount;
    public static float ClientMaxShieldAmount;
    public static void getShieldAmount(float shieldAmount){
        ClientShieldAmount = shieldAmount;
    }
    public static void getShieldMaxAmount(float maxShieldAmount){
        ClientMaxShieldAmount = maxShieldAmount;
    }
}
