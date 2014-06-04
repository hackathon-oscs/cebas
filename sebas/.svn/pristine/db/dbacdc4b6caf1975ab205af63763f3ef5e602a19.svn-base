package br.gov.mds.sebas.util;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Classe responsável por recuperar os textos utilizados na aplicação
 */
@Singleton
@Startup
public class Texto implements Serializable {
	private static final long serialVersionUID = -2960325776270093035L;
	
	private ResourceBundle textos;

	/**
	 * Construtor, recupera lista de textos do arquivo
	 */
	@PostConstruct
	public void init() {
		Locale ptBr = new Locale("pt", "BR");
	    textos = ResourceBundle.getBundle("br.gov.mds.sebas.properties.textos", ptBr);
	}
	
	/**
	 * Recupera valor do texto pela chave
	 * @param chave
	 * @return
	 */
	public String getValor(String chave) {
	    return textos.getString(chave);
	}
}