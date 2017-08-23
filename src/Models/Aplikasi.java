/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZANDUT
 */
public class Aplikasi
{
    private DatabaseConnection db;

    public Aplikasi()
    {
        db = new DatabaseConnection();
    }
    
    public boolean saveMahasiswa(Mahasiswa m)
    {
        db.Connect();
        boolean berhasil = db.Manipulate("insert into mahasiswa (id, nama, nilai) values ('"+m.getNim()+"',"
                + "'"+m.getNama()+"','"+m.getNilai()+"');");
        
        db.disconnect();
        return berhasil;
        
    }
    
    public ArrayList<Mahasiswa> getAllMahasiswa()
    {
        db.Connect();
        ArrayList<Mahasiswa> arrayMahasiswa = new ArrayList<>();
        ResultSet rs = db.getData("select * from mahasiswa");
        try
        {
            while (rs.next())            
            {
                Mahasiswa mhs = new Mahasiswa(rs.getLong("id"), rs.getString("nama"), rs.getDouble("nilai"));
                arrayMahasiswa.add(mhs);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Aplikasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        db.disconnect();
        
        return arrayMahasiswa;
    }
    
    public boolean deleteMahasiswa(long nim)
    {
        db.Connect();
        boolean berhasil = db.Manipulate("delete from mahasiswa where id='"+nim+"'");
        db.disconnect();
        return berhasil;
    }
    
    
}
