package model;

public class ClassificadoraIp{
	
	private String ip;
	private int cidr;
	private int primeiroOcteto;
	private int segundoOcteto;
	private int terceiroOcteto;
	private int quartoOcteto;
	private StringBuilder mascaraBinaria;
	private String mascaraDecimal;
	private String ipClass;
	private String subClasse;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getCidr() {
		return cidr;
	}
	public void setCidr(int cidr) {
		this.cidr = cidr;
	}
	public int getPrimeiroOcteto() {
		return primeiroOcteto;
	}
	public void setPrimeiroOcteto(int primeiroOcteto) {
		this.primeiroOcteto = primeiroOcteto;
	}
	public StringBuilder getMascaraBinaria() {
		return mascaraBinaria;
	}
	public void setMascaraBinaria(StringBuilder mascaraBinaria) {
		this.mascaraBinaria = mascaraBinaria;
	}
	public String getMascaraDecimal() {
		return mascaraDecimal;
	}
	public void setMascaraDecimal(String mascaraDecimal) {
		this.mascaraDecimal = mascaraDecimal;
	}
	public String getIpClass() {
		return ipClass;
	}
	public void setIpClass(String ipClass) {
		this.ipClass = ipClass;
	}
	
	//Descobrir o que fazer nessa parte.
	public ClassificadoraIp(String ip, int cidr) {
		this.ip = ip;
		this.cidr = cidr;
		//Interger.parseInt() Transformar String em INT. (ip.split("\\.) [0]: Aqui a linha de código vai extrair o primeiro octeto de um endereço ip. A utilização do split("\\.") se deve para a separaçao da strig em questão em arrys de string que nesse caso como separador sera definido o ".". [0] Se faz presente para ele acessa o primeiro elemento do array que sera o primeiro octeto.
		this.primeiroOcteto = Integer.parseInt(ip.split("\\.") [0]);
		
}	    
	// Aqui defino caso o primeiro octeto seja => (Maior ou igual) && (E) <= (Menor ou igual) para receber sua devida classe.
	public void classificarIp() {
		if (primeiroOcteto >= 1 && primeiroOcteto <= 127) {
			ipClass = "A";
		} else if (primeiroOcteto >= 128 && primeiroOcteto <=191) {
			ipClass = "B";
		} else if (primeiroOcteto >= 192 && primeiroOcteto <=223) {
			ipClass = "C";
		} else if (primeiroOcteto >= 224 && primeiroOcteto <= 239) {
			ipClass = "D";
		} else if (primeiroOcteto >= 240 && primeiroOcteto <= 255) {
			ipClass = "E";
		} else if (primeiroOcteto > 255) {
			ipClass = "Invalido";
		}
		
		//Calculo para mostrar os IPs disponíveis
		int ipsDisponiveis = (int) Math.pow (2, 32 - cidr) - 2;
		System.out.println("Classe do Ip: " + ipClass);
		System.out.println("Ips disponíveis: " + ipsDisponiveis);
	}
	
	//geração da máscara de sub-rede
	private String gerarMascara(int cidr) {
		if (cidr < 0 | cidr > 32) {
			return "Mascara invalida";
		}
		//Stringbuilder me permite manipular dados de Strings dinamicamente, para criação de variaveis de string modificaveis.
		StringBuilder mascaraBin = new StringBuilder();{
			for (int i = 0; i < 32; i++) {
				if (i < cidr) {
					//.append: utilizado nesse contexto para adicionar dados ao objeto StringBuilder.
					mascaraBin.append("1");
				} else {
					mascaraBin.append("0");
				}
			}
			return mascaraBin.toString();
		}
	}
}
	



	    
