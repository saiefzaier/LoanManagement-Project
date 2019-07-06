
import com.mysql.cj.protocol.Resultset;
import java.awt.CardLayout;
import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class customer extends javax.swing.JFrame {
CardLayout card;  int id;   String name;
connection cn=new connection();
Connection conn;
Statement stmt,stmt2,stmt3,stmt4; 
ResultSet rs; ResultSet rs2,rs3;
int bankid; 
@SuppressWarnings("empty-statement")
    public customer(String name,int id) throws SQLException, NumberFormatException {
        initComponents();    
        this.id=id; 
        this.name=name;
        this.card =  (CardLayout) mainpanel.getLayout();
       
        user.setText(name);
        this.conn=cn.conn;
        this.stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        this.stmt2=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        this.stmt3=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        this.stmt4=conn.createStatement();
   
        rs=stmt.executeQuery("select * from banks");
        while (rs.next()) {
        banks.addItem(rs.getString("bankname"));                      
            System.out.println(LocalDate.now());
    }
        
      
        
       
  

    }
    
    
public void showpending() throws SQLException {
        stmt2=conn.createStatement();
        stmt3=conn.createStatement();
        ResultSet rs2=stmt2.executeQuery("select *  from pendingloans where customerid="+id);
        DefaultTableModel m=(DefaultTableModel) pendingtable.getModel();
        m.setRowCount(0);
        while(rs2.next()) {
          
            rs3=stmt3.executeQuery("select * from banks where bankid="+rs2.getInt("bankid"));
            rs3.next();
            
            m.addRow( new Object[] {rs3.getString("bankname"),rs2.getFloat("amount"),rs2.getDate("requestdate"),rs2.getInt("duration") }     );           
         }
                               
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void viewloans() throws SQLException
    {
       
  LocalDate localDate = LocalDate.now();
        rs=stmt.executeQuery("select * from confirmedloans where customerid="+id);
        DefaultTableModel m=(DefaultTableModel) loanstable.getModel();

        m.setNumRows(0);
        
         while (rs.next()) {
             rs2=stmt2.executeQuery("select * from banks where bankid="+rs.getInt("bankid"));
             rs2.next();
          
             String bankname=rs2.getString("bankname");     System.out.println(bankname);
              Double amount=rs.getDouble("amount");
System.out.println(amount);
             LocalDate requestdate=rs.getDate("requestdate").toLocalDate();   System.out.println(requestdate);
             int duration=rs.getInt("duration")*12;
             LocalDate duedate=requestdate.plusMonths(duration); System.out.println(duedate);
             int rm=     (( (duedate.getYear()-localDate.getYear())*12 )  +  (duedate.getMonthValue()-localDate.getMonthValue()))     ;   
             Double mp=  amount / duration ;  System.out.println(mp);
             Double ra=rm*mp;
             Object [] a=new Object[]{bankname,amount,mp,rm, ra};
            m.addRow(a);
                               }  
                     }
    
   public void set() throws NumberFormatException {
       Float i=parseFloat(interest.getText())/100;
       System.out.println(i);
       Float rq=parseFloat(requestedamount.getText());
       ai.setText(String.valueOf(rq*(1+i) ));
       mp.setText(   String.valueOf(  (rq*(1+i)) /  ((parseInt(duration.getSelectedItem().toString()))*12 ))           );                                  
   } ;
    

    public void confirm() throws SQLException, NumberFormatException {
       String query="insert into pendingloans values(null,"+id+","+bankid+","+parseFloat(ai.getText())+",'"+LocalDate.now().format(DateTimeFormatter.ISO_DATE)+"',"+parseInt(duration.getSelectedItem().toString())+")";
        System.out.println(query);
  
       if(stmt.executeUpdate(query)==1) {
           JOptionPane.showMessageDialog(this,"Loan Sucessfully requested");
       }
       
   }
   

   




  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        user = new javax.swing.JLabel();
        mainpanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        requestloan = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        banks = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        duration = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        interest = new javax.swing.JLabel();
        ai = new javax.swing.JLabel();
        requestedamount = new javax.swing.JTextField();
        mp = new javax.swing.JLabel();
        viewloans = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        loanstable = new javax.swing.JTable();
        pending = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pendingtable = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setAutoRequestFocus(false);
        setFocusTraversalPolicyProvider(true);
        setForeground(new java.awt.Color(153, 0, 102));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Request a loan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Confirmed Loans");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Welcome:");

        user.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        mainpanel.setAutoscrolls(true);
        mainpanel.setFocusCycleRoot(true);
        mainpanel.setFocusTraversalPolicyProvider(true);
        mainpanel.setInheritsPopupMenu(true);
        mainpanel.setNextFocusableComponent(requestloan);
        mainpanel.setRequestFocusEnabled(false);
        mainpanel.setVerifyInputWhenFocusTarget(false);
        mainpanel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mainpanelFocusGained(evt);
            }
        });
        mainpanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 829, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 581, Short.MAX_VALUE)
        );

        mainpanel.add(jPanel1, "card4");

        requestloan.setToolTipText("");
        requestloan.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        requestloan.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setText("Confirm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Requested amount");

        banks.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                banksItemStateChanged(evt);
            }
        });
        banks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                banksActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Bank");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Interest");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Amount After interest");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Monthly Payments");

        duration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        duration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                durationActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Duration");

        interest.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        ai.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        requestedamount.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                requestedamountCaretUpdate(evt);
            }
        });

        mp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout requestloanLayout = new javax.swing.GroupLayout(requestloan);
        requestloan.setLayout(requestloanLayout);
        requestloanLayout.setHorizontalGroup(
            requestloanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestloanLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(requestloanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addGap(37, 37, 37)
                .addGroup(requestloanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(requestloanLayout.createSequentialGroup()
                        .addGroup(requestloanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(duration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(interest)
                            .addComponent(requestedamount, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(requestloanLayout.createSequentialGroup()
                        .addComponent(banks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, requestloanLayout.createSequentialGroup()
                .addContainerGap(364, Short.MAX_VALUE)
                .addGroup(requestloanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, requestloanLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(ai, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, requestloanLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(41, 41, 41)
                        .addGroup(requestloanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addComponent(mp, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44))))
        );
        requestloanLayout.setVerticalGroup(
            requestloanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, requestloanLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(requestloanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(requestedamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(requestloanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(duration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(requestloanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(banks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(requestloanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(interest))
                .addGap(150, 150, 150)
                .addGroup(requestloanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(ai, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(requestloanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(mp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(166, 166, 166))
        );

        mainpanel.add(requestloan, "requestloan");
        requestloan.getAccessibleContext().setAccessibleName("");

        loanstable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bankname", "Amount", "Monthly Payment", "Remaining Months", "Remaining Amount"
            }
        ));
        jScrollPane1.setViewportView(loanstable);

        javax.swing.GroupLayout viewloansLayout = new javax.swing.GroupLayout(viewloans);
        viewloans.setLayout(viewloansLayout);
        viewloansLayout.setHorizontalGroup(
            viewloansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewloansLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
        );
        viewloansLayout.setVerticalGroup(
            viewloansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewloansLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainpanel.add(viewloans, "viewloans");

        pendingtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bank", "Amount", "Request Date", "Duration"
            }
        ));
        jScrollPane2.setViewportView(pendingtable);

        javax.swing.GroupLayout pendingLayout = new javax.swing.GroupLayout(pending);
        pending.setLayout(pendingLayout);
        pendingLayout.setHorizontalGroup(
            pendingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 829, Short.MAX_VALUE)
            .addGroup(pendingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pendingLayout.createSequentialGroup()
                    .addContainerGap(133, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(152, Short.MAX_VALUE)))
        );
        pendingLayout.setVerticalGroup(
            pendingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 581, Short.MAX_VALUE)
            .addGroup(pendingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pendingLayout.createSequentialGroup()
                    .addContainerGap(80, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(60, Short.MAX_VALUE)))
        );

        mainpanel.add(pending, "pendingloans");

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setText("Logout");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton5.setText("Pending Loans");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton6.setText("Change Credentials");
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
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(mainpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(user)
                .addGap(287, 287, 287)
                .addComponent(jButton4)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton4, jButton6});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(user))
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton4)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     card.show(mainpanel,"requestloan");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          
    
    try {
        card.show(mainpanel,"viewloans");
        viewloans();
    } catch (SQLException ex) {
        Logger.getLogger(customer.class.getName()).log(Level.SEVERE, null, ex);
    }
  
    }//GEN-LAST:event_jButton2ActionPerformed

    private void durationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_durationActionPerformed
     set();      
        
        
    }//GEN-LAST:event_durationActionPerformed

    private void mainpanelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mainpanelFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_mainpanelFocusGained

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    try {       
        confirm();
    } catch (SQLException ex) {
        Logger.getLogger(customer.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void requestedamountCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_requestedamountCaretUpdate
     
        try {
            set()   ; 
        }
       catch(NumberFormatException e) {
           
       }
    }//GEN-LAST:event_requestedamountCaretUpdate

    private void banksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_banksActionPerformed
     
        
             try {
         set()   ;
     }  catch(NumberFormatException e){
         System.out.println(e.getStackTrace());
     }
        
        
  
        
        
    }//GEN-LAST:event_banksActionPerformed

    private void banksItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_banksItemStateChanged
      
    try {
   
      rs3=stmt3.executeQuery("select * from banks where bankname='"+banks.getSelectedItem().toString()+"'");
        rs3.next();
        
        interest.setText(String.valueOf(rs3.getInt("interest")));
        bankid=rs3.getInt("bankid");
    } catch (SQLException ex) {
        Logger.getLogger(customer.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }//GEN-LAST:event_banksItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       dispose();
       login.main(null);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    try {
        card.show(mainpanel, "pendingloans");
        showpending();
    } catch (SQLException ex) {
        Logger.getLogger(customer.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    try {
        dispose();
        new updateaccount(id,name).setVisible(true);
    } catch (SQLException ex) {
        Logger.getLogger(customer.class.getName()).log(Level.SEVERE, null, ex);
    }


    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
         
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ai;
    private javax.swing.JComboBox<String> banks;
    private javax.swing.JComboBox<String> duration;
    private javax.swing.JLabel interest;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable loanstable;
    public javax.swing.JPanel mainpanel;
    private javax.swing.JLabel mp;
    private javax.swing.JPanel pending;
    private javax.swing.JTable pendingtable;
    private javax.swing.JTextField requestedamount;
    public javax.swing.JPanel requestloan;
    private javax.swing.JLabel user;
    public javax.swing.JPanel viewloans;
    // End of variables declaration//GEN-END:variables
}
