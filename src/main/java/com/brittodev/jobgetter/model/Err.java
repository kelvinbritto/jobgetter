package com.brittodev.jobgetter.model;

public class Err {

	private String campo;
	private String erro;

	public Err(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}
