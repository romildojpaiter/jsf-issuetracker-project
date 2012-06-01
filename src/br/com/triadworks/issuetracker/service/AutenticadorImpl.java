package br.com.triadworks.issuetracker.service;

import javax.inject.Inject;

import br.com.triadworks.issuetracker.dao.UsuarioDao;
import br.com.triadworks.issuetracker.model.Usuario;


public class AutenticadorImpl implements Autenticador {

	private UsuarioDao usuarioDao;
	
	@Inject
	public AutenticadorImpl(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	@Override
	public Usuario autentica(String login, String senha) {
		Usuario usuario = usuarioDao.buscaPor(login, senha);
		System.out.println("Usuario enontrado:"+usuario);
		return usuario;
	}

}



