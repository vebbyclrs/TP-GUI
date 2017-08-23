/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author ZANDUT
 */
public class Mahasiswa
{
    private long nim;
    private String nama;
    private double nilai;

    public Mahasiswa(long nim, String nama, double nilai)
    {
        this.nim = nim;
        this.nama = nama;
        this.nilai = nilai;
    }

    public Mahasiswa()
    {
    }

    public long getNim()
    {
        return nim;
    }

    public void setNim(long nim)
    {
        this.nim = nim;
    }

    public String getNama()
    {
        return nama;
    }

    public void setNama(String nama)
    {
        this.nama = nama;
    }

    public double getNilai()
    {
        return nilai;
    }

    public void setNilai(double nilai)
    {
        this.nilai = nilai;
    }
    
    
    
}
