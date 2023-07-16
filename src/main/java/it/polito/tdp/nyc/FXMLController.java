/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.nyc;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.nyc.model.LocationNumberNeighbour;
import it.polito.tdp.nyc.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnPercorso"
    private Button btnPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="cmbProvider"
    private ComboBox<String> cmbProvider; // Value injected by FXMLLoader

    @FXML // fx:id="txtDistanza"
    private TextField txtDistanza; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="txtStringa"
    private TextField txtStringa; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtTarget"
    private ComboBox<String> txtTarget; // Value injected by FXMLLoader

    @FXML
    void doAnalisiGrafo(ActionEvent event) {
    	
    	
    	// recupero valori immessi dall'utente con i relativi controlli
    	
    	//analizza la componente connessa
    	// creata classe 'StatsConnessa' per stampare i risultati
    	List<LocationNumberNeighbour> result = this.model.analizzaLocations();
    	this.txtResult.appendText("\n VERTICI CON PIU' VICINI: \n");
    	
    	for (LocationNumberNeighbour l : result) {   		
    		this.txtResult.appendText(l.getLocation() +", numero di vicini: "+ l.getNumeroVicini()+"\n");  		
    	}
    	
    	
    }

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	
    	
    	
    	
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	this.txtResult.clear();
    	
    	// controlli errore numero intero
    	double x =0.0;
    	try {
    	    x = Double.parseDouble( this.txtDistanza.getText() );
    	} catch(NumberFormatException e) {
    	    this.txtResult.setText("Invalid argument. x must be a double!");
    	    return;
    	}
    	// controllo che il numero non sia negativo
    	if(x<0) {
    	  this.txtResult.setText("x must be a nonnegative double.");
    	  return;
    	}
    	
    	// controlli errore comboBox
    	String provider = this.cmbProvider.getValue();
    	if (provider==null) {
    	    this.txtResult.setText("Please select a provider");
    	    return;
    	}
    	
    	    	
		// creo il grafo
	    this.model.creaGrafo(x, provider);
	    	
    	this.txtResult.setText("Grafo creato con " + this.model.getNVertici() + " vertici e " + this.model.getNArchi()+ " archi\n");

    	this.btnPercorso.setDisable(false);
    	this.btnAnalisi.setDisable(false);
    	this.txtTarget.setDisable(false);
    	this.txtStringa.setDisable(false);
    	
    	this.txtTarget.getItems().clear();
    	this.txtTarget.getItems().addAll(model.getAllLocation());
    	
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbProvider != null : "fx:id=\"cmbProvider\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtDistanza != null : "fx:id=\"txtDistanza\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStringa != null : "fx:id=\"txtStringa\" was not injected: check your FXML file 'Scene.fxml'.";
        
    	//disabilita i vari controlli della gui
    	this.btnPercorso.setDisable(true);
    	this.txtResult.clear();
    	this.btnAnalisi.setDisable(true);
    	this.txtTarget.setDisable(true);
    	this.txtStringa.setDisable(true);
    	
    	

    }

    public void setModel(Model model) {
    	this.model = model;
       	//popolare la combo box
    	this.cmbProvider.getItems().clear();
    	this.cmbProvider.getItems().addAll(model.getAllProvider());
    }
}
