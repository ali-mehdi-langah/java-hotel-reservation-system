import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class HotelReservationSystem extends JFrame implements ActionListener {

    JTextField nameField, daysField;
    JRadioButton singleRoom, doubleRoom, suiteRoom;
    JButton bookBtn, resetBtn;
    JTextArea outputArea;
    ButtonGroup bg;
    double roomPrice = 0;

    HotelReservationSystem() {

        setTitle("Hotel Reservation System");
        setSize(600, 550); // increased size
        setLayout(new GridLayout(5, 1, 10, 10)); // main layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== Panel 1: Name =====
        JPanel p1 = new JPanel(new GridLayout(1, 2, 10, 10));
        p1.add(new JLabel("Customer Name:"));
        nameField = new JTextField();
        p1.add(nameField);
        add(p1);

        // ===== Panel 2: Days =====
        JPanel p2 = new JPanel(new GridLayout(1, 2, 10, 10));
        p2.add(new JLabel("Number of Days:"));
        daysField = new JTextField();
        p2.add(daysField);
        add(p2);

        // ===== Panel 3: Room Type =====
        JPanel p3 = new JPanel(new GridLayout(1, 2, 10, 10));
        p3.add(new JLabel("Room Type:"));

        // Sub-panel for 3 radio buttons (VERTICAL)
        JPanel roomPanel = new JPanel(new GridLayout(3, 1));
        singleRoom = new JRadioButton("Single (Rs.2000/day)");
        doubleRoom = new JRadioButton("Double (Rs.4000/day)");
        suiteRoom = new JRadioButton("Suite (Rs.7000/day)");

        bg = new ButtonGroup();
        bg.add(singleRoom);
        bg.add(doubleRoom);
        bg.add(suiteRoom);

        roomPanel.add(singleRoom);
        roomPanel.add(doubleRoom);
        roomPanel.add(suiteRoom);

        p3.add(roomPanel);
        add(p3);

        // ===== Panel 4: Buttons =====
        JPanel p4 = new JPanel();
        bookBtn = new JButton("Book Now");
        resetBtn = new JButton("Reset");

        p4.add(bookBtn);
        p4.add(resetBtn);

        add(p4);

        // ===== Panel 5: Output FULL WIDTH =====
        JPanel p5 = new JPanel(new BorderLayout());
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        p5.add(new JLabel("Booking Details:"), BorderLayout.NORTH);
        p5.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        add(p5);

        // Actions
        bookBtn.addActionListener(this);
        resetBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bookBtn) {

            String name = nameField.getText();
            String daysText = daysField.getText();

            if (name.equals("") || daysText.equals("")) {
                JOptionPane.showMessageDialog(this, "Fill all fields!");
                return;
            }

            int days = Integer.parseInt(daysText);

            if (singleRoom.isSelected()) roomPrice = 2000;
            else if (doubleRoom.isSelected()) roomPrice = 4000;
            else if (suiteRoom.isSelected()) roomPrice = 7000;
            else {
                JOptionPane.showMessageDialog(this, "Select room type!");
                return;
            }

            double total = days * roomPrice;

            outputArea.setText("----- Booking Details -----\n");
            outputArea.append("Customer Name: " + name + "\n");
            outputArea.append("Days: " + days + "\n");

            if (singleRoom.isSelected()) outputArea.append("Room: Single\n");
            else if (doubleRoom.isSelected()) outputArea.append("Room: Double\n");
            else outputArea.append("Room: Suite\n");

            outputArea.append("Total Bill: Rs. " + total + "\n");
            outputArea.append("----------------------------");
        }

        else if (e.getSource() == resetBtn) {
            nameField.setText("");
            daysField.setText("");
            outputArea.setText("");
            bg.clearSelection();
        }
    }

    public static void main(String[] args) {
        new HotelReservationSystem();
    }
}