package org.hhorton.queries.receiving;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

/**
 * Created by hunterhorton on 6/4/17.
 */
public class GetRegularSeasonReceivingTouchdownsByPlayerIDAndSeason {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GetRegularSeasonReceivingTouchdownsByPlayerIDAndSeason(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Map<String, Object> execute(String playerID, int season) {
        return this.jdbcTemplate.queryForMap("SELECT sum(play_player.receiving_tds) AS receiving_touchdowns " +
                "FROM play_player " +
                "LEFT JOIN game " +
                "ON play_player.gsis_id = game.gsis_id " +
                "WHERE player_id = ?" +
                "AND season_year = ?", playerID, season);
    }
}
