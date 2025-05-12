package user;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ITEM.Datamanager;

import ITEM.category;
import ITEM.detailitem;
import ITEM.infobuy;
import ITEM.item;
import ITEM.temp;
import dao.CategoryDAO;
import dao.DetailDAO;
import dao.PurchaseDetailDAO;

import java.awt.Panel;
import java.awt.Color;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import java.awt.Scrollbar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.ImageIcon;
import java.awt.Label;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Toolkit;
import javax.swing.ScrollPaneConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

public class Purchase extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private long totalPrice = 0;
	private boolean isTotalCalculated = false;
	static String ridpString;
	PurchaseDetail p = new PurchaseDetail();
	Color textPrimaryColor = new Color(0, 0, 0);
	Color primaryColor = new Color(102, 102, 102);
	PurchaseDetailDAO s2;
	private JTextField tfidhd;
	private JTextField tfqty;
	private JTextField tftotalprice;
	private JTable table1;
	private JTable table2;
	CategoryDAO s;
	DetailDAO s3;
	private JLabel lbchek;
	private JLabel lbname;
	private JLabel lbprice;
	private JLabel lbqty;
	private JLabel lbid;
	private int totalprice = 0;
	private JLabel lbhour;
	private JLabel lbdate;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Purchase frame = new Purchase();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void setDefault() {
		UserDashboard.panel_4.setBackground(primaryColor);
		UserDashboard.panel_5.setBackground(primaryColor);
		UserDashboard.menu1.setForeground(textPrimaryColor);
		UserDashboard.dark1.setVisible(true);
		UserDashboard.light1.setVisible(false);
	}

	public void setTime() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Date date = new Date();
					SimpleDateFormat tf = new SimpleDateFormat("h:mm:ss aa");
					SimpleDateFormat df = new SimpleDateFormat("EEEE, yyyy-MM-dd");
					String time = tf.format(date);
					lbhour.setText(time.split(" ")[0] + " " + time.split(" ")[1]);
					lbdate.setText(df.format(date));
				}
			}
		}).start();
		;
	}

	private void generateIDHDIfNeeded() {
		String currentIDHD = s2.idhd();
		if ((currentIDHD == null || currentIDHD.isEmpty()) && table2.getRowCount() > 0) {
			Random rand = new Random();
			int number = rand.nextInt(100000000);
			String numberStr = String.format("%08d", number);
			s2.addhd(numberStr);
			tfidhd.setText(numberStr);
		} else if (table2.getRowCount() == 0 && currentIDHD != null) {
			s2.deletehd(currentIDHD);
			tfidhd.setText("########");
		} else {
			tfidhd.setText(currentIDHD != null ? currentIDHD : "########");
		}
	}

	public void LoadData1() {
		ArrayList<category> list = s.loadcategory();

		DefaultTableModel model = (DefaultTableModel) table1.getModel();
		model.setRowCount(0);

		for (category e : list) {
			model.addRow(new Object[] { e.getName(), e.getQty(), e.getPrice()

			});
		}
	}

	public void LoadData2() {
		ArrayList<temp> list = s2.loadbuytemp();

		DefaultTableModel model = (DefaultTableModel) table2.getModel();
		model.setRowCount(0);

		for (temp e : list) {
			model.addRow(new Object[] { e.getName(), e.getQty(), e.getTotalprice()

			});
		}
	}

	private void init() {
		this.s = s.getInstance();
		this.s2 = PurchaseDetailDAO.getInstance();
		this.s3 = s3.getInstance();
		LoadData1();
		LoadData2();
		addListeners();
		generateIDHDIfNeeded();
	}

	private void addListeners() {
		table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = table1.getSelectedRow();
					if (selectedRow != -1) {
						table2.clearSelection();

						Object nameObj = table1.getValueAt(selectedRow, 0);
						lbname.setText(nameObj != null ? nameObj.toString() : "");
						lbid.setText(String.valueOf(s2.getidatCategory(lbname.getText())));
						Object qtyObj = table1.getValueAt(selectedRow, 1);
						lbqty.setText(qtyObj != null ? qtyObj.toString() : "");
						Object priceObj = table1.getValueAt(selectedRow, 2);
						lbprice.setText(priceObj != null ? priceObj.toString() : "");
						lbchek.setText("c1");
					}
				}
			}
		});

		table2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = table2.getSelectedRow();
					if (selectedRow != -1) {
						table1.clearSelection();

						Object nameObj = table2.getValueAt(selectedRow, 0);
						lbname.setText(nameObj != null ? nameObj.toString() : "");
						lbid.setText(String.valueOf(s2.getidatTemp(lbname.getText())));
						Object qtyObj = table2.getValueAt(selectedRow, 1);
						lbqty.setText(qtyObj != null ? qtyObj.toString() : "");
						String priceObj = String.valueOf(table2.getValueAt(selectedRow, 2));
						int price = Integer.parseInt(priceObj) / Integer.parseInt(lbqty.getText());
						lbprice.setText(String.valueOf(price));
						lbchek.setText("c2");
					}
				}
			}
		});

	}

	public Purchase() {
    	addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowOpened(WindowEvent e) {
    			new Thread(() -> {
		            for (double i = 0.1; i <= 1.0; i += 0.1) {
		                float f = (float) i;
		                Purchase.this.setOpacity(f); 
		                try {
		                    Thread.sleep(40);
		                } catch (InterruptedException e1) {
		                    e1.printStackTrace();
		                }
		            }
		        }).start();
    		}
    	});
    	setIconImage(Toolkit.getDefaultToolkit().getImage(Purchase.class.getResource("/icons/LOGO.png")));
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 100, 990, 605);
		setLocation(360,105);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        Panel panel = new Panel();
        panel.setBackground(new Color(51, 51, 51));
        panel.setBounds(0, 0, 990, 605);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel_12 = new JLabel("x");
        lblNewLabel_12.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		setVisible(false);
				setDefault();
        	}
        });
        lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_12.setForeground(Color.WHITE);
        lblNewLabel_12.setFont(new Font("Segoe UI", Font.BOLD, 35));
        lblNewLabel_12.setBounds(948, 0, 42, 47);
        panel.add(lblNewLabel_12);
        
        JButton btnbuyp = new JButton("Thanh toán");
        btnbuyp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(table2.getRowCount() < 0) {
        			JOptionPane.showMessageDialog(null, "Bạn chưa thể thanh toán được. Yêu cầu lựa chọn áo vào giỏ hàng", "Cảnh báo",
    	                    JOptionPane.WARNING_MESSAGE);
        		}  else if(s3.gettabledetail()>0) {
        		JOptionPane.showMessageDialog(null, "Bạn chưa thể thanh toán được. Yêu cầu xác nhận hoá đơn trước", "Cảnh báo",
	                    JOptionPane.WARNING_MESSAGE);}
        		else {
        			
        			ArrayList<temp> tempList = s2.loadbuytemp();
                    for (temp tempItem : tempList) {
                    	int id = s3.getMaxrowDetail();
                        String name = tempItem.getName();
                        int qtyTemp = tempItem.getQty();
                        int totalpricetemp = tempItem.getTotalprice();
                        int price = totalpricetemp/qtyTemp;
                        
                        s3.insertdetail(id, name, qtyTemp, price, totalpricetemp);
                    }
                    String idhd = tfidhd.getText();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String currentDateStr = sdf.format(new Date());
                    java.sql.Date sqlDate = java.sql.Date.valueOf(currentDateStr);
                    infobuy i = new infobuy(1, idhd, totalprice, lbhour.getText(), sqlDate);
                    // Cập nhật thông tin vào cơ sở dữ liệu
                    s3.updateinfo(i);
                    // Xóa dữ liệu tạm và làm mới giao diện
                    s2.Deletealltemp();
                    totalprice = 0;
                    tftotalprice.setText("0");
                    LoadData1();
                    LoadData2();
                    generateIDHDIfNeeded();
        		}
        	}
        });
        btnbuyp.setForeground(new Color(52, 219, 4));
        btnbuyp.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnbuyp.setBackground(Color.WHITE);
        btnbuyp.setBounds(5, 544, 160, 50);
        panel.add(btnbuyp);
        
        JButton btnresetp = new JButton("Làm mới");
        btnresetp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lbname.setText("");
                lbqty.setText("");
                lbprice.setText("");
                lbid.setText("");

                ArrayList<temp> tempList = s2.loadbuytemp();
                for (temp tempItem : tempList) {
                    String name = tempItem.getName();
                    int qtyTemp = tempItem.getQty();
                    int qtyCategory = s2.getqtyatCategory(name);
                    s.updateProduct2(name, qtyCategory + qtyTemp);
                }

                s2.Deletealltemp();
                totalprice = 0;
                tftotalprice.setText("0");
                LoadData1();
                LoadData2();
                generateIDHDIfNeeded();
            }
        });
        btnresetp.setForeground(new Color(202, 202, 0));
        btnresetp.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnresetp.setBackground(Color.WHITE);
        btnresetp.setBounds(175, 544, 164, 50);
        panel.add(btnresetp);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(51, 51, 51));
        panel_1.setBorder(new LineBorder(new Color(255, 255, 255), 4));
        panel_1.setBounds(349, 86, 290, 508);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel_1_2_2_2 = new JLabel("Mã hoá đơn");
        lblNewLabel_1_2_2_2.setBounds(10, 11, 269, 41);
        panel_1.add(lblNewLabel_1_2_2_2);
        lblNewLabel_1_2_2_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_2_2_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_1_2_2_2.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel_1_2_2_2.setBackground(Color.BLACK);
        
        tfidhd = new JTextField();
        tfidhd.setBounds(10, 74, 269, 42);
        panel_1.add(tfidhd);
        tfidhd.setText("########");
        tfidhd.setHorizontalAlignment(SwingConstants.CENTER);
        tfidhd.setFont(new Font("Segoe UI", Font.BOLD, 26));
        tfidhd.setColumns(10);
        
        JLabel lblNewLabel_1_2_2_2_1 = new JLabel("Số lượng");
        lblNewLabel_1_2_2_2_1.setBounds(12, 127, 269, 41);
        panel_1.add(lblNewLabel_1_2_2_2_1);
        lblNewLabel_1_2_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_2_2_2_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1_2_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel_1_2_2_2_1.setBackground(Color.BLACK);
        
        JLabel lbadd = new JLabel("+");
        lbadd.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		try {
					int current = Integer.parseInt(tfqty.getText());
					if (current < 999) {
						current++;
					}
					tfqty.setText(String.valueOf(current));
				} catch (NumberFormatException ex) {
					tfqty.setText("0");
				}
        	}
        });
        lbadd.setBounds(13, 179, 80, 80);
        panel_1.add(lbadd);
        lbadd.setVerticalAlignment(SwingConstants.BOTTOM);
        lbadd.setHorizontalTextPosition(SwingConstants.LEADING);
        lbadd.setHorizontalAlignment(SwingConstants.CENTER);
        lbadd.setForeground(new Color(255, 255, 255));
        lbadd.setFont(new Font("Segoe UI", Font.BOLD, 60));
        lbadd.setBackground(new Color(80, 118, 135));
        
        tfqty = new JTextField();
        tfqty.setBounds(98, 193, 96, 50);
        panel_1.add(tfqty);
        tfqty.setText("0");
        tfqty.setHorizontalAlignment(SwingConstants.CENTER);
        tfqty.setFont(new Font("Segoe UI", Font.BOLD, 25));
        tfqty.setColumns(10);
        tfqty.setAlignmentX(0.0f);
        
        JLabel lbsubtract = new JLabel("-");
        lbsubtract.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int current = Integer.parseInt(tfqty.getText());
				if (current > 0) {
					current--;
				}
				tfqty.setText(String.valueOf(current));
			
        	}
        });
        lbsubtract.setBounds(201, 179, 80, 80);
        panel_1.add(lbsubtract);
        lbsubtract.setVerticalTextPosition(SwingConstants.TOP);
        lbsubtract.setVerticalAlignment(SwingConstants.BOTTOM);
        lbsubtract.setHorizontalTextPosition(SwingConstants.LEADING);
        lbsubtract.setHorizontalAlignment(SwingConstants.CENTER);
        lbsubtract.setForeground(new Color(255, 255, 255));
        lbsubtract.setFont(new Font("Segoe UI", Font.BOLD, 60));
        lbsubtract.setBackground(new Color(80, 118, 135));
        
        JButton btnaddintemp = new JButton("Thêm");
        btnaddintemp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (lbchek.equals("c2") || lbchek.getText().isEmpty()) {
        			JOptionPane.showMessageDialog(null, "Bạn chưa thể thêm được. Yêu cầu lựa chọn áo vào giỏ hàng", "Cảnh báo",
    	                    JOptionPane.WARNING_MESSAGE);
        		} 
        		else {
        			int qtytrade = Integer.parseInt(tfqty.getText());
        			int qtychooser = Integer.parseInt(lbqty.getText());
        			if(qtytrade == 0) {
        				JOptionPane.showMessageDialog(null, "Bạn chưa thể thêm được. Yêu cầu nhập số lượng áo vào giỏ hàng", "Cảnh báo",
        	                    JOptionPane.WARNING_MESSAGE);
        			} else if (qtytrade > qtychooser) {
        				JOptionPane.showMessageDialog(null, "Bạn chưa thể thêm được. Yêu cầu nhập số lượng áo bé hơn số lượng trong kho", "Cảnh báo",
        	                    JOptionPane.WARNING_MESSAGE);
        			} else {
        				int max = s2.getMaxrow();
        				int price = Integer.parseInt(lbprice.getText());
        				String name = lbname.getText();
        				int qtytemp = s2.getqtyatTemp(name);
        				int id = Integer.parseInt(lbid.getText());
        				if(qtytemp>0) {
        					s2.updatetemp(name, qtytemp+qtytrade, price);
        				} else {
        					s2.inserttemp(id, name, price, qtytrade, price*qtytrade);
        				}
        				s.updateProduct2(name, qtychooser-qtytrade);
        				totalprice += price*qtytrade; 
        				tftotalprice.setText(String.valueOf(totalprice));
        				LoadData1();
        				LoadData2();
        				generateIDHDIfNeeded();
        			}
        		}
        	}
        });
        btnaddintemp.setBounds(67, 270, 153, 50);
        panel_1.add(btnaddintemp);
        btnaddintemp.setForeground(new Color(0, 0, 0));
        btnaddintemp.setFont(new Font("Segoe UI", Font.BOLD, 30));
        btnaddintemp.setBackground(Color.WHITE);
        
        JLabel lblNewLabel_1_2_2 = new JLabel("Tổng số tiền");
        lblNewLabel_1_2_2.setBounds(10, 403, 269, 41);
        panel_1.add(lblNewLabel_1_2_2);
        lblNewLabel_1_2_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_2_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel_1_2_2.setBackground(Color.BLACK);
        
        tftotalprice = new JTextField();
        tftotalprice.setBounds(10, 455, 269, 42);
        panel_1.add(tftotalprice);
        tftotalprice.setText("0");
        tftotalprice.setHorizontalAlignment(SwingConstants.CENTER);
        tftotalprice.setFont(new Font("Segoe UI", Font.BOLD, 30));
        tftotalprice.setColumns(10);
        
        JButton btndeleteitemp = new JButton("Xoá");
        btndeleteitemp.setBounds(67, 341, 153, 50);
        panel_1.add(btndeleteitemp);
        btndeleteitemp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (lbchek.equals("c1") || lbchek.getText().isEmpty()) {
        			JOptionPane.showMessageDialog(null, "Bạn chưa thể xoá được. Yêu cầu lựa chọn áo trong giỏ hàng", "Cảnh báo",
    	                    JOptionPane.WARNING_MESSAGE);
        		} else {
        			int qtychooser = Integer.parseInt(lbqty.getText());
        			String name = lbname.getText().trim();
        			int price = Integer.parseInt(lbprice.getText());
        			int qtyproduct = s2.getqtyatCategory(name);
        			s.updateProduct2(name, qtyproduct+qtychooser);
        			s2.deletetemp(name);
        			totalprice -= qtychooser*price;
        			tftotalprice.setText(String.valueOf(totalprice));
        			LoadData1();
    				LoadData2();
    				generateIDHDIfNeeded();
        		}
        	}
        });
        btndeleteitemp.setForeground(new Color(0, 0, 0));
        btndeleteitemp.setFont(new Font("Segoe UI", Font.BOLD, 30));
        btndeleteitemp.setBackground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(5, 20, 334, 513);
        panel.add(scrollPane);
        
        table1 = new JTable();
        table1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        table1.setRowHeight(40);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table1.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null},
        	},
        	new String[] {
        		"T\u00EAn \u00E1o", "S\u1ED1 l\u01B0\u1EE3ng", "Gi\u00E1 \u00E1o"
        	}
        ));
        table1.getColumnModel().getColumn(0).setPreferredWidth(200);
        table1.getColumnModel().getColumn(1).setPreferredWidth(60);
        table1.getColumnModel().getColumn(2).setPreferredWidth(150);
        scrollPane.setViewportView(table1);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(649, 88, 331, 506);
        panel.add(scrollPane_1);
        
        table2 = new JTable();
        table2.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null},
        	},
        	new String[] {
        		"T\u00EAn \u00E1o", "S\u1ED1 l\u01B0\u1EE3ng", " Th\u00E0nh ti\u1EC1n"
        	}
        ));
        table2.getColumnModel().getColumn(0).setPreferredWidth(200);
        table2.getColumnModel().getColumn(1).setPreferredWidth(60);
        table2.getColumnModel().getColumn(2).setPreferredWidth(150);
        table2.setFont(new Font("Segoe UI", Font.BOLD, 20));
        table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table2.setRowHeight(40);
        scrollPane_1.setViewportView(table2);
        
        JLabel lblNewLabel_1_2_2_2_2 = new JLabel("Mua áo");
        lblNewLabel_1_2_2_2_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_2_2_2_2.setForeground(Color.WHITE);
        lblNewLabel_1_2_2_2_2.setFont(new Font("Tahoma", Font.BOLD, 40));
        lblNewLabel_1_2_2_2_2.setBackground(Color.BLACK);
        lblNewLabel_1_2_2_2_2.setBounds(522, 4, 269, 55);
        panel.add(lblNewLabel_1_2_2_2_2);
        
        lbchek = new JLabel("");
        lbchek.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbchek.setForeground(new Color(255, 255, 255));
        lbchek.setBounds(368, 11, 1, 1);
        panel.add(lbchek);
        
        lbname = new JLabel("");
        lbname.setForeground(Color.WHITE);
        lbname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbname.setBounds(368, 33, 1, 1);
        panel.add(lbname);
        
        lbprice = new JLabel("");
        lbprice.setForeground(Color.WHITE);
        lbprice.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbprice.setBounds(368, 58, 1, 1);
        panel.add(lbprice);
        
        lbqty = new JLabel("");
        lbqty.setForeground(Color.WHITE);
        lbqty.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbqty.setBounds(490, 20, 1, 1);
        panel.add(lbqty);
        
        lbid = new JLabel("");
        lbid.setForeground(Color.WHITE);
        lbid.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbid.setBounds(490, 43, 1, 14);
        panel.add(lbid);
        
        lbhour = new JLabel("");
        lbhour.setForeground(Color.WHITE);
        lbhour.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbhour.setBounds(607, 11, 1, 14);
        panel.add(lbhour);
        
        lbdate = new JLabel("");
        lbdate.setForeground(Color.WHITE);
        lbdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbdate.setBounds(607, 27, 1, 14);
        panel.add(lbdate);
        init();
        setTime();
    }
}
