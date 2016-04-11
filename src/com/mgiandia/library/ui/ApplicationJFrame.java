package com.mgiandia.library.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

//import com.mgiandia.library.dao.Initializer;
//import com.mgiandia.library.jpadao.JpaInitializer;
import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.memorydao.MemoryInitializer;
import com.mgiandia.library.ui.borrower.BorrowerListJFrame;
import com.mgiandia.library.ui.borrower.BorrowerListPresenter;
import com.mgiandia.library.ui.loan.LoanJFrame;
import com.mgiandia.library.ui.loan.LoanPresenter;
import com.mgiandia.library.ui.loan.ReturnJFrame;

import com.mgiandia.library.ui.loan.ReturnPresenter;

public class ApplicationJFrame {
    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        // Αρχικοποιούμε τη βάση δεδομένων 
        Initializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
 
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ApplicationJFrame window = new ApplicationJFrame();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public ApplicationJFrame() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JButton btnBorrowerList = new JButton("Borrowers");
        btnBorrowerList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                    	BorrowerListJFrame inst = new BorrowerListJFrame();
                        BorrowerListPresenter presenter = new BorrowerListPresenter(inst);
                        presenter.start();
                    }

                });
            }
        });
        
        JButton btnNewLoan = new JButton("New Loan");
        btnNewLoan.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		loanButtonActionPerformed(e);
        	}
        });
        
        JButton btnReturnItem = new JButton("Return Item");
        btnReturnItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		returnItemButtonActionPerformed(e);
        	}
        });

        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(21)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(btnReturnItem, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnNewLoan, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnBorrowerList, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addContainerGap(324, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
        			.addGap(27)
        			.addComponent(btnNewLoan)
        			.addGap(18)
        			.addComponent(btnReturnItem)
        			.addGap(54)
        			.addComponent(btnBorrowerList)
        			.addContainerGap(96, Short.MAX_VALUE))
        );
        frame.getContentPane().setLayout(groupLayout);
    }
    
    
    private void loanButtonActionPerformed(ActionEvent evt) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoanJFrame inst = new LoanJFrame();
                inst.setLocationRelativeTo(null);
                LoanPresenter presenter = new LoanPresenter(inst);
                presenter.start();
            }
        });
    }
    
    private void returnItemButtonActionPerformed(ActionEvent evt) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ReturnJFrame inst = new ReturnJFrame();
                inst.setLocationRelativeTo(null);
                ReturnPresenter presenter = new ReturnPresenter(inst);
                presenter.start();
            }
        });
    }

}
