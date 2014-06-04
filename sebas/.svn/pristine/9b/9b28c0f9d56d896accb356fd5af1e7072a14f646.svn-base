package br.gov.mds.sebas.util;

import java.io.Serializable;

public class UtilController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean formAtivo = true;
	private Status status;

	public boolean isFormAtivo() {
		return formAtivo;
	}

	public void setFormAtivo(boolean formAtivo) {
		this.formAtivo = formAtivo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isStatusListing() {
		if (getStatus() == Status.LISTING) {
			return true;
		}
		return false;
	}

	public boolean isStatusViewing() {
		if (getStatus() == Status.VIEWING) {
			return true;
		}
		return false;
	}

	public boolean isStatusInserting() {
		if (getStatus() == Status.INSERTING) {
			return true;
		}
		return false;
	}

	public boolean isStatusEditing() {
		if (getStatus() == Status.EDITING) {
			return true;
		}
		return false;
	}

	public boolean isStatusRemoving() {
		if (getStatus() == Status.DELETING) {
			return true;
		}
		return false;
	}

	public boolean isStatusFiltering() {
		if (getStatus() == Status.FILTERING) {
			return true;
		}
		return false;
	}
}
