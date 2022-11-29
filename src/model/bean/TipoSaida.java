package model.bean;
public class TipoSaida {
        private int id;
        private String des_saida;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDes_saida() {
        return des_saida;
    }

    public void setDes_saida(String des_saida) {
        this.des_saida = des_saida;
    }

    @Override
    public String toString() {
        return des_saida;
    }
    
    
}
