package it.prova.televisoredaowithservices.test;

import java.time.LocalDate;
import java.util.List;

import com.mysql.cj.protocol.a.LocalDateTimeValueEncoder;

import it.prova.televisoredaowithservices.model.Televisore;
import it.prova.televisoredaowithservices.service.MyServiceFactory;
import it.prova.televisoredaowithservices.service.televisore.TelevisoreService;

public class TestTelevisore {

	public static void main(String[] args) {
		TelevisoreService televisoreService = MyServiceFactory.getTelevisoreServiceImpl();
		try {

			// ora con il service posso fare tutte le invocazioni che mi servono
			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");
//

//			testInserisciTelevisore(televisoreService);
//			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

//			testRimuoviTelevisore(televisoreService);
//			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

//			testAggiornaTelevisore(televisoreService);
//			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

//			testFindByExample(televisoreService);
//			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");
			
			testTrovaIlPiuGrande(televisoreService);
			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testInserisciTelevisore(TelevisoreService televisoreService) throws Exception {
		System.out.println(".....testInserisci inizio......");
		Televisore televisoreDaAggiungere = new Televisore("sony", "akt", 42, LocalDate.now());
		int quantiInseriti = televisoreService.inserisciNuovo(televisoreDaAggiungere);
		if (quantiInseriti != 1) {
			throw new RuntimeException("TestInserisci: FAILED. Non è stata inserita nessuna voce.");
		}
		System.out.println("Televisore inserito: " + "\n" + televisoreDaAggiungere);
		System.out.println(".....testInserisci: PASSED......");
	}

	public static void testRimuoviTelevisore(TelevisoreService televisoreService) throws Exception {
		System.out.println(".....testRimuoviTelevisore inizio......");
		List<Televisore> listaVociPresenti = televisoreService.listAll();
		if (listaVociPresenti.size() < 1) {
			throw new RuntimeException("Non sono presenti voci nel DB");
		}
		long idPrimoTelevisore = listaVociPresenti.get(0).getId();
		int quantiRimossi = televisoreService.rimuovi(idPrimoTelevisore);
		if (quantiRimossi != 1) {
			throw new RuntimeException("testRimuoviTelevisore: FAILED. Non è stata inserita nessuna voce.");
		}

		System.out.println(".....testRimuoviTelevisore: PASSED......");
	}

	public static void testAggiornaTelevisore(TelevisoreService televisoreService) throws Exception {
		System.out.println(".....testAggiornaTelevisore inizio......");
		List<Televisore> listaVociPresenti = televisoreService.listAll();
		if (listaVociPresenti.size() < 1) {
			throw new RuntimeException("Non sono presenti voci nel DB");
		}
		String marcaDaAggiornare = "samsung";
		Long idPrimoDellaLista = listaVociPresenti.get(0).getId();
		Televisore primoDellaLista = televisoreService.findById(idPrimoDellaLista);
		System.out.println("before update: " + primoDellaLista);

		primoDellaLista.setMarca(marcaDaAggiornare);
		int quantiAggiornati = televisoreService.aggiorna(primoDellaLista);
		if (quantiAggiornati != 1) {
			throw new RuntimeException("testAggiornaTelevisore: FAILED. Non è stata inserita nessuna voce.");
		}
		System.out.println("after update: " +primoDellaLista);

		System.out.println(".....testAggiornaTelevisore: PASSED......");
	}
	
	public static void testFindByExample(TelevisoreService televisoreService) throws Exception {
		System.out.println(".....testFindByExample inizio......");
		List<Televisore> listaVociPresenti = televisoreService.listAll();
		if (listaVociPresenti.size() < 1) {
			throw new RuntimeException("Non sono presenti voci nel DB");
		}
		
		Televisore daCercare = new Televisore("s","q");
		
		List<Televisore> listaTrovati = televisoreService.findByExample(daCercare);
		if (listaTrovati.size()<1) {
			throw new RuntimeException("testAggiornaTelevisore: FAILED. Non è stata inserita nessuna voce.");
		}
		System.out.println(listaTrovati);
		System.out.println(".....testFindByExample: PASSED......");
	}
	
	public static void testTrovaIlPiuGrande(TelevisoreService televisoreService) throws Exception {
		System.out.println(".....testTrovaIlPiuGrande inizio......");
		List<Televisore> listaVociPresenti = televisoreService.listAll();
		if (listaVociPresenti.size() < 1) {
			throw new RuntimeException("Non sono presenti voci nel DB");
		}
		Televisore televisorePiuGrande = televisoreService.voglioIlTelevisorePiuGrande();
		if(televisorePiuGrande == null) {
			throw new RuntimeException("testTrovaIlPiuGrande: FAILED.");
		}
		System.out.println(televisorePiuGrande);
		System.out.println(".....testTrovaIlPiuGrande inizio......");
	}
}