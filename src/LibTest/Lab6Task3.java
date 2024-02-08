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

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author oleksii
 */
public class Lab6Task3 {
    
    public static void main(String[] args) throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {
        
        System.out.println("\t  ### Time modelling: 1000 ###");
        System.out.println("Create Check  Arrival   Sold   Failed  Mean storage");
        
        for (TaskParameters param: modellingList()) {
            
            PetriNet net = NetLibrary.CreateNetLab6Task3(param.getUserCreationTime(),
                    param.getCheckCreationTime(), 
                    param.getArrivalTime());
        
            runModel(net);
            
            System.out.print(param.getUserCreationTime()+" \t"+
                    param.getCheckCreationTime()+" \t"+
                    param.getArrivalTime());

            System.out.print("\t"+net.getListP()[3].getMark());
            System.out.print("\t"+net.getListP()[5].getMark());
            System.out.println("\t"+String.format("%.2f", net.getListP()[2].getMean()));
        }
    }
    
    private static void runModel(PetriNet net) {
        
        PetriObjModel model = new PetriObjModel(new ArrayList<>(List.of(new PetriSim(net))));
        model.setIsProtokol(false);
        double timeModeling = 1000;
        model.go(timeModeling);
    }
    
    private static List<TaskParameters> modellingList() {
        
        return List.of(
                new TaskParameters(0.2, 4.0, 3.0),
                new TaskParameters(0.1, 4.0, 3.0),
                new TaskParameters(0.5, 4.0, 3.0),
                new TaskParameters(0.2, 2.0, 3.0),
                new TaskParameters(0.2, 6.0, 3.0),
                new TaskParameters(0.2, 4.0, 2.0),
                new TaskParameters(0.2, 4.0, 4.0)
        );
    }
    
    private static class TaskParameters {
    
        private final double userCreationTime;
        private final double checkCreationTime;
        private final double arrivalTime;

        // Constructor
        public TaskParameters(double userCreationTime, double checkCreationTime, double arrivalTime) {
            this.userCreationTime = userCreationTime;
            this.checkCreationTime = checkCreationTime;
            this.arrivalTime = arrivalTime;
        }

        // Getters
        public double getUserCreationTime() {
            return userCreationTime;
        }

        public double getCheckCreationTime() {
            return checkCreationTime;
        }

        public double getArrivalTime() {
            return arrivalTime;
        }
    }
}
