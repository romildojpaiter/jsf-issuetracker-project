package br.com.triadworks.issuetracker.controller;

import javax.enterprise.inject.Produces;
import javax.faces.bean.SessionScoped;

import br.com.triadworks.issuetracker.model.Usuario;
import br.com.triadworks.issuetracker.qualifier.UsuarioLogado;

@SessionScoped
public class UsuarioWeb {

	private Usuario usuario;
	
	public void loga(Usuario usuario) {
		this.usuario = usuario;
	}

	public void logout() {
		this.usuario = null;
	}
	
	public boolean isLogado() {
		return this.usuario != null;
	}

	@Produces
	@UsuarioLogado
	public Usuario getUsuario() {
		return usuario;
	}
	
}
