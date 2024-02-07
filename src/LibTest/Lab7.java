/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibTest;

import LibNet.NetLibrary;
import PetriObj.ExceptionInvalidNetStructure;
import PetriObj.ExceptionInvalidTimeDelay;
import PetriObj.PetriNet;
import PetriObj.PetriObjModel;
import PetriObj.PetriSim;

import java.util.ArrayList;

/**
 *
 * @author oleksii
 */
public class Lab7 {
    
    public static void main(String[] args) throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {
        
//        runRobotModel();
        
        runBusModel();
    }
    
    private static void runRobotModel() throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {
        
        ArrayList<PetriSim> list = new ArrayList<>();
          
        PetriNet creator = NetLibrary.CreateRobotNetDetailCreator();        
        PetriNet robot1 = NetLibrary.CreateRobotNetRobot();
        PetriNet executor1 = NetLibrary.CreateRobotNetExecutor(60);
        PetriNet robot2 = NetLibrary.CreateRobotNetRobot();
        PetriNet executor2 = NetLibrary.CreateRobotNetExecutor(100);
        PetriNet robot3 = NetLibrary.CreateRobotNetRobot();

        creator.getListP()[1] = robot1.getListP()[0];
        robot1.getListP()[4] = executor1.getListP()[0];
        executor1.getListP()[1] = robot2.getListP()[0];
        robot2.getListP()[4] = executor2.getListP()[0];
        executor2.getListP()[1] = robot3.getListP()[0];

        list.add(new PetriSim(creator));
        list.add(new PetriSim(robot1));
        list.add(new PetriSim(executor1));
        list.add(new PetriSim(robot2));
        list.add(new PetriSim(executor2));
        list.add(new PetriSim(robot3));

        PetriObjModel model = new PetriObjModel(list);
        model.setIsProtokol(false);
        double timeModeling = 1000;
        model.go(timeModeling);
        
        System.out.println("Executed " + robot3.getListP()[4].getMark());
    }
    
    private static void runBusModel() throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {
        
        ArrayList<PetriSim> list = new ArrayList<>();
          
        PetriNet station1 = NetLibrary.CreateBusNetStop();
        PetriNet station2 = NetLibrary.CreateBusNetStop();
        PetriNet bus1 = NetLibrary.CreateBusNetBus(true);
        PetriNet bus2 = NetLibrary.CreateBusNetBus(false);
        
        // Filter
        station1.getListP()[2] = bus1.getListP()[0];
        station2.getListP()[2] = bus2.getListP()[0];
        // Queue
        station1.getListP()[4] = bus1.getListP()[2];
        station2.getListP()[4] = bus2.getListP()[2];
        // NA
        bus1.getListP()[9] = bus2.getListP()[3];
        bus2.getListP()[9] = bus1.getListP()[3];
        // NB
        bus1.getListP()[8] = bus2.getListP()[1];
        bus2.getListP()[8] = bus1.getListP()[1];
        
        
        list.add(new PetriSim(station1));
        list.add(new PetriSim(station2));
        list.add(new PetriSim(bus1));
        list.add(new PetriSim(bus2));
        
        PetriObjModel model = new PetriObjModel(list);
        model.setIsProtokol(false);
        double timeModeling = 1000;
        model.go(timeModeling);
        
        int na1 = bus1.getListP()[10].getMark();
        int nb1 = bus1.getListP()[11].getMark();
        int na2 = bus2.getListP()[10].getMark();
        int nb2 = bus2.getListP()[11].getMark();
        
        System.out.println("NA1 total: " + na1);
        System.out.println("NA2 total: " + na2);
        System.out.println("NB1 total: " + nb1);
        System.out.println("NB2 total: " + nb2);
        System.out.println("Total: "+(na1+nb1+na2+nb2));
    }
}
