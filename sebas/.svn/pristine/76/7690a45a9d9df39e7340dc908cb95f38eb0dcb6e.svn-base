package br.gov.mds.sebas.seguranca;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.gov.mds.sebas.util.Mensagem;

/*
 * Verifica as autorizações das páginas
 * */

@Named
public class Autorizacao implements Serializable {
	private static final long serialVersionUID = -6523410857885207523L;

	public Permissao verificaAutorizacao(List<Modulo> listaModulo, List<Permissao> listaPermissao) {
		Permissao permissao = new Permissao();
		permissao.setAcesso('n');
		
		/* busca caminho da pagina atual */
		FacesContext faces = FacesContext.getCurrentInstance();
		ExternalContext context = faces.getExternalContext();
		String redireciona = context.getRequestContextPath();
		HttpServletRequest request = (HttpServletRequest) faces
				.getExternalContext().getRequest();
		String caminho = request.getRequestURI();
		
		/* verifica se tem permissão para acessar a pagina */
		for (Permissao p : listaPermissao) {
			String url = redireciona + p.getModulo().getUrl();
			if (url.equals(caminho)	&& p.getModulo().isAcessivel()) {
				if(p.getAcesso() == 's'){
					permissao = p;
				}
				break;
			}
		}
		/* se invalido: mandar para uma tela de aviso */
		if (permissao.getAcesso() != 's') {
			try {
				context.redirect(redireciona + "/seguranca/semAutorizacao.xhtml");
			} catch (IOException e) {
				Mensagem.mostrarErro("Erro ao redirecionar");
			}
		}
		return permissao;
	}
}