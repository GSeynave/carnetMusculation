select * from program;
select * from seance;

select * from exercice;
select * from entrainement;
select * from serie;
select * from seance_exercices;
select * from exercice_seances;
SELECT * 
FROM exercice AS e 
INNER JOIN seances_exercice AS s 
	ON e.id = s.exercice_id
WHERE s.seance_id = 1;