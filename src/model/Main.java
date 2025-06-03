package model;

import model.ClassificadoraIp;
import view.ClassificadoraIpGui;

public class Main {	
    public static void main(String[] args) {
    	
    	ClassificadoraIpGui app = new ClassificadoraIpGui();
        app.setVisible(true);
    	
    	ClassificadoraIp ip1 = new ClassificadoraIp();
    	
//    	ip1.setPrimeiroOcteto("192");
//    	ip1.setSegundoOcteto("168");
//    	ip1.setTerceiroOcteto("2");
//    	ip1.setQuartoOcteto("2");
//    	ip1.setCidr(24);
//    	
//    	ip1.setIp(ip1.getPrimeiroOcteto() + "." + ip1.getSegundoOcteto() + "." + ip1.getTerceiroOcteto() + "." + ip1.getQuartoOcteto());
//
//    	
//    	ip1.mostrarDados();
//    	ip1.listarSubRedes();
    }
}