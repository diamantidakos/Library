package com.mgiandia.library.ui.borrower;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import com.mgiandia.library.ui.DefaultJFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BorrowerJFrame extends DefaultJFrame implements BorrowerView {

    private static final long serialVersionUID = 2986246662691677145L;

    private BorrowerPresenter presenter;
    
    private JPanel contentPane;
    private JTextField firstNameJText;
    private JTextField lastNameJText;
    private JTextField borrwerNoJText;
    private JLabel lblBorrowerNo;

	private JButton btnSave;

	private JButton btnCancel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BorrowerJFrame frame = new BorrowerJFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public BorrowerJFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        firstNameJText = new JTextField();
        firstNameJText.setColumns(10);
        
        lastNameJText = new JTextField();
        lastNameJText.setColumns(10);
        
        JLabel lblFirstName = new JLabel("First Name");
        
        JLabel lblLastName = new JLabel("Last Name");
        
        btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                presenter.save();
            }
        });
        
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                presenter.cancel();
            }
        });
        
        borrwerNoJText = new JTextField();
        borrwerNoJText.setColumns(10);
        
        lblBorrowerNo = new JLabel("Borrower No");
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addComponent(btnSave)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnCancel))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addComponent(lblBorrowerNo)
        					.addGap(18)
        					.addComponent(borrwerNoJText, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblFirstName)
        						.addComponent(lblLastName))
        					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(lastNameJText)
        						.addComponent(firstNameJText, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))))
        			.addGap(167))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(18)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(borrwerNoJText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblBorrowerNo))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(firstNameJText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblFirstName))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lastNameJText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblLastName))
        			.addPreferredGap(ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnSave)
        				.addComponent(btnCancel))
        			.addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
    }



    public void setPresenter(BorrowerPresenter presenter) {
        this.presenter = presenter;
        
    }


    public String getFirstName() {
        return firstNameJText.getText();
    }


    public void setFirstName(String firstName) {
        firstNameJText.setText(firstName);
    }


    public String getLastName() {
        return lastNameJText.getText();
    }


    public void setLastName(String lastName) {
        lastNameJText.setText(lastName);
    }


	public int getBorrowerNo() {
		return Integer.parseInt(borrwerNoJText.getText()); 
	}

	@Override
	public void setBorrowerNo(int borrowerNo) {
		borrwerNoJText.setText(String.valueOf(borrowerNo));
	}
	
	
	
	
}
