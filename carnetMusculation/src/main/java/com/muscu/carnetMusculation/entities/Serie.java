package com.muscu.carnetMusculation.entities;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity 
@Table(name="serie")
@NamedNativeQueries({
	@NamedNativeQuery(name = "serie.findById", query = "select * from Serie s where s.id = :id", resultClass = Serie.class),
	@NamedNativeQuery(name = "serie.findBySeanceIdAndNumeroSerie", query = "select * from Serie s where s.seance_id = :seanceId and s.numero_serie = :numeroSerie", resultClass = Serie.class),
	@NamedNativeQuery(name = "serie.findBySeanceIdAndNumeroSerieAndEntrainementIdAndExerciceId", query = "select * from Serie s where s.seance_id = :seanceId and s.numero_serie = :numeroSerie and s.entrainement_id = :entrainementId and s.exercice_id = :exerciceId", resultClass = Serie.class),
	@NamedNativeQuery(name = "serie.findBySeanceId", query = "select * from Serie s where s.seance_id = :seanceId", resultClass = Serie.class),
	@NamedNativeQuery(name = "serie.deleteByEntrainementIdAndSeanceIdAndExerciceIdIn", query = "delete from Serie s where s.entrainement_id =:entrainementId and s.seance_id = :seanceId and s.exercice_id in :exerciceIds"),
	@NamedNativeQuery(name = "serie.deleteById", query = "delete from Serie s where s.id = :id")
})
public class Serie {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "numero_serie", nullable = false)
	private String numeroSerie;
	
	@Column(name="rep", length=5, nullable=false, unique=false)
	private String rep;
	
	@Column(name="poids")
	private String poids;
	
	@Column(name="recup")
	private String recup;

	@ManyToOne
	@JoinColumn(name = "seance_id")
	Seance seance;
	
	@ManyToOne
	@JoinColumn(name = "exercice_id")
	Exercice exercice;

	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name = "entrainement_id")
	Entrainement entrainement;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSérie) {
		this.numeroSerie = numeroSérie;
	}

	public String getRep() {
		return rep;
	}

	public void setRep(String rep) {
		this.rep = rep;
	}

	public String getPoids() {
		return poids;
	}

	public void setPoids(String poids) {
		this.poids = poids;
	}

	public String getRecup() {
		return recup;
	}

	public void setRecup(String recup) {
		this.recup = recup;
	}

	public Seance getSeance() {
		return seance;
	}

	public void setSeance(Seance seance) {
		this.seance = seance;
	}

	public Exercice getExercice() {
		return exercice;
	}

	public void setExercice(Exercice exercice) {
		this.exercice = exercice;
	}

	public Entrainement getEntrainement() {
		return entrainement;
	}

	public void setEntrainement(Entrainement entrainement) {
		this.entrainement = entrainement;
	}
	
}
