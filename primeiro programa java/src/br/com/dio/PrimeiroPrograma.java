package br.com.dio;

import br.com.dio.model.Gato;

public class PrimeiroPrograma {

	public static void main(String[] args) {
		
		Gato gato = new Gato();
		
		Livros novo = new Livros();
		
		System.out.println(gato);
		System.out.println(novo);		
		
	}

}

class Livros {
	private String nome;
	private String npag;	
	
}
