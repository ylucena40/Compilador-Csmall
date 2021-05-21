package compiladorcsmall;

import java.io.PrintWriter;

public class AnalisadorSintatico {
    private AnalisadorLexico tokens = new AnalisadorLexico();
    private int pos;

    public AnalisadorSintatico(AnalisadorLexico token) {
        pos = 0;
        this.tokens = token;
    }

    public int Erro(PrintWriter gravarArq) {
        gravarArq.printf("Erro: Falha no token: %s\nNa linha: %d\n",tokens.getTokens().get(pos),
                tokens.getLinhaTokens().get(pos));
        System.out.println("Erro: Falha no token: " 
                + tokens.getTokens().get(pos) + "\nNa Linha: " 
                + tokens.getLinhaTokens().get(pos));
        return 0;
    }

     
    public int Programa(PrintWriter gravarArq) {
        if (tokens.getTokens().get(pos).equals("INT")) {
            pos++;
        } else {
            pos++;
            return Erro(gravarArq);
        }
        if (tokens.getTokens().get(pos).equals("MAIN")) {
            pos++;
        } else {
            pos++;
            return Erro(gravarArq);
        }
        if (tokens.getTokens().get(pos).equals("LBRACKET")) {
            pos++;
        } else {
            pos++;
            return Erro(gravarArq);
        }
        if (tokens.getTokens().get(pos).equals("RBRACKET")) {
            pos++;
        } else {
            pos++;
            return Erro(gravarArq);
        }
        if (tokens.getTokens().get(pos).equals("LBRACE")) {
            pos++;
        } else {
            pos++;
            return Erro(gravarArq);
        }
        while(Decl_Comando() != 1 || (pos + 1) < tokens.getTokens().size()) {
            Erro(gravarArq);
            pos++;
        }
        if (tokens.getTokens().get(pos).equals("RBRACE")) {
        } else {
            return Erro(gravarArq);
        }
        return 1;
    }
    
    public int Decl_Comando() {
        if (Declaracao() == 1) {
            return Decl_Comando();
        } else if (Comando() == 1) {
            return Decl_Comando();
        } else {
            return 1;
        }
    }
    
    public int Declaracao() {
        if (Tipo() == 1) {
            if (tokens.getTokens().get(pos) == "ID") {
                pos++;
                return Decl2();
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public int Decl2() {
        if (tokens.getTokens().get(pos) == "COMMA") {
            pos++;
            if (tokens.getTokens().get(pos) == "ID") {
                pos++;
                return Decl2();
            } else {
                return 0;
            }
        } else if (tokens.getTokens().get(pos) == "PCOMMA") {
            pos++;
            return 1;
        } else if (tokens.getTokens().get(pos) == "ATTR") {
            pos++;
            if (Expressao() == 1) {
                return Decl2();
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public int Tipo() {
        if (tokens.getTokens().get(pos) == "INT") {
            pos++;
            return 1;
        } else if (tokens.getTokens().get(pos) == "FLOAT") {
            pos++;
            return 1;
        } else {
            return 0;
        }
    }

    public int Comando() {
        if (Bloco() == 1) {
            return 1;
        } else if (Atribuicao() == 1) {
            return 1;
        } else if (ComandoSe() == 1) {
            return 1;
        } else if (ComandoEnquanto() == 1) {
            return 1;
        } else if (ComandoRead() == 1) {
            return 1;
        } else if (ComandoPrint() == 1) {
            return 1;
        } else if (ComandoFor() == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public int Bloco() {
        if (tokens.getTokens().get(pos) == "LBRACE") {
            pos++;
            if (Decl_Comando() == 1) {
                if (tokens.getTokens().get(pos) == "RBRACE") {
                    pos++;
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public int Atribuicao() {
        if (tokens.getTokens().get(pos) == "ID") {
            pos++;
            if (tokens.getTokens().get(pos) == "ATTR") {
                pos++;
                if (Expressao() == 1) {
                    if (tokens.getTokens().get(pos) == "PCOMMA") {
                        pos++;
                        return 1;
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public int ComandoSe() {
        if (tokens.getTokens().get(pos) == "IF") {
            pos++;
            if (tokens.getTokens().get(pos) == "LBRACKET") {
                pos++;
                if (Expressao() == 1) {
                    if (tokens.getTokens().get(pos) == "RBRACKET") {
                        pos++;
                        if (Comando() == 1) {
                            return ComandoSenao();
                        } else {
                            return 0;
                        }
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public int ComandoSenao() {
        if (tokens.getTokens().get(pos) == "ELSE") {
            pos++;
            return Comando();
        } else {
            return 1;
        }
    }

    public int ComandoEnquanto() {
        if (tokens.getTokens().get(pos) == "WHILE") {
            pos++;
            if (tokens.getTokens().get(pos) == "LBRACKET") {
                pos++;
                if (Expressao() == 1) {
                    if (tokens.getTokens().get(pos) == "RBRACKET") {
                        pos++;
                        return Comando();
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public int ComandoRead() {
        if (tokens.getTokens().get(pos) == "READ") {
            pos++;
            if (tokens.getTokens().get(pos) == "ID") {
                pos++;
                if (tokens.getTokens().get(pos) == "PCOMMA") {
                    pos++;
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public int ComandoPrint() {
        if (tokens.getTokens().get(pos) == "PRINT") {
            pos++;
            if (tokens.getTokens().get(pos) == "LBRACKET") {
                pos++;
                if (Expressao() == 1) {
                    if (tokens.getTokens().get(pos) == "RBRACKET") {
                        pos++;
                        if (tokens.getTokens().get(pos) == "PCOMMA") {
                            pos++;
                            return 1;
                        } else {
                            return 0;
                        }
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public int ComandoFor() {
        if (tokens.getTokens().get(pos) == "FOR") {
            pos++;
            if (tokens.getTokens().get(pos) == "LBRACKET") {
                pos++;
                if (AtribuicaoFor() == 1) {
                    if (tokens.getTokens().get(pos) == "PCOMMA") {
                        pos++;
                        if (Expressao() == 1) {
                            if (tokens.getTokens().get(pos) == "PCOMMA") {
                                pos++;
                                if (AtribuicaoFor() == 1) {
                                    if (tokens.getTokens().get(pos) == "RBRACKET") {
                                        pos++;
                                        return Comando();
                                    } else {
                                        return 0;
                                    }
                                } else {
                                    return 0;
                                }
                            } else {
                                return 0;
                            }
                        } else {
                            return 0;
                        }
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public int AtribuicaoFor() {
        if (tokens.getTokens().get(pos) == "ID") {
            pos++;
            if (tokens.getTokens().get(pos) == "ATTR") {
                pos++;
                return Expressao();
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public int Expressao() {
        if (Adicao() == 1) {
            return RelacaoOpc();
        } else {
            return 0;
        }
    }

    public int RelacaoOpc() {
        if (OpRel() == 1) {
            if (Adicao() == 1) {
                return RelacaoOpc();
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    public int OpRel() {
        if (tokens.getTokens().get(pos) == "LT") {
            pos++;
            return 1;
        } else if (tokens.getTokens().get(pos) == "LE") {
            pos++;
            return 1;
        } else if (tokens.getTokens().get(pos) == "GT") {
            pos++;
            return 1;
        } else if (tokens.getTokens().get(pos) == "GE") {
            pos++;
            return 1;
        } else {
            return 0;
        }
    }

    public int Adicao() {
        if (Termo() == 1) {
            return AdicaoOpc();
        } else {
            return 0;
        }
    }

    public int AdicaoOpc() {
        if (OpAdicao() == 1) {
            if (Termo() == 1) {
                return AdicaoOpc();
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    public int OpAdicao() {
        if (tokens.getTokens().get(pos) == "PLUS") {
            pos++;
            return 1;
        } else if (tokens.getTokens().get(pos) == "MINUS") {
            pos++;
            return 1;
        } else {
            return 0;
        }
    }

    public int Termo() {
        if (Fator() == 1) {
            return TermoOpc();
        } else {
            return 0;
        }
    }

    public int TermoOpc() {
        if (OpMult() == 1) {
            if (Fator() == 1) {
                return TermoOpc();
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    public int OpMult() {
        if (tokens.getTokens().get(pos) == "MULT") {
            pos++;
            return 1;
        } else if (tokens.getTokens().get(pos) == "DIV") {
            pos++;
            return 1;
        } else {
            return 0;
        }
    }

    public int Fator() {
        if (tokens.getTokens().get(pos) == "ID") {
            pos++;
            return 1;
        } else if (tokens.getTokens().get(pos) == "INTEGER_CONST") {
            pos++;
            return 1;
        } else if (tokens.getTokens().get(pos) == "FLOAT_CONST") {
            pos++;
            return 1;
        } else if (tokens.getTokens().get(pos) == "LBRACKET") {
            pos++;
            if (Expressao() == 1) {
                if (tokens.getTokens().get(pos) == "RBRACKET") {
                    pos++;
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
}
