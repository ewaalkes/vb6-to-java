/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VariableDlg.java
 *
 * Created on Apr 19, 2010, 1:00:52 PM
 */

package buggymastercode;

import org.jdesktop.application.Action;

/**
 *
 * @author jalvarez
 */
public class VariableDialog extends javax.swing.JDialog {

    /** Creates new form VariableDlg */
    public VariableDialog(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txJavaName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txVbName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        setResizable(false);

        txJavaName.setName("txJavaName"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(buggymastercode.BuggyMasterCodeApp.class).getContext().getResourceMap(VariableDialog.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txVbName.setName("txVbName"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(buggymastercode.BuggyMasterCodeApp.class).getContext().getActionMap(VariableDialog.class, this);
        jButton1.setAction(actionMap.get("saveVariable")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addComponent(jLabel1)
                            .addGap(10, 10, 10)
                            .addComponent(txVbName, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(10, 10, 10)
                            .addComponent(txJavaName, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))
                    .addComponent(txVbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(txJavaName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txJavaName;
    private javax.swing.JTextField txVbName;
    // End of variables declaration//GEN-END:variables

    public int getId() {
        return m_id;
    }

    public boolean edit(int id, int cl_id) {
        m_cl_id = cl_id;
        if (id != Db.CS_NO_ID) {
            String sqlstmt = "select var_vbname, var_javaname from tvariable where var_id = " + ((Integer)id).toString();
            DBRecordSet rs = new DBRecordSet();
            if (!Db.db.openRs(sqlstmt, rs)) {return false;}

            if (rs.getRows().isEmpty()) {
                m_id = Db.CS_NO_ID;
                this.txVbName.setText("");
                this.txJavaName.setText("");
            }
            else {
                m_id = id;
                this.txVbName.setText(rs.getRows().get(0).get("var_vbname").toString());
                this.txJavaName.setText(rs.getRows().get(0).get("var_javaname").toString());
            }
        }
        else {
            m_id = Db.CS_NO_ID;
            this.txVbName.setText("");
            this.txJavaName.setText("");
        }
        return true;
    }

    @Action
    public void saveVariable() {
        if (validateVariable()) {

            VariableObject var = new VariableObject();
            var.setId(m_id);
            var.setClId(m_cl_id);
            var.setVbName(this.txVbName.getText());
            var.setJavaName(this.txJavaName.getText());
            if (var.saveVariable()) {
                m_id = var.getId();
            }
        }
    }

    private boolean validateVariable() {

        if (this.txVbName.getText().length() == 0) {
            G.showInfo("The vb name field is required");
            return false;
        }
        if (this.txJavaName.getText().length() == 0) {
            G.showInfo("The java name field is required");
            return false;
        }
        return true;
    }

    private int m_id;
    private int m_cl_id;
}
