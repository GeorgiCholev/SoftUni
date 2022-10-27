package _1_Intro.exercise;

public class Query {

    public static final String GET_VILLAINS_BY_MINION_COUNT =
            "SELECT v.name, count(DISTINCT mv.minion_id) AS 'minion_count'" +
                    " FROM villains v" +
                    " JOIN minions_villains mv on v.id = mv.villain_id" +
                    " GROUP BY v.id" +
                    " HAVING minion_count > ?" +
                    " ORDER BY minion_count DESC;";

    public static final String GET_MINIONS_BY_VILLAIN_ID =
            "SELECT *" +
                    " FROM minions AS m" +
                    " JOIN minions_villains mv on m.id = mv.minion_id" +
                    " WHERE villain_id = ?;";

    public static final String GET_VILLAIN_NAME_BY_ID =
            "SELECT v.name" +
                    " FROM villains AS v" +
                    " WHERE id = ?;";

    public static final String FIND_ID_IN_TABLE_TOWNS =
            "SELECT id" +
                    " FROM towns" +
                    " WHERE name = ?;";

    public static final String FIND_ID_IN_TABLE_VILLAINS =
            "SELECT id" +
                    " FROM villains" +
                    " WHERE name = ?;";

    public static final String FIND_ID_IN_TABLE_MINIONS =
            "SELECT id" +
                    " FROM minions" +
                    " WHERE name = ?;";

    public static String INSERT_NAME_IN_TOWNS =
            "INSERT INTO towns (name) VALUES (?);";

    public static String INSERT_NAME_AND_DEFAULT_EVIL_IN_VILLAINS =
            "INSERT INTO villains (name, evilness_factor) VALUES (?, 'evil');";

    public static final String INSERT_MINION =
            "INSERT INTO minions (name, age, town_id) VALUES (?, ?, ?);";

    public static final String INSERT_PAIR_IN_MINIONS_VILLAINS =
            "INSERT INTO minions_villains (minion_id, villain_id) VALUES (?, ?)";

    public static final String SET_TOWN_NAMES_TO_UPPERCASE_FOR_COUNTRY =
            "UPDATE towns SET name = UPPER(name) WHERE country = ?;";

    public static final String GET_TOWN_NAMES_FOR_COUNTRY =
            "SELECT name FROM towns WHERE country = ?;";

    public static final String GET_VILLAIN_INFO_BY_ID =
            "SELECT v.id, v.name, count(mv.minion_id) AS 'minion_count'" +
                    " FROM villains AS v" +
                    " JOIN minions_villains mv on v.id = mv.villain_id" +
                    " WHERE v.id = ?" +
                    " GROUP BY v.id;";

    public static final String REMOVE_VILLAIN_FROM_MINIONS_VILLAINS =
            "DELETE FROM minions_villains WHERE villain_id = ?;";

    public static final String REMOVE_VILLAIN =
            "DELETE FROM villains WHERE id = ?;";

    public static final String SELECT_ALL_MINION_NAMES =
            "SELECT name FROM minions;";

    public static final String TOTAL_MINION_COUNT =
            "SELECT count(id) AS 'minion_count' FROM minions;";
}
