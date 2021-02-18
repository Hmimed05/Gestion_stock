package fstt.Hamza.miniProjet2;

import java.util.Date;

public class Operation {
    private long Operation_Id;
    private String article_Name;
    private int article_Qte;
    private String dateOperation;
    private double article_Price;

    public Operation(long operation_Id,String article_Name,  double article_Price, int article_Qte,String dateOperation) {
        Operation_Id = operation_Id;
        this.article_Name = article_Name;
        this.article_Qte = article_Qte;
        this.dateOperation = dateOperation;
        this.article_Price = article_Price;
    }

    public long getOperation_Id() {
        return Operation_Id;
    }

    public void setOperation_Id(long operation_Id) {
        Operation_Id = operation_Id;
    }

    public String getArticle_Name() {
        return article_Name;
    }

    public void setArticle_Name(String article_Name) {
        this.article_Name = article_Name;
    }

    public int getArticle_Qte() {
        return article_Qte;
    }

    public void setArticle_Qte(int article_Qte) {
        this.article_Qte = article_Qte;
    }

    public String getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(String dateOperation) {
        this.dateOperation = dateOperation;
    }

    public double getArticle_Price() {
        return article_Price;
    }

    public void setArticle_Price(double article_Price) {
        this.article_Price = article_Price;
    }
}

