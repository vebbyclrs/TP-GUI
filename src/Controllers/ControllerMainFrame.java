/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Aplikasi;
import Models.Mahasiswa;
import Views.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ZANDUT
 */
public class ControllerMainFrame implements ActionListener, KeyListener
{
    private MainFrame main;
    private Aplikasi aplikasi;

    public ControllerMainFrame()
    {
        aplikasi = new Aplikasi();
        
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
       
        main = new MainFrame();
        main.getjButton1().addActionListener(this);
        main.getjTable1().addKeyListener(this);
        
        ArrayList<Mahasiswa> dataMahasiswa = aplikasi.getAllMahasiswa();
        RefreshTable(main.getjTable1(), dataMahasiswa);
        
        main.setLocationRelativeTo(null);
        main.setVisible(true);
    }

    private void RefreshTable(JTable table, ArrayList<Mahasiswa> data)
    {
        
        DefaultTableModel tb = (DefaultTableModel) table.getModel();
        tb.setRowCount(0);
        for (Mahasiswa mhs : data)
        {
            String[] dataTable = {""+mhs.getNim(), mhs.getNama(), ""+mhs.getNilai()};
            tb.addRow(dataTable);
        }
        
        
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(main.getjButton1())) //addMahasiswa
        {
            Mahasiswa m = new Mahasiswa(Long.parseLong(main.getTextNIM().getText()), main.getTextNama().getText(), Double.parseDouble(main.getTextNilai().getText()));
            if (aplikasi.saveMahasiswa(m))
            {
                main.showMessage("Berhasil");
                ArrayList<Mahasiswa> dataMahasiswa = aplikasi.getAllMahasiswa();
                RefreshTable(main.getjTable1(), dataMahasiswa);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == e.VK_DELETE)
        {
            Mahasiswa m = new Mahasiswa(Long.parseLong(main.getjTable1().getValueAt(main.getjTable1().getSelectedRow(), 0).toString()), main.getjTable1().getValueAt(main.getjTable1().getSelectedRow(), 1).toString(), Double.parseDouble(main.getjTable1().getValueAt(main.getjTable1().getSelectedRow(), 2).toString()));
            int result = main.showConfirmationDialog("Apakah yakin menghapus "+m.getNama()+" ?");
            if (result == JOptionPane.YES_OPTION)
            {
                if (aplikasi.deleteMahasiswa(m.getNim()))
                {
                    main.showMessage("Berhasil");
                    ArrayList<Mahasiswa> dataMahasiswa = aplikasi.getAllMahasiswa();
                    RefreshTable(main.getjTable1(), dataMahasiswa);
                }
                
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        
    }
    
    
    
    
}
