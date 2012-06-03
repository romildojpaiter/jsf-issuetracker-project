package br.com.triadworks.issuetracker.service;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.triadworks.issuetracker.dao.UsuarioDao;
import br.com.triadworks.issuetracker.model.Usuario;

@Dependent
public class AutenticadorImpl implements Autenticador {

	private UsuarioDao usuarioDao;
	
	@Inject
	public AutenticadorImpl(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	@Override
	public Usuario autentica(String login, String senha) {
		Usuario usuario = usuarioDao.buscaPor(login, senha);
		return usuario;
	}

}



