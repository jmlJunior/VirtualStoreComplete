package design;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import accessinterface.UserInterface;
import application.ClientRegister;
import application.SaleRegistration;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
//import javax.swing.UIManager;

public class CashRegister extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton enter, insert, clear, out, explore;

	private JLabel title, subTitle;

	LoginBackImage cashRegister;
	private JLabel saleId, clientId, clientName, idNumber, product, description, quantity, stock, price, sum, discounts;
	public static JTextField insertSaleId, insertClientId, insertClientName, insertProductCode, productName,
			                 productQuantity, priceValue, sumValue, discountValue, sold;
	public static JTextArea productDescription;
	
	public static JTable table;
	public static JScrollPane scroll;

	/**
	 * Create the panel.
	 */
	public CashRegister() {

		title = new JLabel("VIRTUAL STORE");
		title.setFont(new Font("Bodoni MT Condensed", Font.BOLD, 100));
		title.setForeground(new Color(153, 0, 51));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(0, 0, 1250, 118);

		cashRegister = new LoginBackImage();
		cashRegister.setLayout(null);
		cashRegister.add(title);

		subTitle = new JLabel("CAIXA:");
		subTitle.setHorizontalAlignment(SwingConstants.CENTER);
		subTitle.setForeground(new Color(153, 0, 51));
		subTitle.setFont(new Font("Bodoni MT Black", Font.BOLD, 100));
		subTitle.setBounds(0, 150, 1250, 85);
		cashRegister.add(subTitle);

		saleId = new JLabel("Código da Venda:");
		saleId.setForeground(new Color(153, 0, 51));
		saleId.setFont(new Font("Bodoni MT Condensed", Font.BOLD, 32));
		saleId.setBounds(114, 272, 192, 40);
		cashRegister.add(saleId);

		insertSaleId = new JTextField();
		insertSaleId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		insertSaleId.setBounds(114, 312, 182, 34);
		cashRegister.add(insertSaleId);

		clientId = new JLabel("Código do Cliente:");
		clientId.setForeground(new Color(153, 0, 51));
		clientId.setFont(new Font("Bodoni MT Condensed", Font.BOLD, 32));
		clientId.setBounds(328, 272, 192, 40);
		cashRegister.add(clientId);

		insertClientId = new JTextField();
		insertClientId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		insertClientId.setBounds(328, 312, 187, 34);
		cashRegister.add(insertClientId);

		clientName = new JLabel("Nome do Cliente:");
		clientName.setForeground(new Color(153, 0, 51));
		clientName.setFont(new Font("Bodoni MT Condensed", Font.BOLD, 32));
		clientName.setBounds(545, 272, 192, 40);
		cashRegister.add(clientName);

		insertClientName = new JTextField();
		insertClientName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		insertClientName.setBounds(545, 312, 592, 34);
		cashRegister.add(insertClientName);

		idNumber = new JLabel("Cód. do Produto:");
		idNumber.setForeground(new Color(153, 0, 51));
		idNumber.setFont(new Font("Bodoni MT Condensed", Font.BOLD, 32));
		idNumber.setBounds(114, 368, 224, 40);
		cashRegister.add(idNumber);

		insertProductCode = new JTextField();
		insertProductCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		insertProductCode.setBounds(114, 408, 182, 34);
		cashRegister.add(insertProductCode);

		product = new JLabel("Produto:");
		product.setForeground(new Color(153, 0, 51));
		product.setFont(new Font("Bodoni MT Condensed", Font.BOLD, 32));
		product.setBounds(328, 368, 224, 40);
		cashRegister.add(product);

		productName = new JTextField();
		productName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		productName.setBounds(328, 408, 187, 34);
		cashRegister.add(productName);

		description = new JLabel("Descriçãoo do Produto:");
		description.setForeground(new Color(153, 0, 51));
		description.setFont(new Font("Bodoni MT Condensed", Font.BOLD, 32));
		description.setBounds(545, 368, 273, 40);
		cashRegister.add(description);

		productDescription = new JTextArea();
		productDescription.setLineWrap(true);
		productDescription.setWrapStyleWord(true);
		productDescription.setFont(new Font("Tahoma", Font.PLAIN, 16));
		productDescription.setBounds(545, 408, 592, 85);
		cashRegister.add(productDescription);

		quantity = new JLabel("Quantidade:");
		quantity.setHorizontalAlignment(SwingConstants.LEFT);
		quantity.setForeground(new Color(153, 0, 51));
		quantity.setFont(new Font("Bodoni MT Condensed", Font.BOLD, 32));
		quantity.setBackground(Color.WHITE);
		quantity.setBounds(114, 611, 131, 40);
		cashRegister.add(quantity);

		sold = new JTextField();
		sold.setHorizontalAlignment(SwingConstants.CENTER);
		sold.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sold.setBounds(244, 611, 52, 34);
		cashRegister.add(sold);

		stock = new JLabel("ESTOQUE:");
		stock.setHorizontalAlignment(SwingConstants.RIGHT);
		stock.setForeground(new Color(153, 0, 51));
		stock.setFont(new Font("Bodoni MT Condensed", Font.BOLD, 30));
		stock.setBackground(Color.WHITE);
		stock.setBounds(145, 534, 94, 40);
		cashRegister.add(stock);

		productQuantity = new JTextField();
		productQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		productQuantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		productQuantity.setBounds(244, 534, 52, 34);
		cashRegister.add(productQuantity);

		price = new JLabel("Valor Unitário:");
		price.setBackground(Color.WHITE);
		price.setHorizontalAlignment(SwingConstants.LEFT);
		price.setForeground(new Color(153, 0, 51));
		price.setFont(new Font("Bodoni MT Condensed", Font.BOLD, 32));
		price.setBounds(328, 496, 182, 40);
		cashRegister.add(price);

		priceValue = new JTextField();
		priceValue.setHorizontalAlignment(SwingConstants.LEFT);
		priceValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
		priceValue.setBounds(328, 534, 187, 34);
		cashRegister.add(priceValue);

		sum = new JLabel("TOTAL DA VENDA:");
		sum.setHorizontalAlignment(SwingConstants.LEFT);
		sum.setForeground(new Color(153, 0, 51));
		sum.setFont(new Font("Bodoni MT Condensed", Font.BOLD, 32));
		sum.setBackground(Color.WHITE);
		sum.setBounds(328, 645, 182, 40);
		cashRegister.add(sum);

		sumValue = new JTextField();
		sumValue.setHorizontalAlignment(SwingConstants.CENTER);
		sumValue.setFont(new Font("Tahoma", Font.BOLD, 28));
		sumValue.setBounds(328, 684, 187, 55);
		cashRegister.add(sumValue);
		sumValue.setText("0,00");

		discounts = new JLabel("Descontos:");
		discounts.setHorizontalAlignment(SwingConstants.LEFT);
		discounts.setForeground(new Color(153, 0, 51));
		discounts.setFont(new Font("Bodoni MT Condensed", Font.BOLD, 32));
		discounts.setBackground(Color.WHITE);
		discounts.setBounds(328, 574, 182, 40);
		cashRegister.add(discounts);

		discountValue = new JTextField();
		discountValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
		discountValue.setBounds(328, 611, 187, 34);
		cashRegister.add(discountValue);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Código", "Produto", "Descrição/Detalhe", "Qtde", "Valor Unitário"
			}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(348);
		table.getColumnModel().getColumn(3).setPreferredWidth(41);
		table.getColumnModel().getColumn(4).setPreferredWidth(82);
		table.setBounds(545, 534, 592, 205);
					
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

		scroll = new JScrollPane();
		scroll.setBounds(545, 511, 592, 280);
		cashRegister.add(scroll);
		scroll.add(table);
		scroll.setViewportView(table);

		/**
		 * 
		**/

		enter = new JButton("REGISTRAR");
		enter.setForeground(new Color(0, 100, 0));
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaleRegistration.SaleConfirm();
				
				MessageFormat header = new MessageFormat("NOTA SEM VALOR FISCAL!");
				MessageFormat footer = new MessageFormat("Valor Total da Compra: R$ " + sumValue.getText());
				
				try {
					table.print(JTable.PrintMode.NORMAL, header, footer);
				} catch (Exception i) {
					
				}
				((DefaultTableModel) table.getModel()).setRowCount(0);
				JOptionPane.showMessageDialog(null, "Compra aprovada/finalizada com sucesso!"
						                            + "\n\nValor Total da Compra: R$ " + sumValue.getText()                         
						);
				
				sumValue.setText("0,00");
				
				insertSaleId.setText("");
				insertClientId.setText("");
				insertClientName.setText("");
				
				ClientRegister.insertClientCode.setText("");
				ClientRegister.insertDocNumber.setText("");
				ClientRegister.insertName.setText("");
				ClientRegister.insertPosition.setText("");
				ClientRegister.insertNumber.setText("");
				ClientRegister.insertCoplement.setText("");
				ClientRegister.insertPhoneNumber.setText("");
				ClientRegister.mail.setText("");
				
				ClientRegister.adressAct.setSelected(isDisplayable());
				ClientRegister.complementAct.setSelected(isDisplayable());
				ClientRegister.phoneNumberAct.setSelected(isDisplayable());
				ClientRegister.emailAct.setSelected(isDisplayable());
				
				ClientRegister.ClearTable();
				ClientRegister.GenerateClientId();
			}
		});
		enter.setFont(new Font("Bodoni MT Condensed", Font.BOLD, 30));
		enter.setBounds(855, 802, 150, 41);
		cashRegister.add(enter);

		explore = new JButton("PESQUISAR");
		explore.setForeground(new Color(0, 100, 0));
		explore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaleRegistration.SaleConsult();
			}
		});
		explore.setFont(new Font("Bodoni MT Condensed", Font.BOLD, 30));
		explore.setBounds(114, 453, 182, 41);
		cashRegister.add(explore);

		clear = new JButton("LIMPAR");
		clear.setForeground(new Color(204, 102, 0));
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertSaleId.setText("");
				insertClientId.setText("");
				insertClientName.setText("");
				insertProductCode.setText("");
				productName.setText("");
				productDescription.setText("");
				sold.setText("");
				productQuantity.setText("");
				priceValue.setText("");
				discountValue.setText("");
				
				SaleRegistration.GenerateSaleId();
			}
		});
		clear.setFont(new Font("Bodoni MT Condensed", Font.BOLD, 30));
		clear.setBounds(114, 802, 182, 41);
		cashRegister.add(clear);

		out = new JButton("SAIR");
		out.setForeground(new Color(174, 34, 34));
		out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UserInterface.InitialScreen();
				LoginPanel.textField.setText("");
				LoginPanel.passwordField.setText("");
				
			}
		});
		out.setFont(new Font("Bodoni MT Condensed", Font.BOLD, 30));
		out.setBounds(1015, 802, 122, 41);
		cashRegister.add(out);

		insert = new JButton("SACOLA");
		insert.setForeground(new Color(0, 100, 0));
		insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SaleRegistration.InsertOnBag();

				insertProductCode.setText("");
				productName.setText("");
				productDescription.setText("");
				sold.setText("");
				productQuantity.setText("");
				priceValue.setText("");
				discountValue.setText("");
				
			}
		});
		insert.setFont(new Font("Bodoni MT Condensed", Font.BOLD, 30));
		insert.setBounds(114, 698, 182, 41);
		cashRegister.add(insert);
		
		JButton remove = new JButton("EXCLUIR");
		remove.setForeground(Color.RED);
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaleRegistration.RemoveFromBag();
			}
		});
		remove.setFont(new Font("Bodoni MT Condensed", Font.BOLD, 30));
		remove.setBounds(545, 802, 150, 41);
		cashRegister.add(remove);
		
		SaleRegistration.GenerateSaleId();
		
	}

	public LoginBackImage getCashRegister() {
		return cashRegister;
	}

	public void setCashRegister(LoginBackImage cashRegister) {
		this.cashRegister = cashRegister;
	}
}