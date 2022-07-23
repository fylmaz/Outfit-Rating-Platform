package Views;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Interfaces.IObservable;
import Interfaces.IObserver;
import Models.CommentModel;

import javax.swing.JScrollPane;
public class CommentView extends JFrame implements IObserver {
	private JPanel contentPane;
	private JTextArea comment;

	public CommentView() {
		this.setTitle("Comment View");
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 300); 
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0,69,107));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 267, 245);
		contentPane.add(scrollPane);
		
		comment = new JTextArea();
		scrollPane.setViewportView(comment);
		comment.setDisabledTextColor(Color.BLACK);
		comment.setEnabled(false);
	}

	public void update(IObservable observable) {
		CommentModel model = (CommentModel) observable;
		this.setCommentField("Id: " + model.getId() + "\n" +
				 "Comment Owner: " + model.getcommentOwnerUsername() + "\n" +
				 "Comment: " + model.getText());
	}

	public void setCommentField(String text) {
		this.comment.setText(text);
		
	}


}
