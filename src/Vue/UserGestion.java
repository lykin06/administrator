package Vue;

import java.util.ArrayList;

import Config.Config;
import Gestion.Database;
import Gestion.Manager;
import Personnel.Administrator;
import Personnel.Personnel;
import Personnel.Student;
import Personnel.Teacher;

/**
 * <b>userGestion class</b>
 * <p>
 * This class provides an interface to manage all the users
 * </p>
 * 
 * @author mathieu BOUTELIER
 * 
 */
public class UserGestion {
    private Administrator user;
    private Manager manager;
    private Database database;

    public UserGestion(Database database, Administrator administrator,
            Manager manager) {
        this.manager = manager;
        this.database = database;
        this.user = administrator;
        this.managementUser();
    }

    /**
     * <b>Shows the command on users</b>
     * <p>
     * An administrator can do the following actions on users:
     * <ul>
     * <li>Add a new user</li>
     * <li>Confirm an inscription</li>
     * <li>Modify a user</li>
     * <li>Delete a user</li>
     * <li>See the list of users</li>
     * </ul>
     * </p>
     */
    private void managementUser() {
        System.out.println("1. Ajouter un utilisateur.");
        System.out.println("2. Valider un utilisateur.");
        System.out.println("3. Modifier un utilisateur.");
        System.out.println("4. Supprimer un utilisateur.");
        System.out.println("5. Afficher la liste des utilisateurs inscrits");
        System.out.println("6. Retour");

        int value = this.manager.requestInt(1, 6);

        switch (value) {
        case 1:
            this.addUser();
            break;
        case 2:
            this.confirmUser();
            break;
        case 3:
            this.changeUser();
            break;
        case 4:
            this.removeUser();
            break;
        case 5:
            this.displayUsers();
            break;
        case 6:
            new AdminMenu(database, user);
            return;
        default:
            break;
        }

        this.managementUser();
    }

    /**
     * <b>Add a new user</b>
     */
    private void addUser() {
        int identifiant, nature;
        String firstName, name, email, password;

        System.out.println("1. administrateur\n2. enseignant\n3. etudiant");
        nature = manager.requestInt(1, 3);

        System.out.println("Prenom :");
        firstName = manager.requestString();

        System.out.println("Nom :");
        name = manager.requestString();

        System.out.println("Mail :");
        email = manager.requestString();

        System.out.println("Mot de passe :");
        password = manager.requestString();

        System.out.println("Identifiant :");
        identifiant = manager.requestInt(1, Config.MAX_USERS);

        switch (nature) {
        case 1:
            this.database.addUser(new Administrator(firstName, name, email,
                    password, identifiant));
            break;
        case 2:
            this.database.addUser(new Teacher(firstName, name, email, password,
                    identifiant, 0));
            break;
        case 3:
            this.database.addUser(new Student(firstName, name, email, password,
                    identifiant, 0));
            break;
        }

        System.out.println("Utilisateur ajoute");
    }

    /**
     * <b>Confirm the inscription of a new user</b>
     */
    private void confirmUser() {
        System.out.println("Entrer le mail de l'utilisateur a confirmer :");
        String str = manager.requestString();

        ArrayList<Personnel> unknown = this.database.getUnknown();
        Personnel user = database.searchUser(unknown, str);

        if (user == null) {
            System.out.println("Utilisateur inconnu");
            return;
        }

        System.out.println("Identifiant :");
        int identifiant = manager.requestInt(1, Config.MAX_USERS);
        System.out.println("Utilisateur confirme");
        this.database.removeUser(user);

        switch (user.getIdentifiant()) {
        case 1:
            this.database.addUser(new Administrator(user.getFirstName(), user
                    .getName(), user.getEmail(), user.getPassword(),
                    identifiant));
            return;
        case 2:
            this.database.addUser(new Student(user.getFirstName(), user
                    .getName(), user.getEmail(), user.getPassword(),
                    identifiant, 0));
            return;
        case 3:
            this.database.addUser(new Teacher(user.getFirstName(), user
                    .getName(), user.getEmail(), user.getPassword(),
                    identifiant, 0));
            return;
        }
    }

    /**
     * <b>Change the user's informations</b>
     */
    private void changeUser() {
        System.out.println("Entrer le mail de l'utilisateur a modifier :");
        String str = manager.requestString();

        ArrayList<Personnel> students = this.database.getStudents();
        Personnel userChanged = database.searchUser(students, str);

        if (userChanged == null) {
            ArrayList<Personnel> teachers = this.database.getTeachers();
            userChanged = database.searchUser(teachers, str);
        }

        if (userChanged == null) {
            System.out.println("L'utilisateur avec l'adresse mail " + str
                    + " n'a pas ete trouve.");
            return;
        }

        this.addUser();
        this.database.removeUser(userChanged);
        System.out.println("Utilisateur modifie.");
    }

    /**
     * <b>Remove a user</b>
     */
    private void removeUser() {
        System.out.println("Entrer le mail de l'utilisateur a modifier :");
        String str = manager.requestString();

        ArrayList<Personnel> students = this.database.getStudents();
        Personnel userChanged = database.searchUser(students, str);

        if (userChanged == null) {
            ArrayList<Personnel> teachers = this.database.getTeachers();
            userChanged = database.searchUser(teachers, str);
        }

        if (userChanged == null) {
            ArrayList<Personnel> unknown = this.database.getUnknown();
            userChanged = database.searchUser(unknown, str);
        }

        if (userChanged == null) {
            System.out.println("L'utilisateur avec l'adresse mail " + str
                    + " n'a pas ete trouve.");
            return;
        }

        this.database.removeUser(userChanged);
        System.out.println("Utilisateur supprime.");
    }

    /**
     * <b>Display all the users</b>
     */
    private void displayUsers() {
        System.out.println(database.toString());
    }
}
