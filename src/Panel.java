import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Panel extends JPanel {
private JTextField output = new JTextField();;
private Font font = new Font("SanSerif", Font.BOLD, 22);
private Font fontEnter = new Font("SanSerif", Font.BOLD, 35);
private JButton [] buttons;
private String [] listTokens;
private Main main;
private String text;

        public Panel(Main _main){
            main = _main;
        }
        public Panel(){

        }

   public JPanel North(){
       setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
       output.setFont(fontEnter);
       output.setEditable(false);
       add(output);
       return this;
   }

   public JPanel Center(){
       listTokens = new String[] {"(",   ")",  "C", "←",
                                  "7",   "8",   "9", "÷",
                                  "4",   "5",   "6", "×",
                                  "1",   "2",   "3", "-",
                                  ".",   "0",   "=", "+" };

       setLayout(new GridLayout(5, 4, 1,1 ));
       buttons = new JButton[listTokens.length];
       for(int i = 0; i< listTokens.length; i++){
           buttons[i] = new JButton(listTokens[i]);
           buttons[i].setFont(font);
           add(buttons[i]);
       }
       Listeners();

       return this;
   }

   public void Listeners(){
       ActionListener a1 = new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               JButton b = (JButton) e.getSource();
               text = main.getPanel1().output.getText();

               if(text.isEmpty() || !text.substring(text.length() - 1).matches("[-+×÷.]")) {
                   main.getPanel1().output.setText(text + b.getText());
               }else if(!b.getText().matches("[-+×÷.]")) {
                   main.getPanel1().output.setText(text + b.getText());
               }
           }
       };
       for(int i = 0; i<listTokens.length; i ++) {
           String patternFormat = "[\\d-+×÷.()]";
           if(listTokens[i].matches(patternFormat))
           buttons[i].addActionListener(a1);
       }

       ActionListener a2 = new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               text = main.getPanel1().output.getText();
               try {
                   text = text.replace("(-", "($"); // So it is necessary)
                   if(text.substring(0,1).equals("-")){
                       text = "$" + text.substring(1);
                   }

                   double equals = new RPN().count(text);
                   if(equals%1==0){                                 // For a beautiful answer
                       main.getPanel1().output.setText((int) equals +"");
                   }else {
                       String formattedDouble = new DecimalFormat("#0.00").format(equals).replace(',', '.');
                       main.getPanel1().output.setText(formattedDouble);
                   }
               } catch (Exception exception) {
                   JOptionPane.showMessageDialog(null, "Проверьте корректность введёных данных");
               }

           }
       };
       buttons[18].addActionListener(a2);

       ActionListener a3 = new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               main.getPanel1().output.setText("");
           }
       };
       buttons[2].addActionListener(a3);

       ActionListener a4 = new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               text = main.getPanel1().output.getText();
               main.getPanel1().output.setText(text.substring(0, text.length()-1));
           }
       };
       buttons[3].addActionListener(a4);
   }
}