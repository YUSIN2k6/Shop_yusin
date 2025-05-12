package admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import ITEM.category;
import dao.CategoryDAO;


import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Changeshirt extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfname;
	private JTextField tfpricee;
	private JTextField tfid;
	private JTextField search;
	int selectedRow;
	CategoryDAO s;
	Color selectionColor = new Color(34,48,62); 
	Color sideColor = new Color(255, 33, 33);
	Color textSelectionColor = new Color(255,255,255);
	Color backsize = new Color(102,102,102);
	Color black = new Color(0,0,0);
	Color textPrimaryColor = new Color (0, 0, 0);
	Color primaryColor = new Color (102,102,102);
	private JTextField tfqty;
	private JTable table;
	StatisticsDAO s2;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Changeshirt frame = new Changeshirt();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void LoadData() {
		ArrayList<category> list = s.loadcategory();

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); 
		
		for (category e : list) {
			model.addRow(new Object[]{
				e.getId(),
				e.getName(),
				e.getPrice(),
				e.getQty()
			
			});
		}
	}
	private void init () {
		s2.getInstance();
		this.s = CategoryDAO.getInstance();
		LoadData();
		
	}
	private boolean isEmpty1() {
		if (tfid.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập id áo", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			return false;
		} if (tfname.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên áo", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			return false;
		} if (tfpricee.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập giá áo", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			return false;
		} if (tfqty.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng áo", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			return false;
		} 
		return true;
	}
	private boolean isEmpty2() {
		 if (tfname.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên áo", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			return false;
		} if (tfpricee.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập giá áo", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			return false;
		} if (tfqty.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng áo", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			return false;
		} 
		return true;
	}
	private void clear() {
		tfid.setText("");
		tfname.setText("");
		tfpricee.setText("");
		tfqty.setText("");
	}
	public Changeshirt() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Changeshirt.class.getResource("/icons/aaa (2).png")));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 990, 605);
		setLocation(360,105);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Tên Áo:");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel_4.setBounds(10, 76, 77, 25);
		contentPane.add(lblNewLabel_4);
		
		tfname = new JTextField();
		tfname.setFont(new Font("Segoe UI", Font.BOLD, 22));
		tfname.setColumns(10);
		tfname.setBounds(10, 108, 321, 31);
		contentPane.add(tfname);
		
		JLabel tfprice = new JLabel("Giá áo:");
		tfprice.setForeground(Color.WHITE);
		tfprice.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tfprice.setBounds(10, 152, 79, 25);
		contentPane.add(tfprice);
		
		tfpricee = new JTextField();
		tfpricee.setFont(new Font("Segoe UI", Font.BOLD, 22));
		tfpricee.setColumns(10);
		tfpricee.setBounds(10, 190, 321, 31);
		contentPane.add(tfpricee);
		
		JLabel lblNewLabel_2 = new JLabel("Kho");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel_2.setBounds(346, -14, 279, 52);
		contentPane.add(lblNewLabel_2);
		JLabel lblNewLabel_4_1 = new JLabel("ID Áo:");
		lblNewLabel_4_1.setForeground(Color.WHITE);
		lblNewLabel_4_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel_4_1.setBounds(10, 0, 77, 25);
		contentPane.add(lblNewLabel_4_1);
		
		tfid = new JTextField();
		tfid.setFont(new Font("Segoe UI", Font.BOLD, 22));
		tfid.setColumns(10);
		tfid.setBounds(11, 26, 321, 31);
		contentPane.add(tfid);
		
		JButton btnSa = new JButton("Sửa");
		btnSa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isEmpty2()) {
					int id = Integer.parseInt(tfid.getText());
					String name = tfname.getText();
					int price = Integer.parseInt(tfpricee.getText());
					int qty = Integer.parseInt(tfqty.getText());
					s.updateProduct(id, name, price, qty);
					
					LoadData();
					clear();
				}
			}
		});
		btnSa.setForeground(new Color(51, 51, 51));
		btnSa.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnSa.setBackground(Color.WHITE);
		btnSa.setBounds(20, 394, 300, 38);
		contentPane.add(btnSa);
		
		JButton btnDnDp = new JButton("Dọn dẹp");
		btnDnDp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnDnDp.setForeground(new Color(51, 51, 51));
		btnDnDp.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnDnDp.setBackground(Color.WHITE);
		btnDnDp.setBounds(20, 494, 300, 38);
		contentPane.add(btnDnDp);
		
		JButton btnThot = new JButton("Thoát");
		btnThot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AdminDashboards.panel_4_3.setBackground(primaryColor);
				AdminDashboards.panel_5_4.setBackground(primaryColor);
				AdminDashboards.menu4.setForeground(textPrimaryColor);
				AdminDashboards.dark4.setVisible(true);
				AdminDashboards.Light4.setVisible(false);
			}
		});
		btnThot.setForeground(new Color(51, 51, 51));
		btnThot.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnThot.setBackground(Color.WHITE);
		btnThot.setBounds(20, 544, 300, 38);
		contentPane.add(btnThot);
		
		search = new JTextField();
		search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String searcher = search.getText();
				s.getcategoryValue(table, searcher);
			}
		});
		search.setFont(new Font("Segoe UI", Font.BOLD, 22));
		search.setColumns(10);
		search.setBounds(740, 0, 240, 31);
		contentPane.add(search);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Tìm:");
		lblNewLabel_4_1_1.setIcon(new ImageIcon(Changeshirt.class.getResource("/icons/search_Light.png")));
		lblNewLabel_4_1_1.setForeground(Color.WHITE);
		lblNewLabel_4_1_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_4_1_1.setBounds(637, 1, 93, 25);
		contentPane.add(lblNewLabel_4_1_1);
		
		JLabel lblSLng = new JLabel("Số lượng");
		lblSLng.setForeground(Color.WHITE);
		lblSLng.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblSLng.setBounds(10, 231, 108, 25);
		contentPane.add(lblSLng);
		
		tfqty = new JTextField();
		tfqty.setFont(new Font("Segoe UI", Font.BOLD, 22));
		tfqty.setColumns(10);
		tfqty.setBounds(10, 269, 321, 31);
		contentPane.add(tfqty);
		
		JButton btnThm = new JButton("Thêm");
		btnThm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isEmpty2()) {
					int id = 1;
					String name = tfname.getText();
					int price = Integer.parseInt(tfpricee.getText());
					int qty = Integer.parseInt(tfqty.getText());
					category c = new category(id, name, price, qty);
					s.insertProduct(c);
					LoadData();
					clear();
				}
			}
		});
		btnThm.setForeground(new Color(51, 51, 51));
		btnThm.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnThm.setBackground(Color.WHITE);
		btnThm.setBounds(20, 344, 300, 38);
		contentPane.add(btnThm);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(367, 40, 613, 542);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Id \u00E1o", "T\u00EAn \u00E1o ", "Gi\u00E1 \u00E1o", "S\u1ED1 l\u01B0\u1EE3ng"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(260);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.setRowHeight(40);
		table.setFont(new Font("Segoe UI", Font.BOLD, 20));
		scrollPane.setViewportView(table);
		
		JButton btndelete = new JButton("Xoá");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(tfid.getText());
				if(tfid.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập id áo", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
				} else {
					s.delete(id);
					LoadData();
					clear();
				}
				
			}
		});
		btndelete.setForeground(new Color(51, 51, 51));
		btndelete.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btndelete.setBackground(Color.WHITE);
		btndelete.setBounds(20, 444, 300, 38);
		contentPane.add(btndelete);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                    	Object idObj = table.getValueAt(selectedRow, 0);
                    	tfid.setText(idObj != null ? idObj.toString() : "");

                    	Object nameObj = table.getValueAt(selectedRow, 1);
                    	tfname.setText(nameObj != null ? nameObj.toString() : "");
                    	
                    	Object priceObj = table.getValueAt(selectedRow, 2);
                    	tfpricee.setText(priceObj != null ? priceObj.toString() : "");
                    	Object qtyObj = table.getValueAt(selectedRow, 3);
                    	tfqty.setText(qtyObj != null ? qtyObj.toString() : "");
      
                    }
                }
            }
        });
		init();
	}
}
