package opgave15;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Opgave15 {

    static String fromkonto = "";
    static String toKonto = "";
    static int amount = 0;
    static int frakontosaldo;
    static int tilkontosaldo;
    static int newsaldofromkonto;
    static int newsaldotokonto;

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Program startet");
            Connection minConnection;
            //set database connection
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            minConnection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;" +
                    "databaseName=kontodb;" +
                    "user=sa;" +
                    "password=123;");
            Statement stmt = minConnection.createStatement();


            System.out.println("Hvilken konto vil du overføre fra?");
            fromkonto = scan.nextLine();
            System.out.println("Hvilken konto vil du overføre til?");
            toKonto = scan.nextLine();
            System.out.println("Hvor mange penge vil du overføre?");
            amount = Integer.parseInt(scan.nextLine());


            minConnection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            minConnection.setAutoCommit(false);
            ResultSet res = stmt.executeQuery("select saldo from konto where kontonr =" + fromkonto);
            if (res.next()) {
                if (res.getInt(1) < amount) {
                    System.out.println("Konto findes ikke");
                } else {
                    frakontosaldo = res.getInt(1);
                    System.out.println("Saldo fra konto: " + frakontosaldo);
                }
            }
            res = stmt.executeQuery("select saldo from konto where kontonr =" + toKonto);
            if (res.next()) {
                System.out.println("Saldo til konto: " + res.getInt(1));
                tilkontosaldo = res.getInt(1);
            }

            if (frakontosaldo != 0 && tilkontosaldo != 0) {
                newsaldofromkonto = frakontosaldo - amount;
                newsaldotokonto = tilkontosaldo + amount;
                //begin tran
                System.out.println("start forfra");
                scan.nextLine();
                stmt.execute("update konto set saldo =" + newsaldofromkonto + "where kontonr =" + fromkonto);
                stmt.execute("update konto set saldo =" + newsaldotokonto + "where kontonr =" + toKonto);
                minConnection.commit();
                res = stmt.executeQuery("select * from konto");
                while (res.next()) {
                    System.out.println("Konto " + res.getInt(1) + " har saldo " + res.getInt(2) + " og ejer " + res.getString(3));
                }
            } else {
                minConnection.setAutoCommit(false);
                System.out.println("Der er ikke nok penge på kontoen");
                minConnection.rollback();
            }

            if (stmt != null)
                stmt.close();
            if (minConnection != null)
                minConnection.close();
        } catch (Exception e) {
            System.out.print("fejl:  " + e.getMessage());
        }
    }
}
