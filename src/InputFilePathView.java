


import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class InputFilePathView extends JFrame {

	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private JTextField textField;

	public InputFilePathView() {
		setTitle("ファイルパス入力");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 629, 268);
		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("ok");
		btnNewButton.setBounds(90, 152, 116, 25);
		btnNewButton.setAction(action);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("cancel");
		btnNewButton_1.setAction(action_1);
		btnNewButton_1.addActionListener(action_1);
		btnNewButton_1.setBounds(384, 152, 101, 25);
		getContentPane().add(btnNewButton_1);

		textField = new JTextField();
		textField.setDragEnabled(true);
		textField.setBounds(23, 103, 567, 25);
		getContentPane().add(textField);
		textField.setColumns(10);

		JEditorPane dtrpnAaaa = new JEditorPane();
		dtrpnAaaa.setDragEnabled(true);
		dtrpnAaaa
				.setText("監視したいファイルがあるディレクトリを指定してください。空白の場合はデフォルトディレクトリのファイル選択ダイアログが起動します");
		dtrpnAaaa.setBounds(23, 38, 567, 52);
		getContentPane().add(dtrpnAaaa);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "ok");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			String text = textField.getText();
			setVisible(false);
			MainModel model = new MainModel();
			model.settingFile(text);

		}
	}

	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "cancel");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

}
