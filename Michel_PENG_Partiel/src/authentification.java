import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;



    public class authentification extends JFrame implements ActionListener {

        private Container contentPane;
        private JPanel pane1, pane2, pane3;
        private JTextField JTxtField;
        private JPasswordField JPwdField;
        private JButton Bok, Bannuler;
        private JLabel titre1, titre2, icon;


        public authentification() {


            this.setTitle("Authentification");
            this.setSize(564,319);
            this.setLocationRelativeTo(getOwner());
            this.setResizable(false); //Impossible de redimensionner la fentre
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //Creation des panel
            contentPane = getContentPane();
            contentPane.setLayout(null);
            this.setContentPane(contentPane);

            pane1 = new JPanel();
            pane2 = new JPanel();
            pane3 = new JPanel();

            pane1.setLayout(null);
            pane2.setLayout(null);
            pane3.setLayout(null);

            pane1.setBackground(Color.black);
            pane2.setBackground(Color.black);
            pane3.setBackground(Color.black);

            pane1.setBounds(0,0,550,86);
            pane2.setBounds(0,86,550,125);
            pane3.setBounds(0,126,550,156);

            this.contentPane.add(pane1);
            this.contentPane.add(pane2);
            this.contentPane.add(pane3);

            //Creation de boutons
            Bok = new JButton("Valider");
            Bannuler = new JButton("Annuler");

            pane3.add(Bok);
            Bok.setBounds(100,100,150,50);
            Bok.setFont(new Font("Helvetica", Font.BOLD, 17));

            pane3.add(Bannuler);
            Bannuler.setBounds(360,100,150,50);
            Bannuler.setFont(new Font("Helvetica", Font.BOLD, 17));

            Bok.addActionListener(this);
            Bannuler.addActionListener(this);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //Titrage
            titre1 = new JLabel("Authentification  :");
            titre1.setBounds(10,10,500, 50);
            titre1.setFont(new Font("Helvetica", Font.BOLD, 20));
            titre1.setForeground(Color.white);
            pane1.add(titre1);

            titre2 = new JLabel("Veuillez vous identifier svp :");
            titre2.setBounds(10,30,500, 50);
            titre2.setFont(new Font("Helvetica", Font.BOLD, 12));
            titre2.setForeground(Color.white);
            pane1.add(titre2);

            //champs de textes
            JTxtField = new JTextField();
            JTxtField.setFont(new Font("Helvetica", Font.PLAIN, 20));
            JTxtField.setBounds(250,20,250,30);
            pane2.add(JTxtField);

            JPwdField = new JPasswordField();
            JPwdField.setEchoChar('*');
            JPwdField.setFont(new Font("Helvetica", Font.PLAIN, 20));
            JPwdField.setBounds(250, 65, 250, 30);
            pane2.add(JPwdField);


        }

        //Action sur les boutons
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == Bok) {

                Model mod = new Model();
                String log = JTxtField.getText();
                String pass = new String(JPwdField.getPassword());
                String req = "Select count(*) From user WHERE login='"+log+"' AND password = '"+pass+"'";

                try {
                    if (mod.verifLogin(req) == 1) {
                        suiviSession fen1 = new suiviSession();
                        fen1.setResizable(false);
                        this.setVisible(false);
                        fen1.setVisible(true);
                        fen1.setLocationRelativeTo(null);
                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur d'authentification");
                        JTxtField.setText("");
                        JPwdField.setText("");
                        JTxtField.requestFocus();
                    }
                }
                catch(SQLException f){
                    System.out.println("Connection Error");
                }
            }

            else if (e.getSource() == Bannuler)
                System.exit(0);
        }

        //main
        public static void main(String[] args) {
            authentification Fenetre = new authentification();
            Fenetre.setVisible(true);
        }
    }

