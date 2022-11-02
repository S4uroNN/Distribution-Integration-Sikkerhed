package opgave19;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Opgave19 {
    static String konto = "";
    static int amount = 0;

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

            System.out.println("Hvilken konto vil du ændre?");
            System.out.println("Indtast kontonummer:");
            konto = scan.nextLine();

            System.out.println("Hvor mange penge vil du sætte ind?");
            System.out.println("Indtast antal:");
            amount = Integer.parseInt(scan.nextLine());

            minConnection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            minConnection.setAutoCommit(false);
            ResultSet res = stmt.executeQuery("select saldo from konto where kontonr =" + konto);
            if(res.next()){
                System.out.println(res.getInt(1));
            }
            int oldsaldo = res.getInt(1);
            System.out.println("Nuværende saldo: " + oldsaldo);
            int newsaldo = oldsaldo + amount;
            System.out.println("Ny saldo efter overføresel: " + newsaldo);

            System.out.println("Ønsker du at fortsætte? Ja eller nej?");
            if(scan.nextLine().equalsIgnoreCase("ja")){
                stmt.execute("update konto set saldo=" + newsaldo + "where kontonr=" +konto);
                minConnection.commit();
                res = stmt.executeQuery("select saldo from konto where kontonr =" + konto);
                if(res.next()){
                    System.out.println("Saldo: " + res.getInt(1));
                }
            }else{
                stmt.execute("update konto set saldo=" + newsaldo + "where kontonr=" +konto);
                minConnection.rollback();
                res = stmt.executeQuery("select saldo from konto where kontonr =" + konto);
                if(res.next()){
                    System.out.println("Saldo: " + res.getInt(1));
                }
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
