/*
 * Author: jianqing
 * Date: Aug 3, 2020
 * Description: This document is created for
 */
package bmcvocabtestgenerator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author jianqing
 */
public class CheatingJFrame extends javax.swing.JFrame
{

    /**
     * Creates new form CheatingJFrame
     */
    public CheatingJFrame()
    {
        initComponents();
        postInit();
    }

    private void postInit()
    {
        new Thread(() ->
        {
            //connnect to do to retrieve words.
            try (DatabaseConnector dbConn = DatabaseConnector.getDefaultInstance())
            {
                vocabList = dbConn.selectFromVocabulary();
                messageField.setText("Welcome to cheat!");
                jButton1.setEnabled(true);
            } catch (Exception e)
            {
                Warning.createWarningDialog(e);
            }

        }).start();

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

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        messageField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                formKeyPressed(evt);
            }
        });

        jLabel1.setText("Cheating Tool Vocab Test");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                jTextField1KeyPressed(evt);
            }
        });

        jLabel2.setText("Enter Letter of words here");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel3.setText("Possible Results:");

        jButton1.setText("OK");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        messageField.setEditable(false);
        messageField.setText("Vocab List Loading...");

        jLabel4.setText("Advanced Cheating");

        jButton2.setBackground(new java.awt.Color(102, 204, 255));
        jButton2.setText("Select Excel File");
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("T");
        jButton3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(57, 57, 57))
            .addComponent(messageField)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel3))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(63, 63, 63)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(messageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        // TODO add your handling code here:
        searchWord(jTextField1.getText());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_formKeyPressed
    {//GEN-HEADEREND:event_formKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER && vocabList != null)
        {
            searchWord(jTextField1.getText());
        }
    }//GEN-LAST:event_formKeyPressed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextField1KeyPressed
    {//GEN-HEADEREND:event_jTextField1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER && vocabList != null)
        {
            searchWord(jTextField1.getText());
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try
        {
            advancedCheating();
        } catch (Exception e)
        {
            e.printStackTrace();
            Warning.createWarningDialog(e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton3ActionPerformed
    {//GEN-HEADEREND:event_jButton3ActionPerformed
        // TODO add your handling code here:
        advanced2Cheating();
    }//GEN-LAST:event_jButton3ActionPerformed

    public void advanced2Cheating()
    {
        try
        {
            messageField.setText("Please choose .xlsx file in a newly opened window. Support office 2007+");
            JFileChooser chooser = new JFileChooser("Desktop", FileSystemView.getFileSystemView());
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Office Excel Document", "xlsx");
            chooser.setDragEnabled(true);
            chooser.setMultiSelectionEnabled(false);
            chooser.setFileFilter(filter);
            int accept = chooser.showOpenDialog(this);
            if (accept == JFileChooser.APPROVE_OPTION)
            {
                messageField.setText("Please continue in a new window.");
                File file = chooser.getSelectedFile();
                ExcelTranslator translator = new ExcelTranslator(vocabList, file);
                translator.translateExcel(file.getParentFile(), "xlsmiaomiao"+RandomNumberGenerator.randomInt(1, 9999)+".xlsx");
            } else
            {
                messageField.setText("You cancelled file selection.");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This is the method for advanced cheating.
     *
     * @throws java.io.IOException
     */
    public void advancedCheating() throws IOException
    {
        messageField.setText("Please choose .xlsx file in a newly opened window. Support office 2007+");
        JFileChooser chooser = new JFileChooser("Desktop", FileSystemView.getFileSystemView());
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Office Excel Document", "xlsx");
        chooser.setDragEnabled(true);
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileFilter(filter);
        int accept = chooser.showOpenDialog(this);
        if (accept == JFileChooser.APPROVE_OPTION)
        {
            messageField.setText("Please continue in a new window.");
            File file = chooser.getSelectedFile();
            ExcelTranslator translator = new ExcelTranslator(vocabList, file);
            TestJFrame frame = new TestJFrame(translator.readFromExcel(true), true);
            frame.getViewAnswerButton().setVisible(false);
            frame.getjLabel1().setText("Answer of your BMC test");
            frame.setVisible(true);
        } else
        {
            messageField.setText("You cancelled file selection.");
        }

    }

    private void searchWord(String letter)
    {
        //clear panel first
        jTextArea1.setText("");

        //for the words
        String answer = "";
        for (int i = 0; i < vocabList.size(); i++)
        {
            Vocabulary vocabulary = vocabList.get(i);

            if (vocabulary.getTerm().startsWith(letter))
            {
                answer += vocabulary.getTerm() + " - " + vocabulary.getTranslate() + "; \n";
            }
        }

        jTextArea1.setText(answer);
        jTextField1.setText("");

        jTextArea1.setCaretPosition(0);
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
            java.util.logging.Logger.getLogger(CheatingJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(CheatingJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(CheatingJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(CheatingJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() ->
        {
            new CheatingJFrame().setVisible(true);
        });
    }

    private ArrayList<Vocabulary> vocabList;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField messageField;
    // End of variables declaration//GEN-END:variables
}
