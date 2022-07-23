package Controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Business.AppManager;
import Models.CollectionModel;
import Models.OutfitModel;
import Views.CollectionView;

public class CollectionController {
	private AppManager appManager;
	private CollectionModel model;
	private CollectionView view;

	public CollectionController(AppManager appManager, CollectionModel model, CollectionView view) {
		this.appManager = appManager;
		this.model = model;
		this.view = view;
		model.addObserver(view);
		this.updateViewComponents();
		
		view.addRemoveOutfitListener(new RemoveOutfitListener());
		view.addOutfitPopupListener(new AddOutfitPopupListener());
		view.addAllOutfitsListSelectionListener(new AllOutfitsListSelectionListener());
		view.addWindowListener(new WindowListenerr());
		view.addOutfitListener(new AddOutfitListener());
		view.addSeeOutfitDetailsListener(new SeeOutfitDetailsListener());
		view.addOutfitsListSelectionListener(new OutfitsListSelectionListener());
		 
	}
	
	/**
	 * To remove selected outfit from the collection
	 */
	class RemoveOutfitListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int outfitId = Integer.parseInt(view.getOutfitListSelectedValue().split(":")[1]);
			OutfitModel outfit = appManager.getOutfitService().getOutfitById(outfitId);
			model.removeOutfit(outfit);
			view.showCollectionViewMessage("The selected outfit removed successfuly.");
		}
	}

	/**
	 * To add a new outfit(will be selected from a popup consists all outfits) to the collection
	 */
	class AddOutfitPopupListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int outfitId = Integer.parseInt(view.getAllOutfitsListSelectedValue().split(":")[1]);
			OutfitModel outfit = appManager.getOutfitService().getOutfitById(outfitId);
			if(!model.getOutfits().contains(outfit)) {
				model.addOutfit(outfit);
				view.setPopupMessageLabelForegroundColor(Color.GREEN);
				view.showPopupMessage("The Outfit is added successfuly.");
				view.showCollectionViewMessage("New Outfit is added successfuly.");
			}else {
				view.setPopupMessageLabelForegroundColor(Color.RED);
				view.showPopupMessage("The Outfit is already exists in the collection.");	
			}

		}
	}
	
	/**
	 * To make visible the add outfit button in the popup when an outfit selected to add the selected outfit to the collection
	 */
	class AllOutfitsListSelectionListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent e) {
			view.setVisibilityOfAddOutfitPopupButton(true);		
		} 
	}
	
	/**
	 * To dispose popup of the view when view closed
	 */
	class WindowListenerr extends WindowAdapter{
	    public void windowClosing(WindowEvent windowEvent) {
			view.disposePopup();
	    }
	}
	
	/**
	 * To make popup visible(to add new outfits to the collection) to show all outfits to the user
	 */
	class AddOutfitListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			view.setVisibilityOfPopup(true);
		}
	}
	
	/**
	 * To show details of the selected outfit from the collection view
	 */
	class SeeOutfitDetailsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int outfitId = Integer.parseInt(view.getOutfitsListSelectedValue().split(":")[1]);
			appManager.showNewOutfitView(model.getOutfitById(outfitId));
			
		}
	}
	
	/**
	 * To show remove outfit(if the online user owns the collection) and see outfit details buttons when an outfit selected from the collection view 
	 */
	class OutfitsListSelectionListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent e) {
			if(appManager.getOnlineUser().getCollections().contains(model))
				view.setRemoveOutfitButtonVisibility(true);
			view.setSeeOutfitDetailButtonVisibility(true);
			
		} 
	}
	

	public void updateViewComponents() {
		this.view.addDefaultListModelToAllOutfitsList();
		for(OutfitModel outfit: this.appManager.getOutfitService().getAllOutfits()) {
			view.addElementToAllOutfitsList("Product Id:" + outfit.getId());
		}
		if(this.appManager.getOnlineUser().getCollections().contains(model)) {
			this.view.setVisibilityOfAddOutfitButton(true); 
		}
		this.view.showCollectionName(this.model.getName());
		this.view.addDefaultListModelToOutfitsList();
		for(OutfitModel outfit: this.model.getOutfits()) {
			this.view.addElementToOutfitsList("Product Id:" + outfit.getId());
		}
	}
	


}
