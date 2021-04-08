package eleicao;

import java.util.Scanner;

/**
 *
 * @author Renan Siqueira
 */
public class Main {
    
    public static boolean busca_candidatos(int[] num, int v){
        
        for(int i = 0; i < num.length; i++){
            if(v == num[i]){
                return true;
            }
        }
        return false;
    }
    
    public static int posicao_candidatos(int[] num, int v){
        
        for(int i = 0; i < num.length; i++){
            if(v == num[i]){
                return i;
            }
        }
        return -1;
    }
    
    public static void ordenacao_por_bolha(String nomes[], int valores[], int numeros[]){ 
        
        int valAux, tam = valores.length;
        String nomeAux;
        int numAux;
        
        for(int i = 0; i <  tam - 1; i++)
            for(int j = 0; j < tam-i-1; j++)
                if(valores[j] > valores[j+1]){ 
                    valAux = valores[j]; 
                    valores[j] = valores[j+1];
                    valores[j+1] = valAux;                  
                  
                    nomeAux = nomes[j]; 
                    nomes[j] = nomes[j+1];
                    nomes[j+1] = nomeAux;
                    
                    numAux = numeros[j]; 
                    numeros[j] = numeros[j+1];
                    numeros[j+1] = numAux;
                }
    }
    
    public static void imprimeMaiores(String[] nomes, int[] valores, int numeros[]){
        
        ordenacao_por_bolha(nomes, valores, numeros);
        int n = valores.length;
        System.out.printf("Vetor de Valores: [%d", valores[0]);
        for(int i = 1; i < n; i++)
            System.out.printf(", %d", valores[i]);
        System.out.printf("]\n");   
        
        System.out.printf("Vetor de Nomes: [%s", nomes[0]);
        for(int i = 1; i < n; i++)
            System.out.printf(", %s", nomes[i]);
        System.out.printf("]\n");
            
        System.out.printf("Primeiro candidato: %s - %d votos\n",nomes[n-1], valores[n-1]);
        System.out.printf("Segundo candidato: %s - %d votos\n",nomes[n-2], valores[n-2]);
    }
    
    public static void main(String[] args){
    
        String[] candidatos = {"Rodrigo", "Ananias", "Rosangela", "Augusto", "Joana", "Eliana"};
        int[] numeros = {22, 98, 73, 41, 28, 11};
        int[] votos = new int[6];       
        int controle = 1;
        int totalVotos = 0;
        
        while(controle != 0){
            System.out.println("Numero de seu candidato: ");
            Scanner scan = new Scanner(System.in);
            int voto = scan.nextInt();
            controle = voto;
                        
            if(busca_candidatos(numeros, voto)){
               
                int posicao_candidatos = posicao_candidatos(numeros, voto);
                System.out.printf("O Numero " + voto + " eh o(a) candidato(a) " + candidatos[posicao_candidatos] + "\n");
                System.out.println("\nConfirmar seu voto? (S/N)");
                Scanner scan2 = new Scanner(System.in);
                String confirmacao = scan2.nextLine().toUpperCase();

                if(confirmacao.equals("S")){
                    totalVotos ++;
                    votos[posicao_candidatos] ++;
                    System.out.printf("Seu voto ao " + candidatos[posicao_candidatos] + " foi confirmado.\n");
                }         
            }        
            else{
                if(voto != 0){
                    System.out.println("Numero Invalido. Deseja anular seu voto? (S/N)");
                    Scanner scan2 = new Scanner(System.in);
                    String confirmacao = scan2.nextLine().toUpperCase();


                    if(confirmacao.equals("S")){
                        System.out.printf("Voto foi anulado.\n");
                    }
                }               
            } 
        }
                    
        imprimeMaiores(candidatos, votos, numeros);
                
        if(votos[votos.length-1] > totalVotos * 0.5){
            System.out.printf("O candidato " + candidatos[candidatos.length-1] + " foi eleito(a) o(a) prefeito(a).\n");
        }
        else{
            System.out.println("Inicio do segundo turno.\n");
            String[] segundoTurnoC = {candidatos[candidatos.length-1], candidatos[candidatos.length-2]};
            int[] segundoTurnoV = new int[2];
            int[] segundoTurnoN = {numeros[numeros.length-1], numeros[numeros.length-2]};
            controle = 1;
            
            while(controle != 0){
                System.out.println("Numero de seu candidato: ");
                Scanner scan = new Scanner(System.in);
                int voto = scan.nextInt();
                controle = voto;

                if(busca_candidatos(segundoTurnoN, voto)){
                    
                    int posicao_candidatos = posicao_candidatos(segundoTurnoN, voto);
                    System.out.printf("O Numero " + voto + " eh o(a) candidato(a) " + segundoTurnoC[posicao_candidatos] + "\n");
                    System.out.println("\nConfirmar seu voto? (S/N)");
                    Scanner scan2 = new Scanner(System.in);
                    String confirmacao = scan2.nextLine().toUpperCase();

                    if(confirmacao.equals("S")){
                        segundoTurnoV[posicao_candidatos] ++;
                        System.out.printf("Seu voto ao " + segundoTurnoC[posicao_candidatos] + " foi confirmado.\n");
                    }
                }        
                else{
                    if(voto != 0){
                        System.out.println("Numero Invalido. Deseja anular seu voto? (S/N)");
                        Scanner scan2 = new Scanner(System.in);
                        String confirmacao = scan2.nextLine().toUpperCase();

                        if(confirmacao.equals("S")){
                            System.out.printf("Voto foi anulado.\n");
                        }
                    }
                }
            }
            
            imprimeMaiores(segundoTurnoC, segundoTurnoV, segundoTurnoN);
            if(segundoTurnoV[segundoTurnoV.length-1] == segundoTurnoV[segundoTurnoV.length-2]){
                System.out.println("Em caso de empate, o mesmo será julgado pela Justica Eleitoral.");
            }
            else{
                System.out.printf("O candidato " + segundoTurnoC[segundoTurnoC.length-1] + " foi eleito(a) o(a) prefeito(a).\n");  
            }             
        }       
    }
}
