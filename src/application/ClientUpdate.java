package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.DB;
import database.DbIntegrityException;
import design.ClientRegisterPanel;

public class ClientUpdate extends ClientRegisterPanel {
	public ClientUpdate() {
	}
	
	private static final long serialVersionUID = 1L;
	
	public static void clientUpdate() {
		
		Integer clientCode = Integer.parseInt(ClientRegisterPanel.insertClientCode.getText());
		
		String clientAdress = ClientRegisterPanel.insertPosition.getText();
		Integer adressNumber = Integer.parseInt(ClientRegisterPanel.insertNumber.getText());
		String adressComplement = ClientRegisterPanel.insertCoplement.getText();
		String phoneNumber = ClientRegisterPanel.insertPhoneNumber.getText();
		String mail = ClientRegisterPanel.mail.getText();
		
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();

			if (ClientRegisterPanel.adressAct.isSelected()) {
				st = conn.prepareStatement("UPDATE client " + "SET adress = ? " + "WHERE " + "code = ?");
				st.setString(1, clientAdress);
				st.setInt(2, clientCode);
				st.executeUpdate();
				DB.closeStatement(st);
			}
			
			if (ClientRegisterPanel.adressAct.isSelected()) {
				st = conn.prepareStatement("UPDATE client " + "SET number = ? " + "WHERE " + "code = ?");
				st.setInt(1, adressNumber);
				st.setInt(2, clientCode);
				st.executeUpdate();
				DB.closeStatement(st);
			}

			if (ClientRegisterPanel.complementAct.isSelected()) {
				st = conn.prepareStatement("UPDATE client " + "SET complement = ? " + "WHERE " + "code = ?");
				st.setString(1, adressComplement);
				st.setInt(2, clientCode);
				st.executeUpdate();
				DB.closeStatement(st);
			}

			if (ClientRegisterPanel.phoneNumberAct.isSelected()) {
				st = conn.prepareStatement("UPDATE client " + "SET phone = ? " + "WHERE " + "code = ?");
				st.setString(1, phoneNumber);
				st.setInt(2, clientCode);
				st.executeUpdate();
				DB.closeStatement(st);
			}
			
			if (ClientRegisterPanel.emailAct.isSelected()) {
				st = conn.prepareStatement("UPDATE client " + "SET mail = ? " + "WHERE " + "code = ?");
				st.setString(1, mail);
				st.setInt(2, clientCode);
				st.executeUpdate();
				DB.closeStatement(st);
			}

		}

		catch (SQLException g) {
			throw new DbIntegrityException(g.getMessage());
		}
		
	}
	
}
