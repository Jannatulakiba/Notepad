// A Simple Notepad Program using java
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.text.*;

class SimpleNotepad extends JFrame implements ActionListener {
    // Text component
    JTextArea editor;
 
    // Constructor
    SimpleNotepad()
    {
        super("Jannat's Notepad");
        createMenu();

        // Text Area component
        editor = new JTextArea();
        this.add(editor);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void createMenu()
    {
        // Create a menubar
        JMenuBar mb = new JMenuBar();
 
        // Create amenu for menu
        JMenu m1 = new JMenu("File");
 
        // Create menu items
        JMenuItem mi1 = new JMenuItem("New");
        JMenuItem mi2 = new JMenuItem("Open");
        JMenuItem mi3 = new JMenuItem("Save");
        JMenuItem mi9 = new JMenuItem("Print");
        JMenuItem mi10 = new JMenuItem("Exit");
 
        // Add action listener
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi9.addActionListener(this);
        mi10.addActionListener(this);
 
        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi9);
        m1.add(mi10);
 
        // Create amenu for menu
        JMenu m2 = new JMenu("Edit");
 
        // Create menu items
        JMenuItem mi4 = new JMenuItem("Cut");
        JMenuItem mi5 = new JMenuItem("Copy");
        JMenuItem mi6 = new JMenuItem("Paste");
 
        // Add action listener
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);
 
        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);
 
        mb.add(m1);
        mb.add(m2);
 
        this.setJMenuBar(mb);
    }

    void openFile() 
    {
        // Create an object of JFileChooser class
        JFileChooser j = new JFileChooser("f:");

        // Invoke the showsOpenDialog function to show the save dialog
        int r = j.showOpenDialog(null);

        // If the user selects a file
        if (r == JFileChooser.APPROVE_OPTION) {
            // Set the label to the path of the selected directory
            File fi = new File(j.getSelectedFile().getAbsolutePath());

            try {
                // String
                String s1 = "", sl = "";

                // File reader
                FileReader fr = new FileReader(fi);

                // Buffered reader
                BufferedReader br = new BufferedReader(fr);

                // Initialize sl
                sl = br.readLine();

                // Take the input from the file
                while ((s1 = br.readLine()) != null) {
                    sl = sl + "\n" + s1;
                }

                // Set the text
                editor.setText(sl);
            }
            catch (Exception evt) {
                JOptionPane.showMessageDialog(this, evt.getMessage());
            }
        }
        // If the user cancelled the operation
        else
            JOptionPane.showMessageDialog(this, "the user cancelled the operation");
    }

    void saveFile() 
    {
        // Create an object of JFileChooser class
        JFileChooser j = new JFileChooser("f:");

        // Invoke the showsSaveDialog function to show the save dialog
        int r = j.showSaveDialog(null);

        if (r == JFileChooser.APPROVE_OPTION) {

            // Set the label to the path of the selected directory
            File fi = new File(j.getSelectedFile().getAbsolutePath());

            try {
                // Create a file writer
                FileWriter wr = new FileWriter(fi, false);

                // Create buffered writer to write
                BufferedWriter w = new BufferedWriter(wr);

                // Write
                w.write(editor.getText());

                w.flush();
                w.close();
            }
            catch (Exception evt) {
                JOptionPane.showMessageDialog(this, evt.getMessage());
            }
        }
        // If the user cancelled the operation
        else
            JOptionPane.showMessageDialog(this, "the user cancelled the operation");
    }
 
    // If a button is pressed
    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
 
        if (s.equals("Cut")) {
            editor.cut();
        }
        else if (s.equals("Copy")) {
            editor.copy();
        }
        else if (s.equals("Paste")) {
            editor.paste();
        }
        else if (s.equals("Save")) {
            saveFile();
        }
        else if (s.equals("Print")) {
            try {
                // print the file
                editor.print();
            }
            catch (Exception evt) {
                JOptionPane.showMessageDialog(this, evt.getMessage());
            }
        }
        else if (s.equals("Open")) {
            openFile();
        }
        else if (s.equals("New")) {
            editor.setText("");
        }
        else if (s.equals("Exit")) {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }
 
    // Main class
    public static void main(String args[])
    {
        SimpleNotepad notepad = new SimpleNotepad();
        notepad.setSize(1280, 720);
        notepad.show();
    }
}

