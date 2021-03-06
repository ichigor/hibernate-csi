package br.ufsm.csi.seguranca.filtros;

import br.ufsm.csi.seguranca.model.Usuario;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cpol on 05/06/2017.
 */
@WebFilter("*.priv")
public class FiltroSeguranca implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getSession().getAttribute("usuario") == null ) {
            ((HttpServletResponse) servletResponse).sendRedirect("/spring-teste");
        } else {
//            if(((Usuario)request.getSession().getAttribute("usuario")).isTipo() == true ){
//            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
