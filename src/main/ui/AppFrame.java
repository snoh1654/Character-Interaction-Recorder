package ui;

import model.Champion;
import model.ChampionCollection;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// The JFrame used for the GUI of ChampionCollection
public class AppFrame extends JFrame implements ActionListener {
    private static final String JSON_LOCATION = "./data/championsGuideGUI.json";

    private ChampionCollection champions;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton addChampion;
    private JButton removeChampion;
    private JButton saveInfo;
    private JButton loadInfo;
    private JPanel savedStatusInfo;
    private JLabel savedStatus;

    // EFFECTS: runs the GUI for the Champion Collection
    public AppFrame() throws FileNotFoundException {
        super("Champion Guide");

        champions = new ChampionCollection();
        jsonWriter = new JsonWriter(JSON_LOCATION);
        jsonReader = new JsonReader(JSON_LOCATION);

        initComponents();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1000, 1000);
        this.setResizable(false);
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes the frame and components
    private void initComponents() {
        initTable();
        initAddButton();
        initRemoveButton();
        initSaveButton();
        initLoadButton();
        initStatus();
    }

    // MODIFIES: this
    // EFFECTS: initializes the table of champion information
    private void initTable() {
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        tableModel.addColumn("Name");
        tableModel.addColumn("Difficulty");
        tableModel.addColumn("Information");
        table.setPreferredScrollableViewportSize(new Dimension(500,100));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,250, 1000, 800);
        scrollPane.setBackground(new Color(0x735DD0));
        this.add(scrollPane);
        table.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: initializes the Add Champion button
    private void initAddButton() {
        addChampion = new JButton("Add Champion");
        this.add(addChampion);
        addChampion.setBounds(0, 0, 200, 250);
        addChampion.addActionListener(this);
        addChampion.setBackground(new Color(0xE74646));
        addChampion.setFocusable(false);
    }

    // MODIFIES: this
    // EFFECTS: initializes the Remove Champion button
    private void initRemoveButton() {
        removeChampion = new JButton("Remove Champion");
        this.add(removeChampion);
        removeChampion.setBounds(200,0,200,250);
        removeChampion.addActionListener(this);
        removeChampion.setBackground(new Color(0xE0D849));
        removeChampion.setFocusable(false);
    }

    // MODIFIES: this
    // EFFECTS: initializes the Save button
    private void initSaveButton() {
        saveInfo = new JButton("Save");
        this.add(saveInfo);
        saveInfo.setBounds(400, 0, 200, 250);
        saveInfo.addActionListener(this);
        saveInfo.setBackground(new Color(0x7E7ED5));
        saveInfo.setFocusable(false);
    }

    // MODIFIES: this
    // EFFECTS: initializes the Load button
    private void initLoadButton() {
        loadInfo = new JButton("Load");
        this.add(loadInfo);
        loadInfo.setBounds(600, 0, 200, 250);
        loadInfo.addActionListener(this);
        loadInfo.setBackground(new Color(0xF372F3));
        loadInfo.setFocusable(false);
    }

    // MODIFIES: this
    // EFFECTS: initializes the current Save Status
    private void initStatus() {
        savedStatusInfo = new JPanel();
        savedStatus = new JLabel();
        savedStatusInfo.setLayout(new BorderLayout());
        savedStatusInfo.add(savedStatus);
        this.add(savedStatusInfo);
        setSavedStatus();
        savedStatusInfo.setBounds(800,0,200,250);
        savedStatusInfo.setBackground(new Color(0xD2D2D2));
    }

    // MODIFIES: this
    // EFFECTS: sets the Save status to Saved
    private void setSavedStatus() {
        savedStatus.setText("Saved");
        savedStatus.setIcon(new ImageIcon("./data/saved.png"));
        setUpImage(savedStatus);
        savedStatus.setForeground(new Color(0x48AD5A));
        savedStatus.setFont(new Font("Arial", Font.PLAIN,12));
    }

    // MODIFIES: this
    // EFFECTS: sets the Save status to Not Saved
    private void setNotSavedStatus() {
        savedStatus.setText("Not Saved");
        savedStatus.setIcon(new ImageIcon("./data/notSaved.png"));
        setUpImage(savedStatus);
        savedStatus.setForeground(new Color(0xC72929));
        savedStatus.setFont(new Font("Arial", Font.PLAIN,12));
    }

    // MODIFIES: this
    // EFFECTS: sets up the alignment of the Save status label
    private void setUpImage(JLabel label) {
        label.setHorizontalTextPosition(JLabel.RIGHT);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: associates the buttons with a mouse click
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addChampion) {
            doAdd();
        } else if (e.getSource() == removeChampion) {
            doRemove();
        } else if (e.getSource() == saveInfo) {
            doSave();
        } else if (e.getSource() == loadInfo) {
            doLoad();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a champion to the champion collection and the table
    private void doAdd() {
        String championName = JOptionPane.showInputDialog(null, "Enter Champion Name");
        String championDifficulty = JOptionPane.showInputDialog(null,
                "Enter Champion Difficulty");
        String championInfo = JOptionPane.showInputDialog(null, "Enter Champion Info");

        int count = tableModel.getRowCount();
        tableModel.insertRow(count, new Object[] {championName, championDifficulty, championInfo});

        Champion addingChampion = new Champion(championName, Integer.parseInt(championDifficulty));
        addingChampion.setChampionInfo(championInfo);
        champions.addChampion(addingChampion);

        setNotSavedStatus();
    }

    // MODIFIES: this
    // EFFECTS: if inputted champion is in the table, removes that  champion from the champion collection and the table,
    private void doRemove() {
        String championName = JOptionPane.showInputDialog(null, "Enter Champion Name");
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (championName.equals(tableModel.getValueAt(i, 0).toString())) {
                champions.removeChampion(tableModel.getValueAt(i, 0).toString());
                tableModel.removeRow(i);
                setNotSavedStatus();
                break;
            }
        }
    }

    // EFFECTS: saves a JSON file of the current ChampionCollection at JSON_LOCATION
    private void doSave() {
        try {
            jsonWriter.begin();
            jsonWriter.write(champions);
            jsonWriter.close();
            setSavedStatus();
        } catch (FileNotFoundException ex) {
            // NOT SAVED
        }
    }

    // MODIFIES: this
    // EFFECTS: loads a JSON files of ChampionCollection from JSON_LOCATION
    private void doLoad() {
        try {
            this.champions = jsonReader.read();
            tableModel.setRowCount(champions.getChampionsGuide().size());
            for (int i = 0; i < champions.getChampionsGuide().size(); i++) {
                tableModel.setValueAt(champions.getChampionsGuide().get(i).getName(), i, 0);
                tableModel.setValueAt(champions.getChampionsGuide().get(i).getDifficulty(), i, 1);
                tableModel.setValueAt(champions.getChampionsGuide().get(i).getChampionInfo(), i, 2);
            }
            setSavedStatus();
        } catch (IOException ex) {
            // FAILED TO LOAD
        }
    }
}