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
          
          PetriNet eventCreator = NetLibrary.CreateNetСreation();
          PetriNet failureCreator = NetLibrary.CreateNetFailure();
          
//          // Connect is enable values
//          failureCreator.getListP()[2] = mainChannel.getListP()[4];
//          
//          // Connect event creator
//          eventCreator.getListP()[1] = mainChannel.getListP()[0];
//          eventCreator.getListP()[1] = reservChannel.getListP()[0];

          ArrayList<PetriSim> list = new ArrayList<>();
          list.add(new PetriSim(mainChannel));
          list.add(new PetriSim(reservChannel));
          list.add(new PetriSim(eventCreator));
          list.add(new PetriSim(failureCreator));

          PetriObjModel model = new PetriObjModel(list);
          model.setIsProtokol(false);
          double timeModeling = 1000;
          model.go(timeModeling);
          
          System.out.print("\tRESULTS");
          System.out.print("\nEvents created: " + eventCreator.getListP()[2].getMark());
          System.out.print("\nFailure created: " + failureCreator.getListP()[3].getMark());
          System.out.print("\nMain channel: " + mainChannel.getListP()[2].getMark());
          System.out.print("\nReserv channel: " + reservChannel.getListP()[2].getMark());
          System.out.print("\n");
      } 
}
