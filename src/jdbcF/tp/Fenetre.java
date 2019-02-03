package jdbcF.tp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Fenetre extends JFrame {

    /**
     * ToolBar pour le lancement des requêtes
     */
    private JToolBar tool = new JToolBar();

    /**
     * Le bouton
     */
    private JButton load = new JButton(new ImageIcon("E:\\javaOpenClass\\src\\jdbcF\\tp\\test.png"));

    /**
     * Le délimiteur
     */
    private JSplitPane split;

    /**
     * Le conteneur de résultat
     */
    private JPanel result = new JPanel();

    /**
     * Requête par défaut pour le démarrage
     */
    private String requete = "SELECT  * FROM article";

    /**
     * Le composant dans lequel taper la requête
     */
    private JTextArea text = new JTextArea(requete);

    /**
     * Constructeur
     */
    public Fenetre() {
        setSize(900, 600);
        setTitle("TP JDBC");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initToolbar();
        initContent();
        initTable(requete);
    }

    /**
     * Point de départ du programme
     *
     * @param args
     */
    public static void main(String[] args) {
        Fenetre fen = new Fenetre();
        fen.setVisible(true);
    }

    /**
     * Initialise la toolbar
     */
    private void initToolbar() {
        load.setPreferredSize(new Dimension(30, 35));
        load.setBorder(null);
        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                initTable(text.getText());
            }
        });

        tool.add(load);
        getContentPane().add(tool, BorderLayout.NORTH);
    }

    /**
     * Initialise le contenu de la fenêtre
     */
    public void initContent() {
        //Vous connaissez ça...
        result.setLayout(new BorderLayout());
        split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(text), result);
        split.setDividerLocation(100);
        getContentPane().add(split, BorderLayout.CENTER);
    }

    /**
     * Initialise le visuel avec la requête saisie dans l'éditeur
     *
     * @param query
     */
    public void initTable(String query) {
        try {
            //On crée un statement
            long start = System.currentTimeMillis();
            Statement state = SdzConnection.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            //On exécute la requête
            ResultSet res = state.executeQuery(query);
            //Temps d'exécution

            //On récupère les meta afin de récupérer le nom des colonnes
            ResultSetMetaData meta = res.getMetaData();
            //On initialise un tableau d'Object pour les en-têtes du tableau
            Object[] column = new Object[meta.getColumnCount()];

            for (int i = 1; i <= meta.getColumnCount(); i++)
                column[i - 1] = meta.getColumnName(i);

            //Petite manipulation pour obtenir le nombre de lignes
            res.last();
            int rowCount = res.getRow();
            Object[][] data = new Object[res.getRow()][meta.getColumnCount()];

            //On revient au départ
            res.beforeFirst();
            int j = 1;

            //On remplit le tableau d'Object[][]
            while (res.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++)
                    data[j - 1][i - 1] = res.getObject(i);

                j++;
            }

            //On ferme le tout
            res.close();
            state.close();

            long totalTime = System.currentTimeMillis() - start;

            //On enlève le contenu de notre conteneur
            result.removeAll();
            //On y ajoute un JTable
            result.add(new JScrollPane(new JTable(data, column)), BorderLayout.CENTER);
            result.add(new JLabel("La requête à été exécuter en " + totalTime + " ms et a retourné " + rowCount + " ligne(s)"), BorderLayout.SOUTH);
            //On force la mise à jour de l'affichage
            result.revalidate();

        } catch (SQLException e) {
            //Dans le cas d'une exception, on affiche une pop-up et on efface le contenu
            result.removeAll();
            result.add(new JScrollPane(new JTable()), BorderLayout.CENTER);
            result.revalidate();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERREUR ! ", JOptionPane.ERROR_MESSAGE);
        }
    }
}