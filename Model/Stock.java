package Model;

import java.util.Vector;

/**
 * Cette classe contient la représentation objet de la table Stock de la base de données
 *
 * @author C.Balboni
 * @author D.Hamel
 *
 * @version 1.0
 */
public class Stock {

    /**
     * Membres privés
     */
    private int id;
    private String description;
    private double quantite;
    private double quantiteMin;
    private String unite;
    private double commande;

    /**
     * Constructeur par défaut
     */
    public Stock() {}

    /**
     * Constructeur simple à partir de l'identifiant
     *
     * @param id un entier correspondant à l'ID de l'élément Stock
     */
    public Stock(int id) {
        this.id = id;
    }

    /**
     * Constructeur complet à partir d'un objet Stock passé en paramètre
     * @param sStock un objet de type Stock
     */
    public Stock(Stock sStock){
        this.id = sStock.id;
        this.description = sStock.description;
        this.quantite = sStock.quantite;
        this.quantiteMin = sStock.quantiteMin;
        this.unite = sStock.unite;
        this.commande = sStock.commande;

    }

    /**
     * Constructeur complet, à partir des attributs séparément
     *
     * @param id un entier, identifiant de l'élément Stock
     * @param description une String décrivant l'élément du stock
     * @param quantite un double indiquant la quantité en Stock
     * @param quantiteMin un double indiquant la quantité minimum pouvant être commandé à la fois
     * @param unite une String décrivant l'unité de l'élément (pièce, kg, g, etc...)
     */
    public Stock(int id, String description, double quantite, String unite, double quantiteMin) {
        this.id = id;
        this.description = description;
        this.quantite = quantite;
        this.quantiteMin = quantiteMin;
        this.unite = unite;

        double temp = quantiteMin - quantite;
        if(temp > 0){
            this.commande = temp;
        }else{
            this.commande = 0;
        }
    }

    /**
     * Constructeur avec tous les membres sauf l'ID
     *
     * @param description une String décrivant l'élément du stock
     * @param quantite un double indiquant la quantité en Stock
     * @param quantiteMin un double indiquant la quantité minimum pouvant être commandé à la fois
     * @param unite une String décrivant l'unité de l'élément (pièce, kg, g, etc...)
     */
    public Stock(String description, double quantite, double quantiteMin, String unite) {
        this.description = description;
        this.quantite = quantite;
        this.quantiteMin = quantiteMin;
        this.unite = unite;

        double temp = quantiteMin - quantite;
        if(temp > 0){
            this.commande = temp;
        }else{
            this.commande = 0;
        }

    }

    /**
     * Getter ID
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter ID
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter description
     * @return String nom
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter nom
     * @param description une String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter quantité en stock
     * @return double
     */
    public double getQuantite() {
        return quantite;
    }

    /**
     * Setter quantité en stock
     * @param quantite double ( potentiellement en kilogramme )
     */
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    /**
     * Getter quantiteMin Stock
     * @return getQuantiteMin double
     */
    public double getQuantiteMin() {
        return quantiteMin;
    }


    /**
     * Setter nom
     * @param quantiteMin double
     */
    public void setQuantiteMin(double quantiteMin) {
        this.quantiteMin = quantiteMin;
    }

    /**
     * Getter unite Stock
     * @return unite String
     */
    public String getUnite() {
        return unite;
    }

    /**
     * Setter unite
     * @param unite String
     */
    public void setUnite(String unite) {
        this.unite = unite;
    }

    public double getCommande(){
        return commande;
    }

    public void setCommande(double commande){
        this.commande = commande;
    }

    public Vector<Object> toVector(){
        Vector<Object> vStock = new Vector<Object>();
        vStock.add(getId());
        vStock.add(getDescription());
        vStock.add(getQuantite());
        vStock.add(getQuantiteMin());
        vStock.add(getUnite());
        vStock.add(getCommande());

        return vStock;
    }

    public Vector<Object> toVectorAddDel(){
        Vector<Object> vStock = new Vector<Object>();
        vStock.add(getId());
        vStock.add(getDescription());
        vStock.add(new Double(0.0));
        vStock.add(getUnite());

        return vStock;
    }

    public Vector<Object> toVectorForOrder(){
        Vector<Object> vStock = new Vector<>();
        vStock.add(getId());
        vStock.add(getDescription());
        vStock.add(getCommande());
        vStock.add(getUnite());

        return vStock;
    }

    public Vector<Object> toVectorToLookOrder(){
        Vector<Object> vStock = new Vector<>();
        vStock.add(getId());
        vStock.add(getDescription());
        vStock.add(getQuantite());
        vStock.add(getUnite());

        return vStock;
    }

    public void addToStock(double quantity){
        this.quantite += quantity;
    }
}
