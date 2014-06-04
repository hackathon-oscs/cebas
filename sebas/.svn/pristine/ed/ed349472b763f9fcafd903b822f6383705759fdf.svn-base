package br.gov.mds.sebas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.gov.mds.sebas.modelo.TipoContato;

@FacesConverter(forClass=TipoContato.class)
public class TipoContatoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && !value.isEmpty()) {
            return (TipoContato) uic.getAttributes().get(value);
        }
        return null;
	}
 
	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		if (o instanceof TipoContato) {
			TipoContato entity= (TipoContato) o;
            if (entity != null && entity instanceof TipoContato && entity.getId() != null) {
            	uic.getAttributes().put( entity.getId().toString(), entity);
                return entity.getId().toString();
            }
        }
        return "";
	}

}
