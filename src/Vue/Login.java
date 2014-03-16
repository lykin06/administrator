package Vue;

import Gestion.Database;
import Gestion.Manager;
import Personnel.Unknown;

/**
 * <b>Login class<b>
 * <p>
 * This class can launch new sessions with the login method or add new users
 * with the inscription method.
 * </p>
 * 
 * @author Aurelien COLOMBET
 */
public class Login {
    /**
     * The database
     */
    private Database database;
    private Manager manager;

    public Login() {
        this.database = new Database();
        this.manager = new Manager();
        heading();
        goToMenu();
    }

    /**
     * this method display on the screen Welcome to this application.
     */
    private void heading() {
        System.out
                .println("Bienvenue dans ce gestionnaire. Voici vos options disponibles :");
    }

    /**
     * <b>Display the options of the menu</b>
     * <p>
     * The method display the options available to the user and return his
     * choice.
     * </p>
     * 
     * @return the user's choice
     */
    private int loginOptions() {
        System.out.println("1. Connexion.");
        System.out.println("2. Inscription");
        System.out.println("3. Quitter");
        int answer = this.manager.requestInt(1, 3);

        return answer;
    }

    /**
     * 
     * This method enable to login.
     */
    private boolean login() {
        String email, password;
        System.out
                .println("Afin de vous connecter au serveur, veuillez entrer vos informations personnelles.");
        System.out.println("Entrez votre adresse mail :");
        email = this.manager.requestString();
        System.out.println("Entrez votre mot de passe :");
        password = this.manager.requestString();

        if (database.isAuthorized(email, password)) {
            return true;
        }

        System.out.println("Erreur d'authentification !");
        System.out.println("Menu 2 pour vous inscrire.");
        return false;
    }

    /**
     * Quit the application
     */
    private void quit() {
        System.out.println("Merci d'avoir utilise ce gestionnaire");
        System.exit(1);
    }

    /**
     * 
     * This method call goToMenu.
     */
    private void goToMenu() {
        int value = this.loginOptions();

        switch (value) {
        case 1:
            if (login()) {
                new Menu(this.database);
            } else {
                goToMenu();
            }
            break;
        case 2:
            inscription();
            break;
        case 3:
            quit();
            break;
        default:
            System.out.println("Entree inconue.");
            goToMenu();
            break;
        }
    }

    private void inscription() {
        int identifiant;
        String firstName, name, email, password;
        System.out.println("Qui etes-vous ?");
        System.out.println("1. administrateur\n2. etudiant\n3. enseignant");
        identifiant = manager.requestInt(1, 3);
        System.out.println("Entrez votre prenom :");
        firstName = manager.requestString();
        System.out.println("Entrez votre nom :");
        name = manager.requestString();
        System.out.println("Entrez votre adresse mail :");
        email = manager.requestString();
        System.out.println("Entrez un mot de passe :");
        password = manager.requestString();

        switch (identifiant) {
        case 1:
            database.addUser(new Unknown("administrateur", firstName, name,
                    email, password, identifiant));
            break;
        case 2:
            database.addUser(new Unknown("etudiant", firstName, name, email,
                    password, identifiant));
            break;
        case 3:
            database.addUser(new Unknown("enseignant", firstName, name, email,
                    password, identifiant));
            break;
        }

        System.out.println("Utilisateur ajoute");
        goToMenu();
    }

    /**
     * @return the database
     */
    public Database getDatabase() {
        return database;
    }

    /**
     * @param database
     *            the database to set
     */
    public void setBdd(Database database) {
        this.database = database;
    }

    /**
     * @return the manager
     */
    public Manager getManager() {
        return manager;
    }

    /**
     * @param manager
     *            the manager to set
     */
    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
