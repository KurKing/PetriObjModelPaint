/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibTest;

import LibNet.NetLibrary;
import static LibTest.TestPetriObjSimulation.getModel;
import PetriObj.ExceptionInvalidNetStructure;
import PetriObj.ExceptionInvalidTimeDelay;
import PetriObj.PetriNet;
import PetriObj.PetriObjModel;
import PetriObj.PetriSim;

import java.util.ArrayList;

/**
 *
 * @author lyosh
 */
public class CourseWorkKurkin {
    
      public static void main(String[] args) throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {
         
          PetriNet mainChannel = NetLibrary.CreateNetChannel();
          PetriNet reservChannel = NetLibrary.CreateNetChannel();
          
          PetriNet eventCreator = NetLibrary.CreateNetEventCreator();
          PetriNet failureCreator = NetLibrary.CreateNetFailure();
          
          // Connect is enable values
          failureCreator.getListP()[2] = eventCreator.getListP()[0];
          
          // Connect event creator
          eventCreator.getListP()[4] = mainChannel.getListP()[4];
          eventCreator.getListP()[3] = reservChannel.getListP()[4];
          
          // Is enabled connector for main channel
          mainChannel.getListP()[3] = eventCreator.getListP()[0];
          
          // Event return connector
          mainChannel.getListP()[0] = eventCreator.getListP()[2];
          
          ArrayList<PetriSim> list = new ArrayList<>();
          list.add(new PetriSim(mainChannel));
          list.add(new PetriSim(reservChannel));
          list.add(new PetriSim(eventCreator));
          list.add(new PetriSim(failureCreator));

          PetriObjModel model = new PetriObjModel(list);
          model.setIsProtokol(false);
          
          double timeModeling = 1000;
          model.go(timeModeling);
          
          System.out.println("RESULTS");
          System.out.println("Events created: " + eventCreator.getListP()[6].getMark());
          System.out.println("Failure created: " + failureCreator.getListP()[3].getMark());
          
          printChannelStats("MAIN", mainChannel, timeModeling);
          printChannelStats("RESERVED", reservChannel, timeModeling);
      } 
      
    private static void printChannelStats(String name, PetriNet channel, double timeModeling) {
        
        int executed = channel.getListP()[1].getMark();
        double workingTime = 7 * executed;
        String locked = String.format("%.2f", Math.min(workingTime / timeModeling, 1.0) * 100) + "%";

        System.out.println("\n"+name + " channel:");
        System.out.println("\texecuted: "+executed);
        System.out.println("\tfailures: "+channel.getListP()[5].getMark());
        System.out.println("\twork time total: "+workingTime);
        System.out.println("\tlocked: "+locked);
    }
}
