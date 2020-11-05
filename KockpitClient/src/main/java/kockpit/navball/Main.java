package kockpit.navball;

import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;
import org.jblas.DoubleMatrix;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;
import krpc.client.Connection;
import krpc.client.services.KRPC;

public class Main {
    public static final int HEIGHT = 400;
    public static final int WIDTH = 600;

    public static void main(String[] args) throws IOException, RPCException {
        final Frame f = new Frame("Canvas Example");
        final var navBall = new NavBall();
        f.add(navBall);
        navBall.setLocation((WIDTH - NavBall.WIDTH) / 2, (HEIGHT - NavBall.HEIGHT) / 2);
        f.setLayout(null);
        f.setSize(WIDTH, HEIGHT);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setVisible(true);

        final var connection = Connection.newInstance("navball client");
        final var spaceCenter = SpaceCenter.newInstance(connection);
        final var vessel = spaceCenter.getActiveVessel();
        final var flight = vessel.flight(vessel.getSurfaceReferenceFrame());

        while (true) {
            navBall.setPitch(flight.getPitch());
            navBall.setRoll(flight.getRoll());
            navBall.setRotation(flight.getRotation());
            navBall.repaint();
//*
            try {
                Thread.sleep(30);
            } catch (Exception e){
                e.printStackTrace();
                System.exit(0);
            }
            //*/
        }
    }
}
