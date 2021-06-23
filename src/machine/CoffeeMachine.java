package machine;

import java.util.Scanner;

public class CoffeeMachine {

    static public Scanner scanner=new Scanner(System.in);

    public static void main(String[] args) {
        CoffeDevice mydevice= new CoffeDevice();
        String action=" ";
        while(!action.equals("exit")){

            if(mydevice.machineMode==MachineMode.WAITFORCOMMAND){
                System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
                action = scanner.nextLine();
            }
            if(mydevice.machineMode==MachineMode.BUY){
                action = scanner.nextLine();
            }
            if(mydevice.machineMode==MachineMode.FILL){
                if(mydevice.step>0){
                    action = scanner.nextLine();
                }
            }
            mydevice.setCommand(action);
        }
    }

}