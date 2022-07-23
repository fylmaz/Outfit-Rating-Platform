package Views;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import Business.AppManager;
import Interfaces.IObservable;
import Interfaces.IObserver;
import Models.CollectionModel;
import Models.OutfitModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class CollectionView extends JFrame implements IObserver{
	private AppManager appManager;
	private JPanel contentPane;
	private JLabel collectionNameLabel;
	private JScrollPane outfitsListPane;
	private JList outfitsList;
	private JLabel outfitsLabel;
	private JButton addOutfitButton;
	private JButton removeOutfitButton;
	private JButton seeOutfitDetailsButton;
	private JFrame popup;
	private JScrollPane allOutfitsListPane;
	private JList allOutfitsList;
	private JLabel allOutfitsLabel;
	private JButton addOutfitPopupButton;
	private JLabel popupMessageLabel;
	private JLabel collectionViewMessageLabel;
	
	public CollectionView(AppManager appManager) {
		this.setTitle("Collection View");
		this.appManager = appManager;
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 441); 
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0,69,107));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.collectionNameLabel = new JLabel("Collection Name");
		collectionNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		collectionNameLabel.setForeground(Color.WHITE);
		collectionNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		collectionNameLabel.setBounds(10, 10, 366, 42);
		contentPane.add(collectionNameLabel);
		
		this.outfitsListPane = new JScrollPane();
		outfitsListPane.setBounds(10, 60, 366, 248);
		contentPane.add(outfitsListPane);
		
		this.outfitsList = new JList();
		outfitsListPane.setViewportView(outfitsList);
		
		this.outfitsLabel = new JLabel("Outfits");
		outfitsLabel.setForeground(Color.BLACK);
		outfitsLabel.setBackground(Color.LIGHT_GRAY);
		outfitsLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		outfitsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		outfitsListPane.setColumnHeaderView(outfitsLabel);
		
		this.addOutfitButton = new JButton("Add Outfit");
		addOutfitButton.setForeground(Color.WHITE);
		addOutfitButton.setBackground(Color.GREEN);
		addOutfitButton.setBounds(10, 318, 120, 20);
		contentPane.add(addOutfitButton);
		addOutfitButton.setVisible(false);
		
		this.removeOutfitButton = new JButton("Remove Outfit");
		removeOutfitButton.setForeground(Color.WHITE);
		removeOutfitButton.setBackground(Color.RED);
		removeOutfitButton.setBounds(256, 318, 120, 20);
		contentPane.add(removeOutfitButton);
		removeOutfitButton.setVisible(false);

		seeOutfitDetailsButton = new JButton("See Outfit Details"); 
		seeOutfitDetailsButton.setForeground(Color.WHITE);
		seeOutfitDetailsButton.setBackground(Color.GRAY);
		seeOutfitDetailsButton.setBounds(212, 348, 164, 20);
		contentPane.add(seeOutfitDetailsButton);
		
		collectionViewMessageLabel = new JLabel("");
		collectionViewMessageLabel.setForeground(Color.GREEN);
		collectionViewMessageLabel.setBounds(10, 379, 366, 13);
		contentPane.add(collectionViewMessageLabel);
		seeOutfitDetailsButton.setVisible(false);
		
        popup = new JFrame("Add Outfit");
        popup.getContentPane().setBackground(new Color(0,69,107)); 
        popup.setSize(300, 370);
        popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popup.setVisible(false);
        popup.setResizable(false);
        
        popup.getContentPane().setLayout(null);
        
		this.allOutfitsListPane = new JScrollPane();
		allOutfitsListPane.setBounds(10, 10, 265, 250);
		popup.getContentPane().add(allOutfitsListPane);
		
		this.allOutfitsList = new JList();
		allOutfitsListPane.setViewportView(allOutfitsList);
		
		this.allOutfitsLabel = new JLabel("All Outfits");
		allOutfitsLabel.setForeground(Color.BLACK);
		allOutfitsLabel.setBackground(Color.LIGHT_GRAY);
		allOutfitsLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		allOutfitsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		allOutfitsListPane.setColumnHeaderView(allOutfitsLabel);
		
		addOutfitPopupButton = new JButton("Add Outfit");
		addOutfitPopupButton.setBounds(10, 275, 100, 20);
		addOutfitPopupButton.setBackground(Color.GREEN);
		addOutfitPopupButton.setForeground(Color.WHITE);
		addOutfitPopupButton.setVisible(false);
		
		popup.getContentPane().add(addOutfitPopupButton);
		popupMessageLabel = new JLabel();
		popupMessageLabel.setBounds(10, 300, 300, 20);
		popupMessageLabel.setForeground(Color.WHITE);
		popup.getContentPane().add(popupMessageLabel);
        
	}

	public void update(IObservable observable) {
		if(observable == null) {
			this.dispose();
		}else {
			CollectionModel model = (CollectionModel) observable;
			if(this.appManager.getOnlineUser().getCollections().contains(model)) {
				this.setVisibilityOfAddOutfitButton(true);
			}
			this.showCollectionName(model.getName());
			this.addDefaultListModelToOutfitsList();
			for(OutfitModel outfit: model.getOutfits()) {
				this.addElementToOutfitsList("Product Id:" + outfit.getId());
			}
		}
	}

	
	public void addOutfitsListSelectionListener(ListSelectionListener outfitsListSelectionListener) {
		this.outfitsList.addListSelectionListener(outfitsListSelectionListener);	
	}
	
	public void setRemoveOutfitButtonVisibility(boolean isVisible) {
		this.removeOutfitButton.setVisible(isVisible);
	}

	public void setSeeOutfitDetailButtonVisibility(boolean isVisible) {
		this.seeOutfitDetailsButton.setVisible(true);	
	}

	public void addSeeOutfitDetailsListener(ActionListener seeOutfitDetailsListener) {
		this.seeOutfitDetailsButton.addActionListener(seeOutfitDetailsListener);
	}

	public void addOutfitListener(ActionListener addOutfitListener) {
		this.addOutfitButton.addActionListener(addOutfitListener);
		
	}

	public void addAllOutfitsListSelectionListener(ListSelectionListener allOutfitsListSelectionListener) {
		this.allOutfitsList.addListSelectionListener(allOutfitsListSelectionListener);	
	}

	public void addOutfitPopupListener(ActionListener addOutfitListener) {
		this.addOutfitPopupButton.addActionListener(addOutfitListener);
	}

	public void addRemoveOutfitListener(ActionListener removeOutfitListener) {
		this.removeOutfitButton.addActionListener(removeOutfitListener);
	}

	public String getOutfitListSelectedValue() {
		return this.outfitsList.getSelectedValue().toString();
	}

	public void showCollectionViewMessage(String string) {
		this.collectionViewMessageLabel.setText(string);
	}

	public void setPopupMessageLabelForegroundColor(Color color) {
		this.popupMessageLabel.setForeground(color);
	}

	public String getAllOutfitsListSelectedValue() {
		return this.allOutfitsList.getSelectedValue().toString();
	}

	public void showPopupMessage(String string) {
		this.popupMessageLabel.setText(string);
	}

	public void setVisibilityOfAddOutfitPopupButton(boolean visibility) {
		this.addOutfitPopupButton.setVisible(visibility);
	}
	
	public void disposePopup() {
		this.popup.dispose();	
	}

	public void setVisibilityOfPopup(boolean visibility) {
		this.popup.setVisible(visibility);	
	}

	public String getOutfitsListSelectedValue() {
		return this.outfitsList.getSelectedValue().toString();
	}

	public void addDefaultListModelToAllOutfitsList() {
		this.allOutfitsList.setModel(new DefaultListModel());
	}

	public void addElementToAllOutfitsList(String newElem) {
		((DefaultListModel)this.allOutfitsList.getModel()).addElement(newElem);
		
	}

	public void setVisibilityOfAddOutfitButton(boolean visibility) {
		this.addOutfitButton.setVisible(visibility);	
	}

	public void showCollectionName(String name) {
		this.collectionNameLabel.setText(name);
	}

	public void addDefaultListModelToOutfitsList() {
		this.outfitsList.setModel(new DefaultListModel());
	}

	public void addElementToOutfitsList(String newElem) {
		((DefaultListModel)this.outfitsList.getModel()).addElement(newElem);
	}
	


}
