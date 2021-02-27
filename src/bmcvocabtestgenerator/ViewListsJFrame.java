/*
 * Author: jianqing
 * Date: Jun 22, 2020
 * Description: This document is created for
 */
package bmcvocabtestgenerator;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jianqing
 */
public class ViewListsJFrame extends javax.swing.JFrame
{

    /**
     * Creates new form ViewListsJFrame
     */
    public ViewListsJFrame()
    {
        initComponents();
        postInit();
    }

    /**
     * Code after init process.
     */
    private void postInit()
    {

        //JPopupMenu vocabTablePopupMenu = new JPopupMenu();
        loadLists();

        initVocabListPopup();
    }

    private void initVocabListPopup()
    {
        JPopupMenu listPopup = new JPopupMenu();
        JMenuItem showVocabMenuItem = new JMenuItem("Show Vocab"),
                deleteListMenuItem = new JMenuItem("Delete"),
                refreshListMenuItem = new JMenuItem("Refresh");

        //show vocab list code
        showVocabMenuItem.addActionListener((e) ->
        {
            int rowSelected = jList1.getSelectedIndex();
            if (rowSelected == -1)
            {
                JOptionPane.showMessageDialog(this, "Please select a list to see the vocab!");
            } else
            {
                int listId = Integer.parseInt(jList1.getSelectedValue());

                Thread loadingThread = new Thread(() ->
                {
                    messageField.setText("Loading vocabulary list #" + listId + "...");
                    loadVocabTable(listId);
                    messageField.setText("Vocabulary List Loaded.");
                });
                loadingThread.start();
            }
        });

        //delete list from db code
        deleteListMenuItem.addActionListener((e) ->
        {

            int rowSelected = jList1.getSelectedIndex();
            if (rowSelected == -1)
            {
                JOptionPane.showMessageDialog(this, "Please select a list to delete!");
            } else
            {
                int listId = Integer.parseInt(jList1.getSelectedValue());
                int agree = JOptionPane.showConfirmDialog(this, "Are you sure to delete list#" + listId + "?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
                if (agree == JOptionPane.YES_OPTION)
                {
                    messageField.setText("Deleting.");
                    Thread deleteThread = new Thread(() ->
                    {
                        deleteList(listId);
                    });
                    deleteThread.start();
                } else
                {
                    messageField.setText("Operation canceled.");
                }
            }

        });

        //set refresh list action
        refreshListMenuItem.addActionListener((e) ->
        {
            Thread tr = new Thread(() ->
            {
                messageField.setText("Loading Lists...");
                loadLists();
                messageField.setText("List refreshed.");
            });
            
            tr.start();
        });

        //add list popup to list
        listPopup.add(showVocabMenuItem);
        listPopup.add(deleteListMenuItem);
        listPopup.add(refreshListMenuItem);
        this.jList1.setComponentPopupMenu(listPopup);
    }

    private void deleteList(int listId)
    {
        try (DatabaseConnector dbConn = DatabaseConnector.getDefaultInstance())
        {
            int rows = dbConn.deleteFromVocabTableByListId(listId);
            messageField.setText(rows + "row" + (rows > 1 ? "s" : "") + " has been deleted from vocab storage.");
            loadLists();
        } catch (Exception ex)
        {
            messageField.setText("An error has occured.");
            Warning.createWarningDialog(ex);
        }
    }

    private void loadVocabTable(int listId)
    {
        List<Vocabulary> vocabularys;

        try (DatabaseConnector dbConn = DatabaseConnector.getDefaultInstance())
        {
            //read data from database
            vocabularys = dbConn.selectFromVocabulary(new VocabRange(Integer.toString(listId), 110));
            //tableData[row][column]
            //load vocab table
            Object[][] tableData = new Object[vocabularys.size()][VOCABTABLE_HEADER.length];
            for (int i = 0; i < vocabularys.size(); i++)
            {
                Vocabulary get = vocabularys.get(i);
                tableData[i][0] = i + 1;
                tableData[i][1] = get.getTerm();
                tableData[i][2] = get.getTranslate();

            }

            //create table model and put data inside
            DefaultTableModel model = new DefaultTableModel(tableData, VOCABTABLE_HEADER);

            //set the model
            jTable2.setModel(model);

        } catch (Exception e)
        {
            //warning
            Warning.createWarningDialog(e);
            messageField.setText("An error has occured.");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        messageField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("View My Lists");

        jScrollPane3.setViewportView(jList1);

        jButton1.setBackground(new java.awt.Color(102, 255, 51));
        jButton1.setText("+ Create");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jLabel1)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel2.setText("List Operations");

        jLabel3.setText("Vocabulary In List");

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String []
            {
                "#", "Term", "Translate"
            }
        ));
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0)
        {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(50);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        messageField.setEditable(false);
        messageField.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        messageField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        messageField.setText("Welcome,please Right click on your list to view vocab.");
        messageField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                messageFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(169, 169, 169)
                                .addComponent(jLabel2)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(messageField, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void messageFieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_messageFieldActionPerformed
    {//GEN-HEADEREND:event_messageFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_messageFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new CreateNewListJFrame().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void loadLists()
    {
        ArrayList<String> dbData;
        String[] listData;
        try (DatabaseConnector dbConn = DatabaseConnector.getDefaultInstance())
        {
            //get the distinct number list from db
            dbData = dbConn.selectFromVocabTableDisListId();
            //parse the list to an array.
            listData = new String[dbData.size()];
            for (int i = 0; i < dbData.size(); i++)
            {
                String get = dbData.get(i);
                listData[i] = get;
            }

            //load the array to the JList
            jList1.setListData(listData);

        } catch (Exception e)
        {
            Warning.createWarningDialog(e);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(ViewListsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(ViewListsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(ViewListsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(ViewListsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new ViewListsJFrame().setVisible(true);
            }
        });
    }

    final String[] VOCABTABLE_HEADER =
    {
        "#", "Term", "Translate"
    };
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField messageField;
    // End of variables declaration//GEN-END:variables

}
