package model;

import model.ClassificadoraIp;

public class Main {	
    public static void main(String[] args) {
    	
    	ClassificadoraIp ip1 = new ClassificadoraIp();
    	
    	ip1.setPrimeiroOcteto("124");
    	ip1.setSegundoOcteto("168");
    	ip1.setTerceiroOcteto("242");
    	ip1.setQuartoOcteto("221");
    	ip1.setCidr(17);
    	
    	ip1.mostrarDados();
    }
}