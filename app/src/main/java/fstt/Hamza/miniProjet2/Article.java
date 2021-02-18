package fstt.Hamza.miniProjet2;

import java.io.Serializable;

public class Article implements Serializable {

    private int article_Id;
    private String article_Name;
    private double articlePrice;
    private int article_Qte;

    public Article(int article_Id, String article_Name, double articlePrice, int article_Qte) {
        this.article_Id = article_Id;
        this.article_Name = article_Name;
        this.articlePrice = articlePrice;
        this.article_Qte = article_Qte;
    }

    public Article(String article_Name, double articlePrice, int article_Qte) {
        this.article_Name = article_Name;
        this.articlePrice = articlePrice;
        this.article_Qte = article_Qte;
    }

    public int getArticle_Id() {
        return article_Id;
    }

    public void setArticle_Id(int article_Id) {
        this.article_Id = article_Id;
    }

    public String getArticle_Name() {
        return article_Name;
    }

    public void setArticle_Name(String article_Name) {
        this.article_Name = article_Name;
    }

    public double getArticlePrice() {
        return articlePrice;
    }

    public void setArticlePrice(double articlePrice) {
        this.articlePrice = articlePrice;
    }

    public int getArticle_Qte() {
        return article_Qte;
    }

    public void setArticle_Qte(int article_Qte) {
        this.article_Qte = article_Qte;
    }
}

