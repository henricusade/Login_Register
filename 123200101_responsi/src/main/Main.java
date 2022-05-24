/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author user
 */
public class Main {
    public static void main(String[] args){
    ViewMovie vm = new ViewMovie();
    ModelMovie mm = new ModelMovie();
    ControllerMovie cm = new ControllerMovie(mm, vm);
    }
}
