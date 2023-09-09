package telas;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bd.Crud;

public class TelaLogin extends JFrame implements ActionListener{
    // Atributos encapsulados
    private JLabel label_username;
    private JTextField text_username;
    
    private JLabel label_password;
    private JPasswordField text_password;

    private JButton button_login;
    private JButton button_exit;

    private ResourceBundle bn;

    // construtor
    public TelaLogin(ResourceBundle bn){
        // Método "super"
        super("Template");
    
        // Atribuição do ResourceBundle
        this.bn = bn;

        // Criação do GUI inteiro
        /*
         * 3 layouts:
         * 1) GridLayout(rows, cols)
         * 2) FlowLayout()
         * 3) BorderLayout()
         *  - caixa.add(widget, type);
         *  - type: BorderLayout.[CENTER, EAST, WEST, NORTH, SOUTH]
        */

        Container box = getContentPane();
        box.setLayout(new GridLayout(3,1));


        // Separação do GUI
        /*
         * feita por JPanel (utilizando o layout neles)
         */
        JPanel username = new JPanel(new FlowLayout());
        JPanel password = new JPanel(new FlowLayout());
        JPanel buttons = new JPanel(new FlowLayout());

        // Criação dos widgets
        /*
         * JButton(text)
         * JLabel(text)
         * JTextField(text, columns)
         * 
         * 1. Setando fonte dos widgets
         * 2. Desabilitando edição do TextField
         * 3. Uso de ArrayList
         * 4. Colocar underline em textos de widgets
         * 5. Centralizar valores de textos
         * 6. Formatação de datas
         * 7. Formatação de horários
         * 8. JOptionPane
         * 9. JComboBox
         * 10. JPasswordField
         * 11. JTable
         * 
         * 
         * 1. Setando fonte dos widgets
         * widget.setFont(new Font(name, type, size))
         * ex: sair.setFont(new Font("Serif",  Font.PLAIN, 20))
         * 
         * 2. Desabilitando edição do TextField
         * widget.setEditable(false);
         * 
         * 3. Uso de ArrayList
         * ex: criação de uma lista de títulos onde cada texto dentro do widget estará centralizado
         * ArrayList<JLabel> titulos = new ArrayList<>(
         *  Arrays.asList(
         *      new JLabel("Matéria", SwingConstants.CENTER),
         *      new JLabel("P1", SwingConstants.CENTER), 
         *      new JLabel("P2", SwingConstants.CENTER),        
         *      new JLabel("P3", SwingConstants.CENTER), 
         *      new JLabel("P4", SwingConstants.CENTER)
         *  )
         * );
         * 
         * 4. Colocar underline em textos de widgets
         * ex: colocar o underline nos títulos criados acima
         * for (JLabel titulo : titulos) {
         *      Font font = titulo.getFont();
         *      Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
         *      attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
         *      titulo.setFont(font.deriveFont(attributes));
         * }
         * 
         * 5. Centralizar valores de textos
         * ex: em um arraylist de textfields:
         * for (JTextField linha : materias) {
         *     linha.setHorizontalAlignment(JTextField.CENTER);
         * }
         * 
         * 6. Datas
         * 6.1. Data → str
         * private SimpleDateFormat date_formatter = new SimpleDateFormat("dd-MM-yyyy");
         * date_formatter.format(Data data)
         * 6.2. str → Data
         * private SimpleDateFormat date_formatter = new SimpleDateFormat("dd-MM-yyyy");
         * date_formatter.parse(String data)
         * 
         * 7. Horários
         * 7.1. Time → str
         * private SimpleDateFormat date_formatter = new SimpleDateFormat("dd-MM-yyyy");
         * time_formatter.format(Time time)
         * 7.2. str → Time
         * private SimpleDateFormat date_formatter = new SimpleDateFormat("dd-MM-yyyy");
         * time_formatter.parse(String time)
         * 
         * 8. JOptionPane
         * 8.1. JOptionPane.showInputDialog
         * String nome = JOptionPane.showInputDialog(null, "Digite o nome do aluno: ");
         * 8.2. JOptionPane.showMessageDialog
         * JOptionPane.showMessageDialog(null, "Aluno não encontrado!");
         * 
         * 9. JComboBox
         * String names[] = {"name1", "name2", "name3"}
         * JComboBox cb = new JComboBox();
         * cb.setMaximumRowCount(3); // delimita o número que aparecerá por vez para o user; se quiser ver mais terá que scrollar
         * cb.addItemListener(
         *      new ItemListener(){
         *          public void itemStateChanged(ItemEvent event){
         *              if(event.getStateChange() == ItemEvent.SELECTED){
         *                  System.out.println("Item "+ cb.getSelectedIndex() + "foi selecionado");
         *              }
         *          }
         *      }
         * )
         * // adicionar o JComboBox no GUI
         * 
         * 10. JPasswordField
         * JPasswordField password = new JPasswordField(20);
         * 
         * 11. JTable
         * 11.1. Com uma matriz de objetos
         * String[] titles = {"Nome", "Nota Final", "Quantidade de Faltas"};
         * Object[][] data = {
         *      {"name1", "nota1", "falta1"},
         *      {"name2", "nota2", "falta2"},
         *      {"name3", "nota3", "falta3"},
         * };
         * JTable table = new JTable(data, titles);
         * JScrollPane scrollPane = new JScrollPane(table);
         * 11.2. Com um DefaultTableModel
         * String[] titles = {"Nome", "Nota Final", "Quantidade de Faltas"};
         * DefaultTableModel model = new DefaultTableModel(titles, 0);
         * model.addRow(new Object[]{"name1", "nota1", "falta1"});
         * model.addRow(new Object[]{"name2", "nota2", "falta2"});
         * model.addRow(new Object[]{"name3", "nota3", "falta3"});
         * JTable table = new JTable(model);
         * JScrollPane scrollPane = new JScrollPane(table);
         * // adicionar o scrollPane no GUI
         * 
         * 
         * 
         */
        label_username = new JLabel(this.bn.getString("tela_login.nome"));
        text_username = new JTextField(20);
        
        label_password = new JLabel(this.bn.getString("tela_login.senha"));
        text_password = new JPasswordField(20);

        button_login = new JButton(this.bn.getString("tela_login.entrar"));
        button_exit = new JButton(this.bn.getString("tela_login.sair"));

        // Fazendo a adição dos widgets nas separações feitas
        /*
         * 1. Preenchimento em um GridLayout 
         * 2. Pegando valor e alterando valor de um widget
         * 3. Interagindo com ArrayList
         * 
         * 1. Preenchimento em um GridLayout 
         * ao adicionar em um grid, adiciona preenchendo linha por linha (preenche a linha, dps vai pra próxima)
         * 
         * 2. Pegando valor e alterando valor de um widget
         * widget.getText() ; widget.setText(valor)
         * 
         * 3. Interagindo com ArrayList
         * 3.1. get:
         * arrayList.get(index);
         * 
         * 3.2. update:
         * arrayList.set(index, newValue);
         * 
         * 3.3. remove:
         * ValueType removedValue = arrayList.remove(index);
         * 
         * 3.4. add (in the end):
         * arrayList.add(value);
        */
        username.add(label_username);
        username.add(text_username);

        password.add(label_password);
        password.add(text_password);

        buttons.add(button_login);
        buttons.add(button_exit);

        // Fazendo a adição das partes do GUI no GUI
        box.add(username);
        box.add(password);
        box.add(buttons);
        
        // Adicionando ações aos botões
        /*
         * button.addActionListener(this);
        */
        button_login.addActionListener(this);
        button_exit.addActionListener(this);

        // Tamannho total da janela
        setSize(400, 250);

        // Se fechar a janela, o processo se encerra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Habilitando a visualização da janela
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button_login){
            String password = Crud.get_password(text_username.getText());
            if(!text_password.getText().equals(password)){
                JOptionPane.showMessageDialog(null, this.bn.getString("tela_login.cadastro.incorreto"));
            }
            else{
                JOptionPane.showMessageDialog(null, this.bn.getString("tela_login.cadastro.correto"));
                TelaDados tela_dados = new TelaDados(text_username.getText());
                this.dispose();
            }
        }else if(e.getSource() == button_exit){
            this.dispose();
            System.exit(0);
        }
    }
}



