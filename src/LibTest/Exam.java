/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibTest;

/**
 *
 * @author lyosh
 */

import LibNet.NetLibrary;
import PetriObj.ExceptionInvalidNetStructure;
import PetriObj.ExceptionInvalidTimeDelay;
import PetriObj.PetriNet;
import PetriObj.PetriObjModel;
import PetriObj.PetriSim;

import java.util.ArrayList;


public class Exam {
      public static void main(String[] args) throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {
                   
          ArrayList<PetriSim> list = new ArrayList<>();
          
          PetriNet server = NetLibrary.CreateNetfullServer();
          
          for (int i = 0; i < 200; i++) {
              
              PetriNet camera = NetLibrary.CreateNetcamera();
              camera.getListP()[1] = server.getListP()[0];
              list.add(new PetriSim(camera));
          }
          
          list.add(new PetriSim(server));
          
          PetriObjModel model = new PetriObjModel(list);
          model.setIsProtokol(false);
          double timeModeling = 1000;
          model.go(timeModeling);

          System.out.println("Max in buffer: "+server.getListP()[0].getObservedMax());
      }    
}
