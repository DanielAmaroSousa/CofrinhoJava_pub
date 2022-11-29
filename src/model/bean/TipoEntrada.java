package model.bean;
public class TipoEntrada {
        private int id;
        private String des_entrada;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDes_entrada() {
        return des_entrada;
    }

    public void setDes_entrada(String des_entrada) {
        this.des_entrada = des_entrada;
    }

    @Override
    public String toString() {
        return des_entrada;
    }
    
    
}
