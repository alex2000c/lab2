package bsu.rfe.java.group6.lab2.Dorozhko.varA6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Formula extends JFrame {


	private static final int WIDTH = 420;
	private static final int HEIGHT = 320;
	private static final String Sum = null;
	
	private JTextField textFieldX;
	private JTextField textFieldY;
	private JTextField textFieldZ;
	
	private JTextField textFieldResult;
	private JTextField textFieldMplus;
	
//Группа радио-кнопок для обеспечения уникальности выделения в группе
	private ButtonGroup radioButtons = new ButtonGroup();
//Контейнер для отображения радио-кнопок
	private Box hboxFormulaType = Box.createHorizontalBox();
	private int formulaId = 1;
	
	  public Double calculate1(Double x, Double y, Double z)
	  {
		  
		  return Math.pow(Math.cos(Math.exp(y)) + Math.exp(y*y) + Math.sqrt(1/x),0.25)/Math.pow((Math.cos(Math.PI*z)+ Math.log(Math.pow(1+z, 2))), Math.sin(y));
	  }
	  
	  
	  public Double calculate2 (Double x, Double y, Double z)
	  {
		  return (1+Math.pow(x, z)+Math.log(y*y))*(1-Math.sin(y*z))/(Math.sqrt(Math.pow(x, 3) + 1));
	  }
	 
	  
	 
	
//Вспомогательный метод для добавления кнопок на панель
	private void addRadioButton(String buttonName, final int formulaId) {
		JRadioButton button = new JRadioButton(buttonName);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Formula.this.formulaId = formulaId;
				}
			});
		radioButtons.add(button);
		hboxFormulaType.add(button);
		}

	public Formula() {
		super("Вычисление формулы");
		setSize(WIDTH, HEIGHT);
		Toolkit kit = Toolkit.getDefaultToolkit();
		
		setLocation((kit.getScreenSize().width - WIDTH)/2,
				(kit.getScreenSize().height - HEIGHT)/2);
//добавить клей слева
		hboxFormulaType.add(Box.createHorizontalGlue());
		addRadioButton("Формула 1", 1);
		addRadioButton("Формула 2", 2);
	
//Установить выделенной 1-ую кнопку из группы
		radioButtons.setSelected( radioButtons.getElements().nextElement().getModel(), true);
//добавить клей справа
		hboxFormulaType.add(Box.createHorizontalGlue());
		
		JLabel labelForX = new JLabel("X:");
		textFieldX = new JTextField("0", 7);
		textFieldX.setMaximumSize(textFieldX.getPreferredSize());
		
		JLabel labelForY = new JLabel("Y:");
		textFieldY = new JTextField("0", 7);
		textFieldY.setMaximumSize(textFieldY.getPreferredSize());
		
		JLabel labelForZ = new JLabel("Z:");
		textFieldZ = new JTextField("0", 7);
		textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
		
		
//Создать контейнер «коробка с горизонтальной укладкой»
		Box hboxVariables = Box.createHorizontalBox();

//добавить клей
		
		hboxVariables.add(Box.createHorizontalStrut(5));
		
//Добавить подпись для переменной Х
		hboxVariables.add(labelForX);
		hboxVariables.add(Box.createHorizontalStrut(5));
//Добавить само текстовое поле для ввода Х
		hboxVariables.add(textFieldX);
//Добавить «распорку» шириной 30 пикселов для отступа между текстовым полем для ввода X и подписью для Y
		hboxVariables.add(Box.createHorizontalStrut(30));
		hboxVariables.add(Box.createHorizontalGlue());
		
//Добавить подпись для переменной Y
		hboxVariables.add(labelForY);
		hboxVariables.add(Box.createHorizontalStrut(5));
		hboxVariables.add(textFieldY);
//Добавить «распорку» шириной 30 пикселов для отступа между текстовым полем для ввода Y и подписью для Z
		hboxVariables.add(Box.createHorizontalStrut(30));
		hboxVariables.add(Box.createHorizontalGlue());
		
//Добавить подпись для переменной Z
		hboxVariables.add(labelForZ);
		hboxVariables.add(Box.createHorizontalStrut(5));
		hboxVariables.add(textFieldZ);
		
//Добавить «клей» для максимального удаления от правого края
	
		
		
		
//Создать подпись для поля с результатом
		JLabel labelForResult = new JLabel("Результат:");
//Создать текстовое поле для вывода результата, начальное значение - 0
		textFieldResult = new JTextField("0", 15);
//setMaximumSize - максимальный размер компонентов, равный желаемому
		textFieldResult.setMaximumSize(	textFieldResult.getPreferredSize() );
//Создать контейнер «коробка с горизонтальной укладкой»
		Box hboxResult = Box.createHorizontalBox();
		hboxResult.add(Box.createHorizontalGlue());
//Добавить подпись для результата
		hboxResult.add(labelForResult);
		hboxResult.add(Box.createHorizontalStrut(10));
//Добавить текстовое поле для вывода результата
		hboxResult.add(textFieldResult);
		hboxResult.add(Box.createHorizontalGlue());
		
//Создать область для кнопок
		JButton buttonCalc = new JButton("Вычислить");
//Определить и зарегистрировать обработчик нажатия на кнопку
		buttonCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					Double x = Double.parseDouble(textFieldX.getText());
					Double y = Double.parseDouble(textFieldY.getText());
					Double z = Double.parseDouble(textFieldZ.getText());
					

					Double result;
					if (formulaId==1)							
						
						result = calculate1(x, y, z);
					else
						
						result = calculate2(x, y, z);
					
//Установить текст надписи равным результату
					textFieldResult.setText(result.toString());
					} 
				catch (NumberFormatException ex) 	
				{
//В случае исключительной ситуации показать сообщение
						JOptionPane.showMessageDialog(Formula.this, "Ошибка в формате записи числа с плавающей точкой",
								"Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
						}
				}
			});
				
		JButton buttonReset = new JButton("Очистить поля");
		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				textFieldX.setText("0");
				textFieldY.setText("0");
				textFieldZ.setText("0");
				textFieldResult.setText("0");
				}
			});
	
//Создать подпись для поля с результатом
		JLabel labelForMplus = new JLabel(":");
//Создать текстовое поле для вывода результата, начальное значение - 0
		textFieldMplus = new JTextField("0", 15);
//setMaximumSize - максимальный размер компонентов, равный желаемому
		textFieldMplus.setMaximumSize(	textFieldMplus.getPreferredSize() );
//Создать контейнер «коробка с горизонтальной укладкой»
		Box hboxMplus = Box.createHorizontalBox();
		hboxMplus.add(Box.createHorizontalGlue());
//Добавить подпись для результата
		hboxMplus.add(labelForMplus);
		hboxMplus.add(Box.createHorizontalStrut(10));
//Добавить текстовое поле для вывода результата
		hboxMplus.add(textFieldMplus);
		hboxMplus.add(Box.createHorizontalGlue());

		
		JButton buttonMplus = new JButton("M+");
//Определить и зарегистрировать обработчик нажатия на кнопку
		buttonMplus.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ev) 
			{
				Double Sum = Double.parseDouble(textFieldMplus.getText() ) + Double.parseDouble(textFieldResult.getText() );
//Установить текст надписи равным результату
				textFieldMplus.setText(Sum.toString());
			}
			
		});
		

		
		JButton buttonMclear = new JButton("MC");
		buttonMclear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ev) {
				textFieldMplus.setText("0") ;
			}
			
		});
		

		
		Box hboxButtons = Box.createHorizontalBox();
		hboxButtons.add(Box.createHorizontalStrut(50) );
		hboxButtons.add(buttonCalc);
		hboxButtons.add(Box.createHorizontalStrut(40));
		hboxButtons.add(buttonReset);		
		hboxButtons.add(Box.createHorizontalStrut(40) );
		
		
		Box ButtonsSumResult = Box.createHorizontalBox();
		ButtonsSumResult.add(Box.createHorizontalStrut(20) );
		ButtonsSumResult.add(buttonMplus);
		ButtonsSumResult.add(Box.createHorizontalGlue() );
				
		
		
		
		Box ButtonsClear = Box.createHorizontalBox();
		ButtonsClear.add(Box.createHorizontalStrut(20) );
		ButtonsClear.add(buttonMclear);
		ButtonsClear.add(Box.createHorizontalGlue() );
		
		ButtonsClear.add(hboxMplus);
		
		
		
				
//Связать области воедино в компоновке BoxLayout

//Создать контейнер «коробка с вертикальной укладкой»
		Box contentBox = Box.createVerticalBox();
//Добавить «клей» V1 сверху
		contentBox.add(Box.createVerticalGlue());

//Добавить контейнер с переменными
		contentBox.add(Box.createVerticalGlue() );
		contentBox.add(hboxVariables);
//Добавить контейнер с результатом вычислений
		
		contentBox.add(Box.createVerticalGlue() );
		contentBox.add(hboxResult);
//Добавить контейнер с кнопками
		contentBox.add(Box.createVerticalGlue() );
		contentBox.add(hboxButtons);
		contentBox.add(ButtonsSumResult);
		contentBox.add(ButtonsClear);
		contentBox.add(Box.createVerticalGlue());
		//Добавить контейнер с выбором формулы
				contentBox.add(hboxFormulaType);
//Установить «вертикальную коробку» в область содержания главного окна
		getContentPane().add(contentBox, BorderLayout.CENTER);		
	}
		
//Главный метод класса
	public static void main(String[] args) 
	{
		Formula frame = new Formula();	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}
