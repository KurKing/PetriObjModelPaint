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
}
