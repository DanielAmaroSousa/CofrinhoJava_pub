package model.bean;
public class Saida {
    
    private int id;
    private int tipo_ids;
    private String des_saida;
    private String data;
    private double valor;
    private String descr;

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo_ids() {
        return tipo_ids;
    }

    public void setTipo_ids(int tipo_ids) {
        this.tipo_ids = tipo_ids;
    }
    
    public String getDes_saida() {
        return des_saida;
    }

    public void setDes_saida(String des_saida) {
        this.des_saida = des_saida;
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
