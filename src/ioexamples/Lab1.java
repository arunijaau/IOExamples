/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ioexamples;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ajayasooriya
 */
public class Lab1 {
    public static void main(String[] args) throws IOException {
        
        File file1 = new File("src" + File.separatorChar + "contactList.txt");
        BufferedReader in = null;
        List<Contact> contacts = new ArrayList<>();
        
        try{
            in = new BufferedReader(new FileReader(file1));
            
            int lineCount = 0;
            Contact contact = null;
            
            String line = in.readLine();
            while(line != null){
                if(lineCount == 0){
                    contact = new Contact();
                    contacts.add(contact);
                    
                    String[] parts = line.split(" ");
                    contact.setFirstName(parts[0]);
                    contact.setLastName(parts[1]);
                    
                    lineCount++;
                }else if(lineCount > 2){
                    contact = new Contact();
                    lineCount = 0;
                }
                
                line = in.readLine();
                if(lineCount == 1){
                    contact.setAddress(line);
                    lineCount++;
                    
                }else if(lineCount == 2){
                    String[] cityParts = line.split(", ");
                    contact.setCity(cityParts[0]);
                    String[] stateZipParts = cityParts[1].split(" ");
                    contact.setState(stateZipParts[0]);
                    contact.setZipCode(stateZipParts[1]); 
                    lineCount++;
                }
                
            }
            for(Contact c: contacts){
                System.out.println(c);
            }
            
            System.out.println(contacts.size());
        }catch(IOException io){
            System.out.println(io.getMessage());
        }finally{
            if(in != null){
                in.close();
            }
        }
        
        // append records
        boolean append = true;
        PrintWriter out = null;

        try{
            out = new PrintWriter(new BufferedWriter(new FileWriter(file1, append)));
            out.print(new Contact("Natalie", "Jacob", "456 Main Street", "Pewaukee", "WI", "53072"));
           out.print(new Contact("Nathan", "Peterson", "789 Main Street", "Park Ave", "WI", "56708"));
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }finally{
            if(out != null){
                out.close();
            }
        }
        
        // Find second contact and output State.
        Contact secondContact = contacts.get(1);
        System.out.println(secondContact.getFirstName() + " " + secondContact.getLastName() + " state: "+ secondContact.getState());
    
    }
      
}
