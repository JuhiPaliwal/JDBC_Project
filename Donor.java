/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbms;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.awt.*;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author aishwarya
 */
public class Donor extends Frame {

    Button b1, b2;
    Label l;
    TextField name, age, gender, addr, cont;//add donorid
    CheckboxGroup gen=new CheckboxGroup();
    public Donor() {
        setVisible(true);
        setSize(500, 500);
        setBackground(Color.red);
        setLayout(null);
        Font f = new Font("Verdana", Font.BOLD, 20);
        setFont(f);
        addWindowListener(new MyEvent() {
        });
        Label l0=new Label("Enter the donor details");
        Label l1 = new Label("Name:");
        Label l2 = new Label("Age:");
        Label l3 = new Label("Gender:");
        Label l4 = new Label("Contact:");
        Label l5 = new Label("Address:");
        Checkbox male=new Checkbox("Male",gen,true);
        Checkbox female=new Checkbox("Female",gen,false);
        Checkbox other=new Checkbox("Other",gen,false);
        name = new TextField(20);
        age = new TextField(20);
        gender = new TextField(20);
        addr = new TextField(20);
        cont = new TextField(20);
        b1 = new Button("Submit");
        add(l0);
        add(l1);
        add(name);
        add(l2);
        add(age);
        add(l3);
        add(gender);        
        add(l4);
        add(addr);
        add(l5);
        add(cont);
        add(b1);
       
        
        add(male);
        add(female);
        add(other);
        
        l0.setBounds(10,20,400,40);
        l1.setBounds(10, 80, 90, 80);
        name.setBounds(200, 80, 110, 40);
        l2.setBounds(10, 150, 90, 80);
        age.setBounds(200, 150, 110, 40);
        l3.setBounds(10, 220, 90, 80);
        gender.setBounds(200, 220, 110, 40);
        l4.setBounds(10, 290, 90, 80);
        addr.setBounds(200, 290, 110, 40);
        l5.setBounds(10, 370, 90, 80);
        cont.setBounds(200, 370, 110, 40);
        b1.setBounds(10, 450, 100, 40);
       
      

    }

    class MyEvent extends WindowAdapter {

        public void windowClosing(WindowEvent ex) {
            System.exit(0);
        }
    }

    public void paint(Graphics g) {
        repaint();
    }

    public static void main(String args[]) {
        Donor d = new Donor();
        Database o = new Database();
        o.storeDonor();
    }

}
