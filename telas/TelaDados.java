package telas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bd.Crud;
import entidades.StudentData;

public class TelaDados extends JFrame implements ActionListener{
    // atributos encapsulados
    private StudentData studentData = new StudentData();
    private JLabel label;
    private JButton bCreate;
    private JButton bGet;
    private JButton bUpdate;
    private JButton bDelete;
    private JButton bGetAll;
    private JButton bExit;
    private ResourceBundle bn;
    // construtor
    public TelaDados(ResourceBundle bn){
        // Método "super"
        super("Template - Tela Login");
    
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
        box.setLayout(new BorderLayout());


        // Separação do GUI
        /*
         * feita por JPanel (utilizando o layout neles)
         */
        JPanel panel_label = new JPanel(new FlowLayout());
        JPanel panel_buttons = new JPanel(new FlowLayout());

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
         * 6.3. Pegar data atual
         * date_formatter.format(new Date())
         * 
         * 7. Horários
         * 7.1. Time → str
         * private SimpleDateFormat date_formatter = new SimpleDateFormat("dd-MM-yyyy");
         * time_formatter.format(Time time)
         * 7.2. str → Time
         * private SimpleDateFormat date_formatter = new SimpleDateFormat("dd-MM-yyyy");
         * time_formatter.parse(String time)
         * 7.3. Pegar tempo atual
         * time_formatter.format(new Date())
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
         * 11.3. Pegando valor da tabela
         * model.getValueAt(int linha, int coluna);
         * 
         * 
         */

        label = new JLabel(this.bn.getString("tela_dados.label"));
        bCreate = new JButton(this.bn.getString("tela_dados.create"));
        bGet = new JButton(this.bn.getString("tela_dados.get"));
        bUpdate = new JButton(this.bn.getString("tela_dados.update"));
        bDelete = new JButton(this.bn.getString("tela_dados.delete"));
        bGetAll = new JButton(this.bn.getString("tela_dados.get_all"));
        bExit = new JButton(this.bn.getString("tela_dados.sair"));


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
        panel_label.add(label);
        panel_buttons.add(bCreate);
        panel_buttons.add(bGet);
        panel_buttons.add(bUpdate);
        panel_buttons.add(bDelete);
        panel_buttons.add(bGetAll);
        panel_buttons.add(bExit);

        // Fazendo a adição das partes do GUI no GUI
        box.add(panel_label, BorderLayout.NORTH);
        box.add(panel_buttons, BorderLayout.CENTER);
        
        // Adicionando ações aos botões
        /*
         * button.addActionListener(this);
        */
        bCreate.addActionListener(this);
        bGet.addActionListener(this);
        bUpdate.addActionListener(this);
        bDelete.addActionListener(this);
        bGetAll.addActionListener(this);
        bExit.addActionListener(this);

        // Tamannho total da janela
        setSize(400, 250);

        // Se fechar a janela, o processo se encerra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Habilitando a visualização da janela
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.bCreate){
            float grade = Float.parseFloat(JOptionPane.showInputDialog(null, this.bn.getString("tela_dados.create.grade")));
            int idUser = Integer.parseInt(JOptionPane.showInputDialog(null, this.bn.getString("tela_dados.create.id_user"))); 
            this.studentData.setGrade(grade);
            this.studentData.setIdUser(idUser);
            Crud.create(this.studentData);
        } else if(e.getSource() == this.bGet){
            int id_grade = Integer.parseInt(JOptionPane.showInputDialog(null, this.bn.getString("tela_dados.get.id_grade")));
            this.studentData = Crud.get(id_grade);
            JOptionPane.showMessageDialog(null, this.studentData.toString());
        } else if(e.getSource() == this.bUpdate){
            float grade = Float.parseFloat(JOptionPane.showInputDialog(null, this.bn.getString("tela_dados.update.grade")));
            int idGrade = Integer.parseInt(JOptionPane.showInputDialog(null, this.bn.getString("tela_dados.update.id_grade"))); 
            this.studentData.setGrade(grade);
            this.studentData.setIdGrade(idGrade);
            Crud.update(this.studentData);
        } else if(e.getSource() == this.bDelete){
            int id_grade = Integer.parseInt(JOptionPane.showInputDialog(null, this.bn.getString("tela_dados.get.id_grade")));
            Crud.delete(id_grade);
        } else if(e.getSource() == this.bGetAll){
            DefaultTableModel model = new DefaultTableModel(new String[]{"id_grade", "id_user", "grade", "date", "time"}, 0);
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            for(StudentData data : Crud.get_all()){
                model.addRow(new Object[]{data.getIdGrade(), data.getIdUser(), data.getGrade(), data.getDateString(), data.getTimeString()});
            }
            JOptionPane.showMessageDialog(null, scrollPane);
        } else if(e.getSource() == this.bExit){
            this.dispose();
            System.exit(0);
        }
        
    }
}