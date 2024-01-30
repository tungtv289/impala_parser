import dev.tungtv.ImpalaParser;
import dev.tungtv.TableStatic;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

public class ImpalaParserTest {
    private final ImpalaParser impalaParser = new ImpalaParser();

    @Test
    public void createTableStmt() throws IOException {
        String stmt = Common.getStmtFromFile("impala_create_as_select.txt");
        String stmt1 = Common.getStmtFromFile("impala_create_as_select_1.txt");

        Set<TableStatic> rsStmt =  impalaParser.parser(stmt);
        Set<TableStatic> rsStmt1 =  impalaParser.parser(stmt1);

        Assert.assertEquals(6, rsStmt.size());
        Assert.assertEquals(4, rsStmt1.size());
        Assert.assertTrue(rsStmt.contains(new TableStatic(TableStatic.CMD.CREATE_TABLE, "das_report", "ingest_kpi_xlhh_lego_bag_pkgs_imex")));
        Assert.assertTrue(rsStmt1.contains(new TableStatic(TableStatic.CMD.CREATE_TABLE, "das_report", "hangnt_deliver_bbs")));
    }

    @Test
    public void queryStmt() throws IOException {
        String stmt = Common.getStmtFromFile("impala_select_1.txt");
        Set<TableStatic> actual = impalaParser.parser(stmt);
        Assert.assertEquals(13, actual.size());
    }

    @Test
    public void insertStmt() throws IOException {
        String stmt = Common.getStmtFromFile("impala_insert.txt");
        Set<TableStatic> actual = impalaParser.parser(stmt);
        Assert.assertEquals(3, actual.size());
    }

}
