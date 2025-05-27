package model;

public class ClassificadoraIp{
	
	private String ip;
	private int cidr;
	private String primeiroOcteto;
	private String segundoOcteto;
	private String terceiroOcteto;
	private String quartoOcteto;
	private String mascaraBinaria;
	private String mascaraDecimal;
	private String ipClasse;
	private double ipsubClasse;
	
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
		if (cidr < 0 || cidr> 32) {
			throw new IllegalArgumentException("CIDR incorreto: seu cidr deve ser entre 0 a 32");
		}
		this.cidr = cidr;
	}
	public int getPrimeiroOcteto() {
		int primeiroOctetoInt = Integer.parseInt(primeiroOcteto);
		return primeiroOctetoInt;
	}
	public void setPrimeiroOcteto(String primeiroOcteto) {
		this.primeiroOcteto = primeiroOcteto;
	}
	public String getMascaraBinaria() {
		return mascaraBinaria;
	}
	public void setMascaraBinaria(String mascaraBinaria) {
		this.mascaraBinaria = mascaraBinaria;
	}
	public String getMascaraDecimal() {
		return mascaraDecimal;
	}
	public void setMascaraDecimal(String mascaraDecimal) {
		this.mascaraDecimal = mascaraDecimal;
	}
	public String getIpClasse() {
		
		int primeiroOctetoInt = getPrimeiroOcteto();
		if(primeiroOctetoInt >= 1 && primeiroOctetoInt <= 126)
			ipClasse = "A";
		else if (primeiroOctetoInt >= 128 && primeiroOctetoInt <= 191)
			ipClasse = "B";
		else if (primeiroOctetoInt >= 192 && primeiroOctetoInt <= 223)
			ipClasse = "C";
		else if (primeiroOctetoInt >= 224 && primeiroOctetoInt <= 239)
			ipClasse = "D";
		else if (primeiroOctetoInt >= 240 && primeiroOctetoInt <= 255)
			ipClasse = "E";
		else if (primeiroOctetoInt <1 || primeiroOctetoInt > 255)
			throw new IllegalArgumentException("Não é possivel encontrar endereço IP utilizando-se um numero menor que 1 e maior que 255, por favor coloque um numero valido.");
		return ipClasse;
	}
	
	public int getSegundoOcteto() {
		int segundoOctetoInt = Integer.parseInt(segundoOcteto);
		return segundoOctetoInt;
	}
	
	public void setSegundoOcteto (String segundoOcteto) {
	this.segundoOcteto = segundoOcteto;
	}
	
	public int getTerceiroOcteto() {
		int terceiroOctetoInt = Integer.parseInt(terceiroOcteto);
		return terceiroOctetoInt;
	}
	
	public void setTerceiroOcteto (String terceiroOcteto) {
	this.terceiroOcteto = terceiroOcteto;
	}
	
	public int getQuartoOcteto() {
		int quartoOctetoInt = Integer.parseInt(quartoOcteto);
		return quartoOctetoInt;
	}
	
	public void setQuartoOcteto (String quartoOcteto) {
	this.quartoOcteto = quartoOcteto;
	}
	
	public double getSubClasse() {
		if (cidr > 32) {
			System.out.println("Cidr maior que 32, não pode ser utilizado para a realização da conta, por favor insira um numero menor ou igual a 32");
		} else if (cidr < 30) {
			ipsubClasse = Math.pow(2, 32 - cidr) - 2;
		} else {
			ipsubClasse = Math.pow(2, 32 - cidr);
		}
		
		return ipsubClasse;
	}
	
	public int getSubRedes() {
	    int cidrBase = 0;

	    switch (getIpClasse()) {
	        case "A":
	            cidrBase = 8;
	            break;
	        case "B":
	            cidrBase = 16;
	            break;
	        case "C":
	            cidrBase = 24; break;
	        default: return 0;
	    }
	    
	    if (cidr < cidrBase) {
	    	return 0;
	    }

	    return (int) Math.pow(2, cidr - cidrBase);
	}

	
	//geração da máscara de sub-rede
	private StringBuilder gerarMascaraBin(int cidr) {
		StringBuilder mascaraBinaria = new StringBuilder();
		for (int i = 0; i < 32; i++) {
			mascaraBinaria.append(i < cidr ? '1' : '0');
		}
		
		return mascaraBinaria;
	}
	
	public String gerarMascaraDecimal (StringBuilder binaria) {
		
		StringBuilder decimal = new StringBuilder();
		
		for (int i = 0; i < 4; i++) {
			String octeto = binaria.substring(i * 8, (i + 1) * 8);
			
			decimal.append(Integer.parseInt(octeto, 2));
			
			if (i < 3)
				decimal.append(".");
		}
		
		return decimal.toString();
	}
	
	public void mostrarDados() {
		
		mascaraBinaria = gerarMascaraBin(cidr).toString();
		mascaraDecimal = gerarMascaraDecimal(new StringBuilder(mascaraBinaria));
		ipClasse = getIpClasse();
	
		System.out.println("------------------------------------");
		System.out.println("IP informado: " + primeiroOcteto + "." + segundoOcteto + "." + terceiroOcteto + "."
				+ quartoOcteto + "/" + cidr);
		System.out.println("Primeiro octeto: " + primeiroOcteto);
		System.out.println("Classe do IP: " + "Classe " + ipClasse);
		System.out.println("Máscara binária: " + mascaraBinaria);
		System.out.println("Máscara decimal: " + mascaraDecimal);
		System.out.println("IPs por sub-rede com /" + cidr + ": " + getSubClasse() + " IPs disponíveis");
		System.out.println("Quantidade de sub-redes possíveis: " + getSubRedes());
		System.out.println("------------------------------------");
	}
	
	}
	



	    
	    