package telas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

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
    private SimpleDateFormat time_formatter = new SimpleDateFormat("HH:mm:ss");
    private SimpleDateFormat date_formatter = new SimpleDateFormat("dd-MM-yyyy");
    private Object[][] data;
    private String[] titles = {"Nome", "Nota Final", "Quantidade de Faltas"};
    private JButton exit;    
    private JButton atualizaDB;
    private JButton lerDB;
    private StudentData studentData;
    private DefaultTableModel model;
    private JLabel value_date;
    private JLabel value_time;
    // construtor
    public TelaDados(String nome){
        // método "super"
        super("Tabela - Notas e Faltas - Aula 14");
        
        // pegando os dados iniciais do aluno
        int id = Crud.get_id_by_name(nome);
        studentData = new StudentData(nome, id);
        studentData = Crud.get_student_data(studentData);
        data = new Object[][]{{studentData.getName(), studentData.getFinal_grade(), studentData.getMiss()}};

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
        JPanel labels = new JPanel(new GridLayout(2,2));
        JPanel table_background = new JPanel(new GridLayout(1,1));
        JPanel buttons = new JPanel(new FlowLayout());
        
        
        // Criação dos widgets
        /*
        * JButton(text)
        * JLabel(text)
         * JTextField(text, columns)
         * 
         * widget.setFont(new Font(name, type, size))
         * ex: sair.setFont(new Font("Serif",  Font.PLAIN, 20))
         * 
         */
        JLabel label_date = new JLabel("Data: " + date_formatter.format(studentData.getDate()));
        value_date = new JLabel(date_formatter.format(studentData.getDate()));
        JLabel label_time = new JLabel("Horário: " + time_formatter.format(studentData.getTime()));
        value_time = new JLabel(time_formatter.format(studentData.getTime()));
        
        model = new DefaultTableModel(titles, 0);
        model.addRow(new Object[]{studentData.getName(), studentData.getFinal_grade(), studentData.getMiss()});
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        

        exit = new JButton("Sair");
        atualizaDB = new JButton("Atualizar DB");
        lerDB = new JButton("Ler DB");
        
        // Fazendo a adição dos widgets nas separações feitas
        /* 
         * ao adicionar em um grid, adiciona preenchendo linha por linha (preenche a linha, dps vai pra próxima)
        */
        labels.add(label_date);
        labels.add(value_date);
        labels.add(label_time);
        labels.add(value_time);
        
        table_background.add(scrollPane);
        
        buttons.add(exit);
        buttons.add(atualizaDB);
        buttons.add(lerDB);

        // Fazendo a adição das partes do GUI no GUI
        box.add(labels, BorderLayout.NORTH);
        box.add(table_background, BorderLayout.CENTER);
        box.add(buttons, BorderLayout.SOUTH);

        // Adicionando ações aos botões
        /*
         * button.addActionListener(this);
        */
        exit.addActionListener(this);
        atualizaDB.addActionListener(this);
        lerDB.addActionListener(this);

        // Tamannho total da janela
        setSize(500, 150);

        // Se fechar a janela, o processo se encerra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Habilitando a visualização da janela
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == atualizaDB){
            studentData.setFinal_grade(Float.parseFloat(model.getValueAt(0, 1).toString()));
            studentData.setMiss(Integer.parseInt(model.getValueAt(0, 2).toString()));
            Crud.update_student_data(studentData);
            JOptionPane.showMessageDialog(null, "Banco de dados atualizado com sucesso!");
        }else if(e.getSource() == lerDB){
            studentData = Crud.get_student_data(studentData);
            model.setValueAt(studentData.getName(), 0, 0);
            model.setValueAt(studentData.getFinal_grade(), 0, 1);
            model.setValueAt(studentData.getMiss(), 0, 2);
            value_date.setText(date_formatter.format(studentData.getDate()));
            value_time.setText(time_formatter.format(studentData.getTime()));
        } else if(e.getSource() == exit){
            this.dispose();
            System.exit(0);
        }
    }
}