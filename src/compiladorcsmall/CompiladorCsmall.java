
package compiladorcsmall;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CompiladorCsmall {


    public static void main(String[] args) throws IOException {
        AnalisadorLexico token = new AnalisadorLexico();
        token.formaToken();
        String SToken = new String("Token");
        String SLexema = new String("Lexema");
        String SLinha = new String("Numero da Linha");
        String SFim = new String("EOF");
        
        FileWriter arq = new FileWriter("SaidaDoPrograma.txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        
        System.out.println("--------------------Token Buffer--------------------");
        gravarArq.printf("--------------------Token Buffer--------------------\n");
        System.out.println("----------------------------------------------------");
        gravarArq.printf("----------------------------------------------------\n");
        System.out.printf("%-20s |%-20s |%-20s\n",SToken,SLinha,SLexema);
        gravarArq.printf("%-20s |%-20s |%-20s\n",SToken,SLinha,SLexema);
        
        for (int i = 0; i < token.getTokens().size(); i++) {
            System.out.printf("%-21s %-21d %-20s\n",token.getTokens().get(i)
                    ,token.getLinhaTokens().get(i)
                    ,token.getLexemas().get(i));
            
            gravarArq.printf("%-21s %-21d %-20s\n",token.getTokens().get(i)
                    ,token.getLinhaTokens().get(i)
                    ,token.getLexemas().get(i));
        }
        System.out.printf("%-21s 0\n",SFim);
        gravarArq.printf("%-21s 0\n",SFim);
        System.out.println("----------------------------------------------------");
        gravarArq.printf("----------------------------------------------------\n");
        AnalisadorSintatico analisador = new AnalisadorSintatico(token);
        if(!token.getTokens().get(token.getLinhaTokens().size() - 1).equals("RBRACE")){
            System.out.println("Erro: Programa sem } de fechamento");
        }
        else
            analisador.Programa(gravarArq);
        
        gravarArq.close();
    }
    
}
