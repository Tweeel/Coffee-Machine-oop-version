package machine;

public class CoffeDevice {
    int water_Available;
    int milk_Available;
    int beans_Available;
    int money_Available;
    int cups_Available;
    String actionValue;
    int step;

    MachineMode machineMode;

    CoffeDevice(){
        machineMode=MachineMode.WAITFORCOMMAND;
        water_Available=400;
        milk_Available=540;
        beans_Available=120;
        money_Available=550;
        cups_Available=9;
        step=0;
    }

    void showStat(){

        String mn = "\nThe coffee machine has:\n" +
                water_Available + " ml of water\n" +
                milk_Available + " ml of milk\n" +
                beans_Available + " g of coffee beans\n" +
                cups_Available + " disposable cups\n" +
                "$" + money_Available + " of money";
        System.out.println(mn);
    }

    void fillMachine(){
        switch (step){
            case 0:{
                System.out.println("\nWrite how many ml of water you want to add:");
                break;
            }
            case 1:{
                water_Available += Integer.parseInt(actionValue);
                System.out.println("Write how many ml of milk you want to add:");
                break;
            }
            case 2: {
                milk_Available += Integer.parseInt(actionValue);
                System.out.println("Write how many grams of coffee beans you want to add:");
                break;
            }
            case 3: {
                beans_Available += Integer.parseInt(actionValue);
                System.out.println("Write how many disposable cups of coffee you want to add:");
                break;
            }
            case 4: {
                cups_Available += Integer.parseInt(actionValue);
                break;
            }
            default: {
                break;
            }
        }
        step +=1;
        if(step>4){
            step=0;
            machineMode=MachineMode.WAITFORCOMMAND;
        }
    }

    void buyCoffee(){
        machineMode=MachineMode.WAITFORCOMMAND;
        CoffeeTypes selectedCoffee;
        switch(actionValue){
            case "1":{
                selectedCoffee=CoffeeTypes.ESPRESSO;
                break;
            }
            case "2":{
                selectedCoffee= CoffeeTypes.LATTE;
                break;
            }
            case "3":{
                selectedCoffee=CoffeeTypes.CAPPUCCINO;
                break;
            }
            case "back":{
                machineMode=MachineMode.WAITFORCOMMAND;
                return;
            }
            default:{
                selectedCoffee=CoffeeTypes.UNKNOWN;
                return;
            }
        }
        if(cups_Available<=0){
            System.out.println("Sorry, not enough disposable cups!");
            return;
        }
        if(water_Available<selectedCoffee.water){
            System.out.println("Sorry, not enough water!");
            return;
        }
        if(milk_Available< selectedCoffee.milk){
            System.out.println("Sorry, not enough milk!");
            return;
        }
        if(beans_Available< selectedCoffee.beans){
            System.out.println("Sorry, not enough beans!");
            return;
        }
        System.out.println("I have enough resources, making you a coffee!");
        water_Available -= selectedCoffee.water;
        milk_Available -= selectedCoffee.milk;;
        beans_Available -= selectedCoffee.beans;
        cups_Available -=2;
        money_Available += selectedCoffee.money;
    }

    void setCommand(String command){
        switch (machineMode){
            case WAITFORCOMMAND:{
                switch(command){
                    case "buy":{
                        machineMode=MachineMode.BUY;
                        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
                        break;
                    }
                    case "fill":{
                        machineMode=MachineMode.FILL;
                        break;
                    }
                    case "take":{
                        machineMode=MachineMode.TAKE;
                        System.out.println("\nI gave you $" + money_Available);
                        money_Available = 0;
                        machineMode = MachineMode.WAITFORCOMMAND;
                        break;
                    }
                    case "remaining":{
                        machineMode = MachineMode.TAKE;
                        showStat();
                        machineMode=MachineMode.WAITFORCOMMAND;
                        break;
                    }
                    default:{
                        break;
                    }
                }
                break;
            }
            case BUY:{
                actionValue=command;
                buyCoffee();
                break;
            }
            case FILL:{
                actionValue=command;
                fillMachine();
                break;
            }
            default:{
                break;
            }
        }
    }

}
