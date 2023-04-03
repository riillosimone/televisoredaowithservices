package it.prova.televisoredaowithservices.dao.televisore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.prova.televisoredaowithservices.dao.AbstractMySQLDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public class TelevisoreDAOImpl extends AbstractMySQLDAO implements TelevisoreDAO {

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Televisore> list() throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		List<Televisore> result = new ArrayList<>();

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from televisore")) {

			while (rs.next()) {
				Televisore televisoreTemp = new Televisore();
				televisoreTemp.setMarca(rs.getString("Marca"));
				televisoreTemp.setModello(rs.getString("Modello"));
				televisoreTemp.setPollici(rs.getInt("Pollici"));
				televisoreTemp.setDataProduzione(
						rs.getDate("DATAPRODUZIONE") != null ? rs.getDate("DATAPRODUZIONE").toLocalDate() : null);
				televisoreTemp.setId(rs.getLong("ID"));
				result.add(televisoreTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Televisore get(Long idInput) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		if (idInput == null || idInput < 1) {
			throw new Exception("Valore input non ammesso");
		}
		Televisore result = null;

		try (PreparedStatement ps = connection.prepareStatement("select * from televisore where id=?")) {
			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {
					result = new Televisore();
					result.setMarca(rs.getString("Marca"));
					result.setModello(rs.getString("Modello"));
					result.setPollici(rs.getInt("Pollici"));
					result.setDataProduzione(
							rs.getDate("DATAPRODUZIONE") != null ? rs.getDate("DATAPRODUZIONE").toLocalDate() : null);
					result.setId(rs.getLong("ID"));
				} else {
					result = null;
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}

		}
		return result;
	}

	@Override
	public int update(Televisore input) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		if (input == null) {
			throw new Exception("Valore input non ammesso");
		}
		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"update televisore set marca=?, modello=?, pollici=?, dataproduzione=? where id=?;")) {
			ps.setString(1, input.getMarca());
			ps.setString(2, input.getModello());
			ps.setInt(3, input.getPollici());
			ps.setDate(4, java.sql.Date.valueOf(input.getDataProduzione()));
			ps.setLong(5, input.getId());
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Televisore input) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		if (input == null) {
			throw new Exception("Valore input non ammesso");
		}
		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO televisore (marca, modello, pollici, dataproduzione) VALUES (?, ?, ?, ?);")) {
			ps.setString(1, input.getMarca());
			ps.setString(2, input.getModello());
			ps.setInt(3, input.getPollici());
			ps.setDate(4, java.sql.Date.valueOf(input.getDataProduzione()));
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Long input) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		if (input == null || input < 1) {
			throw new Exception("Valore input non ammesso");
		}
		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("Delete from televisore where id = ?")) {
			ps.setLong(1, input);
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Televisore> findByExample(Televisore input) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		if (input == null) {
			throw new Exception("Valore input non ammesso");
		}
		List<Televisore> result = new ArrayList<>();
		String query = "select * from televisore where 1=1 ";
		if (input.getMarca() != null && !input.getMarca().isBlank()) {
			query += "and marca like '" + input.getMarca() + "%' ";
		}
		if (input.getModello() != null && !input.getModello().isBlank()) {
			query += "and modello like '" + input.getModello() + "%' ";
		}
		if (input.getPollici() > 0) {
			query += "and pollici = " + input.getPollici() + " ";
		}
		if (input.getDataProduzione() != null) {
			query += "and dataproduzione like " + java.sql.Date.valueOf(input.getDataProduzione()) + " ";
		}
		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery(query);
			while (rs.next()) {
				Televisore televisoreTemp = new Televisore();
				televisoreTemp.setMarca(rs.getString("Marca"));
				televisoreTemp.setModello(rs.getString("Modello"));
				televisoreTemp.setPollici(rs.getInt("Pollici"));
				televisoreTemp.setDataProduzione(
						rs.getDate("DATAPRODUZIONE") != null ? rs.getDate("DATAPRODUZIONE").toLocalDate() : null);
				televisoreTemp.setId(rs.getLong("ID"));
				result.add(televisoreTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Televisore giveMeTheBiggestTelevisore() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		Televisore result = null;

		try (PreparedStatement ps = connection.prepareStatement(
				"select * from televisore t where t.pollici = (select max(t.pollici) from televisore);")) {
			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {
					result = new Televisore();
					result.setMarca(rs.getString("Marca"));
					result.setModello(rs.getString("Modello"));
					result.setPollici(rs.getInt("Pollici"));
					result.setDataProduzione(
							rs.getDate("DATAPRODUZIONE") != null ? rs.getDate("DATAPRODUZIONE").toLocalDate() : null);
					result.setId(rs.getLong("ID"));

				} else {
					result = null;
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}

		}
		return result;
	}

	@Override
	public int countTelevisoriBetweenTwoDates(LocalDate before, LocalDate after) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		if (before == null || after == null) {
			throw new Exception("Valore input non ammesso");
		}
		int result = 0;

		try (PreparedStatement ps = connection
				.prepareStatement("select count(*) from televisore t where dataproduzione between ? and ?;")) {
			ps.setDate(1, java.sql.Date.valueOf(before));
			ps.setDate(2, java.sql.Date.valueOf(after));
			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {
					result = rs.getInt("count(*)");
				} else {
					result = 0;
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}

		}
		return result;
	}

	@Override
	public List<String> marcheTelevisoriLastSixMonths() throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		List<String> result = new ArrayList<>();
		try (PreparedStatement ps = connection
				.prepareStatement("select distinct (marca) from televisore where dataproduzione > ?;")) {
			ps.setDate(1, java.sql.Date.valueOf(LocalDate.now().minusMonths(6)));
			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					String marcaTemp ="";
					marcaTemp = rs.getString("marca");
					result.add(marcaTemp);
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}

		}
		return result;
	}

}
