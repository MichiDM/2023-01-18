package it.polito.tdp.nyc.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;


import com.javadocmd.simplelatlng.LatLngTool;

import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.nyc.db.NYCDao;

public class Model {
	
	
	private NYCDao dao;
	private Graph<String, DefaultWeightedEdge> grafo;


	
	
	public Model() {
		this.dao = new NYCDao();
		

		
	}
	
	
	
	public List<String> getAllProvider(){
		return dao.getAllProvider();
	}
	
	public List<String> getAllLocation(){
		return dao.getAllLocation();
	}
	
		
	// svuota il grafo per crearne nel caso uno nuovo
	private void clearGraph() {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);			
	}


	// GRAFO
	/**
	 * Metodo che crea il grafo
	 */
	public void creaGrafo(double x, String provider) {
		clearGraph();
		//costruzione di un nuovo grafo
		this.grafo = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		// per grafici orientati SimpleDirectedWeightedGraph

		//assegnazione dei vertici
		List<String> vertici = this.dao.getVertici(provider);
		Graphs.addAllVertices(this.grafo, vertici);
		
		
		
		// VERSIONE 2) aggreghiamo gli archi lato codice
		//Per ogni vertice del grafo, leggo tutti i suoi prodotti nell'anno
		// BISOGNA creare una nuova classe RetailerProducts (con attributi il retailer e il set di prodotti che vende)
		List<LocationCoords> lc = new ArrayList<LocationCoords>();
		for (String s : vertici) {
			lc.add(new LocationCoords(s, this.dao.getavgCoordLocalityProviderAndLocationSpec(provider, s)) );
		}
		// Doppio ciclo for, per verificare le coppie di retailers
		for (int i = 0; i<lc.size(); i++) {
			for (int j = i+1; j < lc.size(); j ++) {
				String location1 = lc.get(i).getLocation();
				String location2 = lc.get(j).getLocation();
				double distanzaLocation =  LatLngTool.distance(lc.get(i).getCoords(), lc.get(j).getCoords(), LengthUnit.KILOMETER);
				
				//se i due venditori hanno almeno Nmin prodotti in comune, 
				//aggiungo l'arco
				if (distanzaLocation<= x) {
					Graphs.addEdgeWithVertices(this.grafo, location1, location2, distanzaLocation);
				}
			}
		}

	}

	public int getNVertici() {
		// TODO Auto-generated method stub
		return this.grafo.vertexSet().size();
	}


	public int getNArchi() {
		// TODO Auto-generated method stub
		return this.grafo.edgeSet().size();
	}



	public List<LocationNumberNeighbour> analizzaLocations() {
		
		List<LocationNumberNeighbour> verticiGradoMassimo = new ArrayList<LocationNumberNeighbour>();
		for (String s : this.grafo.vertexSet()) {
			int grado = Graphs.neighborListOf(this.grafo, s).size();
			if (verticiGradoMassimo.isEmpty() || grado == verticiGradoMassimo.get(0).getNumeroVicini()) {
				verticiGradoMassimo.add(new LocationNumberNeighbour (s, grado) );
			} else if(grado > verticiGradoMassimo.get(0).getNumeroVicini()) {
				verticiGradoMassimo.clear();
				verticiGradoMassimo.add(new LocationNumberNeighbour (s, grado) );
			}
		}		
		return verticiGradoMassimo;
	}
}
