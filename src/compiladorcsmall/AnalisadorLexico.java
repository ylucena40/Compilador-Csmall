package compiladorcsmall;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnalisadorLexico {
    int TAM = 5000;
    int linha = 1;
    private ArrayList<String> tokens = new ArrayList<String>();
    private ArrayList<String> lexemas = new ArrayList<String>();
    private ArrayList<Integer> linhaTokens = new ArrayList<Integer>();

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public boolean palavraReservada(String palavra) {
        if (palavra.equals("main")) {
            this.tokens.add("MAIN");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals("int")) {
            this.tokens.add("INT");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals("float")) {
            this.tokens.add("FLOAT");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals("if")) {
            this.tokens.add("IF");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals("else")) {
            this.tokens.add("ELSE");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals("while")) {
            this.tokens.add("WHILE");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals("for")) {
            this.tokens.add("FOR");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals("read")) {
            this.tokens.add("READ");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals("print")) {
            this.tokens.add("PRINT");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        }
        return false;
    }

    public boolean palavraConst(String palavra) {
        char[] palavraArray = palavra.toCharArray();
        {

            int j;
            if(!palavra.isEmpty()){
                if (palavraArray[0] < '0' || palavraArray[0] > '9') {
                    return false;
                }

                for (j = 0; j < palavraArray.length; j++) {
                    if (palavraArray[j] == '.') {
                        this.tokens.add("FLOAT_CONST");
                        this.lexemas.add(palavra);
                        this.linhaTokens.add(this.linha);
                        return true;

                    }

                }
                if (j == palavraArray.length) {
                    this.tokens.add("INTEGER_CONST");
                    this.lexemas.add(palavra);
                    this.linhaTokens.add(this.linha);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean palavraID(String palavra) {
        char[] palavraArray = palavra.toCharArray();
        if(!palavra.isEmpty()){
            if ((palavraArray[0] >= 'A' && palavraArray[0] <= 'Z')
                    || (palavraArray[0] >= 'a' && palavraArray[0] <= 'z')) {
                this.tokens.add("ID");
                this.lexemas.add(palavra);
                this.linhaTokens.add(this.linha);
                return true;
            }
        }
        return false;
    }

    public boolean palavraToken(String palavra) {
        if (palavra.equals("(")) {
            this.tokens.add("LBRACKET");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals(")")) {
            this.tokens.add("RBRACKET");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals("{")) {
            this.tokens.add("LBRACE");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals("}")) {
            this.tokens.add("RBRACE");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals(",")) {
            this.tokens.add("COMMA");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals(";")) {
            this.tokens.add("PCOMMA");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals("=")) {
            this.tokens.add("ATTR");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals("<")) {
            this.tokens.add("LT");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals("<=")) {
            this.tokens.add("LE");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals(">")) {
            this.tokens.add("GT");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals(">=")) {
            this.tokens.add("GE");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals("+")) {
            this.tokens.add("PLUS");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals("-")) {
            this.tokens.add("MINUS");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals("*")) {
            this.tokens.add("MULT");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals("/")) {
            this.tokens.add("DIV");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals("[")) {
            this.tokens.add("LCOL");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        } else if (palavra.equals("]")) {
            this.tokens.add("RCOL]");
            this.lexemas.add(palavra);
            this.linhaTokens.add(this.linha);
            return true;
        }
        return false;

    }

    public void formaToken() {
        Scanner ler = new Scanner(System.in);

        System.out.printf("Informe o nome de arquivo texto:\n");
        String nome = ler.nextLine();

        try {
            FileReader arq = new FileReader(nome);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();
            
            while (linha != null) {
                char[] linhaArray = linha.toCharArray();
                String palavraDaVez = new String();
                int contador = 0;
                
                for (int i = 0; i < linhaArray.length; i++) {
                    if (linhaArray[i] != ' ') {
                        if (i + 1 <= linhaArray.length) {
                            if (linhaArray[i] != ',' && linhaArray[i] != ';' && linhaArray[i] != ')') {
                                palavraDaVez += linhaArray[i];
                            }
                        }

                        if (palavraReservada(palavraDaVez)) {
                            contador = 0;
                            palavraDaVez = new String();
                        } else if ((linhaArray[i] == '>' && linhaArray[i + 1] == '=')
                                || (linhaArray[i] == '<' && linhaArray[i + 1] == '=')) {
                            i++;
                            palavraDaVez += linhaArray[i];
                            if (palavraToken(palavraDaVez)) {
                                contador = 0;
                                palavraDaVez = new String();

                            }
                        } else if (linhaArray[i] == '='){
                            if(linhaArray[i+1] == '=')
                                i++;
                            if(palavraToken(palavraDaVez)) {
                            contador = 0;
                            palavraDaVez = new String();
                            }
                        }
                        else if(palavraToken(palavraDaVez)) {
                            contador = 0;
                            palavraDaVez = new String();
                        }

                    }
                    if (i + 1 == linhaArray.length) {
                        if (linhaArray[i] == ')') {
                            palavraDaVez += linhaArray[i];
                            if (palavraToken(palavraDaVez)) {
                                contador = 0;
                                palavraDaVez = new String();
                            }
                        }
                        if (linhaArray[i] == ';') {
                            palavraDaVez += linhaArray[i];
                            if (palavraToken(palavraDaVez)) {
                                contador = 0;
                                palavraDaVez = new String();
                            }
                        }

                    }
                    if (i + 1 < linhaArray.length) {
                        if(linhaArray[i] == ')' && (palavraDaVez.isEmpty())){
                            palavraDaVez += linhaArray[i];
                            if (palavraToken(palavraDaVez)) {
                                contador = 0;
                                palavraDaVez = new String();
                            }
                        }
                        if(linhaArray[i+1] == '*'){
                            if (palavraID(palavraDaVez)) {
                                contador = 0;
                                palavraDaVez = new String();
                            }else if (palavraConst(palavraDaVez)) {
                                contador = 0;
                                palavraDaVez = new String();
                            }
                        }
                        if (linhaArray[i + 1] == ',') {
                            if (palavraID(palavraDaVez)) {
                                contador = 0;
                                palavraDaVez = new String();
                            }
                            else if (palavraConst(palavraDaVez)) {
                                contador = 0;
                                palavraDaVez = new String();
                            }
                        }
                        if (linhaArray[i + 1] == ';') {
                            if (palavraConst(palavraDaVez)) {
                                contador = 0;
                                palavraDaVez = new String();
                            } else if (palavraID(palavraDaVez)) {
                                contador = 0;
                                palavraDaVez = new String();
                            } else if (palavraToken(palavraDaVez)) {
                                contador = 0;
                                palavraDaVez = new String();
                            }

                        } else if (linhaArray[i] == ';') {
                            palavraDaVez += linhaArray[i];
                            if (palavraToken(palavraDaVez)) {
                                contador = 0;
                                palavraDaVez = new String();
                            }
                        }
                        if (linhaArray[i + 1] == ')' && (!palavraDaVez.isEmpty())) {
                            if (palavraID(palavraDaVez)) {
                                contador = 0;
                                palavraDaVez = new String();
                            } else if (palavraConst(palavraDaVez)) {
                                contador = 0;
                                palavraDaVez = new String();
                            }
                        }
                        if (linhaArray[i + 1] == '}') {
                            if (palavraToken(palavraDaVez)) {
                                contador = 0;
                                palavraDaVez = new String();
                            }
                        }
                    }
                    if ((linhaArray[i] == ' ' || linhaArray[i] == ','
                            || linhaArray[i] == '=' || linhaArray[i] == ';')
                            && (!palavraDaVez.isEmpty())) {
                        if (palavraID(palavraDaVez)) {

                            contador = 0;
                            palavraDaVez = new String();
                        } else if (palavraConst(palavraDaVez)) {
                            contador = 0;
                            palavraDaVez = new String();
                        }
                    }
                    if (contador == 1 && linhaArray[i] == ',') {
                        palavraDaVez += linhaArray[i];
                        i--;
                    }
                    contador++;

                }
                //System.out.printf("%s\n", linha);
                linha = lerArq.readLine(); // lê da segunda até a última linha
                this.linha++;
            }

            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

        System.out.println();

    }

    public ArrayList<String> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<String> tokens) {
        this.tokens = tokens;
    }

    public ArrayList<String> getLexemas() {
        return lexemas;
    }

    public void setLexemas(ArrayList<String> lexemas) {
        this.lexemas = lexemas;
    }

    public ArrayList<Integer> getLinhaTokens() {
        return linhaTokens;
    }

    public void setLinhaTokens(ArrayList<Integer> linhaTokens) {
        this.linhaTokens = linhaTokens;
    }
}
