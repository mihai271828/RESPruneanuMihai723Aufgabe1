package org.example;

import Model.Event;
import Service.Service;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Service service=new Service();
        List<Event> even=service.readXML();
        for(Event e:even){
            System.out.println(e);
        }


    }
}