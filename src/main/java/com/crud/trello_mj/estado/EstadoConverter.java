package com.crud.trello_mj.estado;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter(forClass = Estado.class)
public class EstadoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Long id = Long.valueOf(value);
            // Obtener el EstadoBean usando evaluateExpressionGet
            EstadoBean estadoBean = (EstadoBean) context.getApplication()
                    .evaluateExpressionGet(context, "#{estadoBean}", EstadoBean.class);
            // Usar el EstadoServicio del EstadoBean
            EstadoServicio estadoServicio = estadoBean.getEstadoServicio();
            return estadoServicio.buscarPorId(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        if (object == null || !(object instanceof Estado)) {
            return "";
        }
        return String.valueOf(((Estado) object).getId());
    }
}
