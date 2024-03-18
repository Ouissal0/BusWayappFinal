package Controlleur;

import Modele.BO.Arret;
import Modele.BO.Voyage;
import java.util.ArrayList;
import java.util.List;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.Values;

public class SystemeRes {

    private List<Voyage> voyages;
    private Driver driver;

    public SystemeRes() {
        this.voyages = new ArrayList<>();
        // Initialisez votre driver Neo4j ici
        this.driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "12398765"));
    }

    // Méthode pour ajouter un voyage à la liste
    public void addVoyage(Voyage voyage) {
        voyages.add(voyage);
    }

    // Méthode pour afficher les informations sur tous les voyages
    public void afficherVoyages() {
        if (voyages.isEmpty()) {
            System.out.println("Aucun voyage dans la liste.");
        } else {
            System.out.println("Liste des voyages :");
            for (Voyage voyage : voyages) {
                // Utilisation de la méthode afficherVoyage pour chaque voyage
                voyage.afficherVoyage();
            }
        }
    }

    public void ajouterRelationsArrets() {
        try (Session session = driver.session()) {
            for (Voyage voyage : voyages) {
                List<Arret> arrets = voyage.getArrets();
                String matricule = voyage.getB().getMatricule();
                String nomBus = "B" + matricule; // Nom du nœud de bus dans Neo4j
                // Vérifier si le bus existe dans le graphe Neo4j
                if (!busExiste(matricule, session)) {
                    // Créer un nouveau nœud de bus avec sa capacité
                    int capacite = voyage.getB().getNbrPlacesLimite();
                    try (Transaction tx = session.beginTransaction()) {
                        tx.run("CREATE (:BUS {matricule: $matricule, capacite: $capacite})", Values.parameters("matricule", matricule, "capacite", capacite));
                        tx.commit();
                    }
                }
                // Démarrer une nouvelle transaction pour chaque arret
                try (Transaction tx = session.beginTransaction()) {
                    for (Arret arret : arrets) {
                        String nomStation = arret.getS().getStation();
                        String heureArret = arret.getHeureArret().toString();
                        int idVoyage = voyage.getNumV();
                        // Création de la relation ARRET entre le bus et la station
                        tx.run("MATCH (b:BUS {matricule: $matricule}), (s:STATION {nom: $nomStation}) " +
                                        "MERGE (b)-[:ARRET {heure: $heureArret, nbreDesc: 0, idVoyage: $idVoyage}]->(s)",
                                Values.parameters(
                                        "matricule", matricule,
                                        "nomStation", nomStation,
                                        "heureArret", heureArret,
                                        "idVoyage", idVoyage
                                ));
                    }
                    tx.commit();
                }
            }
        }
    }


    private boolean busExiste(String matricule, Session session) {
        org.neo4j.driver.Result result = session.run(
                "MATCH (b:BUS {matricule: $matricule}) RETURN count(b) AS count",
                org.neo4j.driver.Values.parameters("matricule", matricule)
        );
        int count = result.single().get("count").asInt();
        return count > 0;
    }


    // Méthode pour fermer la connexion au driver Neo4j
    public void close() {
        driver.close();
    }
}