package com.mgiandia.library.ui.borrower;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.ui.DefaultJFrame;

@SuppressWarnings("serial")
public class BorrowerListJFrame extends DefaultJFrame implements BorrowerListView {
    private BorrowerListPresenter presenter;
    @SuppressWarnings("unused")
	private List<Borrower> borrowers;
    @SuppressWarnings("rawtypes")
	private DefaultListModel borrowerModel;
    
    private JPanel contentPane;
    private JList borrowerJList;
    private JButton btnAdd;
    private JButton btnRefresh;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BorrowerListJFrame frame = new BorrowerListJFrame();
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
	public BorrowerListJFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        JButton btnEdit = new JButton("Edit");
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                presenter.editSelected();
            }
        });
        
        borrowerModel = new DefaultListModel();
        borrowerJList = new JList();
        borrowerJList.setModel(borrowerModel);
        borrowerJList.setCellRenderer(new DefaultListCellRenderer() {

            public Component getListCellRendererComponent(JList list,
                    Object value, int index, boolean isSelected, boolean cellHasFocus) {
                
                Borrower borrower = (Borrower) value;
                String line = String.valueOf(borrower.getBorrowerNo()) 
                	+ " " + borrower.getLastName() 
                	+ " " + borrower.getFirstName();
                	
                return super.getListCellRendererComponent(list, 
                		line, 
                		index, isSelected, cellHasFocus);
            }
        });
        
        borrowerJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		presenter.addBorrower();
        	}
        });
        
        btnRefresh = new JButton("refresh");
        btnRefresh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		presenter.refresh();
        	}
        });
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(24)
        					.addComponent(btnAdd)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(btnEdit)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnRefresh))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(18)
        					.addComponent(borrowerJList, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(70, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(25)
        			.addComponent(borrowerJList, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnAdd)
        				.addComponent(btnEdit)
        				.addComponent(btnRefresh))
        			.addContainerGap(74, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }

    public void setPresenter(BorrowerListPresenter presenter) {
        this.presenter = presenter;
        
    }

    @Override
    public void setBorrowers(List<Borrower> borrowers) {
        this.borrowers = borrowers;
        borrowerModel.clear();
        for(Borrower borrower : borrowers) {
            borrowerModel.addElement(borrower);
        }
        borrowerJList.setModel(borrowerModel);
    }

    @Override
    public Borrower getSelectedBorrower() {
        return (Borrower) borrowerJList.getSelectedValue();
    }
}
