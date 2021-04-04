package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI {

    private JTextArea inputClientsText = new JTextArea();
    private JTextArea inputQueuesText = new JTextArea();
    private JTextArea inputTimeText = new JTextArea();
    private JTextArea inputArrivalMin = new JTextArea();
    private JTextArea inputArrivalMax = new JTextArea();
    private JTextArea inputServiceMin = new JTextArea();
    private JTextArea inputServiceMax = new JTextArea();
    private JTextArea consoleText = new JTextArea();
    private JButton start=new JButton("START!");

    public GUI()
    {

    }

    public String getInputClientsText()
    {
        return inputClientsText.getText();
    }
    public String getInputQueuesText()
    {
        return inputQueuesText.getText();
    }
    public String getInputTimeText()
    {
        return inputTimeText.getText();
    }
    public String getInputArrivalMinText()
    {
        return inputArrivalMin.getText();
    }
    public String getInputArrivalMaxText()
    {
        return inputArrivalMax.getText();
    }
    public String getInputServiceMinText()
    {
        return inputServiceMin.getText();
    }
    public String getInputServiceMaxText()
    {
        return inputServiceMax.getText();
    }

    public void setActionOnStartButton(ActionListener actionListener)
    {
        start.addActionListener(actionListener);
    }

    public void addTextOnConsole(String s)
    {
        consoleText.setText(consoleText.getText() + s);
    }
    public void setTextOnConsole(String s)
    {
        consoleText.setText(s);
    }

    public void initialise()
    {
        Color frameColor = Color.decode("#4ECDC4");
        Color buttonColor = Color.decode("#1A535C");
        int width = 1024; int height = 720;
        int labelWidth = width / 4;
        int labelHeight = height / 15;
        int leftMargin = width / 60;
        int buttonWidth = width / 8;
        int buttonHeight = height / 8;
        JFrame frame=new JFrame("Queues Simulator");//creating instance of JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("Queues Simulator");
        titleLabel.setFont(new Font(Font.SERIF,  Font.BOLD, 22));
        titleLabel.setBounds(labelWidth + labelWidth / 2, 0,labelWidth,labelHeight);
        frame.add(titleLabel);

        JLabel madeByLabel = new JLabel("@ Made By Fleser Mihai");
        madeByLabel.setFont(new Font(Font.SERIF,  Font.ITALIC, 14));
        madeByLabel.setBounds(labelWidth + labelWidth / 2, height - labelHeight,labelWidth,labelHeight);
        frame.add(madeByLabel);

        JLabel clientsLabel = new JLabel("Number of Clients:");
        clientsLabel.setFont(new Font(Font.SERIF,  Font.ITALIC, 18));
        clientsLabel.setBounds(leftMargin, labelHeight,labelWidth,labelHeight);
        frame.add(clientsLabel);

        inputClientsText.setFont(new Font(Font.SERIF,  Font.PLAIN, 17));
        inputClientsText.setBounds(clientsLabel.getX(), clientsLabel.getY() + labelHeight,labelWidth / 2,labelHeight - labelHeight / 3);
        inputClientsText.setText("30");
        frame.add(inputClientsText);

        JLabel queuesLabel = new JLabel("Number of Queues:");
        queuesLabel.setFont(new Font(Font.SERIF,  Font.ITALIC, 18));
        queuesLabel.setBounds(leftMargin, labelHeight * 3,labelWidth,labelHeight);
        frame.add(queuesLabel);

        inputQueuesText.setFont(new Font(Font.SERIF,  Font.PLAIN, 17));
        inputQueuesText.setBounds(queuesLabel.getX(), queuesLabel.getY() + labelHeight,labelWidth / 2,labelHeight - labelHeight / 3);
        inputQueuesText.setText("4");
        frame.add(inputQueuesText);

        JLabel timeLabel = new JLabel("Time for the Simulation:");
        timeLabel.setFont(new Font(Font.SERIF,  Font.ITALIC, 18));
        timeLabel.setBounds(leftMargin, labelHeight * 5,labelWidth,labelHeight);
        frame.add(timeLabel);

        inputTimeText.setFont(new Font(Font.SERIF,  Font.PLAIN, 17));
        inputTimeText.setBounds(timeLabel.getX(), timeLabel.getY() + labelHeight,labelWidth / 2,labelHeight - labelHeight / 3);
        inputTimeText.setText("60");
        frame.add(inputTimeText);

        JLabel arrivalLabel = new JLabel("Arrival Time, Min and Max:");
        arrivalLabel.setFont(new Font(Font.SERIF,  Font.ITALIC, 18));
        arrivalLabel.setBounds(leftMargin, labelHeight * 7,labelWidth,labelHeight);
        frame.add(arrivalLabel);

        inputArrivalMin.setFont(new Font(Font.SERIF,  Font.PLAIN, 17));
        inputArrivalMin.setBounds(arrivalLabel.getX(), arrivalLabel.getY() + labelHeight,labelWidth / 5,labelHeight - labelHeight / 3);
        inputArrivalMin.setText("2");
        frame.add(inputArrivalMin);

        inputArrivalMax.setFont(new Font(Font.SERIF,  Font.PLAIN, 17));
        inputArrivalMax.setBounds(arrivalLabel.getX() + inputArrivalMin.getWidth() * 2, arrivalLabel.getY() + labelHeight,labelWidth / 5,labelHeight - labelHeight / 3);
        inputArrivalMax.setText("30");
        frame.add(inputArrivalMax);

        JLabel serviceLabel = new JLabel("Service Time, Min and Max:");
        serviceLabel.setFont(new Font(Font.SERIF,  Font.ITALIC, 18));
        serviceLabel.setBounds(leftMargin, labelHeight * 9,labelWidth,labelHeight);
        frame.add(serviceLabel);

        inputServiceMin.setFont(new Font(Font.SERIF,  Font.PLAIN, 17));
        inputServiceMin.setBounds(serviceLabel.getX(), serviceLabel.getY() + labelHeight,labelWidth / 5,labelHeight - labelHeight / 3);
        inputServiceMin.setText("3");
        frame.add(inputServiceMin);

        inputServiceMax.setFont(new Font(Font.SERIF,  Font.PLAIN, 17));
        inputServiceMax.setBounds(serviceLabel.getX() + inputServiceMin.getWidth() * 2, serviceLabel.getY() + labelHeight,labelWidth / 5,labelHeight - labelHeight / 3);
        inputServiceMax.setText("9");
        frame.add(inputServiceMax);

        start.setBounds(leftMargin,inputServiceMax.getY() + labelHeight,buttonWidth, buttonHeight);//x axis, y axis, width, height
        start.setBackground(buttonColor);
        start.setFont(new Font(Font.SERIF,  Font.BOLD, 16));
        start.setForeground(Color.white);
        frame.add(start);

        JLabel consoleLabel = new JLabel("Console:");
        consoleLabel.setFont(new Font(Font.SERIF,  Font.ITALIC, 18));
        consoleLabel.setBounds(clientsLabel.getX() + labelWidth, clientsLabel.getY(),labelWidth,labelHeight);
        frame.add(consoleLabel);

        consoleText.setRows(20);
        consoleText.setColumns(30);
        consoleText.setBounds(consoleLabel.getX(),consoleLabel.getY() + labelHeight, width / 2  + width / 6, height / 2 + height / 4);
        consoleText.setEditable(false);
        consoleText.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(consoleLabel.getX(),consoleLabel.getY() + labelHeight, width / 2  + width / 6, height / 2 + height / 4);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.getViewport().add(consoleText);
        scroll.setLocation(consoleLabel.getX(), consoleLabel.getY() + labelHeight);
        frame.add(scroll);

        //frame.pack();
        frame.setSize(width,height + 38);
        frame.setLayout(null);//using no layout managers
        frame.getContentPane().setBackground(frameColor);
        frame.setVisible(true);//making the frame visible
    }
}
