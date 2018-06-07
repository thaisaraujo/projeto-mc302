package Gerenciador;

import java.util.ArrayList;

import Entidades.Cliente;
import Entidades.ClienteGaragem;

public class GerenciadorClienteGaragem extends GerenciadorCliente {
	
	//Atributo Static
	private static ArrayList < ClienteGaragem > listClienteGaragem = new ArrayList < ClienteGaragem >();


	double precoGaragem[] = new double[4];
	
	
	protected double[] getPrecoGaragem() { //inicio do metodo getPrecoGaragem
		return precoGaragem;
	}//fim do metodo getPrecoGaragem

	protected void setPrecoGaragem(double[] precoGaragem) { //inicio do metodo setPrecoGaragem
		this.precoGaragem = precoGaragem;
	}//fim do metodo setPrecoGagram

	@Override
	public double calcularMontate(Cliente cliente) { //inicio do metodoCalculaMontante
		int periodo = ((ClienteGaragem) cliente).getPeriodo(); //posicao do vetor referente a posicao a ser paga
		
		/*perido em dias 
		* posicao 0 : 0 a 1 semanas
		* posicao 1 : 1 a 2 semanas
		* posicao 2 : 2 a 3 semanas
		* posicao 3 : 3 a 4 semanas
		*/
		
		if (periodo <= 7) {
				return precoGaragem[0];
		}else if(periodo > 7 && periodo <= 15) {
			return precoGaragem[1];
		}else if(periodo > 15 && periodo <= 21) {
			return precoGaragem[2];
		}else if(periodo > 21 && periodo <= 28) {
			return precoGaragem[3];
		}
		return -1.0; //periodo não reconhecido pelo sistema
	
	}//fim do metodoCalculaMontante

	@Override
	public void imprimirMontate(Cliente cliente) { //inicio do metodo imprimirMontante
		
		System.out.printf("Total (Aluguel): %d", calcularMontate(cliente));
		
	}//fim do método imprimirMontate

	@Override
	public void cadastrarCliente(Cliente cliente) { //inicio do metodo cadastraCliente
		boolean resIdade = cliente.calcularIdade(cliente.getDataDeNascimento());
		
		if(resIdade == true) {
			listClienteGaragem.add((ClienteGaragem) cliente);
		}
				
	}//fim do método cadastraCliente

	@Override
	public void descadastrarCliente(Cliente cliente) { //inicio do metodo descadastrarCliente
		listClienteGaragem.remove(cliente);
	}//fim do metodo descadastrarCliente

	@Override
	public Cliente buscaCliente(String cpf) { //inicio metodo buscaCliente pelo cpf
		
		for(ClienteGaragem busca : listClienteGaragem) {
				
			if(busca.getCPF().compareToIgnoreCase(cpf) == 0) {
				return busca;
			}
		}
		
		return null;
	}//fim do metodo buscaCliente pelo cpf

	@Override
	public Cliente buscaCliente(String cpf, String placa) { //inicio metodo buscaCliente pelo cpf e carro
		String tempPlaca;
		
		for(ClienteGaragem busca : listClienteGaragem) {
			
			tempPlaca = busca.getCarro().getPlaca();
			
			if(busca.getCPF().compareToIgnoreCase(cpf) == 0 && tempPlaca.compareToIgnoreCase(placa) == 0) {
				return busca;
			}
		}
		
		return null;
	}//inicio metodo buscaCliente pelo cpf e carro

	
}//fim classe gerenciadorClienteGaragem
