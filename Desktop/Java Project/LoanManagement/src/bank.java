
import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Saief
 */
public class bank extends javax.swing.JFrame {
    connection c=new connection();   ArrayList<Integer> lid=new ArrayList();
    CardLayout card;Connection conn;Statement stmt,stmt2,stmt3,stmt4,stmt5;ResultSet rs,rs2,rs3,rs4,rs5; int id; int s;   int np; String bname;
      
        int r;
    public bank(String name,int id) throws SQLException,NullPointerException {
    
        initComponents();
        this.id=id;
        this.bname=name;
        this.name.setText(name);
        card=(CardLayout) mainpanel.getLayout();
        conn=c.conn;
        stmt=conn.createStatement();
         stmt4=conn.createStatement();
       npending();
        
       
    }
    
    public void npending() throws SQLException {
         rs=stmt.executeQuery("select count(pendingloanid)  as n from pendingloans where bankid="+id);
        rs.next();
        np=rs.getInt("n");
        if (rs.getInt("n")>0) {
             pending.setText("You have: "+rs.getInt("n")+" pending loans");
        }
        else {
           pending.setText(null);
        }
            
            }
    
    
    
    
    

    public void showpending() throws SQLException {
        stmt2=conn.createStatement();
        stmt3=conn.createStatement();
        ResultSet rs2=stmt2.executeQuery("select *  from pendingloans where bankid="+id);
        DefaultTableModel m=(DefaultTableModel) pendingtable.getModel();
        m.setRowCount(0);
        while(rs2.next()) {
          
            rs3=stmt3.executeQuery("select * from customers where cin="+rs2.getInt("customerid"));
            rs3.next();
             lid.add(rs2.getInt("pendingloanid"));
            m.addRow( new Object[] {rs3.getString("firstname")+" "+rs3.getString("lastname"),rs2.getInt("customerid"),rs2.getFloat("amount"),rs2.getDate("requestdate"),rs2.getInt("duration") }     );           
         }
                               
    }
    
    public void showregistered () throws SQLException {
      
       rs4=stmt4.executeQuery("select * from confirmedloans where bankid="+id);
       DefaultTableModel m2=(DefaultTableModel) registeredtable.getModel();
       m2.setNumRows(0);
       while (rs4.next()) {
            stmt5=conn.createStatement();
            rs5=stmt5.executeQuery(("select * from customers where cin="+rs4.getInt("customerid")));
           rs5.next();
           LocalDate requestdate=rs4.getDate("requestdate").toLocalDate();           
           LocalDate duedate=requestdate.plusMonths(rs4.getInt("duration")*12);
           
              int rm=    Math.max(((duedate.getYear()-LocalDate.now().getYear())*12   +  (duedate.getMonthValue()-LocalDate.now().getMonthValue())  ),  0); 
              System.out.println(rm);
              Float mp=  rs4.getFloat("amount") / (rs4.getInt("duration")*12) ; 
              Float ra=Math.max(rm*mp,0);
          
           m2.addRow(new Object[] {rs5.getString("firstname")+" "+rs5.getString("lastname"),rs5.getInt("cin"),rs4.getFloat("amount"),mp,rm,ra} );
          
                      }                
    }
    
    
    public void accept() throws SQLException {
        int r = pendingtable.getSelectedRow();
      TableModel  m = pendingtable.getModel();
      DefaultTableModel m2=(DefaultTableModel) pendingtable.getModel();
         Statement stmt6=conn.createStatement();
         Statement stmt7=conn.createStatement();
       Date date= (Date) m.getValueAt(r,3);
     
        int q1=stmt6.executeUpdate("insert into confirmedloans values(null,"+m.getValueAt(r,1)+","+id+","+m.getValueAt(r,2)+",'"+date.toLocalDate().format(DateTimeFormatter.ISO_DATE)+"',"+m.getValueAt(r,4)+")" );
        int q2=stmt7.executeUpdate("delete from pendingloans where pendingloanid="+lid.get(r));
        if ( q1==1 && q2==1  ) {
            JOptionPane.showMessageDialog(pendingloans, "Loan Accepted Sucessfully");
            lid.remove(r);
            
        } else { 
             JOptionPane.showMessageDialog(pendingloans, "Error");
        }
        
        
        
    }
    
    public void refuse () throws SQLException {
             TableModel m=pendingtable.getModel();
        int r=pendingtable.getSelectedRow();
         Statement stmt6=conn.createStatement();
         Statement stmt7=conn.createStatement();
       Date date= (Date) m.getValueAt(r,3);
        
         DefaultTableModel m2=(DefaultTableModel) pendingtable.getModel();
        int q=stmt7.executeUpdate("delete from pendingloans where pendingloanid="+lid.get(r));
        if ( q==1  ) {
            JOptionPane.showMessageDialog(pendingloans, "Loan Refused Sucessfully"); m2.setNumRows(m2.getRowCount());
            lid.remove(r);
        } else { 
        }
    }
    
    
    
    
    
    
    
    
    
    
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        name = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pending = new javax.swing.JLabel();
        mainpanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        pendingloans = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        pendingtable = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        registeredloans = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        registeredtable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setAutoRequestFocus(false);
        setFocusTraversalPolicyProvider(true);

        name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        name.setText("a");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Welcome");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Administrator");

        pending.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        mainpanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 923, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 496, Short.MAX_VALUE)
        );

        mainpanel.add(jPanel1, "card4");

        pendingtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer", "CIN", "Amount", "Request Date", "Duration"
            }
        ));
        jScrollPane3.setViewportView(pendingtable);

        jButton4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton4.setText("Accept Loan");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton5.setText("Refuse Loan");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pendingloansLayout = new javax.swing.GroupLayout(pendingloans);
        pendingloans.setLayout(pendingloansLayout);
        pendingloansLayout.setHorizontalGroup(
            pendingloansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendingloansLayout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(151, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pendingloansLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(91, 91, 91))
        );
        pendingloansLayout.setVerticalGroup(
            pendingloansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendingloansLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pendingloansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        mainpanel.add(pendingloans, "pendingloans");

        registeredtable.setAutoCreateRowSorter(true);
        registeredtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer", "CIN", "Amount", "Monthly Payment", "Remaining Months", "Remaining Amount"
            }
        ));
        registeredtable.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                registeredtableCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jScrollPane2.setViewportView(registeredtable);

        javax.swing.GroupLayout registeredloansLayout = new javax.swing.GroupLayout(registeredloans);
        registeredloans.setLayout(registeredloansLayout);
        registeredloansLayout.setHorizontalGroup(
            registeredloansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registeredloansLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );
        registeredloansLayout.setVerticalGroup(
            registeredloansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registeredloansLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 55, Short.MAX_VALUE))
        );

        mainpanel.add(registeredloans, "registeredloans");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Pending loans");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Registered loans");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton3.setText("Logout");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton6.setText("Update Account");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(843, 843, 843)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pending)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton6)
                                    .addComponent(jButton3))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 923, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton3, jButton6});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pending)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      card.show(mainpanel,"pendingloans");
        try {
            showpending();
        } catch (SQLException ex) {
            Logger.getLogger(bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
           
        try {
            card.show(mainpanel,"registeredloans");
            showregistered ();
        } catch (SQLException ex) {
            Logger.getLogger(bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
        login.main(null);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void registeredtableCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_registeredtableCaretPositionChanged
     
    }//GEN-LAST:event_registeredtableCaretPositionChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      
        if (np!=0) {
            
       
        
        try {
            accept();
            showpending();
            npending();
        } catch (SQLException ex) {
            Logger.getLogger(bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

         else {
            JOptionPane.showMessageDialog(mainpanel,"You have no pending loans",null,JOptionPane.ERROR_MESSAGE);
        }
    
    }
        
        
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        dispose();
        try {
            new updateaccount(id,bname).setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            refuse();
            showpending();
            npending();
        } catch (SQLException ex) {
            Logger.getLogger(bank.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }//GEN-LAST:event_jButton5ActionPerformed
       
   
    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JLabel name;
    private javax.swing.JLabel pending;
    private javax.swing.JPanel pendingloans;
    private javax.swing.JTable pendingtable;
    private javax.swing.JPanel registeredloans;
    private javax.swing.JTable registeredtable;
    // End of variables declaration//GEN-END:variables
}

