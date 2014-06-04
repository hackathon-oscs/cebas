package br.gov.mds.sebas.util;

import java.util.Iterator;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;

public class LimpaComponentes {
	/**
     * Limpa os dados dos componentes de edição e de seus filhos,
     * recursivamente. Checa se o componente é instância de EditableValueHolder
     * e 'reseta' suas propriedades.
     * <p>
     * Quando este método, por algum motivo, não funcionar, parta para ignorância
     * e limpe o componente assim:
     * component.getChildren().clear()
     */
    public static void cleanSubmittedValues(UIComponent component) {
    	
        if (component instanceof EditableValueHolder) {
            EditableValueHolder evh = (EditableValueHolder) component;
            evh.setSubmittedValue(null);
            evh.setValue(null);
            evh.setLocalValueSet(false);
            evh.setValid(true);
        }
        
        // Dependendo de como se implementa um Composite Component, ele retorna ZERO
        // na busca por filhos. Nesse caso devemos iterar sobre os componentes que o 
        // compõe de forma diferente.
        if(UIComponent.isCompositeComponent(component)) {
            Iterator<UIComponent> i = component.getFacetsAndChildren();
            
            while(i.hasNext()) {
                UIComponent comp = (UIComponent) i.next();
                if (comp.getChildCount() > 0) {
                    for (UIComponent child : comp.getChildren()) {
                        cleanSubmittedValues(child);
                    }
                }
            }
        }
        if (component.getChildCount() > 0) {
            for (UIComponent child : component.getChildren()) {
                cleanSubmittedValues(child);
            }
        }
    }
}