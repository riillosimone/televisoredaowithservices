package it.prova.televisoredaowithservices.service.televisore;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.prova.televisoredaowithservices.connection.MyConnection;
import it.prova.televisoredaowithservices.dao.Constants;
import it.prova.televisoredaowithservices.dao.televisore.TelevisoreDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public class TelevisoreServiceImpl implements TelevisoreService {
	private TelevisoreDAO televisoreDAO;

	@Override
	public void setTelevisoreDao(TelevisoreDAO televisoreDAO) {
		this.televisoreDAO = televisoreDAO;
	}

	@Override
	public List<Televisore> listAll() throws Exception {
		List<Televisore> result = new ArrayList<>();
		try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDAO.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		return result;
	}

	@Override
	public Televisore findById(Long idInput) throws Exception {
		Televisore result = null;
		try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDAO.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDAO.get(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		return result;
	}

	@Override
	public int aggiorna(Televisore input) throws Exception {
		int result = 0;
		try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDAO.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDAO.update(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		return result;
	}

	@Override
	public int inserisciNuovo(Televisore input) throws Exception {
		int result = 0;
		try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDAO.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDAO.insert(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		return result;
	}

	@Override
	public int rimuovi(Long input) throws Exception {
		int result = 0;
		try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDAO.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDAO.delete(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		return result;
	}

	@Override
	public List<Televisore> findByExample(Televisore input) throws Exception {
		List<Televisore> result = new ArrayList<>();
		try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDAO.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDAO.findByExample(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		return result;
	}

	@Override
	public Televisore voglioIlTelevisorePiuGrande() throws Exception {
		Televisore result = null;
		try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDAO.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDAO.giveMeTheBiggestTelevisore();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		return result;
	}

	@Override
	public int quantiTelevisoriProdottiTraDueDate(LocalDate before, LocalDate after) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> marcheTelevisoriProdottiNegliUltimiSeiMesi() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
