package telas;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TelaLinguagem extends JFrame{
    // Atributos encapsulados
    private ResourceBundle bn = null;

    // construtor
    public TelaLinguagem(){
        super("Template - Tela Linguagem");
        
        // Se fechar a janela, o processo se encerra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Criando a seleção da linguagem
        int op = Integer.parseInt(JOptionPane.showInputDialog("Idioma - Language - Langue - Lingua\n\n1- Português\n2- English\n3- Française\n4-  Italiano\n"));

        // Seleciona a linguagem
        switch(op){
            case 1:
                bn = ResourceBundle.getBundle("linguagem.template");
                break;
            case 2:
                bn = ResourceBundle.getBundle("linguagem.template", Locale.US);
                break;
            case 3:
                bn = ResourceBundle.getBundle("linguagem.template", Locale.FRANCE);
                break;
            case 4:
                bn = ResourceBundle.getBundle("linguagem.template", Locale.ITALY);
                break;
            default:
                bn = ResourceBundle.getBundle("linguagem.template", new Locale("pt", "BR"));
                break;
        }
        TelaLogin login = new TelaLogin(bn);
    }

    
}



