package el.professor.faceitstatistics.data.mapper

import el.professor.faceitstatistics.data.local.PlayerEntity
import el.professor.faceitstatistics.data.remote.dto.*
import el.professor.faceitstatistics.domain.model.*
import el.professor.faceitstatistics.domain.model.Map

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
        level = games.find { it.name == "csgo" }!!.skill_level
    )
}

fun PlayerDetailsDto.toPlayerDetails(playerInfo: PlayerInfoDto): PlayerDetails {
    return PlayerDetails(
        gameId = gameId,
        maps = maps.map { it.toMap() },
        lifetime = lifetime.toLifetimeStats(),
        avatar = playerInfo.avatar,
        country = playerInfo.country,
        friends = playerInfo.friends,
        games = playerInfo.games.toGames(),
        nickname = playerInfo.nickname
    )
}

fun MapDto.toMap(): Map {
    return Map(
        imgRegular = imgRegular,
        imgSmall = imgSmall,
        name = name,
        mapStats = mapStats.toMapStats()
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
        csgo = csgo.toCsgo()
    )
}

fun CsgoDto.toCsgo(): Csgo {
    return Csgo(
        elo = elo,
        playerNameInGame = playerNameInGame,
        level = level,
        region = region
    )
}