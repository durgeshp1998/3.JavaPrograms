import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class calculator
{
	public static void main(String s[])
	{
		calculatorFrame frame=new calculatorFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
class calculatorFrame extends JFrame
{
	public calculatorFrame()
	{
		setTitle("Simple calculator");
		calculatorPanel panel=new calculatorPanel();
		add(panel);
		pack();
	}
}
class calculatorPanel extends JPanel
{
	public calculatorPanel()
	{
		setLayout(new BorderLayout());
		result=0;
		lastcommand="=";
		Start=true;
		display=new JButton("0");
		display.setEnabled(false);
		add(display,BorderLayout.NORTH);
		ActionListener insert=new InsertAction();
		ActionListener command=new CommandAction();
		panel=new JPanel();
		panel.setLayout(new GridLayout(4,4));
		addButton("1",insert);
		addButton("2",insert);
		addButton("3",insert);
		addButton("+",command);
		addButton("4",insert);
		addButton("5",insert);
		addButton("6",insert);
		addButton("-",command);
		addButton("7",insert);
		addButton("8",insert);
		addButton("9",insert);
		addButton("*",command);
		addButton("0",insert);
		addButton(".",insert);
		addButton("=",command);
		addButton("/",command);
		add(panel,BorderLayout.CENTER);
	}
	private void addButton(String label,ActionListener listener)
	{
		JButton button=new JButton(label);
		button.addActionListener(listener);
		panel.add(button);
	}
	private class InsertAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String input=event.getActionCommand();
			if(Start)
			{
				display.setText("");
				Start=false;
			}
			display.setText(display.getText()+input);
		}
	}
	private class CommandAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String command=event.getActionCommand();
			if(Start)
			{
				if(command.equals("-"))
				{
					display.setText(command);
					Start=false;
				}
				else
					lastcommand=command;
			}
			else
			{
				calculate(Double.parseDouble(display.getText()));
				lastcommand=command;
				Start=true;
			}
		}
	}
	public void calculate(double x)
	{
		if(lastcommand.equals("+"))
			result +=x;
		else if(lastcommand.equals("-"))
			result -=x;
		else if(lastcommand.equals("*"))
			result *=x;
		else if(lastcommand.equals("/"))
			result /=x;
		else if(lastcommand.equals("="))
			result =x;

		display.setText(""+result);
	}
	private JButton display;
	private JPanel panel;
	private double result;
	private String lastcommand;
	private boolean Start;
}
