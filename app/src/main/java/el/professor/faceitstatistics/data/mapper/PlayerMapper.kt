package el.professor.faceitstatistics.data.mapper

import el.professor.faceitstatistics.data.local.PlayerEntity
import el.professor.faceitstatistics.data.remote.dto.*
import el.professor.faceitstatistics.data.remote.dto.matchDto.FactionDto
import el.professor.faceitstatistics.data.remote.dto.matchDto.MatchInfoDto
import el.professor.faceitstatistics.data.remote.dto.matchDto.MatchPlayerDto
import el.professor.faceitstatistics.data.remote.dto.matchDto.ResultsDto
import el.professor.faceitstatistics.data.remote.dto.matchDto.ScoreDto
import el.professor.faceitstatistics.data.remote.dto.matchDto.TeamsDto
import el.professor.faceitstatistics.domain.model.*
import el.professor.faceitstatistics.domain.model.Map
import el.professor.faceitstatistics.util.Constants

fun PlayerEntity.toPlayer(): Player {
    return Player(
        nickname = nickname,
        playerId = playerId,
        level = level.toString()
    )
}

fun Player.toPlayerEntity(): PlayerEntity {
    return PlayerEntity(
        nickname = nickname,
        playerId = playerId,
        level = level.toInt()
    )
}

fun Item.toPlayer(): Player {
    return Player(
        nickname = nickname,
        playerId = playerId,
        level = games.find { it.name == Constants.game }!!.skill_level
    )
}

fun PlayerDetailsDto.toPlayerDetails(playerId: String, playerInfo: PlayerInfoDto, playerHistory: List<MatchInfoDto>): PlayerDetails {
    return PlayerDetails(
        gameId = gameId,
        maps = maps.map { it.toMap() },
        lifetime = lifetime.toLifetimeStats(),
        avatar = playerInfo.avatar,
        country = playerInfo.country,
        friends = playerInfo.friends,
        games = playerInfo.games.toGames(),
        nickname = playerInfo.nickname,
        matches = playerHistory.map { it.toMatchInfo(playerId) }
    )
}

fun MapDto.toMap(): Map {
    return Map(
        imgRegular = imgRegular,
        imgSmall = imgSmall,
        name = name,
        mapStats = mapStats.toMapStats(),
        mode = mode
    )
}

fun MapStatsDto.toMapStats(): MapStats {
    return MapStats(
        avgAssists = avgAssists,
        avgDeaths = avgDeaths,
        avgHeadshots = avgHeadshots,
        avgKdRatio = avgKdRatio,
        avgKrRatio = avgKrRatio,
        avgKills = avgKills,
        matches = matches,
        pentaKills = pentaKills,
        quadroKills = quadroKills,
        tripleKills = tripleKills,
        winrate = winrate,
        wins = wins
    )
}

fun LifetimeStatsDto.toLifetimeStats(): LifetimeStats {
    return LifetimeStats(
        headshots = headshots,
        kdRatio = kdRatio,
        currentWinStreak = currentWinStreak,
        longestWinStreak = longestWinStreak,
        totalMatches = totalMatches,
        recentResults = recentResults,
        winrate = winrate,
        wins = wins
    )
}

fun GamesDto.toGames(): Games {
    return Games(
        cs = cs.toCs()
    )
}

fun CsDto.toCs(): Cs {
    return Cs(
        elo = elo,
        playerNameInGame = playerNameInGame,
        level = level,
        region = region
    )
}

fun MatchInfoDto.toMatchInfo(playerId: String): MatchInfo {
    val results = results.toResults()
    val teams = teams.toTeams()
    val me = teams.faction1.players.find { it.playerId == playerId }
    var status = "LOSS"

    if (results.winner == "faction1" && me != null) {
        status = "WIN"
    } else if (results.winner == "faction2" && me == null) {
        status = "WIN"
    }
    return MatchInfo(
        title = "${teams.faction1.nickname} vs ${teams.faction2.nickname}",
        status = status,
        startedAt = startedAt,
        finishedAt = finishedAt,
        matchId = matchId,
        results = results,
        teams = teams,
    )
}

fun ResultsDto.toResults(): Results {
    return Results(
        score = score.toScore(),
        winner = winner
    )
}

fun ScoreDto.toScore(): Score {
    return Score(
        faction1 = faction1,
        faction2 = faction2
    )
}

fun TeamsDto.toTeams(): Teams {
    return Teams(
        faction1 = faction1.toFaction(),
        faction2 = faction2.toFaction()
    )
}

fun FactionDto.toFaction(): Faction {
    return Faction(
        avatar = avatar,
        nickname = nickname,
        players = players.map { it.toMatchPlayer() },
        teamId = teamId,
        type = type
    )
}

fun MatchPlayerDto.toMatchPlayer(): MatchPlayer {
    return MatchPlayer(
        avatar = avatar,
        faceitUrl = faceitUrl,
        gamePlayerId = gamePlayerId,
        gamePlayerName = gamePlayerName,
        nickname = nickname,
        playerId = playerId,
        skillLevel = skillLevel
    )
}
