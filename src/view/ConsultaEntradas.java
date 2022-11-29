package view;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.DAO.AnoDataDAO;
import model.DAO.EntradaDAO;
import model.DAO.TipoEntradaDAO;
import model.bean.AnoData;
import model.bean.Entrada;
import model.bean.TipoEntrada;

public class ConsultaEntradas extends javax.swing.JInternalFrame {

    private double totalentradas = 0;
    
    public ConsultaEntradas() {
        initComponents();
        
        DefaultTableModel modelo = (DefaultTableModel) jTentradas.getModel();
        jTentradas.setRowSorter(new TableRowSorter(modelo));

        readJTable();
        
        TipoEntradaDAO tedao = new TipoEntradaDAO();
        jCBtipoentrada.addItem("Qualquer");
        for(TipoEntrada tecb: tedao.read()){
            jCBtipoentrada.addItem(tecb);
        }

        CBdata();
        
        Date myDate = new Date();
        String dStr = new SimpleDateFormat("yyyy-MM-dd").format(myDate);
        jTdataA.setText(dStr);
        jTdataDe.setText(String.valueOf(myDate.getYear()+1900) +"-"+ String.valueOf(myDate.getMonth()+1)+"-01");
       
    }

    
    public void CBdata() {
        //MesDataDAO mdao = new MesDataDAO();
        AnoDataDAO adao = new AnoDataDAO();
        //jCBmes.addItem("");
        /**
        for(MesData m: mdao.read_distint_mes()){
            jCBmes.addItem(m.getNomemes());
        }
        **/ 
        jCBmes.addItem("");
        jCBmes.addItem("Janeiro");
        jCBmes.addItem("Fevereiro");
        jCBmes.addItem("Março");
        jCBmes.addItem("Abril");
        jCBmes.addItem("Maio");
        jCBmes.addItem("Junho");
        jCBmes.addItem("Julho");
        jCBmes.addItem("Agosto");        
        jCBmes.addItem("Setembro");
        jCBmes.addItem("Outubro");
        jCBmes.addItem("Novembro");        
        jCBmes.addItem("Dezembro");
        
        jCBano.addItem("");
        for(AnoData a: adao.read_distint_ano()){
            jCBano.addItem(String.valueOf(a.getAno()));
        }
    }
    public void readJTable() {
        String idte = "*";
        if (jCBtipoentrada.getSelectedIndex()>0) {
            TipoEntrada te = (TipoEntrada) jCBtipoentrada.getSelectedItem();
            idte = String.valueOf(te.getId());
            System.out.println(idte);
        }
        
        DefaultTableModel modelo = (DefaultTableModel) jTentradas.getModel();
        modelo.setNumRows(0);
        EntradaDAO edao = new EntradaDAO();
        totalentradas = 0;

        for (Entrada e : edao.read_testr(idte)) {

            modelo.addRow(new Object[]{
                e.getId(),
                e.getDes_entrada(),
                e.getData(),
                e.getValor(),
                e.getDescr()
            });
        totalentradas=totalentradas+e.getValor();
        }
        txtValorTotal.setText(String.format("%.2f €",totalentradas));
    }

       public void readPesquisa() {
        String idte = "*";
        if (jCBtipoentrada.getSelectedIndex()>0) {
            TipoEntrada te = (TipoEntrada) jCBtipoentrada.getSelectedItem();
            idte = String.valueOf(te.getId());
            System.out.println(idte);
        }
        
        DefaultTableModel modelo = (DefaultTableModel) jTentradas.getModel();
        modelo.setNumRows(0);
        EntradaDAO edao = new EntradaDAO();
        totalentradas = 0;

        for (Entrada e : edao.read_pesquisa(jTdataDe.getText(), jTdataA.getText(), idte)) {

            modelo.addRow(new Object[]{
                e.getId(),
                e.getDes_entrada(),
                e.getData(),
                e.getValor(),
                e.getDescr()
            });
        totalentradas=totalentradas+e.getValor();
        }
        txtValorTotal.setText(String.format("%.2f €",totalentradas));
    } 
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTentradas = new javax.swing.JTable();
        txtValorTotal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jBdelete = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jCBtipoentrada = new javax.swing.JComboBox<>();
        jBpesquisa = new javax.swing.JButton();
        jCBano = new javax.swing.JComboBox<>();
        jTdataDe = new javax.swing.JTextField();
        jTdataA = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jCBmes = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Consulta Entradas");

        jTentradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Tipo Entrada", "Data", "Valor", "Descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTentradas);
        if (jTentradas.getColumnModel().getColumnCount() > 0) {
            jTentradas.getColumnModel().getColumn(0).setMaxWidth(40);
            jTentradas.getColumnModel().getColumn(2).setMaxWidth(100);
            jTentradas.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        txtValorTotal.setEditable(false);
        txtValorTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel1.setText("Valor Total");

        jBdelete.setText("Apagar Entrada");
        jBdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBdeleteActionPerformed(evt);
            }
        });

        jBpesquisa.setText("Pesquisa");
        jBpesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBpesquisaActionPerformed(evt);
            }
        });

        jCBano.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBanoItemStateChanged(evt);
            }
        });

        jTdataDe.setText("aaaa-MM-dd");

        jTdataA.setText("aaaa-MM-dd");

        jLabel5.setText("a");

        jCBmes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBmesItemStateChanged(evt);
            }
        });

        jLabel4.setText("De");

        jLabel3.setText("Tipo de entrada");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCBmes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTdataDe, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTdataA, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBano, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCBtipoentrada, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(74, 74, 74)))
                .addGap(18, 18, 18)
                .addComponent(jBpesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTdataA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTdataDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBmes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBtipoentrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBpesquisa))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(302, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 12, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBdelete)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jBdelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBdeleteActionPerformed
       
        if (jTentradas.getSelectedRow() != -1) {

            Entrada e = new Entrada();
            EntradaDAO dao = new EntradaDAO();

            e.setId((int) jTentradas.getValueAt(jTentradas.getSelectedRow(), 0));
            
            dao.delete(e);

            readJTable();

        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma linha para remover.");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jBdeleteActionPerformed

    private void jBpesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBpesquisaActionPerformed
        readPesquisa();
    }//GEN-LAST:event_jBpesquisaActionPerformed

    private void jCBanoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBanoItemStateChanged
        if (jCBano.getSelectedItem() ==""){
            Date myDate = new Date();
            jTdataDe.setText(String.valueOf(myDate.getYear()+1900) +"-"+ String.valueOf(jCBmes.getSelectedIndex()) + "-01");
            jTdataA.setText(String.valueOf(myDate.getYear()+1900) +"-"+ String.valueOf(jCBmes.getSelectedIndex()+1) + "-01");
        } else {
            jTdataDe.setText(jCBano.getSelectedItem() + "-" + String.valueOf(jCBmes.getSelectedIndex()) + "-01");
            jTdataA.setText(jCBano.getSelectedItem() + "-" + String.valueOf(jCBmes.getSelectedIndex()+1) + "-01");
        }

    }//GEN-LAST:event_jCBanoItemStateChanged

    private void jCBmesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBmesItemStateChanged

        if (jCBano.getSelectedItem() == ""){
            Date myDate = new Date();
            jTdataDe.setText(String.valueOf(myDate.getYear()+1900) +"-"+ String.valueOf(jCBmes.getSelectedIndex()) + "-01");
            jTdataA.setText(String.valueOf(myDate.getYear()+1900) +"-"+ String.valueOf(jCBmes.getSelectedIndex()+1) + "-01");
        } else {
            jTdataDe.setText(jCBano.getSelectedItem() + "-" + String.valueOf(jCBmes.getSelectedIndex()) + "-01");
            jTdataA.setText(jCBano.getSelectedItem() + "-" + String.valueOf(jCBmes.getSelectedIndex()+1) + "-01");
        }

    }//GEN-LAST:event_jCBmesItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBdelete;
    private javax.swing.JButton jBpesquisa;
    private javax.swing.JComboBox<String> jCBano;
    private javax.swing.JComboBox<String> jCBmes;
    private javax.swing.JComboBox<Object> jCBtipoentrada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTdataA;
    private javax.swing.JTextField jTdataDe;
    private javax.swing.JTable jTentradas;
    private javax.swing.JTextField txtValorTotal;
    // End of variables declaration//GEN-END:variables
}
