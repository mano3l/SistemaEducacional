package com.unip.SistemaEducacional.views;

import com.unip.SistemaEducacional.dao.CourseDao;
import com.unip.SistemaEducacional.dao.CourseDaoImpl;
import com.unip.SistemaEducacional.dao.EnrollmentDaoImpl;
import com.unip.SistemaEducacional.dao.StudentDaoImpl;
import com.unip.SistemaEducacional.models.Course;
import com.unip.SistemaEducacional.models.Enrollment;
import com.unip.SistemaEducacional.models.Student;
import com.unip.SistemaEducacional.repositories.CourseRepository;
import com.unip.SistemaEducacional.repositories.EnrollmentRepository;
import com.unip.SistemaEducacional.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

@Component
public class AppMenu extends JFrame {
    private Stack<JPanel> previousPanels = new Stack<>();
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public AppMenu(StudentRepository studentRepository, CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        showMainMenu();
    }

    // Método para exibir o menu principal
    private void showMainMenu() {
        setTitle("Menu Principal");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel setup with custom background color
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBackground(new Color(60, 63, 65)); // Dark background

        // Button font and style customization
        Font buttonFont = new Font("SansSerif", Font.BOLD, 18);
        Color buttonColor = new Color(45, 185, 255); // Light blue
        Color buttonTextColor = Color.WHITE;

        // Creating and styling buttons
        JButton btnCurso = createStyledButton("Gerenciar Curso", buttonFont, buttonColor, buttonTextColor);
        JButton btnEstudante = createStyledButton("Gerenciar Estudante", buttonFont, buttonColor, buttonTextColor);
        JButton btnMatricula = createStyledButton("Gerenciar Matrícula", buttonFont, buttonColor, buttonTextColor);
        JButton btnSair = createStyledButton("Sair", buttonFont, new Color(255, 80, 80), buttonTextColor); // Red for "Sair"

        // Action listeners
        btnCurso.addActionListener(e -> showSubMenu("curso"));
        btnEstudante.addActionListener(e -> showSubMenu("estudante"));
        btnMatricula.addActionListener(e -> showSubMenu("matricula"));
        btnSair.addActionListener(e -> System.exit(0));

        // Adding buttons to the panel
        panel.add(btnCurso);
        panel.add(btnEstudante);
        panel.add(btnMatricula);
        panel.add(btnSair);

        // Setting panel as content pane
        setContentPane(panel);
        setVisible(true);
    }

    private JButton createStyledButton(String text, Font font, Color bgColor, Color textColor) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
        return button;
    }

    // Método para exibir o submenu

    private void showSubMenu(String tipo) {
        setTitle("Submenu - " + tipo);
        setSize(900, 600);

        // Main panel setup with custom background color
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBackground(new Color(60, 63, 65)); // Dark background

        // Button font and style customization
        Font buttonFont = new Font("SansSerif", Font.BOLD, 18);
        Color buttonColor = new Color(45, 185, 255); // Light blue
        Color buttonTextColor = Color.WHITE;

        // Creating and styling buttons
        JButton btnListar = createStyledButton("Listar", buttonFont, buttonColor, buttonTextColor);
        JButton btnCadastrar = createStyledButton("Cadastrar", buttonFont, buttonColor, buttonTextColor);
        JButton btnEditar = createStyledButton("Editar", buttonFont, buttonColor, buttonTextColor);
        JButton btnExcluir = createStyledButton("Excluir", buttonFont, buttonColor, buttonTextColor);
        JButton btnVoltar = createStyledButton("Voltar", buttonFont, new Color(255, 80, 80), buttonTextColor); // Red for "Voltar"

        // Action listeners
        btnListar.addActionListener(e -> showTableWindow(tipo));
        btnCadastrar.addActionListener(e -> showForm(tipo));
        btnEditar.addActionListener(e -> editEntry(tipo));
        btnExcluir.addActionListener(e -> deleteWindow(tipo));
        btnVoltar.addActionListener(e -> goBack());

        // Adding buttons to the panel
        panel.add(btnListar);
        panel.add(btnCadastrar);
        panel.add(btnEditar);
        panel.add(btnExcluir);
        panel.add(btnVoltar);

        // Store the current panel before switching to the new one
        previousPanels.push((JPanel) getContentPane());

        setContentPane(panel);
        revalidate();
        repaint();
    }

    // Consistent button styling method
//    private JButton createStyledButton(String text, Font font, Color bgColor, Color textColor) {
//        JButton button = new JButton(text);
//        button.setFont(font);
//        button.setBackground(bgColor);
//        button.setForeground(textColor);
//        button.setFocusPainted(false);
//        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
//        return button;
//    }

    // Method to handle going back to the previous menu
//    private void goBack() {
//        if (!previousPanels.isEmpty()) {
//            setContentPane(previousPanels.pop());
//            revalidate();
//            repaint();
//        }
//    }

    private void editEntry(String tipo) {
        JFrame tableFrame = new JFrame("Tabela de " + tipo);
        tableFrame.setSize(900, 600);
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tableFrame.setLocationRelativeTo(null);
        tableFrame.getContentPane().setBackground(new Color(60, 63, 65)); // Consistent dark background

        // Editable table model
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (tipo.equals("estudante")) {
                    return column == 2 || column == 3 || column == 4 || column == 5 || column == 6 || column == 7;
                } else if (tipo.equals("curso")) {
                    return column == 1 || column == 2 || column == 3 || column == 4 || column == 5;
                } else if (tipo.equals("matricula")) {
                    return column == 2 || column == 4;
                }
                return false;
            }
        };
        String[] columnNames;

        if (tipo.equals("estudante")) {
            StudentDaoImpl studentDao = new StudentDaoImpl(studentRepository);
            List<Student> students = studentDao.getAllStudents();

            columnNames = new String[]{"ID", "CPF", "Nome", "Sobrenome", "Idade", "Sexo", "Telefone", "Endereço"};
            model.setColumnIdentifiers(columnNames);

            for (Student student : students) {
                model.addRow(new Object[]{student.getId(), student.getCpf(), student.getName(), student.getLastName(),
                        student.getAge(), student.getSex(), student.getPhone(), student.getAddress()});
            }

        } else if (tipo.equals("curso")) {
            CourseDaoImpl courseDao = new CourseDaoImpl(courseRepository);
            List<Course> courses = courseDao.getAllCourses();

            columnNames = new String[]{"Código do Curso", "Nome do Curso", "Nível", "Duração", "Horário", "Descrição"};
            model.setColumnIdentifiers(columnNames);

            for (Course course : courses) {
                model.addRow(new Object[]{course.getCode(), course.getCourseName(), course.getCourseLevel(),
                        course.getDuration(), course.getSchedule(), course.getDescription()});
            }

        } else if (tipo.equals("matricula")) {
            EnrollmentDaoImpl enrollmentDao = new EnrollmentDaoImpl(enrollmentRepository, courseRepository);
            List<Enrollment> enrollments = enrollmentDao.getAllEnrollments();

            columnNames = new String[]{"RA", "Nome do Estudante", "Email", "Curso", "Data de Matrícula"};
            model.setColumnIdentifiers(columnNames);

            for (Enrollment enrollment : enrollments) {
                model.addRow(new Object[]{enrollment.getRa(), enrollment.getStudent().getName() + " " + enrollment.getStudent().getLastName(),
                        enrollment.getEmail(), enrollment.getCourse().getCourseName(), enrollment.getEnrollmentDate()});
            }

        } else {
            JOptionPane.showMessageDialog(tableFrame, "Tipo inválido: " + tipo, "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Creating and styling the table
        JTable table = new JTable(model);
        table.setRowHeight(25);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setForeground(Color.WHITE);
        table.setBackground(new Color(43, 43, 43));
        table.setSelectionBackground(new Color(75, 110, 175));
        table.setSelectionForeground(Color.WHITE);

        // Header styling
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(45, 185, 255));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("SansSerif", Font.BOLD, 16));

        JScrollPane scrollPane = new JScrollPane(table);

        // Create and style the "Salvar Alterações" button
        JButton editButton = createStyledButton("Salvar Alterações", new Font("SansSerif", Font.BOLD, 18), new Color(45, 185, 255), Color.WHITE);

        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                try {
                    Object[] rowData = new Object[model.getColumnCount()];
                    for (int i = 0; i < model.getColumnCount(); i++) {
                        rowData[i] = model.getValueAt(selectedRow, i);
                    }

                    if (tipo.equals("estudante")) {
                        Student updatedStudent = new Student();
                        updatedStudent.setCpf((String) rowData[1]);
                        updatedStudent.setName((String) rowData[2]);
                        updatedStudent.setLastName((String) rowData[3]);
                        updatedStudent.setAge(rowData[4] instanceof String ? Integer.parseInt((String) rowData[4]) : (Integer) rowData[4]);
                        updatedStudent.setSex((String) rowData[5]);
                        updatedStudent.setPhone((String) rowData[6]);
                        updatedStudent.setAddress((String) rowData[7]);

                        StudentDaoImpl studentDao = new StudentDaoImpl(studentRepository);
                        studentDao.updateStudent((Integer) rowData[0], updatedStudent);

                    } else if (tipo.equals("curso")) {
                        Course updatedCourse = new Course();
                        updatedCourse.setCourseName((String) rowData[1]);
                        updatedCourse.setCourseLevel((String) rowData[2]);
                        updatedCourse.setDuration((String) rowData[3]);
                        updatedCourse.setSchedule((String) rowData[4]);
                        updatedCourse.setDescription((String) rowData[5]);

                        CourseDaoImpl courseDao = new CourseDaoImpl(courseRepository);
                        courseDao.updateCourse(Integer.valueOf((String) rowData[0]), updatedCourse);

                    } else if (tipo.equals("matricula")) {
                        Enrollment updatedEnrollment = new Enrollment();
                        updatedEnrollment.setEmail((String) rowData[2]);
                        updatedEnrollment.setEnrollmentDate(rowData[4] instanceof String ? LocalDate.parse((String) rowData[4]) : (LocalDate) rowData[4]);

                        EnrollmentDaoImpl enrollmentDao = new EnrollmentDaoImpl(enrollmentRepository, courseRepository);
                        enrollmentDao.updateEnrollment((String) rowData[0], updatedEnrollment);
                    }

                    JOptionPane.showMessageDialog(tableFrame, "Alterações salvas para a linha " + (selectedRow + 1));

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(tableFrame, "Erro ao salvar alterações: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(tableFrame, "Nenhuma linha selecionada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        tableFrame.add(scrollPane, BorderLayout.CENTER);
        tableFrame.add(editButton, BorderLayout.SOUTH);
        tableFrame.setVisible(true);
    }



    private void deleteWindow(String tipo) {
        JFrame deleteFrame = new JFrame("Deletar " + tipo);
        deleteFrame.setSize(900, 600);
        deleteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteFrame.setLocationRelativeTo(null);
        deleteFrame.getContentPane().setBackground(new Color(60, 63, 65)); // Fundo escuro consistente

        Object[][] data;
        String[] columnNames;

        if (tipo.equals("estudante")) {
            StudentDaoImpl studentDao = new StudentDaoImpl(studentRepository);
            List<Student> students = studentDao.getAllStudents();

            columnNames = new String[]{"ID", "CPF", "Nome", "Sobrenome", "Idade", "Sexo", "Telefone", "Endereço"};
            data = new Object[students.size()][columnNames.length];

            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                data[i][0] = student.getId();
                data[i][1] = student.getCpf();
                data[i][2] = student.getName();
                data[i][3] = student.getLastName();
                data[i][4] = student.getAge();
                data[i][5] = student.getSex();
                data[i][6] = student.getPhone();
                data[i][7] = student.getAddress();
            }

        } else if (tipo.equals("curso")) {
            CourseDaoImpl courseDao = new CourseDaoImpl(courseRepository);
            List<Course> courses = courseDao.getAllCourses();

            columnNames = new String[]{"Código do Curso", "Nome do Curso", "Nível", "Duração", "Horário", "Descrição"};
            data = new Object[courses.size()][columnNames.length];

            for (int i = 0; i < courses.size(); i++) {
                Course course = courses.get(i);
                data[i][0] = course.getCode();
                data[i][1] = course.getCourseName();
                data[i][2] = course.getCourseLevel();
                data[i][3] = course.getDuration();
                data[i][4] = course.getSchedule();
                data[i][5] = course.getDescription();
            }

        } else if (tipo.equals("matricula")) {
            EnrollmentDaoImpl enrollmentDao = new EnrollmentDaoImpl(enrollmentRepository, courseRepository);
            List<Enrollment> enrollments = enrollmentDao.getAllEnrollments();

            columnNames = new String[]{"RA", "Nome do Estudante", "Email", "Curso", "Data de Matrícula"};
            data = new Object[enrollments.size()][columnNames.length];

            for (int i = 0; i < enrollments.size(); i++) {
                Enrollment enrollment = enrollments.get(i);
                data[i][0] = enrollment.getRa();
                data[i][1] = enrollment.getStudent().getName() + " " + enrollment.getStudent().getLastName();
                data[i][2] = enrollment.getEmail();
                data[i][3] = enrollment.getCourse().getCourseName();
                data[i][4] = enrollment.getEnrollmentDate();
            }

        } else {
            JOptionPane.showMessageDialog(deleteFrame, "Tipo inválido: " + tipo, "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Criar e estilizar a tabela
        JTable table = new JTable(data, columnNames);
        table.setRowHeight(25);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setForeground(Color.WHITE);
        table.setBackground(new Color(43, 43, 43));
        table.setSelectionBackground(new Color(75, 110, 175));
        table.setSelectionForeground(Color.WHITE);

        // Estilizar o cabeçalho
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(45, 185, 255));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("SansSerif", Font.BOLD, 16));

        JScrollPane scrollPane = new JScrollPane(table);
        deleteFrame.add(scrollPane, BorderLayout.CENTER);

        // Criar e estilizar o botão "Deletar Selecionado"
        JButton btnDelete = createStyledButton("Deletar Selecionado", new Font("SansSerif", Font.BOLD, 18), new Color(45, 185, 255), Color.WHITE);
        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(deleteFrame, "Selecione uma linha para deletar.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(deleteFrame, "Tem certeza que deseja deletar?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (tipo.equals("estudante")) {
                    int studentId = (int) table.getValueAt(selectedRow, 0);

                    EnrollmentDaoImpl enrollmentDao = new EnrollmentDaoImpl(enrollmentRepository, courseRepository);
                    List<Enrollment> enrollments = enrollmentDao.getEnrollmentsByStudentId(studentId);
                    if (!enrollments.isEmpty()) {
                        JOptionPane.showMessageDialog(deleteFrame, "Não é possível deletar o estudante porque ele possui matrículas associadas.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    StudentDaoImpl studentDao = new StudentDaoImpl(studentRepository);
                    studentDao.deleteStudent(studentId);
                    JOptionPane.showMessageDialog(deleteFrame, "Estudante deletado com sucesso.");

                } else if (tipo.equals("curso")) {
                    int courseCode = (int) table.getValueAt(selectedRow, 0);

                    EnrollmentDaoImpl enrollmentDao = new EnrollmentDaoImpl(enrollmentRepository, courseRepository);
                    List<Enrollment> enrollments = enrollmentDao.getEnrollmentsByCourseCode(courseCode);
                    if (!enrollments.isEmpty()) {
                        JOptionPane.showMessageDialog(deleteFrame, "Não é possível deletar o curso porque ele possui matrículas associadas.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    CourseDaoImpl courseDao = new CourseDaoImpl(courseRepository);
                    courseDao.deleteCourse(courseCode);
                    JOptionPane.showMessageDialog(deleteFrame, "Curso deletado com sucesso.");

                } else if (tipo.equals("matricula")) {
                    String ra = (String) table.getValueAt(selectedRow, 0);
                    EnrollmentDaoImpl enrollmentDao = new EnrollmentDaoImpl(enrollmentRepository, courseRepository);
                    enrollmentDao.deleteEnrollment(ra);
                    JOptionPane.showMessageDialog(deleteFrame, "Matrícula deletada com sucesso.");
                }
                deleteFrame.dispose();
                deleteWindow(tipo); // Atualiza a tabela após a exclusão
            }
        });

        // Painel para o botão
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(60, 63, 65));
        buttonPanel.add(btnDelete);

        deleteFrame.add(buttonPanel, BorderLayout.SOUTH);
        deleteFrame.setVisible(true);
    }



    // Método para voltar ao painel anterior
    private void goBack() {
        if (!previousPanels.isEmpty()) {
            // Remove o painel atual da pilha
            previousPanels.pop();

            // Se ainda houver painéis na pilha, mostra o painel anterior
            if (!previousPanels.isEmpty()) {
                setContentPane(previousPanels.peek());
                revalidate();
                repaint();
            } else {
                showMainMenu(); // Se a pilha estiver vazia, retorna ao menu principal
            }
        }
    }

    // Método para exibir o formulário de cadastro com base no tipo
    private void showForm(String tipo) {
        setTitle("Cadastrar " + tipo);
        setSize(900, 600);
        setLocationRelativeTo(null); // Centraliza a janela

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Layout vertical
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espaçamento em volta do painel
        panel.setBackground(new Color(240, 240, 240)); // Fundo claro

        Color labelColor = Color.BLACK;
        Font labelFont = new Font("Arial", Font.BOLD, 14);

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnVoltar = new JButton("Voltar");

        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCadastrar.setBackground(new Color(45, 185, 255));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btnCadastrar.setFocusPainted(false);

        btnVoltar.setFont(new Font("Arial", Font.BOLD, 16));
        btnVoltar.setBackground(new Color(200, 50, 50));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btnVoltar.setFocusPainted(false);

        if (tipo.equals("curso")) {
            panel.setLayout(new GridLayout(6, 2, 5, 5));
            String[] niveisArray = {"Tecnologo", "Bacharelado"};
            String[] horarios = {"Diurno", "Matutino", "Noturno", "Integral"};

            JTextField nomeField = new JTextField(15);
            JComboBox<String> nivelField = new JComboBox<>(niveisArray);
            JTextField duracaoField = new JTextField(15);
            JComboBox<String> horarioField = new JComboBox<>(horarios);
            JTextArea descricaoArea = new JTextArea(3, 15);
            descricaoArea.setLineWrap(true);
            descricaoArea.setWrapStyleWord(true);

            panel.add(createLabel("Nome:", labelColor, labelFont));
            panel.add(nomeField);
            panel.add(createLabel("Nível:", labelColor, labelFont));
            panel.add(nivelField);
            panel.add(createLabel("Duração (anos):", labelColor, labelFont));
            panel.add(duracaoField);
            panel.add(createLabel("Horário:", labelColor, labelFont));
            panel.add(horarioField);
            panel.add(createLabel("Descrição:", labelColor, labelFont));
            panel.add(new JScrollPane(descricaoArea)); // Usar JScrollPane para o JTextArea

            btnCadastrar.addActionListener(e -> {
                String nome = nomeField.getText();
                String nivel = (String) nivelField.getSelectedItem();
                String duracao = duracaoField.getText();
                String horario = (String) horarioField.getSelectedItem();
                String descricao = descricaoArea.getText();

                Course course = new Course();
                course.setCourseName(nome);
                course.setCourseLevel(nivel);
                course.setDuration(duracao);
                course.setSchedule(horario);
                course.setDescription(descricao);
                CourseDaoImpl courseDao = new CourseDaoImpl(courseRepository);
                courseDao.createCourse(course);

                JOptionPane.showMessageDialog(this, "Curso cadastrado com sucesso!");
            });
            panel.add(btnCadastrar);

        } else if (tipo.equals("estudante")) {
            panel.setLayout(new GridLayout(8, 2, 5, 5));
            String[] generos = {"Masculino", "Feminino"};

            JTextField primeiroNomeField = new JTextField(15);
            JTextField sobrenomeField = new JTextField(15);
            JTextField enderecoField = new JTextField(15);
            JTextField cpfField = new JTextField(15);
            JComboBox<String> sexoField = new JComboBox<>(generos);
            JTextField telefoneField = new JTextField(15);
            JTextField idadeField = new JTextField(15);

            panel.add(createLabel("Primeiro Nome:", labelColor, labelFont));
            panel.add(primeiroNomeField);
            panel.add(createLabel("Sobrenome:", labelColor, labelFont));
            panel.add(sobrenomeField);
            panel.add(createLabel("Endereço:", labelColor, labelFont));
            panel.add(enderecoField);
            panel.add(createLabel("CPF:", labelColor, labelFont));
            panel.add(cpfField);
            panel.add(createLabel("Sexo:", labelColor, labelFont));
            panel.add(sexoField);
            panel.add(createLabel("Telefone:", labelColor, labelFont));
            panel.add(telefoneField);
            panel.add(createLabel("Idade:", labelColor, labelFont));
            panel.add(idadeField);

            btnCadastrar.addActionListener(e -> {
                String primeiroNome = primeiroNomeField.getText();
                String sobrenome = sobrenomeField.getText();
                String endereco = enderecoField.getText();
                String cpf = cpfField.getText();
                String sexo = (String) sexoField.getSelectedItem();
                String telefone = telefoneField.getText();
                int idade = Integer.parseInt(idadeField.getText());

                Student student = new Student();
                student.setName(primeiroNome);
                student.setLastName(sobrenome);
                student.setAddress(endereco);
                student.setCpf(cpf);
                student.setSex(sexo);
                student.setPhone(telefone);
                student.setAge(idade);
                StudentDaoImpl studentDao = new StudentDaoImpl(studentRepository);
                studentDao.createStudent(student);

                JOptionPane.showMessageDialog(this, "Estudante cadastrado com sucesso!");
            });
            panel.add(btnCadastrar);

        } else if (tipo.equals("matricula")) {
            panel.setLayout(new GridLayout(5, 2, 5, 5)); // Ajustando para 5 linhas

            JTextField studentIdField = new JTextField(15);
            JTextField codigoCursoField = new JTextField(15);
            JTextField raField = new JTextField(15);
            JTextField emailField = new JTextField(15);

            panel.add(createLabel("ID do Estudante:", labelColor, labelFont));
            panel.add(studentIdField);
            panel.add(createLabel("Código do Curso:", labelColor, labelFont));
            panel.add(codigoCursoField);
            panel.add(createLabel("RA do Estudante:", labelColor, labelFont));
            panel.add(raField);
            panel.add(createLabel("Email:", labelColor, labelFont));
            panel.add(emailField);

            // Botão de cadastrar
            btnCadastrar.addActionListener(e -> {
                String studentId = studentIdField.getText();
                String codigoCurso = codigoCursoField.getText();
                String ra = raField.getText();
                String email = emailField.getText();

                Enrollment enrollment = new Enrollment();
                enrollment.setRa(ra);
                enrollment.setEmail(email);
                enrollment.setEnrollmentDate(LocalDate.now());

                // Verificar e associar estudante e curso
                StudentDaoImpl studentDao = new StudentDaoImpl(studentRepository);
                studentDao.getStudentById(Integer.parseInt(studentId)).ifPresent(enrollment::setStudent);
                CourseDaoImpl courseDao = new CourseDaoImpl(courseRepository);
                courseDao.getCourseByCode(Integer.parseInt(codigoCurso)).ifPresent(enrollment::setCourse);

                EnrollmentDaoImpl enrollmentDao = new EnrollmentDaoImpl(enrollmentRepository, courseRepository);
                enrollmentDao.createEnrollment(enrollment);

                JOptionPane.showMessageDialog(this, "Matrícula cadastrada com sucesso!");
            });
            panel.add(btnCadastrar);
        }


        // Adiciona botão Voltar no final
        btnVoltar.addActionListener(e -> goBack());
        panel.add(btnVoltar);

        setContentPane(panel);
        revalidate();
        repaint();
    }

    // Método auxiliar para criar labels estilizados
    private JLabel createLabel(String text, Color color, Font font) {
        JLabel label = new JLabel(text);
        label.setForeground(color);
        label.setFont(font);
        return label;
    }



    private JLabel createLabel(String text, Color color) {
        JLabel label = new JLabel(text);
        label.setForeground(color);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        return label;
    }




    // Método para abrir uma janela e mostrar uma tabela
    private void showTableWindow(String tipo) {
        JFrame tableFrame = new JFrame("Tabela de " + tipo);
        tableFrame.setSize(900, 600);
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tableFrame.setLocationRelativeTo(null);
        tableFrame.getContentPane().setBackground(new Color(60, 63, 65)); // Fundo escuro consistente

        Object[][] data;
        String[] columnNames;

        if (tipo.equals("estudante")) {
            StudentDaoImpl studentDao = new StudentDaoImpl(studentRepository);
            List<Student> students = studentDao.getAllStudents();

            columnNames = new String[]{"ID", "CPF", "Nome", "Sobrenome", "Idade", "Sexo", "Telefone", "Endereço"};
            data = new Object[students.size()][columnNames.length];

            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                data[i][0] = student.getId();
                data[i][1] = student.getCpf();
                data[i][2] = student.getName();
                data[i][3] = student.getLastName();
                data[i][4] = student.getAge();
                data[i][5] = student.getSex();
                data[i][6] = student.getPhone();
                data[i][7] = student.getAddress();
            }

        } else if (tipo.equals("curso")) {
            CourseDaoImpl courseDao = new CourseDaoImpl(courseRepository);
            List<Course> courses = courseDao.getAllCourses();

            columnNames = new String[]{"Código do Curso", "Nome do Curso", "Nível", "Duração", "Horário", "Descrição"};
            data = new Object[courses.size()][columnNames.length];

            for (int i = 0; i < courses.size(); i++) {
                Course course = courses.get(i);
                data[i][0] = course.getCode();
                data[i][1] = course.getCourseName();
                data[i][2] = course.getCourseLevel();
                data[i][3] = course.getDuration();
                data[i][4] = course.getSchedule();
                data[i][5] = course.getDescription();
            }

        } else if (tipo.equals("matricula")) {
            EnrollmentDaoImpl enrollmentDao = new EnrollmentDaoImpl(enrollmentRepository, courseRepository);
            List<Enrollment> enrollments = enrollmentDao.getAllEnrollments();

            columnNames = new String[]{"RA", "Nome do Estudante", "Email", "Curso", "Data de Matrícula"};
            data = new Object[enrollments.size()][columnNames.length];

            for (int i = 0; i < enrollments.size(); i++) {
                Enrollment enrollment = enrollments.get(i);
                data[i][0] = enrollment.getRa();
                data[i][1] = enrollment.getStudent().getName() + " " + enrollment.getStudent().getLastName();
                data[i][2] = enrollment.getEmail();
                data[i][3] = enrollment.getCourse().getCourseName();
                data[i][4] = enrollment.getEnrollmentDate();
            }

        } else {
            JOptionPane.showMessageDialog(tableFrame, "Tipo inválido: " + tipo, "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Criar e estilizar a tabela
        JTable table = new JTable(data, columnNames);
        table.setRowHeight(25);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setForeground(Color.WHITE);
        table.setBackground(new Color(43, 43, 43));
        table.setSelectionBackground(new Color(75, 110, 175));
        table.setSelectionForeground(Color.WHITE);

        // Estilizar o cabeçalho
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(45, 185, 255));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("SansSerif", Font.BOLD, 16));

        JScrollPane scrollPane = new JScrollPane(table);
        tableFrame.add(scrollPane, BorderLayout.CENTER);

        // Criar e estilizar o botão "Voltar"
        JButton backButton = createStyledButton("Voltar", new Font("SansSerif", Font.BOLD, 18), new Color(45, 185, 255), Color.WHITE);
        backButton.addActionListener(e -> tableFrame.dispose());

        // Painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(60, 63, 65));
        buttonPanel.add(backButton);

        tableFrame.add(buttonPanel, BorderLayout.SOUTH);

        tableFrame.setVisible(true);
    }



}