package it.prova.televisoredaowithservices.dao.televisore;

import java.time.LocalDate;
import java.util.List;

import it.prova.televisoredaowithservices.dao.IBaseDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public interface TelevisoreDAO extends IBaseDAO<Televisore>{
	
	public Televisore giveMeTheBiggestTelevisore() throws Exception;
	public int countTelevisoriBetweenTwoDates (LocalDate before,LocalDate after)throws Exception;
	public List<String> marcheTelevisoriLastSixMonths()throws Exception;
}
