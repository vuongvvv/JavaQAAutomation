package AutomationSupport;

import AutomationFramework.Keywords;
import AutomationFramework.TestSuite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class AutomationGeneration extends JPanel implements ActionListener {
    JButton jButtonApiFolder, jButtonSourceFile, jButtonGenerate;
    JLabel jLabelApiFolder, jLabelSourceFile;
    JLabel jLabel;
    JFileChooser chooser;
    String choosertitle;
    File apiFolder, sourcePath;

    public AutomationGeneration() {
        jButtonApiFolder = new JButton("Select...");
        jLabelApiFolder = new JLabel("Select path to API folder on Robot Framework");
        jLabelSourceFile = new JLabel("Select source file to generate test script");
        jButtonSourceFile = new JButton("Select...");
        jButtonGenerate = new JButton("Generate the test script");
        jButtonGenerate.setEnabled(false);
        jButtonApiFolder.addActionListener(this);
        jButtonSourceFile.addActionListener(this);
        jButtonGenerate.addActionListener(this);
        add(jLabelApiFolder);
        add(jButtonApiFolder);
        add(jLabelSourceFile);
        add(jButtonSourceFile);
        add(jButtonGenerate);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButtonApiFolder) {
            chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle(choosertitle);
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                apiFolder = chooser.getSelectedFile();
                System.out.println(apiFolder);
            } else {
                System.out.println("No Selection ");
            }
        }

        if (e.getSource() == jButtonSourceFile) {
            chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle(choosertitle);
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                sourcePath = chooser.getSelectedFile();
                System.out.println(sourcePath);
            } else {
                System.out.println("No Selection ");
            }
            if (!apiFolder.toString().isEmpty() && !sourcePath.toString().isEmpty()) {
                jButtonGenerate.setEnabled(true);
            }
        }

        if (e.getSource() == jButtonGenerate) {
            TestSuite testSuite = new TestSuite();
            Keywords keywords = new Keywords();
            testSuite.generatingTestSuiteFromInputFile(sourcePath.toString(), apiFolder.toString());
            keywords.generatingKeywordsFileFromInputFile(sourcePath.toString(), apiFolder.toString());
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 250);
    }

    public static void main(String s[]) {
        JFrame frame = new JFrame("Automation Script Generation");
        AutomationGeneration panel = new AutomationGeneration();
        frame.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
        frame.getContentPane().add(panel, "Center");
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}
