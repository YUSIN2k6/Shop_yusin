package admin;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ITEM.managedetail;
import ITEM.managepurchase;
import dao.DetailDAO;
import dao.ManagerPurchaseDAO;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Detail extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	DetailDAO p = new DetailDAO();
	private JTextField search;
	ManagerPurchaseDAO s;

	private void loadtable() {
		ArrayList<managedetail> list = s.loadDetail();

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		for (managedetail e : list) {
			model.addRow(
					new Object[] { e.getStt(), e.getIdhd(), e.getName(), e.getQty(), e.getPrice(), e.getTotalprice()

					});
		}
	}

	private void init() {
		this.s = ManagerPurchaseDAO.getInstance();
		new Thread(() -> {
			while (true) {
				SwingUtilities.invokeLater(() -> loadtable());
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public Detail(JDialog dialog) {
		setBackground(new Color(51, 51, 51));
		setSize(1200, 800);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 207, 1080, 497);
		add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Segoe UI", Font.BOLD, 22));
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(40);
		table.setShowGrid(true);
		table.setGridColor(Color.BLACK);
		table.setBackground(Color.WHITE);
		table.setSelectionBackground(Color.CYAN);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, },
				new String[] { "Stt", "Id ho\u00E1 \u0111\u01A1n", "T\u00EAn \u00E1o", "S\u1ED1 l\u01B0\u1EE3ng",
						"Gi\u00E1 \u00E1o", "T\u1ED5ng ti\u1EC1n c\u1EE7a \u00E1o" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(250);

		JLabel lblNewLabel = new JLabel("Chi tiết");
		lblNewLabel.setForeground(new Color(255, 33, 33));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblNewLabel.setBounds(455, 0, 323, 59);
		add(lblNewLabel);

		search = new JTextField();
		search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);

			}
		});
		search.setFont(new Font("Segoe UI", Font.BOLD, 20));
		search.setBounds(787, 151, 292, 33);
		add(search);
		search.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Tìm:");
		lblNewLabel_1.setIcon(new ImageIcon(Detail.class.getResource("/icons/search_Light.png")));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 21));
		lblNewLabel_1.setBounds(682, 158, 80, 24);
		add(lblNewLabel_1);
		init();

	}

}
