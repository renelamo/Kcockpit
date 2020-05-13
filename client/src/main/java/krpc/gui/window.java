package krpc.gui;

import com.fazecast.jSerialComm.SerialPort;
import krpc.client.RPCException;
import krpc.core.KRPCClient;
import krpc.core.Logger;
import krpc.core.UnknownOSException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class window {
    private JButton connectKRPCbutton;
    private JButton connectSTM32button;
    private JComboBox devicesList;
    private JPanel panel;
    private JCheckBox logsCheckBox;
    private JComboBox logLevelBox;
    private JButton atcualizeDeviceList;
    private JTextArea logTextArea;

    private KRPCClient client;

    public window() {
        connectSTM32button.addActionListener(actionEvent -> {
            if (connectSTM32button.getText().equals("Connect device")) {
                try {
                    client.connectSerial();
                } catch (UnknownOSException | InterruptedException e) {
                    e.printStackTrace();
                }
                connectSTM32button.setText("Disconnect device");
                devicesList.setEnabled(false);
            } else {
                connectSTM32button.setText("Connect device");
                devicesList.setEnabled(true);
            }
        });
        connectKRPCbutton.addActionListener(actionEvent -> {
            try {
                client.connectKRPC();
            } catch (RPCException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        logsCheckBox.addActionListener(actionEvent -> {
            logTextArea.setVisible(logsCheckBox.isSelected());
            logLevelBox.setVisible(logsCheckBox.isSelected());
        });
        atcualizeDeviceList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                devicesList.removeAllItems();
                for(SerialPort port:SerialPort.getCommPorts()){
                    devicesList.addItem(port);
                }
            }
        });
    }

    public static void main(String[] args) {
        window window = new window();
        JFrame frame = new JFrame("KRPC client");
        window.client = new KRPCClient();
        window.client.logger.logLevel = Logger.LogLevel.Debug;
        window.client.logger.stream = new TextAreaPrintStream(window.logTextArea, System.out);
        frame.setContentPane(window.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
