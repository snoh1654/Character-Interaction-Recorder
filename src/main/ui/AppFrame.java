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

    public AppFrame() throws FileNotFoundException {
        champions = new ChampionCollection();
        jsonWriter = new JsonWriter(JSON_LOCATION);
        jsonReader = new JsonReader(JSON_LOCATION);

        this.setTitle("Champion Guide");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(null);
        this.setSize(1000, 1000);

        initComponents();
    }

    private void initComponents() {
        initTable();
        initAddButton();
        initRemoveButton();
        initSaveButton();
        initLoadButton();
    }

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
    }

    private void initAddButton() {
        addChampion = new JButton("Add Champion");
        addChampion.setBounds(0, 0, 250, 250);
        addChampion.addActionListener(this);
        addChampion.setBackground(new Color(0xE74646));
        addChampion.setFocusable(false);
        this.add(addChampion);
    }

    private void initRemoveButton() {
        removeChampion = new JButton("Remove Champion");
        removeChampion.setBounds(250,0,250,250);
        removeChampion.addActionListener(this);
        removeChampion.setBackground(new Color(0xE0D849));
        addChampion.setFocusable(false);
        this.add(removeChampion);
    }

    private void initSaveButton() {
        saveInfo = new JButton("Save");
        saveInfo.setBounds(500, 0, 250, 250);
        saveInfo.addActionListener(this);
        saveInfo.setBackground(new Color(0xF372F3));
        saveInfo.setFocusable(false);
        this.add(saveInfo);
    }

    private void initLoadButton() {
        loadInfo = new JButton("Load");
        loadInfo.setBounds(750, 0, 250, 250);
        loadInfo.addActionListener(this);
        loadInfo.setBackground(new Color(0x7E7ED5));
        loadInfo.setFocusable(false);
        this.add(loadInfo);
    }


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
    }

    private void doRemove() {
        String championName = JOptionPane.showInputDialog(null, "Enter Champion Name");
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (championName.equals(tableModel.getValueAt(i, 0))) {
                tableModel.removeRow(i);
                champions.removeChampion((String)tableModel.getValueAt(i, 0));
                break;
            }
        }
    }

    private void doSave() {
        try {
            jsonWriter.begin();
            jsonWriter.write(champions);
            jsonWriter.close();
        } catch (FileNotFoundException ex) {
            // NOT SAVED
        }
    }

    private void doLoad() {
        try {
            this.champions = jsonReader.read();
            tableModel.setRowCount(champions.getChampionsGuide().size());
            for (int i = 0; i < champions.getChampionsGuide().size(); i++) {
                tableModel.setValueAt(champions.getChampionsGuide().get(i).getName(), i, 0);
                tableModel.setValueAt(champions.getChampionsGuide().get(i).getDifficulty(), i, 1);
                tableModel.setValueAt(champions.getChampionsGuide().get(i).getChampionInfo(), i, 2);
            }
        } catch (IOException ex) {
            // FAILED TO LOAD
        }
    }
}