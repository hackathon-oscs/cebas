
package br.gov.mds.sebas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.gov.mds.sebas.seguranca.Usuario;

@FacesConverter(forClass=Usuario.class)
public class UsuarioConverter implements Converter {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		try{
			Usuario usuario;
			Long id = Long.parseLong(string);
			usuario = em.find(Usuario.class, id);
			return usuario;
		}  catch (Exception e){
			System.out.println("Erro Exception: "+e);
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		if(o != null){
			Usuario obj = (Usuario) o;
			return obj.toString();
		}else{
			return null;
		}
	}


}
