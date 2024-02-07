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
public class Lab6Task2 {
    
    public static void main(String[] args) throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {
        
        System.out.println("\t  ### Time modelling: 1000 ###");
        
        for (TaskParameters param: modellingList()) {
            
            PetriNet net = NetLibrary.CreateNetLab6Task2(param.getCreationTime(),
                    param.getExecutionTime(), 
                    param.getMoveTime(), 
                    param.getLastMoveTime());
        
            runModel(net);
            
            System.out.print(param.getCreationTime()+"; "+
                    param.getExecutionTime()+"; "+
                    param.getMoveTime()+"; "+
                    param.getLastMoveTime());

            System.out.print("; Executed: "+net.getListP()[3].getMark());
            System.out.println("; Returned: "+net.getListP()[12].getMark());
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
                new TaskParameters(1.0, 1.0, 1.0, 5.0),
                new TaskParameters(2.0, 1.0, 1.0, 5.0),
                new TaskParameters(0.5, 1.0, 1.0, 5.0),
                new TaskParameters(1.0, 2.0, 1.0, 5.0),
                new TaskParameters(1.0, 0.5, 1.0, 5.0),
                new TaskParameters(1.0, 1.0, 2.0, 5.0),
                new TaskParameters(1.0, 1.0, 0.5, 5.0),
                new TaskParameters(1.0, 1.0, 1.0, 3.0),
                new TaskParameters(1.0, 1.0, 1.0, 7.0)
        );
    }
    
    private static class TaskParameters {
    
        private final double creationTime;
        private final double executionTime;
        private final double moveTime;
        private final double lastMoveTime;

        // Constructor
        public TaskParameters(final double creationTime, final double executionTime, final double moveTime, final double lastMoveTime) {
            this.creationTime = creationTime;
            this.executionTime = executionTime;
            this.moveTime = moveTime;
            this.lastMoveTime = lastMoveTime;
        }

        // Getters
        public double getCreationTime() {
            return creationTime;
        }

        public double getExecutionTime() {
            return executionTime;
        }

        public double getMoveTime() {
            return moveTime;
        }

        public double getLastMoveTime() {
            return lastMoveTime;
        }
    }
}

