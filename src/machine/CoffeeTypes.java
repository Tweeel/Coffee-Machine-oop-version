package machine;

public enum CoffeeTypes {
    ESPRESSO(250,0,16,4),
    LATTE(350,75,20,7),
    CAPPUCCINO(200,100,12,6),
    UNKNOWN(0,0,0,0);

    int water;
    int milk;
    int beans;
    int money;
    CoffeeTypes(int water,int milk,int beans,int money){
        this.water=water;
        this.milk=milk;
        this.beans =beans;
        this.money=money;
    }
}
