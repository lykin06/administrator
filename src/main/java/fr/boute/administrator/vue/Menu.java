package fr.boute.administrator.vue;

import fr.boute.administrator.gestion.Database;
import fr.boute.administrator.personnel.Administrator;
import fr.boute.administrator.personnel.Personnel;

/**
 * Menu class
 *
 * @author Aurelien COLOMBET
 */
public class Menu {
    private Database database;
    private Personnel user;

    public Menu(Database database) {
        this.database = database;
        this.user = this.database.getCurrentUser();
        welcome();
        displayMenu();
    }

    /**
     * Welcome message.
     */
    private void welcome() {
        System.out.println("Bienvenue " + this.user.getFirstName() + " "
                + this.user.getName() + " !");
        System.out.println("Etant " + this.user.toString()
                + ", voici vos options disponibles :");
    }

    /**
     * This method diplay the interface for the user.
     */
    private void displayMenu() {
        if (this.user instanceof Administrator) {
            new AdminMenu(this.database, (Administrator) this.user);
        } else {
            new BorrowerMenu(this.database);
        }
    }

    /**
     * @return the database
     */
    public Database getDatabase() {
        return database;
    }

    /**
     * @param database the database to set
     */
    public void setDatabase(Database database) {
        this.database = database;
    }

    /**
     * @return the user
     */
    public Personnel getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Personnel user) {
        this.user = user;
    }
}
