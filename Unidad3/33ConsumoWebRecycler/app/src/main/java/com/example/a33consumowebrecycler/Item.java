package com.example.a33consumowebrecycler;

public class Item {

    private String Name; //Name
    private String Nctrl; //No control
    private String u1; //cel
    private String u2; //carrera
    private String u3; //calificacion


    public String getNctrl() {
        return Nctrl;
    }

    public void setNctrl(String nctrl) {
        Nctrl = nctrl;
    }

    public String getU1() {
        return u1;
    }

    public void setU1(String u1) {
        this.u1 = u1;
    }

    public String getU2() {
        return u2;
    }

    public void setU2(String u2) {
        this.u2 = u2;
    }

    public String getU3() {
        return u3;
    }

    public void setU3(String u3) {
        this.u3 = u3;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString(){
        return "ClassItem [nombre = "+Name+", noControl = "+Nctrl+"]";
    }
}
