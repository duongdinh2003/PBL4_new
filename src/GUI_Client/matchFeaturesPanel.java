package GUI_Client;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class matchFeaturesPanel extends JPanel {
	JTextField textField;
	private JTextArea textArea;
	private JTextArea textArea_1;
	JButton btnNewButton;

	/**
	 * Create the panel.
	 */
	public matchFeaturesPanel() {
		setLayout(null);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea.setBounds(36, 437, 528, 194);
//		add(textArea);
		
		JScrollPane scrollMsg = new JScrollPane(textArea);
		scrollMsg.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMsg.setBounds(36, 437, 528, 194);
		add(scrollMsg);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(36, 644, 438, 34);
		add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Send");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				PlayerSession playerSession = PlayerSession.getExistedInstance();
//				System.out.println("Client: "+playerSession.idPlayer);
				
//				String message = textField.getText();
//				updateMessage(message);
//				textField.setText("");
//			}
//		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 15));
		btnNewButton.setBounds(479, 646, 85, 32);
		add(btnNewButton);
		
		textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setFont(new Font("Monospaced", Font.BOLD, 20));
		textArea_1.setForeground(new Color(255, 255, 255));
		textArea_1.setBackground(new Color(128, 128, 128));
		textArea_1.setBounds(36, 49, 528, 194);
//		add(textArea_1);
		
		JScrollPane scroll = new JScrollPane(textArea_1);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(36, 49, 528, 194);
		add(scroll);
		
		JLabel lblNewLabel = new JLabel("History moves");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setBounds(36, 10, 193, 29);
		add(lblNewLabel);
		
		JLabel lblChat = new JLabel("Chat");
		lblChat.setFont(new Font("Arial", Font.BOLD, 24));
		lblChat.setBounds(36, 398, 193, 29);
		add(lblChat);
		
//		this.setVisible(true);
	}
	
	public void updateHistory(String move) {
		String curText = textArea_1.getText();
		curText += move + "\n";
		textArea_1.setText(curText);
	}
	
	public void updateMessage(String msg) {
		String curMsgText = textArea.getText();
		curMsgText += msg + "\n";
		textArea.setText(curMsgText);
	}
}
