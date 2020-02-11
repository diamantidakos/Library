package com.mgiandia.library.ui.loan;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mgiandia.library.ui.DefaultJFrame;

@SuppressWarnings("serial")
public class LoanJFrame extends DefaultJFrame implements LoanView {

	private JPanel contentPane;
	private JTextField borrowerFirstName;
	private JTextField borrowerLastName;
	private JTextField itemNumber;
	private JTextField borrowerNo;
	private JTextField bookTitle;
	
    private LoanPresenter presenter;
	private JLabel borrowerNoLabel;
	private JLabel lastNameLabel;
	private JLabel firstNameLabel;
	private JLabel itemNoLabel;
	private JLabel titleLabel;
	private JButton searchItem;
	private JButton searchBorrower;
	private JButton loanButton;
	private JButton cancelButton; 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoanJFrame frame = new LoanJFrame();
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
	public LoanJFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		borrowerFirstName = new JTextField();
		
		borrowerLastName = new JTextField();
		
		itemNumber = new JTextField();
		
		borrowerNo = new JTextField();
		
		bookTitle = new JTextField();
		
		borrowerNoLabel = new JLabel();
		borrowerNoLabel.setText("Borrower No");
		
		lastNameLabel = new JLabel();
		lastNameLabel.setText("Last Name");
		
		firstNameLabel = new JLabel();
		firstNameLabel.setText("First Name");
		
		itemNoLabel = new JLabel();
		itemNoLabel.setText("Item Number");
		
		titleLabel = new JLabel();
		titleLabel.setText("Book Title");
		
		searchItem = new JButton();
		searchItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchItemActionPerformed(e);
			}
		});
		searchItem.setText("Search");
		
		searchBorrower = new JButton();
		searchBorrower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchBorrowerActionPerformed(e);
			}
		});
		searchBorrower.setText("Search");
		
		loanButton = new JButton();
		loanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loanButtonActionPerformed(e);
			}
		});
		loanButton.setText("OK");
		
		cancelButton = new JButton();
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButtonActionPerformed(e);
			}
		});
		cancelButton.setText("Cancel");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(borrowerNoLabel, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addGap(24)
							.addComponent(borrowerNo, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(searchBorrower, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lastNameLabel, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(borrowerLastName, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(firstNameLabel, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(borrowerFirstName, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(itemNoLabel, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addGap(14)
							.addComponent(itemNumber, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(searchItem))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addGap(14)
							.addComponent(bookTitle, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(154)
							.addComponent(loanButton, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(76, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addComponent(borrowerNoLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(borrowerNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(searchBorrower))
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lastNameLabel))
						.addComponent(borrowerLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(firstNameLabel))
						.addComponent(borrowerFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addComponent(itemNoLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(itemNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(searchItem))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(titleLabel))
						.addComponent(bookTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cancelButton)
						.addComponent(loanButton))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	   public int getBorrowerNo() {
	        return Integer.parseInt(borrowerNo.getText());       
	    }

	    public int getItemNumber() {
	        return Integer.parseInt(itemNumber.getText());
	    }

	    public boolean isLoanActionEnabled() {  	    	
	        return loanButton.isEnabled();
	    }

	    public void setLoanActionEnabled(boolean enabled) {
	        loanButton.setEnabled(enabled);        
	    }

	    public void setBookTitle(String name) {
	        bookTitle.setText(name);        
	    }

	    public void setBorrowerFirstName(String name) {
	        borrowerFirstName.setText(name);
	        
	    }

	    public void setBorrowerLastName(String name) {
	        borrowerLastName.setText(name);        
	    }


	    public void setPresenter(LoanPresenter presenter) {
	        this.presenter = presenter;
	        
	    }

	    
	    private void searchBorrowerActionPerformed(ActionEvent evt) {
	        presenter.findBorrower();
	    }
	    
	    private void searchItemActionPerformed(ActionEvent evt) {
	        presenter.findItem();    
	    }
	    
	    

	    private void loanButtonActionPerformed(ActionEvent evt) {
	        presenter.borrowItem();
	    }
	    
	    private void cancelButtonActionPerformed(ActionEvent evt) {
	        presenter.cancel();
	    }

	
}
