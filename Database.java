/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbms;
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
public class Database {
    FileInputStream fin;
    Connection con,con2;
    PreparedStatement ps,ps2;
    ResultSet rs,rs2;
    Blob b;
    String img_name;

public Database()
{
	try
	{
      con = DriverManager.getConnection("jdbc:mysql://localhost/BBMS","root","@idx");
      //here dbname is database name, root is username and sharang is the password
      
    }catch(SQLException sqe){
	System.out.println("Could not connect to database");
    }

}
    

public void storeDonor(String fullFileAddr,String image,String name,int age,String gender,String address,String contact)
{
	try
	{
    fin = new FileInputStream(fullFileAddr);
	ps = con.prepareStatement("insert into DonorDetails(name,age,gender,address,contact,image) values(?,?,?,?,?,?)");

    ps.setString(1, name);
    ps.setInt(2,age);
    ps.setString(3,gender);
    ps.setString(4,address);
    ps.setString(5,contact);
    ps.setBlob(6,fin);
    ps.executeUpdate();

    fin.close();
    
    }catch(Exception e)
    {
	 System.out.println("File not found or sql exception");
	}
}




public void storeBlood(String bg,String packtype,String platelet,String wbc,String rbc,Date doe,int vol)
{
	try
	{
   
	ps = con.prepareStatement("insert into DonorDetails(bg,platelet,wbc,rbc,vol,packtype,doe) values(?,?,?,?,?,?,?)");

    ps.setString(1, bg);
    ps.setString(2,platelet);
    ps.setString(3,wbc);
    ps.setString(4,rbc);
    ps.setInt(5,vol);
    ps.setString(6,packtype);
    ps.setDate(7,doe);
    ps.executeUpdate();

    fin.close();
    
    }catch(Exception e)
    {
	 System.out.println("File not found or sql exception");
	}
}




public ResultSet retrieveBlood(String bg)
{
    try{
    ps = con.prepareStatement("select * from BloodDetails where bg = ?");

    ps.setString(1, bg);
    rs = ps.executeQuery();
    return rs;
    //here we need to store the results we get...

            /*while(rs.next())//while there are entries in the db with the particular bg
            {
                b = rs.getBlob("image");
                img_name=new String(rs.getString("name"));
                InputStream in = new ByteArrayInputStream(b.getBytes(1, (int)b.length()));
			    BufferedImage bImageFromConvert = ImageIO.read(in);
                return bImageFromConvert;
            }
            else 
            {
				System.out.printf("Image not found in database.\n");
			}
		}*/
    }catch(Exception e){System.out.println("Sql exception");}
		return null;
}



public ResultSet retrieveDonor(int id)
{
    try{
    ps = con.prepareStatement("select * from DonorDetails where donorid = ?");

    ps.setInt(1, id);
    rs = ps.executeQuery();
    return rs;
    //here we need to store the results we get...

            /*while(rs.next())//while there are entries in the db with the particular bg
            {
                b = rs.getBlob("image");
                img_name=new String(rs.getString("name"));
                InputStream in = new ByteArrayInputStream(b.getBytes(1, (int)b.length()));
			    BufferedImage bImageFromConvert = ImageIO.read(in);
                return bImageFromConvert;
            }
            else 
            {
				System.out.printf("Image not found in database.\n");
			}
		}*/
    }catch(Exception e){System.out.println("Sql exception");}
		return null;
}

public void display(String bg)
{
    
        Database obj = new Database();
        
        ResultSet rs=obj.retrieveBlood(bg);
        
        
        JFrame frame=new JFrame();
        frame.setLayout(new GridLayout());
        frame.setSize(1020,720);
        try{
        while(rs.next())
        {
             JLabel lb[]=new JLabel[13];
             lb[0].setText(rs.getString("bg"));
             lb[1].setText(rs.getString("platelet"));
             lb[2].setText(rs.getString("wbc"));
             lb[3].setText(rs.getString("rbc"));
             lb[4].setText(rs.getString("vol"));
             lb[5].setText(rs.getString("packtype"));
             lb[6].setText(rs.getString("doe"));
             ResultSet rs2=obj.retrieveDonor(rs.getInt("packid"));
             lb[7].setText(rs2.getString("name"));
             lb[8].setText((rs2.getString("age")));
             lb[9].setText(rs.getString("gender"));
             lb[10].setText(rs.getString("address"));
            
             Blob b = rs2.getBlob("image");//blob has the image in binary=4gb max
             InputStream in = new ByteArrayInputStream(b.getBytes(1, (int)b.length()));//byte array from blob b is getting converted into a byte array input stream
	     BufferedImage bi= ImageIO.read(in);//convert the byte input stream into a buffered image
             ImageIcon icon=new ImageIcon(bi);//refine the image from a buffered image to get a proper image icon
             lb[11].setIcon(icon);
             
             frame.add(lb[0]);
             frame.add(lb[1]);            
             frame.add(lb[2]);
             frame.add(lb[3]);
             frame.add(lb[4]);
             frame.add(lb[5]);
             frame.add(lb[6]);
             frame.add(lb[7]);
             frame.add(lb[8]);
             frame.add(lb[9]);
             frame.add(lb[10]);
             
            
        }
        
        
        
        
        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        }catch(Exception e){e.printStackTrace();}
    
    
    
    
    
    
    
    
    
}



}
