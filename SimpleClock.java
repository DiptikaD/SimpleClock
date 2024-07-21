//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Timer;


public class SimpleClock extends JFrame {
    
        Calendar calendar;
        SimpleDateFormat timeFormat;
        SimpleDateFormat timeFormat24;
        SimpleDateFormat gmtFormat;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;

        boolean is24HourFormat = false;
        boolean isGMTformat = false;
    
        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;
        JLabel toggleButton;
        JLabel toggleGMTButton;
        String time;
        String day;
        String date;

        SimpleClock() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");
            this.setLayout(new FlowLayout());
            this.setSize(440, 260);
            this.setResizable(true);
    
            timeFormat = new SimpleDateFormat("hh:mm:ss a");
            timeFormat24 = new SimpleDateFormat("HH:mm:ss");
            dayFormat=new SimpleDateFormat("EEEE");
            dateFormat=new SimpleDateFormat("dd MMMMM, yyyy");
            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
            timeLabel.setBackground(Color.BLACK);
            timeLabel.setForeground(Color.WHITE);
            timeLabel.setOpaque(true);
            dayLabel=new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,34));
    
            dateLabel=new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,30));

            toggleGMTButton = new JLabel("Switch to GMT", Font.ITALIC);
            toggleGMTButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            toggleGMTButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    isGMTformat = !isGMTformat;

                    toggleGMTButton.setText(isGMTformat ? "Switch to local time" : "Switch to GMT time");


                }
            });

            toggleButton = new JLabel("Switch to 24Hour /", Font.ITALIC);
            toggleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            toggleButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    is24HourFormat = !is24HourFormat;

                    toggleButton.setText(is24HourFormat ? "Switch to 12 Hr format /" : "Switch to 24 Hr format /");

                    updateClock();
                }
            });

            this.add(timeLabel);
            this.add(dayLabel);
            this.add(dateLabel);
            this.add(toggleButton);
            this.add(toggleGMTButton);
            this.setVisible(true);


            setTimer();
        }

        public void updateClock(){
            Date now = new Date();
            if (isGMTformat){
                timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                timeFormat24.setTimeZone(TimeZone.getTimeZone("GMT"));
            } else{
                timeFormat.setTimeZone(TimeZone.getDefault());
                timeFormat24.setTimeZone(TimeZone.getDefault());
            }
            String timeString = is24HourFormat ? timeFormat24.format(now) : timeFormat.format(now);

            timeLabel.setText(timeString);
        }

    
        public void setTimer() {
            while (true) {
                time = timeFormat.format(Calendar.getInstance().getTime());
                timeLabel.setText(time);
    
                day = dayFormat.format(Calendar.getInstance().getTime());
                dayLabel.setText(day);
    
                date = dateFormat.format(Calendar.getInstance().getTime());
                dateLabel.setText(date);
                updateClock();
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
        public static void main(String[] args) {
            new SimpleClock();
        }
    }
