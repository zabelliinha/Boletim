//BIBLIOTECAS

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.awt.Color;

//CLASSE PRINCIPAL
public class Main extends JFrame {

    //INSTÂNCIAS
    private List<Aluno> alunos;
    private JTextArea texto = new JTextArea();
    private JComboBox<String> comboBox;
    private JButton button;
    private JTextField textField;
    private JTextField textField1;
    private JTextField textFielda1p;
    private JTextField textFielda2p;
    private JTextField textFielda3p;
    private JTextField textFieldaSp;
    private JTextField textFielda1ms;
    private JTextField textFielda2ms;
    private JTextField textFielda3ms;
    private JTextField textFieldaSms;
    private JTextField textFielda4ms;
    private JTextField textFielda5ms;
    private JTextField textFielda6ms;
    private JTextField textFielda1ms2;
    private JTextField textFielda2ms2;
    private JTextField textFielda3ms2;
    private JTextField textFieldaSms2;
    private JTextField textFielda4ms2;
    private JTextField textFielda5ms2;
    private JTextField textFielda6ms2;
    private static String alunoSelecionado;

    //CONSTRUTOR DA CLASSE MAIN, INICIALIZA OS COMPONENTES NA INTERFACE GRÁFICA
    public Main() {
        super("Boletim Escolar");
        setContentPane(new JPanel(new FlowLayout()));
        ComboBox();
        Botao1();
        Botao2();
        Botao3();
        Botao4();
        Botao5();
        CampoAluno();
        PainelNotas();
        montaJanela();
        alunoSelecionado();
        labelSituacao();
        recarregarDados();
    }

    //MÉTODOS VAZIOS
    private void labelSituacao() {
    }

    private void alunoSelecionado() {
    }

    // CLASSE ADICIONAR ALUNO PARA CRIAR UMA NOVA JANELA JFRAME
    public class AdicionarAlunoFrame extends JFrame {
        private JTextField textFieldName;
        private JTextField textFieldCurso;
        private JTextField textFielda1ms;
        private JTextField textFielda2ms;
        private JTextField textFielda3ms;
        private JTextField textFielda4ms;
        private JTextField textFielda5ms;
        private JTextField textFielda6ms;
        private JButton button;

        public AdicionarAlunoFrame() {
            super("Adicionar Aluno");

            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(200, 30));

            JLabel label5 = new JLabel("Nome do aluno:");
            textFieldName = new JTextField();
            configurePlaceholder(textFieldName, "Digite o nome do aluno");

            JLabel label6 = new JLabel("Curso do aluno:");
            textFieldCurso = new JTextField();
            configurePlaceholder(textFieldCurso, "Digite o curso do aluno");

            JPanel panel2 = new JPanel(new GridLayout(3, 5));
            JLabel labelMateria = new JLabel("Matéria:");
            labelMateria.setHorizontalAlignment(SwingConstants.CENTER);
            panel2.add(labelMateria);
            JLabel labelA1 = new JLabel("A1");
            labelA1.setHorizontalAlignment(SwingConstants.CENTER);
            panel2.add(labelA1);
            JLabel labelA2 = new JLabel("A2");
            labelA2.setHorizontalAlignment(SwingConstants.CENTER);
            panel2.add(labelA2);
            JLabel labelA3 = new JLabel("A3");
            labelA3.setHorizontalAlignment(SwingConstants.CENTER);
            panel2.add(labelA3);
            panel2.add(new JLabel("Modelagem de Software"));

            textFielda1ms = new JTextField();
            configurePlaceholder(textFielda1ms, "Insira a nota da avaliação 1");
            panel2.add(textFielda1ms);

            textFielda2ms = new JTextField();
            configurePlaceholder(textFielda2ms, "Insira a nota da avaliação 2");
            panel2.add(textFielda2ms);

            textFielda3ms = new JTextField();
            configurePlaceholder(textFielda3ms, "Insira a nota da avaliação 3");
            panel2.add(textFielda3ms);

            panel2.add(new JLabel("Programação de soluções computacionais"));

            textFielda4ms = new JTextField();
            configurePlaceholder(textFielda4ms, "Insira a nota da avaliação 1");
            panel2.add(textFielda4ms);

            textFielda5ms = new JTextField();
            configurePlaceholder(textFielda5ms, "Insira a nota da avaliação 2");
            panel2.add(textFielda5ms);

            textFielda6ms = new JTextField();
            configurePlaceholder(textFielda6ms, "Insira a nota da avaliação 3");
            panel2.add(textFielda6ms);

            // CONFIGURAR BOTAO NO MEIO
            JPanel panel3 = new JPanel(new GridLayout(1, 1));
            button = new JButton("Adicionar aluno");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    salvarDados();
                    alunoAdicionado frame = new alunoAdicionado();
                    frame.setVisible(true);
                    recarregarDados();
                    configurePlaceholder(textFieldName, "Digite o nome do aluno");
                    configurePlaceholder(textFieldCurso, "Digite o curso do aluno");
                    configurePlaceholder(textFielda1ms, "Insira a nota da avaliação 1");
                    configurePlaceholder(textFielda2ms, "Insira a nota da avaliação 2");
                    configurePlaceholder(textFielda3ms, "Insira a nota da avaliação 3");
                    configurePlaceholder(textFielda4ms, "Insira a nota da avaliação 1");
                    configurePlaceholder(textFielda5ms, "Insira a nota da avaliação 2");
                    configurePlaceholder(textFielda6ms, "Insira a nota da avaliação 3");
                }
            });
            panel3.add(button);

            // ADICIONA OS COMPONENTES NESSE PAINEL
            panel.add(label5);
            panel.add(textFieldName);
            panel.add(label6);
            panel.add(textFieldCurso);
            panel.add(panel3);
            panel.add(panel2);
            getContentPane().add(panel);
            setSize(1280, 200);
            setLocationRelativeTo(null);
            setVisible(true);
        }

        //MÉTODO QUE CONECTA O CÓDIGO COM O BANCO DE DADOS
        private void salvarDados() {
            String nomeAluno = textFieldName.getText();
            String cursoAluno = textFieldCurso.getText();
            String a1ms = textFielda1ms.getText();
            String a2ms = textFielda2ms.getText();
            String a3ms = textFielda3ms.getText();
            String a1psc = textFielda4ms.getText();
            String a2psc = textFielda5ms.getText();
            String a3psc = textFielda6ms.getText();

            try {
                Connection connection = MySQLConnection.getConnection();

                Statement statement = connection.createStatement();

                String insertQuery = "INSERT INTO alunos (nome_aluno, curso_aluno, a1ms, a2ms, a3ms, a1psc, a2psc, a3psc) " + "VALUES ('" + nomeAluno + "', '" + cursoAluno + "', '" + a1ms + "', '" + a2ms + "', '" + a3ms + "', '" + a1psc + "', '" + a2psc + "', '" + a3psc + "')";

                statement.executeUpdate(insertQuery);

                System.out.println("Dados inseridos com sucesso!");

                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //PLACE HOLDER
    private void configurePlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }

    // JANELA QUE APARECE QUANDO O ALHNO FOR REMOVIDO
    public class alunoRemovido extends JFrame {
        public alunoRemovido() {
            super("Aluno removido");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JPanel panel = new JPanel(new GridLayout(2, 1));
            JLabel label = new JLabel(alunoSelecionado + " removido com sucesso");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(label);
            button = new JButton("OK");
            panel.add(button);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            getContentPane().add(panel);
            setSize(350, 100);
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    //JANELA QUE CONFIRMA SE O ALUNO FOI ADICIONADO OU NÃO
    public class alunoAdicionado extends JFrame {
        public alunoAdicionado() {
            super("Aluno Adicionado");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JPanel panel = new JPanel(new GridLayout(2, 1));
            JLabel label = new JLabel("Aluno adicionado com sucesso");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(label);
            button = new JButton("OK");
            panel.add(button);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            getContentPane().add(panel);
            setSize(350, 100);
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    //JANELA QUE CONFIRMA SE O ALUNO FOI EDITADO OU NÃO
    public class notasEditadas extends JFrame {
        public notasEditadas() {
            super("Notas editadas");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JPanel panel = new JPanel(new GridLayout(2, 1));
            JLabel label = new JLabel("Notas atualizadas com sucesso");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(label);
            button = new JButton("OK");
            panel.add(button);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            getContentPane().add(panel);
            setSize(350, 100);
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }


    //JANELA DE ERRO QUANDO NADA FOR SELECIONADO
    public class erro extends JFrame {
        public erro() {
            super("Erro");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Não é possivel apagar " + alunoSelecionado);
            panel.add(label);
            button = new JButton("OK");
            panel.add(button);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            getContentPane().add(panel);
            setSize(350, 100);
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    public class erro2 extends JFrame {
        public erro2() {
            super("Erro");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Selecione um aluno");
            panel.add(label);
            button = new JButton("OK");
            panel.add(button);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            getContentPane().add(panel);
            setSize(350, 100);
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    //JANELA PARA EDITAR NOTAS DE UM ALUNO ADICIONADO
    public class EditarAlunoFrame extends JFrame {
        private JPanel panel;
        private JComboBox<String> comboBox;
        private String alunoSelecionado;

        public EditarAlunoFrame() {
            super("Editar Aluno");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            panel = new JPanel();
            panel.setPreferredSize(new Dimension(300, 30));
            JLabel label = new JLabel("Selecione o aluno:");
            panel.add(label);
            ComboBox();
            getContentPane().add(panel);
            try {
                Connection connection = MySQLConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from alunos");
                List<Aluno> novosAlunos = new ArrayList<>();
                while (resultSet.next()) {
                    String nomeAluno = resultSet.getString(1);
                    String nomeCurso = resultSet.getString(2);
                    String notaA1ms = resultSet.getString(3);
                    String notaA2ms = resultSet.getString(4);
                    String notaA3ms = resultSet.getString(5);
                    String notaA1psc = resultSet.getString(6);
                    String notaA2psc = resultSet.getString(7);
                    String notaA3psc = resultSet.getString(8);
                    Aluno aluno = new Aluno(nomeAluno, nomeCurso, notaA1ms, notaA2ms, notaA3ms, notaA1psc, notaA2psc, notaA3psc);
                    novosAlunos.add(aluno);
                }
                alunos = novosAlunos;
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JPanel panel2 = new JPanel(new GridLayout(3, 5));

            JLabel labelMateria = new JLabel("Matéria:");
            labelMateria.setHorizontalAlignment(SwingConstants.CENTER);
            panel2.add(labelMateria);

            JLabel labelA1 = new JLabel("A1");
            labelA1.setHorizontalAlignment(SwingConstants.CENTER);
            panel2.add(labelA1);

            JLabel labelA2 = new JLabel("A2");
            labelA2.setHorizontalAlignment(SwingConstants.CENTER);
            panel2.add(labelA2);

            JLabel labelA3 = new JLabel("A3");
            labelA3.setHorizontalAlignment(SwingConstants.CENTER);
            panel2.add(labelA3);

            panel2.add(new JLabel("Modelagem de Software"));

            textFielda1ms2 = new JTextField();
            configurePlaceholder(textFielda1ms2, "Insira a nota da avaliação 1");
            panel2.add(textFielda1ms2);

            textFielda2ms2 = new JTextField();
            configurePlaceholder(textFielda2ms2, "Insira a nota da avaliação 1");
            panel2.add(textFielda2ms2);

            textFielda3ms2 = new JTextField();
            configurePlaceholder(textFielda3ms2, "Insira a nota da avaliação 1");
            panel2.add(textFielda3ms2);
            panel2.add(new JLabel("Programação de soluções computacionais"));

            textFielda4ms2 = new JTextField();
            configurePlaceholder(textFielda4ms2, "Insira a nota da avaliação 1");
            panel2.add(textFielda4ms2);

            textFielda5ms2 = new JTextField();
            configurePlaceholder(textFielda5ms2, "Insira a nota da avaliação 1");
            panel2.add(textFielda5ms2);

            textFielda6ms2 = new JTextField();
            configurePlaceholder(textFielda6ms2, "Insira a nota da avaliação 1");
            panel2.add(textFielda6ms2);

            JPanel panel3 = new JPanel(new GridLayout(1, 1));
            button = new JButton("Alterar Notas");
            panel3.add(button);

            panel.setPreferredSize(new Dimension(200, 30));
            panel.add(panel3);
            panel.add(panel2);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (alunoSelecionado == null || alunoSelecionado == "Selecione um aluno") {
                        erro2 frame = new erro2();
                        frame.setVisible(true);
                    } else {
                        notasEditadas frame = new notasEditadas();
                        frame.setVisible(true);
                        String nomeAluno = alunoSelecionado;
                        String a1ms = textFielda1ms2.getText();
                        String a2ms = textFielda2ms2.getText();
                        String a3ms = textFielda3ms2.getText();
                        String a1psc = textFielda4ms2.getText();
                        String a2psc = textFielda5ms2.getText();
                        String a3psc = textFielda6ms2.getText();
                        MySQL.edit(nomeAluno, a1ms, a2ms, a3ms, a1psc, a2psc, a3psc);
                        recarregarDados();
                    }
                }
            });
            setSize(1280, 200);
            setLocationRelativeTo(null);
            setVisible(true);
        }

        //COMBOBOX
        public void ComboBox() {
            comboBox = new JComboBox<String>();
            comboBox.setPreferredSize(new Dimension(300, comboBox.getPreferredSize().height));
            comboBox.addItem("Selecione um aluno");
            if (alunos != null) {
                for (Aluno aluno : alunos) {
                    comboBox.addItem(aluno.getNome());
                }
            }
            comboBox.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        alunoSelecionado = comboBox.getSelectedItem().toString();
                    }
                }
            });
            panel.add(comboBox);
        }
    }

    //COMBOBOX COM ALUNOS DE EXEMPLO
    public void ComboBox() {
        comboBox = new JComboBox<String>();
        comboBox.addItem("Selecione um aluno");
        comboBox.addItem("Isabelly Bom Tempo Ribeiro");
        comboBox.addItem("Paola");
        comboBox.addItem("Wesley dos Santos Marquez");
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 30));
        panel.add(comboBox);
        JPanel mainPanel = (JPanel) getContentPane();
        mainPanel.add(panel);

        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    alunoSelecionado = comboBox.getSelectedItem().toString();
                }
            }
        });
    }

    //BOTÃO QUE RECARREGA DADOS NO BANCO DE DADOS
    private void recarregarDados() {
        try {
            Connection connection = MySQLConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from alunos");
            //CRIA UMA LISTA
            List<Aluno> novosAlunos = new ArrayList<>();
            while (resultSet.next()) {
                String nomeAluno = resultSet.getString(1);
                String nomeCurso = resultSet.getString(2);
                String notaA1ms = resultSet.getString(3);
                String notaA2ms = resultSet.getString(4);
                String notaA3ms = resultSet.getString(5);
                String notaA1psc = resultSet.getString(6);
                String notaA2psc = resultSet.getString(7);
                String notaA3psc = resultSet.getString(8);
                Aluno aluno = new Aluno(nomeAluno, nomeCurso, notaA1ms, notaA2ms, notaA3ms, notaA1psc, notaA2psc, notaA3psc);
                novosAlunos.add(aluno);
            }
            alunos = novosAlunos;
            connection.close();

            comboBox.removeAllItems();
            comboBox.addItem("Selecione um aluno");
            comboBox.addItem("Isabelly Bom Tempo Ribeiro");
            comboBox.addItem("Paola");
            comboBox.addItem("Wesley dos Santos Marquez");

            textFielda1p.setText("");
            textFielda2p.setText("");
            textFielda3p.setText("");
            textFielda1ms.setText("");
            textFielda2ms.setText("");
            textFielda3ms.setText("");

            if (alunos != null) {
                for (Aluno aluno : alunos) {
                    comboBox.addItem(aluno.getNome());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //CAPTURA OS DADOS DAS STRINGS
    public class Aluno {
        private String nome;
        private String nomeCurso;
        private String notaA1ms;
        private String notaA2ms;
        private String notaA3ms;
        private String notaA1psc;
        private String notaA2psc;
        private String notaA3psc;

        public Aluno(String nome, String nomeCurso, String notaA1ms, String notaA2ms, String notaA3ms, String notaA1psc, String notaA2psc, String notaA3psc) {
            this.nome = nome;
            this.nomeCurso = nomeCurso;
            this.notaA1ms = notaA1ms;
            this.notaA2ms = notaA2ms;
            this.notaA3ms = notaA3ms;
            this.notaA1psc = notaA1psc;
            this.notaA2psc = notaA2psc;
            this.notaA3psc = notaA3psc;
        }

        public String getNome() {
            return nome;
        }

        public String getCurso() {
            return nomeCurso;
        }

        public String getNotaA1ms() {
            return notaA1ms;
        }

        public String getNotaA2ms() {
            return notaA2ms;
        }

        public String getNotaA3ms() {
            return notaA3ms;
        }

        public String getNotaA1psc() {
            return notaA1psc;
        }

        public String getNotaA2psc() {
            return notaA2psc;
        }

        public String getNotaA3psc() {
            return notaA3psc;
        }
    }

    //BOTÃO PARA VERIFICAR ALUNO E APARECER SEUS DADOS AUTOMATICAMENTE NA JANLEA "BOLETIM ESCOLAR"
    public void Botao1() {
        JPanel panel = (JPanel) getContentPane();
        button = new JButton("Verificar aluno");
        panel.add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (alunoSelecionado != null) {
                    centerNoEdit(textFielda1p, textFielda2p, textFielda3p, textFieldaSp);
                    centerNoEdit(textFielda1ms, textFielda2ms, textFielda3ms, textFieldaSms);
                    boolean encontrado = false;
                    if (alunoSelecionado.equals("Isabelly Bom Tempo Ribeiro")) {
                        textField.setText("Isabelly Bom Tempo Ribeiro");
                        textField1.setText("Ciência da computação");
                        textFielda1p.setText("8");
                        textFielda2p.setText("6.5");
                        textFielda3p.setText("7");
                        textFielda1ms.setText("6");
                        textFielda2ms.setText("6");
                        textFielda3ms.setText("7");

                    } else if (alunoSelecionado.equals("Paola")) {
                        textField.setText("Paola");
                        textField1.setText("Ciência da computação");
                        textFielda1p.setText("9");
                        textFielda2p.setText("6.5");
                        textFielda3p.setText("7");
                        textFielda1ms.setText("7");
                        textFielda2ms.setText("5.5");
                        textFielda3ms.setText("7");
                    } else if (alunoSelecionado.equals("Wesley dos Santos Marquez")) {
                        textField.setText("Wesley dos Santos Marquez");
                        textField1.setText("Ciência da computação");
                        textFielda1p.setText("5");
                        textFielda2p.setText("5");
                        textFielda3p.setText("6");
                        textFielda1ms.setText("7");
                        textFielda2ms.setText("5");
                        textFielda3ms.setText("5.5");
                    } else if (!encontrado) {
                        for (Aluno aluno : alunos) {
                            if (aluno.getNome().equals(alunoSelecionado)) {
                                textField.setText(aluno.getNome());
                                textField1.setText(aluno.getCurso());
                                textFielda1p.setText(aluno.getNotaA1ms());
                                textFielda2p.setText(aluno.getNotaA2ms());
                                textFielda3p.setText(aluno.getNotaA3ms());
                                textFielda1ms.setText(aluno.getNotaA1psc());
                                textFielda2ms.setText(aluno.getNotaA2psc());
                                textFielda3ms.setText(aluno.getNotaA3psc());
                                encontrado = true;
                                break;
                            }
                        }
                    }
                    //CHAMA O MÉTODO CALCULAR MÉDIA
                    calcularMedia();
                }
            }
        });
    }

    //MÉTODO PARA CENTRALIZAR OS TEXTOS
    private void centerNoEdit(JTextField textFielda1p, JTextField textFielda2p, JTextField textFielda3p, JTextField textFieldaSp) {
        textFielda1p.setHorizontalAlignment(SwingConstants.CENTER);
        textFielda1p.setEditable(false);
        textFielda2p.setHorizontalAlignment(SwingConstants.CENTER);
        textFielda2p.setEditable(false);
        textFielda3p.setHorizontalAlignment(SwingConstants.CENTER);
        textFielda3p.setEditable(false);
        textFieldaSp.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldaSp.setEditable(false);
    }

    //MÉTODO PARA CALCULAR AS MÉDIAS DOS ALUNOS
    public void calcularMedia() {
        String valorTextoA1P = textFielda1p.getText();
        String valorTextoA2P = textFielda2p.getText();
        String valorTextoA3P = textFielda3p.getText();
        String valorTextoA1Ms = textFielda1ms.getText();
        String valorTextoA2Ms = textFielda2ms.getText();
        String valorTextoA3Ms = textFielda3ms.getText();

        if (!valorTextoA1P.isEmpty() && !valorTextoA2P.isEmpty() && !valorTextoA3P.isEmpty()) {
            float valorInteiroA1P = Float.parseFloat(valorTextoA1P);
            float valorInteiroA2P = Float.parseFloat(valorTextoA2P);
            float valorInteiroA3P = Float.parseFloat(valorTextoA3P);
            float valorInteiroA1Ms = Float.parseFloat(valorTextoA1Ms);
            float valorInteiroA2Ms = Float.parseFloat(valorTextoA2Ms);
            float valorInteiroA3Ms = Float.parseFloat(valorTextoA3Ms);
            float mediaP = (valorInteiroA1P + valorInteiroA2P + valorInteiroA3P) / 3;
            float mediaMs = (valorInteiroA1Ms + valorInteiroA2Ms + valorInteiroA3Ms) / 3;
            if (mediaP >= 6) {
                textFieldaSp.setText("Aprovado");
            } else {
                textFieldaSp.setText("Reprovado");
            }
            if (mediaMs >= 6) {
                textFieldaSms.setText("Aprovado");
            } else {
                textFieldaSms.setText("Reprovado");
            }
        } else {
            textFieldaSp.setText("Dados faltando");
        }
    }

    //BOTÃO PARA ADICIONAR ALUNOS
    public void Botao2() {
        JPanel panel = (JPanel) getContentPane();
        button = new JButton("Adicionar aluno");
        panel.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdicionarAlunoFrame frame = new AdicionarAlunoFrame();
                frame.setVisible(true);
            }
        });
    }

    //BOTÃO PARA REMOVER ALUNOS ADICIONADOS
    public void Botao3() {
        JPanel panel = (JPanel) getContentPane();
        button = new JButton("Remover aluno");
        panel.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (alunoSelecionado == "Isabelly Bom Tempo Ribeiro" || alunoSelecionado == "Paola" || alunoSelecionado == "Wesley dos Santos Marquez" || alunoSelecionado == "Selecione um aluno") {
                    erro frame = new erro();
                    frame.setVisible(true);
                } else {
                    alunoRemovido frame = new alunoRemovido();
                    frame.setVisible(true);
                    MySQL.remove();
                    recarregarDados();
                }
            }
        });
    }

    public class MySQL {
        //MÉTODO PARA REMOVER ALUNO NO BANCO DE DADOS
        public static void remove() {
            try {
                Connection connection = MySQLConnection.getConnection();
                Statement statement = connection.createStatement();
                String deleteQuery = "DELETE FROM alunos WHERE nome_aluno = '" + alunoSelecionado + "'";
                statement.executeUpdate(deleteQuery);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //MÉTODO PARA EDITAR NOTAS DE ALUNO NO BANCO DE DADOS
        public static void edit(String nomeAluno, String a1ms, String a2ms, String a3ms, String a1psc, String a2psc, String a3psc) {
            try {
                Connection connection = MySQLConnection.getConnection();
                Statement statement = connection.createStatement();

                String update = "UPDATE alunos SET ";
                update += "a1ms = '" + a1ms + "', ";
                update += "a2ms = '" + a2ms + "', ";
                update += "a3ms = '" + a3ms + "', ";
                update += "a1psc = '" + a1psc + "', ";
                update += "a2psc = '" + a2psc + "', ";
                update += "a3psc = '" + a3psc + "'";
                update += "WHERE nome_aluno = '" + nomeAluno + "'";

                statement.executeUpdate(update);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //BOTÃO PARA EDITAR NOTAS
    public void Botao4() {
        JPanel panel = (JPanel) getContentPane();
        button = new JButton("Editar notas");
        panel.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditarAlunoFrame frame = new EditarAlunoFrame();
                frame.setVisible(true);
            }
        });
    }

    //BOTÃO PARA RECARREGAR DADOS
    public void Botao5() {
        JPanel panel = (JPanel) getContentPane();
        button = new JButton("Recarregar dados");
        panel.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                recarregarDados();
            }
        });
    }

    //MÉTODO COM PAINEL CAMPO ALUNO, RESPONSÁVEL POR ADICIONAR O NOME E O CURSO DO ALUNO AUTOMATICAMENTE QUANDO ELE FOR SELECIONADO NA COMBOBOX
    public void CampoAluno() {
        JPanel panel = (JPanel) getContentPane();

        JLabel label = new JLabel("Nome:");
        textField = new JTextField("Nome do aluno");
        textField.setColumns(15);
        textField.setEditable(false);

        JLabel label1 = new JLabel("Curso:");
        textField1 = new JTextField("Curso do aluno");
        textField1.setColumns(15);
        textField1.setEditable(false);

        panel.add(label);
        panel.add(textField);
        panel.add(label1);
        panel.add(textField1);
    }

    //PAINEL RESPONSÁVEL POR ADICIONAR AS NOTAS AUTOMATICAMENTE E SE O ALUNO FOI APROVADO NA DISCIPLINA OU NÃO, PAINEL DA JANELA "BOLETIM ESCOLAR"
    public void PainelNotas() {
        JPanel panel = new JPanel(new GridLayout(3, 5));

        JLabel labelMateria = new JLabel("Matéria:");
        labelMateria.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(labelMateria);

        JLabel labelA1 = new JLabel("A1");
        labelA1.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(labelA1);

        JLabel labelA2 = new JLabel("A2");
        labelA2.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(labelA2);

        JLabel labelA3 = new JLabel("A3");
        labelA3.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(labelA3);

        JLabel labelSituacao = new JLabel("Situação");
        labelSituacao.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(labelSituacao);

        panel.add(new JLabel("Programação de soluções computacionais"));

        textFielda1p = new JTextField("");
        panel.add(textFielda1p);

        textFielda2p = new JTextField("");
        panel.add(textFielda2p);

        textFielda3p = new JTextField("");
        panel.add(textFielda3p);

        textFieldaSp = new JTextField("");
        panel.add(textFieldaSp);

        panel.add(new JLabel("Modelagem de Software"));

        textFielda1ms = new JTextField("");
        panel.add(textFielda1ms);

        textFielda2ms = new JTextField("");
        panel.add(textFielda2ms);

        textFielda3ms = new JTextField("");
        panel.add(textFielda3ms);

        textFieldaSms = new JTextField("");
        panel.add(textFieldaSms);

        add(panel);
    }

    //MÉTODO PARA MONTAR JANELA
    private void montaJanela() {
        JPanel panel = (JPanel) getContentPane();
        JPanel frame = (JPanel) getContentPane();
        panel.add(texto);
    }

    //MÉTODO DA JANELA PRINCIPAL "MAIN"
    public static void main(String[] args) {
        Main janela = new Main();
        janela.setSize(1280, 720);
        janela.setVisible(true);
    }
}