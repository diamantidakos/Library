package com.mgiandia.library.ui.loan;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.mgiandia.library.ui.DefaultJFrame;

import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ReturnJFrame extends DefaultJFrame  implements ReturnView  {
    private ReturnPresenter presenter;
	private JPanel contentPane;
	private JTextField itemNumberField;
	private JLabel itemNumberLabel;
	private JButton returnItemButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnJFrame frame = new ReturnJFrame();
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
	public ReturnJFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		itemNumberLabel = new JLabel();
		itemNumberLabel.setText("Item Number");
		
		itemNumberField = new JTextField();
		
		returnItemButton = new JButton();
		returnItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnItemButtonActionPerformed(e);
			}
		});
		returnItemButton.setText("OK");
		
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
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(itemNumberLabel, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(itemNumberField, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(116)
							.addComponent(returnItemButton, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(174, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(itemNumberLabel))
						.addComponent(itemNumberField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(58)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(returnItemButton)
						.addComponent(cancelButton))
					.addContainerGap(127, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}


    public int getItemNumber() {
        return Integer.parseInt(itemNumberField.getText());
    }

    public void setPresenter(ReturnPresenter presenter) {
        this.presenter = presenter;
        
    }
    
    private void returnItemButtonActionPerformed(ActionEvent evt) {
        presenter.returnItem();
    }
    
    private void cancelButtonActionPerformed(ActionEvent evt) {
        presenter.cancel();
    }
}
