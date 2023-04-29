/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab5;

/**
 *
 * @author hp
 */
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
public class Lab6 {
    public static void main(String[] args) throws Exception{
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter file destination");
        String file_name = input.nextLine();
        if(!(file_name.matches(".*.arxml")))
                {
                 throw new InvalidFileException();   
                }
        File new_file = new File(file_name);
        if(new_file.length() == 0)
            throw new EmptyFileException();
        // now that both exceptions are checked we need to sort
        Scanner reader = new Scanner(new_file);
        String[] filearr = new String[500];
        int i = 0;
        while (reader.hasNextLine()){
            filearr[i] = reader.nextLine();
            System.out.println(filearr[i]);
            i++;
        }
        reader.close();
        String containerarr[] = new String[5];
        String[] shortnamearr = new String[5];
        String[] longnamearr = new String[5];
        String[] footerarr = new String[5];
        int containercount = 0;
        int shortnamecount = 0;
        int longnamecount = 0;
        int footercount = 0;
        System.out.println("------------------");
        for(int j = 0; j<i ; j++){
            if(filearr[j].matches("(.*)<CONTAINER(.*)"))
            {
                containerarr[containercount] = filearr[j];
                containercount++;
            }
            else if(filearr[j].matches("(.*)<SHORT-NAME>(.*)"))
            {
                shortnamearr[shortnamecount] = filearr[j];
                shortnamecount++;
            }
            else if(filearr[j].matches("(.*)<LONG-NAME>(.*)"))
            {
                longnamearr[longnamecount]= filearr[j];
                longnamecount++;
            }
            else if(filearr[j].matches("(.*)</CONTAINER>(.*)"))
            {
                footerarr[footercount] = filearr[j];
                footercount++;
            }
        }
        //container
        Container[] containers = new Container[3];
        for(int p=0;p<3;p++){
            containers[p] = new Container(containerarr[p],shortnamearr[p]
            ,longnamearr[p],footerarr[p]);
        }
        Arrays.sort(containers);
        File outputfile = new File(file_name.substring(0,
                file_name.length()-6).concat("_mod.arxml"));
        try(PrintWriter outputxml = new PrintWriter(outputfile)){
            outputxml.println(filearr[0]);
            outputxml.println(filearr[1]);
            for(int q =0;q<containers.length;q++){
                outputxml.println(containers[q].getContainerhead());
                outputxml.println(containers[q].getShort_name());
                outputxml.println(containers[q].getLong_name());
                outputxml.println(containers[q].getContainerfoot());
            }
            outputxml.println(filearr[14]);
        }
}
}
class Container implements Comparable<Container>{
    private String Containerhead;
    private String short_name;
    private String Containerfoot;
    private String long_name;
    //constructor
    public Container(String Containerhead, String short_name, String Containerfoot, String long_name) {
        this.Containerhead = Containerhead;
        this.short_name = short_name;
        this.Containerfoot = Containerfoot;
        this.long_name = long_name;
    }
    public String getContainerhead(){
        return Containerhead;
    }
    public String getShort_name(){
        return short_name;
    }
    public String getLong_name(){
        return long_name;
    }
    public String getContainerfoot(){
        return Containerfoot;
    }
    @Override
    public int compareTo(Container c){
        return this.short_name.compareTo(c.short_name);
    }
}
class EmptyFileException extends RuntimeException{
    public EmptyFileException(){
        super("Empty AUTOSAR file exception!");
    }
}
class InvalidFileException extends Exception{
    public InvalidFileException(){
        super("Invalid AUTOSAR file exception!");
    }
}