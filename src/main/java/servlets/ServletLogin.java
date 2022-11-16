package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoLoginRepository;
import model.ModelGdLogin;


@WebServlet(urlPatterns = {"/principal//ServletLogin","/ServletLogin"})
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   private DaoLoginRepository daoLoginRepository = new DaoLoginRepository();
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		if(acao != null && !acao.isEmpty()&& acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate();//invalidar sesao
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			redirecionar.forward(request, response);
		}else {
			doPost(request, response);
		}
			
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		try {
			if(login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
				ModelGdLogin modelGdLogin = new ModelGdLogin();
				modelGdLogin.setLogin(login);
				modelGdLogin.setSenha(senha);
				
				if(daoLoginRepository.ValidarAutenticacao(modelGdLogin)) {
					request.getSession().setAttribute("usuario", modelGdLogin);
					
					if(url == null || url.equals("null")){
						url = "principal/principal.jsp";
						
					}
					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					redirecionar.forward(request, response);
				}else {
					RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
					request.setAttribute("msg", "informe o login e a senha coretamente");
					redirecionar.forward(request, response);
				}
					
			}else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "informe o login e a senha coretamente");
				redirecionar.forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg",e.getMessage());
			redirecionar.forward(request, response);
		}
		
		
		
		
		
	}

}
