package test.automation.utils;

import org.junit.Assert;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amolleti on 9/26/2018.
 */
public class OsiOneData {
    private static Connection connection;

    private static Connection connection() throws SQLException {
        if (connection == null)
            connection = getDBConnection();
        return connection;
    }

    private static Connection getDBConnection() throws SQLException {
        try {
            String dbURL = "jdbc:mysql://192.168.32.111:3306/osionedb_prod";
            String user = "cds";
            String pass = "osicpl123";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, user, pass);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void deleteExpenses(String startDate) throws SQLException {
        List<String> l = new ArrayList<>();
        try {
            Statement stmt = connection().createStatement();
            LocalDate date = LocalDate.parse(startDate).plusDays(6);
            ResultSet r = stmt.executeQuery("select * from osi_expense_report where entry_week_start_date = '" + startDate + "' and entry_week_end_date='" + date + "' and employee_id=748;");
            while (r.next()) {
                l.add(r.getString("expense_report_id"));
            }
            if (l.size() > 0) {
                for (int s = 0; s < l.size(); s++) {
                    stmt.execute("delete from osi_expenses where expense_report_id=" + l.get(s));
                    System.out.print("Report deleted form Osi_Expenses");
                    stmt.execute("delete from osi_expense_report where expense_report_id=" + l.get(s));
                    System.out.print("Report deleted form Osi_Expenses_Report");
                }
            }
            connection().close();
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }


    public static void deleteTimesheet() throws SQLException {
        try {
            Statement stmt = connection().createStatement();
            stmt.execute("delete from osi_timesheet_entry where employee_id=748 and spent_date between '2018-05-21' and '2018-05-27' ;");
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    public static void deleteDashboradSheet() throws SQLException {
        List<String> l = new ArrayList<>();
        try {
            Statement stmt = connection().createStatement();
            ResultSet rs = stmt.executeQuery("select * from osi_approvers where employee_id=748 and project_id=967;");
            while (rs.next()) {
                l.add(rs.getString("source_id"));
            }
            if (l.size() > 0) {
                for (int s = 0; s < l.size(); s++) {

                    stmt.execute("delete  from osi_approvers where employee_id=748 and project_id=967 and emp_name='rdonepudi' and source_id=" + l.get(s));
                }
            }
            stmt.execute("delete from osi_timesheet_entry where employee_id=748 and spent_date between '2019-02-04' and '2019-04-05' and project_id=967; ");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public static void deleteSaveDates() throws SQLException {
        try {
            Statement stmt = connection().createStatement();
            stmt.execute("delete from osi_timesheet_entry where employee_id=748 and spent_date between '2019-02-04' and '2019-04-05' and project_id=967 and status='N'; ");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

}
