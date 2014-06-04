package br.gov.mds.sebas.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensagem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Mensagem com escolha Tipo
	public void mostrarMsg(MessageType tipo, String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(tipo.getSeverity(), mensagem, null));
	}

	public static void mostrarMsg(MessageType tipo, String titulo,
			String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(tipo.getSeverity(), titulo, mensagem));
	}

	public void mostrarMsgTitulo(MessageType tipo, String titulo) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(tipo.getSeverity(), null, titulo));
	}

	// Mensagem do tipo aviso
	public static void mostrarAviso(String titulo, String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensagem));
	}

	public static void mostrarAviso(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem));
	}

	public static void mostrarAvisoTitulo(String titulo) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, ""));
	}

	// Mensagem do tipo Alerta
	public static void mostrarAlerta(String titulo, String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, mensagem));
	}

	public static void mostrarAlerta(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "", mensagem));
	}

	public static void mostrarAlertaTitulo(String titulo) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, ""));
	}

	// Mensagem do tipo erro
	public static void mostrarErro(String titulo, String mensagem) {
		FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo,
								mensagem));
	}

	public static void mostrarErro(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "", mensagem));
	}

	public static void mostrarErroTitulo(String titulo) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, ""));
	}

}
