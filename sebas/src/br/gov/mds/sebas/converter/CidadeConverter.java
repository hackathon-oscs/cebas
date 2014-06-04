package br.gov.mds.sebas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.gov.mds.sebas.modelo.Cidade;

@FacesConverter(forClass=Cidade.class)
public class CidadeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && !value.isEmpty()) {
            return (Cidade) uic.getAttributes().get(value);
        }
        return null;
	}
 
	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		if (o instanceof Cidade) {
			Cidade entity= (Cidade) o;
            if (entity != null && entity instanceof Cidade && entity.getId() != null) {
            	uic.getAttributes().put( entity.getId().toString(), entity);
                return entity.getId().toString();
            }
        }
        return "";
	}

}
