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
@Table(name = "entrainement_exercice")
@NamedNativeQueries({
	@NamedNativeQuery(name = "entrainementExercice.findByEntrainementIdAndExerciceId", query = "select * from EntrainementExercice e where e.entrainementId = :entrainement_id and e.exercice_id = :exerciceId", resultClass = EntrainementExercice.class),
	@NamedNativeQuery(name = "entrainementExercice.findByEntrainementId", query = "select * from EntrainementExercice e where e.entrainement_id = :entrainementId", resultClass = EntrainementExercice.class),
	@NamedNativeQuery(name = "entrainementExercice.deleteAllById", query = "delete from EntrainementExercice e where e.id in :id"),
	@NamedNativeQuery(name = "entrainementExercice.deleteByEntrainementIdAndExerciceIdIn", query = "delete from EntrainementExercice e where e.entrainement_id = :id and e.exercice_id in :exerciceIds")
})
public class EntrainementExercice {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "entrainement_id")
	Entrainement entrainement;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "exercice_id")
	Exercice exercice;
	
	@Column(name = "nb_serie", nullable = false)
	private long nbSerie;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Entrainement getEntrainement() {
		return entrainement;
	}

	public void setEntrainement(Entrainement entrainement) {
		this.entrainement = entrainement;
	}

	public Exercice getExercice() {
		return exercice;
	}

	public void setExercice(Exercice exercice) {
		this.exercice = exercice;
	}

	public long getNbSerie() {
		return nbSerie;
	}

	public void setNbSerie(long nbSerie) {
		this.nbSerie = nbSerie;
	}

	
}
