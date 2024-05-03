package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import database.DB;
import design.CashRegister;

public class SaleRegistration extends CashRegister {
	
	public SaleRegistration() {

	}

	private static final long serialVersionUID = 1L;
	
	public static void GenerateSaleId() {
		
		Connection conn = null;
		Statement st = null;
		
		String init = "00001";
		int year = LocalDate.now().getYear()-2000;
		int month = LocalDate.now().getMonthValue();
		String saleIds = String.valueOf(year) + String.valueOf(month<10?("0"+month):(month)) + init;
		
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT MAX(SALECODE) as saleCode FROM sale");
			
			Integer control = Integer.parseInt(saleIds);
		
			while (rs.next()) {		
				if (rs.getInt(1) == 0) {
					CashRegister.insertSaleId.setText(saleIds);
				} else
					if (rs.getInt(1) < control) {
						CashRegister.insertSaleId.setText(String.valueOf(control));					
					} else {
				
				int newCode = rs.getInt(1)+1;
				CashRegister.insertSaleId.setText(String.valueOf(newCode));
				}
			}
		}
		
		catch (SQLException e) {
		e.printStackTrace();
	    }

	    finally {
		    DB.closeStatement(st);
		    // DB.closeConnetction();
	    }
	}

	public static void SaleConsult() {

		Integer productCodeNumber = Integer.parseInt(CashRegister.insertProductCode.getText());
		DecimalFormat fmt = new DecimalFormat("0.00");

		Connection conn = null;
		Statement st = null;

		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT name, description, qtde, price FROM products WHERE code = '" + productCodeNumber + "';");

			if (!rs.next()) {
				JOptionPane.showMessageDialog(null, "DESCULPE!!! Produto não Cadastrado!");
			} else {
				
				do {
					String quantity = String.valueOf(rs.getInt(3));
					String price = fmt.format(rs.getDouble(4));

					CashRegister.productName.setText(rs.getString(1));
					CashRegister.productDescription.setText(rs.getString(2));
					CashRegister.productQuantity.setText(quantity);
					CashRegister.priceValue.setText("R$ " + price);
				} while (rs.next());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			DB.closeStatement(st);
			// DB.closeConnetction();
		}
	}

	public static void InsertOnBag() {

		DefaultTableModel table = (DefaultTableModel) CashRegister.table.getModel();

		Integer productCodeNumber = Integer.parseInt(CashRegister.insertProductCode.getText());
		Integer quantitySelected = Integer.parseInt(CashRegister.sold.getText());
		Integer storage = Integer.parseInt(CashRegister.productQuantity.getText());
		DecimalFormat fmt = new DecimalFormat("0.00");

		Connection conn = null;
		Statement st = null;

		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT name, description, qtde, price FROM products WHERE code = '" + productCodeNumber + "';");
			
			while (rs.next()) {

				if (storage < quantitySelected) {
					JOptionPane.showMessageDialog(null, "ATENÇÃO! Quantidade solicitada NÃO DISPONÍVEL no estoque!");
				}
				if (quantitySelected != null && storage >= quantitySelected) {
					String quantity = String.valueOf(rs.getInt(3));
					String price = fmt.format(rs.getDouble(4));

					CashRegister.productName.setText(rs.getString(1));
					CashRegister.productDescription.setText(rs.getString(2));
					CashRegister.productQuantity.setText(quantity);
					CashRegister.priceValue.setText("R$ " + price);

					table.addRow(new Object[] { productCodeNumber, 
							                    rs.getString(1), 
							                    rs.getString(2), 
							                    quantitySelected,
							                    price });

					Double subSum = Double.parseDouble(CashRegister.sumValue.getText().replace(',', '.'));
					Double subTotal = (rs.getDouble(4) * quantitySelected) + subSum;
					CashRegister.sumValue.setText(fmt.format(subTotal));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			DB.closeStatement(st);
			// DB.closeConnetction();
		}
	}

	public static void RemoveFromBag() {

		DefaultTableModel table = (DefaultTableModel) CashRegister.table.getModel();
		DecimalFormat fmt = new DecimalFormat("0.00");

		try {

			int SelectedRowIndex = CashRegister.table.getSelectedRow();

			Integer itemQty = (Integer) table.getValueAt(SelectedRowIndex, 3);
			Double itemPrice = Double.parseDouble(((String) table.getValueAt(SelectedRowIndex, 4)).replace(',', '.'));
			Double subSum = Double.parseDouble(CashRegister.sumValue.getText().replace(',', '.'));
			Double subTotal = subSum - itemPrice * itemQty;
			CashRegister.sumValue.setText(fmt.format(subTotal));

			table.removeRow(SelectedRowIndex);

		} catch (Exception e) {

		}
	}

	public static void SaleConfirm() {

		Connection conn = null;
		PreparedStatement st = null;

		ArrayList<Product> products = new ArrayList<>();
		int totalRows = CashRegister.table.getRowCount();

		for (int i = 0; i < totalRows; i++) {

			Product p = new Product();

			p.setCode((int) CashRegister.table.getModel().getValueAt(i, 0));
			p.setName((String) CashRegister.table.getModel().getValueAt(i, 1));
			p.setDescription((String) CashRegister.table.getModel().getValueAt(i, 2));
			p.setQuantity((int) CashRegister.table.getModel().getValueAt(i, 3));
			p.setPrice(Double.parseDouble(((String) CashRegister.table.getModel().getValueAt(i, 4)).replace(',', '.')));

			products.add(p);
		}

		for (Product p : products) {
			try {
				conn = DB.getConnection();
				st = conn.prepareStatement("UPDATE products " + "SET qtde = qtde - ? " + "WHERE " + "(code = ?)");
				st.setInt(1, p.getQuantity());
				st.setInt(2, p.getCode());
				st.executeUpdate();
			}

			catch (SQLException e) {
				e.printStackTrace();
			}

			finally {
				DB.closeStatement(st);
				// DB.closeConnetction();
			}
			
			/*
			 *************************************************************************************************************************
			 =========================================================================================================================
			*/
			
			Integer saleCode = Integer.parseInt(CashRegister.insertSaleId.getText());
			Integer clientCode = Integer.parseInt(CashRegister.insertClientId.getText());
			String clientName = CashRegister.insertClientName.getText();
			Double valuePaid = p.getPrice() * p.getQuantity();
			
			try {
				conn = DB.getConnection();
				st = conn.prepareStatement("INSERT INTO sale "
						                   + "(saleCode, clientCode, clientName, productCode, productName, productDescription, quantitySold, valuePaid)"
						                   + "VALUES"
						                   + "(?, ?, ? ,? , ?, ?, ?, ?)"
						                   );
				st.setInt(1, saleCode);
				st.setInt(2, clientCode);
				st.setString(3, clientName);
				st.setInt(4, p.getCode());
				st.setString(5, p.getName());
				st.setString(6, p.getDescription());
				st.setInt(7, p.getQuantity());
				st.setDouble(8, valuePaid);
				st.executeUpdate();
			}
			
			catch (SQLException e) {
				e.printStackTrace();
			}

			finally {
				DB.closeStatement(st);
				// DB.closeConnetction();
			}
		}
	}
}
