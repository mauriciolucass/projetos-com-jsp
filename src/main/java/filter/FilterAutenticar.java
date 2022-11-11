package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import conexao.SingleBancoGd;


@WebFilter(urlPatterns = {"/principal/*"})/*intercepta todas as requisiçoes que vierem do projeto ou mapeamento*/
public class FilterAutenticar extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public static Connection connection;
    public FilterAutenticar() {
        super();
        
    }

  //encerra os processo quando o servidor e parado
    // mataria um processo de conexao com o banco
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//intercepta todas as requisiçoes  e as respostas do sistema
		//tudo do sistema pasa por aqui
		/*ex : validacao e autenticao
		 * dar commit e rolbak de transaçoes no banco*/
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			String usuarioLogado = (String) session.getAttribute("usuario");	
			
			String urlParaAutenticar = req.getServletPath();//url que esta sendo acessada
			
			
/*Validação para ser logado se nao volta para tela d login*/
			
			if(usuarioLogado == null ||(usuarioLogado == null && usuarioLogado.isEmpty())&& urlParaAutenticar.contains(" principal/ServletLogin")) {
				
			 RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
			 
			 request.setAttribute("msg", "Realize o Login");
			 redireciona.forward(request, response);
			 return; //para e redirecionar para atela de login
			}else {
				chain.doFilter(request, response);
			}
			
			connection.commit(); //deu certo entao faz o comitt das auterações no banco
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg",e.getMessage());
			redirecionar.forward(request, response);
		}
		
		try {
			connection.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//inicia os processos ou recursos  quando inicia o servidor sobe o  projeto
		//iicia a conexao com o banco 
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleBancoGd.gConnection();
	}

}
