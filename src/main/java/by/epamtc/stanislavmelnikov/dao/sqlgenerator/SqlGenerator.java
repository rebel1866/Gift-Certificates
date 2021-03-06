package by.epamtc.stanislavmelnikov.dao.sqlgenerator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SqlGenerator {
    private static Map<String, Action> actions = new HashMap<>();
    private static final Action concatLike = (sql, key, value) -> {
        StringBuilder newValue = new StringBuilder(value);
        newValue.insert(value.length() - 1, "%");
        newValue.insert(1, "%");
        sql.append("upper(" + key + ") like upper(" + newValue + ")");
    };
    private static final Action concatPriceFrom = (sql, key, value) -> sql.append("price" + ">=" + value);
    private static final Action concatPriceTo = (sql, key, value) -> sql.append("price" + "<=" + value);

    static {
        actions.put("price_from", concatPriceFrom);
        actions.put("price_to", concatPriceTo);
        actions.put("certificate_name", concatLike);
        actions.put("tag_name", concatLike);
    }

    public static String generateSQL(String sourceSql, Map<String, String> params) {
        String sorting = params.remove("sorting");
        String sortingOrder = params.remove("sorting_order");
        String targetSql = addWhereBlock(new StringBuilder(sourceSql), params);
        targetSql = addOrderBlock(targetSql, sorting, sortingOrder);
        return targetSql;
    }

    private static String addOrderBlock(String targetSql, String sorting, String sortingOrder) {
        if (sorting != null) return targetSql + " " + sorting + " " + sortingOrder;
        else return targetSql;
    }


    private static String addWhereBlock(StringBuilder sourceSql, Map<String, String> params) {
        if (params.size() == 0) return sourceSql.toString();
        Set<Map.Entry<String, String>> entries = params.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        sourceSql.append(" where ");
        while (iterator.hasNext()) {
            var entry = iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
            if (!isNumber(value)) {
                value = wrapApostrophe(value);
            }
            Action action = actions.get(key);
            action.doAction(sourceSql, key, value);
            if (iterator.hasNext()) sourceSql.append(" and ");
        }
        return sourceSql.toString();
    }

    private static boolean isNumber(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String wrapApostrophe(String value) {
        return "'" + value + "'";
    }
}
