package model.bean;
public class Entrada {
    
    private int id;
    private int tipo_ide;
    private String des_entrada;
    private String data;
    private double valor;
    private String descr;

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descricao) {
        this.descr = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo_ide() {
        return tipo_ide;
    }

    public void setTipo_ide(int tipo_ide) {
        this.tipo_ide = tipo_ide;
    }
    
    public String getDes_entrada() {
        return des_entrada;
    }

    public void setDes_entrada(String des_entrada) {
        this.des_entrada = des_entrada;
    }
          
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

 
   
}
