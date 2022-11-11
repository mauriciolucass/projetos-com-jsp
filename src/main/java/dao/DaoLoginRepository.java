package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao.SingleBancoGd;
import model.ModelGdLogin;

public class DaoLoginRepository {
	
	private Connection connection;
	
	public DaoLoginRepository() {
		connection = SingleBancoGd.gConnection();
	}
	
	
	public boolean ValidarAutenticacao(ModelGdLogin modelGdLogin) throws SQLException {
		
		String sql = "select * from gd_login where upper(login) =upper( ?) and upper( senha) = upper(?)";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, modelGdLogin.getLogin());
		statement.setString(2, modelGdLogin.getSenha());
		
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			return true;
		}else {
			return false;
		}
		
		
	}

}
