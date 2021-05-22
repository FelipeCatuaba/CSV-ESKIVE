import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;



public class LeitorCSV {

  public static boolean checkFirstLine(String teste) {
    String[] cabecalho = {"name", "surname", "email", "phone_number", "department", "position"};
    String[] r = teste.split(",");
    int c = 0;
    while (c < r.length) {
      if (!r[c].equals(cabecalho[c])) {
        return false;
      }
      c++;
    }
    return true;
  }
  
  public static int checkLine(String linha){
	    if(linha.endsWith(",")) linha+="N/A"; //se linha termina com vírgula
	    String[] r =  linha.split(",");
	    for(int i = 0; i < r.length;i++){
	      if(i == 0) { 
	    	  if(r[i].equals("")) {
	    		  return 1; //name vazio
	    	  }
	      }
	      
	      if(i == 1) { 
	    	  if(r[i].equals("")) {
	    		  return 2; // surname vazio
	    	  }
	      }
	      
	      if(i == 2) { 
	    	  if(r[i].equals("")) {
	    		  return 3; //email vazio
	    	  }
	      }
	      
	      if(i == 2){
	    	  if(!emailValido(r[i])){
	    		  return 4; //email invalido
	    	  }
	      }
	      
	      if(i == 4) { 
	    	  if(r[i].equals("")) {
	    		  return 5;
	    	  }
	      }
	      
	      }
	    return 0;
	  }
	  
	  public static boolean emailValido(String email){
		    return Pattern.matches("^([a-z0-9_\\.\\+-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$", email);
	  }
	  
	  
	    
	  public static void main(String[] args) {

	    String path = "Desafio-CSV.csv";
	    String linha = "";
	    String erros = "";
	    

	    BufferedReader br;
	    try {
	      br = new BufferedReader(new FileReader(path));
	      int n = 0;
	      while ((linha = br.readLine()) != null) {
	          int erro;
	          System.out.println(n+" - "+linha);
	          if (n == 0) { //checando primeira linha
	            if (!checkFirstLine(linha)) {
	              erros+="Cabeçalho Inválido\n";
	            }
	          } else { //checar outras linhas
	            erro = checkLine(linha);
	            if(erro == 1){
	            	erros+="Linha "+n+"- Campos name vazio\n";
	            }else if(erro == 2){
	            	erros+="Linha "+n+" - Campo surname vazio\n";
	            }else if(erro == 3){
		            erros+="Linha "+n+" - Campo email vazio\n";
	            }else if(erro == 4){
	            	erros+="Linha "+n+" - Email inválido\n";
	            }else if(erro == 5){
	            	erros+="Linha "+n+" - Campo departament vazio\n";
	            }
	            if (checkLine(linha) != 0) {
	              
	            }
	          }
	          n++;
	      }
	      System.out.println("\nErros: \n\n"+erros);
	      
	      System.out.println("Por favor verifique os dados inseridos.");
      
    } catch (FileNotFoundException ex) {
      Logger.getLogger(LeitorCSV.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(LeitorCSV.class.getName()).log(Level.SEVERE, null, ex);
    }

  }
  }