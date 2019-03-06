package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


class Logger extends JFrame {

    private JTextArea textArea;
    private JTextField textField;
    private String jtr = "";
    private static String fileName ="fileLog.txt";

    public Logger() {
        super("Log File");
        createGUI();
    }

    public void createGUI() {
        setBounds(300, 200, 400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton button1 = new JButton("Show log");
        button1.setActionCommand("Button 1 was pressed!");
        panel.add(button1,BorderLayout.SOUTH);


        textArea = new JTextArea();
        textArea.setColumns(150);
        textArea.setBackground( Color.black );
        textArea.setForeground( Color.green );
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        panel.add(new JScrollPane(textArea),BorderLayout.CENTER);

//        ActionListener actionListener = new TestActionListener();

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String jtr = null;
                try {
                    jtr = ClientWindow.read("fileLog.txt");
                } catch (IOException e1) {
                    e1.printStackTrace ( );
                }
                textArea.setText(jtr);
            }
        });

        getContentPane().add(panel);
        setPreferredSize(new Dimension(320, 100));
    }

//    public class TestActionListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            Scanner  in = new Scanner (fileName);
//            StringBuilder textBuilder = new StringBuilder();
//            while(in.hasNextLine()){
//                String line = in.nextLine();
//                textBuilder.append(line);
//                textBuilder.append(System.lineSeparator()); // nextLine doesn't return the line separator
//            }
//            textArea.setText(textBuilder.toString ());
//
//        }
//    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                Logger frame = new Logger();
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
