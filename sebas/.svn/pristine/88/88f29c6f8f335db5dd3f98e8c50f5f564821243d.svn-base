package br.gov.mds.sebas.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

public enum MessageType {

	ALERTA(FacesMessage.SEVERITY_WARN), AVISO(FacesMessage.SEVERITY_INFO), ERRO(
			FacesMessage.SEVERITY_ERROR);

	private Severity severity;

	public Severity getSeverity() {
		return severity;
	}

	private MessageType(Severity severity) {
		this.severity = severity;
	}

}
