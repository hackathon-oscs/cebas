package br.gov.mds.sebas.seguranca;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import br.gov.mds.sebas.dao.LoginDAO;

@Named
@SessionScoped
public class Sessao implements Serializable {
	private static final long serialVersionUID = 1L;

	private Usuario usuarioLogado;
	@EJB
	private LoginDAO loginDAO;
	private List<Modulo> modulosUsuario;
	private List<Permissao> permissoesUsuario;
	private MenuModel model;
	
	public void construirMenu() {
		List<Modulo> listaModulo = loginDAO
				.recuperarModuloPorUsuario(this.usuarioLogado);
		this.setModulosUsuario(listaModulo);
		this.setPermissoesUsuario(loginDAO
				.recuperarPermissaoPorUsuario(this.usuarioLogado));
		model = new DefaultMenuModel();
		for (Modulo modulo0 : modulosUsuario) {
			if (modulo0.getNivel() == 0) {
				List<Modulo> itemSubMenus = loginDAO
						.recuperarItemModuloPorBase(this.usuarioLogado,
								modulo0.getId());
				// Se possui filhos
				if (itemSubMenus.size() > 0) {
					// Gera subMenu nivel 1
					DefaultSubMenu submenu1 = new DefaultSubMenu(
							modulo0.getNome());
					construirMenuFilho(submenu1, itemSubMenus);
					model.addElement(submenu1);
				} else {
					DefaultMenuItem item = new DefaultMenuItem(
							modulo0.getNome());
					if (modulo0.isAcessivel()) {
						item.setUrl(modulo0.getUrl());
					} else {
						item.setUrl("#");
					}
					model.addElement(item);
				}
			}
		}

		DefaultMenuItem trocarSenha = new DefaultMenuItem("Trocar Senha");
		trocarSenha.setUrl("/cadastro/seguranca/trocar_senha.xhtml");
		trocarSenha.setIcon("ui-icon-locked");
		model.addElement(trocarSenha);
	}

	public void construirMenuFilho(DefaultSubMenu subMenuPai,
			List<Modulo> modulos) {
		for (Modulo modulo1 : modulos) {
			List<Modulo> itemSubMenus = loginDAO.recuperarItemModuloPorBase(
					this.usuarioLogado, modulo1.getId());
			// Se nivel 1 possui filhos
			if (itemSubMenus.size() > 0) {
				DefaultSubMenu submenuFilho = new DefaultSubMenu(
						modulo1.getNome());
				construirMenuFilho(submenuFilho, itemSubMenus);
				subMenuPai.addElement(submenuFilho);
			} else {
				DefaultMenuItem item = new DefaultMenuItem(modulo1.getNome());
				if (modulo1.isAcessivel()) {
					item.setUrl(modulo1.getUrl());
				} else {
					item.setUrl("#");
				}
				subMenuPai.addElement(item);
			}
		}
	}

	public String getPegaDataAtual() {
		Calendar calendar = new GregorianCalendar();
		Date date = new Date();
		calendar.setTime(date);
		SimpleDateFormat dataSimples = new SimpleDateFormat("dd/MM/yyyy");
		String dataAtual = new DateFormatSymbols().getWeekdays()[calendar
				.get(Calendar.DAY_OF_WEEK)] + " " + dataSimples.format(date);
		return dataAtual;
	}

	
	/* GET'S AND SET'S */

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public MenuModel getModel() {
		return model;
	}

	public List<Modulo> getModulosUsuario() {
		return modulosUsuario;
	}

	public void setModulosUsuario(List<Modulo> modulosUsuario) {
		this.modulosUsuario = modulosUsuario;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public List<Permissao> getPermissoesUsuario() {
		return permissoesUsuario;
	}

	public void setPermissoesUsuario(List<Permissao> permissoesUsuario) {
		this.permissoesUsuario = permissoesUsuario;
	}

}
