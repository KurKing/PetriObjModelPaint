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
 * @author lyosh
 */
public class CourseWorkKurkin {
    
      public static void main(String[] args) throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {
         
          
      } 
            
      public static PetriObjModel getModel() throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure{
                    
          PetriNet mainChannel = NetLibrary.CreateNetChannel(0);
          PetriNet reservChannel = NetLibrary.CreateNetChannel(2.0);
          
          PetriNet eventCreator = NetLibrary.CreateNetСreation();
          PetriNet failureCreator = NetLibrary.CreateNetFailure();
          
//          list.get(0).getNet().getListP()[1] = list.get(1).getNet().getListP()[0]; //gen = > SMO1
//          list.get(1).getNet().getListP()[2] = list.get(5).getNet().getListP()[0]; //SMO1 = > fork
//
//          list.get(5).getNet().getListP()[1] = list.get(2).getNet().getListP()[0]; //fork =>SMO2
//          list.get(5).getNet().getListP()[2] = list.get(3).getNet().getListP()[0]; //fork =>SMO3
//          list.get(5).getNet().getListP()[3] = list.get(4).getNet().getListP()[0]; //fork =>SMO4
//
//          list.get(2).getNet().getListP()[2] = list.get(1).getNet().getListP()[0]; //SMO2 => SMO1
//          list.get(3).getNet().getListP()[2] = list.get(1).getNet().getListP()[0];//SMO3 => SMO1
//          list.get(4).getNet().getListP()[2] = list.get(1).getNet().getListP()[0];//SMO4 => SMO1
          
          ArrayList<PetriSim> list = new ArrayList<>();
          list.add(new PetriSim(mainChannel));
          list.add(new PetriSim(reservChannel));
          list.add(new PetriSim(eventCreator));
          list.add(new PetriSim(failureCreator));

          PetriObjModel model = new PetriObjModel(list);
          return model;
      } 
}
