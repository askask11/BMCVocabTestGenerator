/*
 * Author: jianqing
 * Date: Jul 5, 2020
 * Description: This document is created for
 */
package bmcvocabtestgenerator;

import java.util.ArrayList;

/**
 *
 * @author jianqing
 */
public class WrongWordsJFrame extends javax.swing.JFrame
{

    /**
     * Creates new form WrongWordsJFrame
     */
    public WrongWordsJFrame()
    {
        initComponents();
        setVisible(true);
        postInit();
    }

    private void postInit()
    {
        refreshTable();
        jTable1.setComponentPopupMenu(tablePopupMenu);
    }

    private void refreshTable()
    {
       // ArrayList<WrongVocabulary> wordList;

        Object[][] tableData;//[row][col]

        final String[] TABLE_HEADER = new String[]
        {
            "#", "Term", "Translation", "Count"
        };

        try (DatabaseConnector dbConn = DatabaseConnector.getDefaultInstance())
        {

            //read from db
            wordList = dbConn.selectFromWrongWords();
            tableData = new Object[wordList.size()][4];
            //make 2d array
            for (int i = 0; i < wordList.size(); i++)
            {
                WrongVocabulary vocab = wordList.get(i);
                tableData[i][0] = (i + 1);
                tableData[i][1] = vocab.getTerm();
                tableData[i][2] = vocab.getTranslate();
                tableData[i][3] = vocab.getCount();
            }

            //TODO: CREATE TABLE MODEL
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                    tableData,//table data model
                    TABLE_HEADER
            )
            {
                Class[] types = new Class[]
                {
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
                };
                boolean[] canEdit = new boolean[]
                {
                    false, false, false, false
                };

                @Override
                public Class getColumnClass(int columnIndex)
                {
                    return types[columnIndex];
                }

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex)
                {
                    return canEdit[columnIndex];
                }
            });

            //resize to fit
            if (jTable1.getColumnModel().getColumnCount() > 0)
            {
                jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
                jTable1.getColumnModel().getColumn(3).setPreferredWidth(10);
            }
            
            messageField.setText("Wrong Vocab List Loaded.");
            
            
        } catch (Exception e)
        {
            Warning.createWarningDialog(e);
            messageField.setText("Error!");
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

        tablePopupMenu = new javax.swing.JPopupMenu();
        refreshMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        messageField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        refreshMenuItem.setText("Refresh");
        refreshMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                refreshMenuItemActionPerformed(evt);
            }
        });
        tablePopupMenu.add(refreshMenuItem);

        deleteMenuItem.setText("Delete");
        deleteMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                deleteMenuItemActionPerformed(evt);
            }
        });
        tablePopupMenu.add(deleteMenuItem);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Wrong Words");

        messageField.setEditable(false);
        messageField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        messageField.setText("Welcome, Please wait a moment...");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "#", "Term", "Translation", "Count"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean []
            {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0)
        {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(messageField)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(188, 188, 188))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(messageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_refreshMenuItemActionPerformed
    {//GEN-HEADEREND:event_refreshMenuItemActionPerformed
        // TODO add your handling code here:
        Thread thread = new Thread(()->{
            //loading message
            messageField.setText("Vocabulary Table Loading...");
            refreshMenuItem.setEnabled(false);
            //execution of code
            refreshTable();
            //done message
            messageField.setText("Vocabulary Table Loaded");
            refreshMenuItem.setEnabled(true);
        });
        thread.start();
    }//GEN-LAST:event_refreshMenuItemActionPerformed

    private void deleteMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteMenuItemActionPerformed
    {//GEN-HEADEREND:event_deleteMenuItemActionPerformed
        // TODO add your handling code here:
        runAnotherThread(()->{
            messageField.setText("Please wait... Vocabs are being deleted.");
            deleteFromWrongVocabulary();});
    }//GEN-LAST:event_deleteMenuItemActionPerformed

    
    
    
    private void deleteFromWrongVocabulary()
    {
        int[] rowSelected = jTable1.getSelectedRows();
        if(rowSelected.length == 0)
        {
            //no row selected
            messageField.setText("There is no row selected");
        }else
        {
            String[] terms = new String[rowSelected.length];
            
            for (int i = 0; i < rowSelected.length; i++)
            {
                terms[i] = wordList.get(rowSelected[i]).getTerm();
            }
           
            try(DatabaseConnector dbConn = DatabaseConnector.getDefaultInstance())
            {
                dbConn.deleteFromWrongWords(terms);
                messageField.setText(rowSelected.length + " row" + (rowSelected.length>1?"s":"") +" has been deleted from the table.");
                refreshTable();
            } catch (Exception e)
            {
                Warning.createWarningDialog(e);
            }
        }
    }
            
            
    private void runAnotherThread(Runnable rn)
    {
        Thread tr = new Thread(rn);
        tr.start();
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
            java.util.logging.Logger.getLogger(WrongWordsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(WrongWordsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(WrongWordsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(WrongWordsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() ->
        {
            new WrongWordsJFrame().setVisible(true);
        });
    }

    private ArrayList<WrongVocabulary> wordList = null;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField messageField;
    private javax.swing.JMenuItem refreshMenuItem;
    private javax.swing.JPopupMenu tablePopupMenu;
    // End of variables declaration//GEN-END:variables
}
