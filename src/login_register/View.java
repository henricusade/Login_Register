/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login_register;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

/**
 *
 * @author user
 */
public class View extends JFrame implements ActionListener{
    JLabel llUser, llPw , ldUser, ldPw;
    JButton tombolLogin, tombolDaftar;
    final JTextField flUser, flPw, fdUser, fdPw;
    Connector connector = new Connector();
    
    public View(){
        setTitle("Login dan Register");
        setSize(400,300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        tombolLogin = new JButton("Login");
        tombolDaftar = new JButton("Daftar");
        
        llUser = new JLabel ("Username");
        llPw = new JLabel ("Password");
        ldUser = new JLabel ("Username");
        ldPw = new JLabel ("Password");
        
        flUser = new JTextField();
        flPw = new JTextField();
        fdUser = new JTextField();
        fdPw = new JTextField();
        
        setLayout(null);
        add(tombolLogin);
        add(tombolDaftar);       
        add(llUser);
        add(llPw);
        add(ldUser);
        add(ldPw);
        add(flUser);
        add(flPw);
        add(fdUser);
        add(fdPw);
        
        
        llUser.setBounds(40, 50, 70, 20);
        llPw.setBounds(40, 100, 70, 20);
        ldUser.setBounds(210, 50, 70, 20);
        ldPw.setBounds(210, 100, 70, 20);
        
        
        
        flUser.setBounds(40, 70, 110, 20);
        flPw.setBounds(40, 120, 110, 20);
        fdUser.setBounds(210, 70, 110, 20);
        fdPw.setBounds(210, 120, 110, 20);
        
        
        
        tombolLogin.setBounds(40, 150, 80, 20);
        tombolDaftar.setBounds(210, 150, 80, 20);

        tombolLogin.addActionListener(this);
        tombolDaftar.addActionListener(this);
        
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
     if(e.getSource() == tombolLogin){ 
            try{
                if(getUsername() == null || getUsername().isEmpty()){
                    throw new IllegalArgumentException("Username kosong");
                }
                if(getPassword() == null || getPassword().isEmpty()){
                     throw new IllegalArgumentException("Password kosong");
                }
                
                
                String query = "SELECT password FROM users WHERE username = '"+ getUsername() +"'"; 
                connector.statement = connector.koneksi.createStatement();
                ResultSet result = connector.statement.executeQuery(query);
                
                if(result.next()){
                    String pass = result.getString("password");
                    if(!pass.equals(getPassword())){ 
                        JOptionPane.showMessageDialog(new JFrame(), "Password Salah");
                    }else{
                        JOptionPane.showMessageDialog(new JFrame(), "Login Berhasil");
                    }
                    
                }else{ 
                    JOptionPane.showMessageDialog(new JFrame(), "Username tidak ditemukan");
                }
                connector.statement.close(); 
            }catch(Exception error){
                JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
            }
            
        }
        if(e.getSource() == tombolDaftar){
            try{
                if(getUsernamee() == null || getUsernamee().isEmpty()){
                    throw new IllegalArgumentException("Username kosong");
                }
                if(getPasswordd() == null || getPasswordd().isEmpty()){
                     throw new IllegalArgumentException("Password kosong");
                }
                
                String query = "SELECT username FROM users WHERE username = '"+getUsernamee()+"'"; 
                connector.statement = connector.koneksi.createStatement();
                ResultSet result = connector.statement.executeQuery(query);

                if(result.next() == false){ 
                    String queryInsert = "INSERT INTO users(username, password) VALUES ('"+getUsernamee()+"','"+getPasswordd()+"')";
                    connector.statement = connector.koneksi.createStatement();
                    connector.statement.executeUpdate(queryInsert);
                    JOptionPane.showMessageDialog(new JFrame(), "Input user berhasil");
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Username sudah pernah digunakan");
                }
                connector.statement.close();
            }catch(Exception error){
                JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
            }
        } 
        
    }
    public String getUsername(){
        return flUser.getText();
    }
    public String getPassword(){
        return flPw.getText();
    }
    public String getUsernamee(){
        return fdUser.getText();
    }
    public String getPasswordd(){
        return fdPw.getText();
    }
}
