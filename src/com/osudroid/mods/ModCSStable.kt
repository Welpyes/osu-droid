package com.osudroid.mods

import com.osudroid.GameMode
import com.osudroid.beatmaps.sections.BeatmapDifficulty

/**
 * Represents a mod that converts osu!droid circle size to osu!stable circle size.
 */
class ModCSStable : Mod(), IModApplicableToDifficultyWithMods {
    override val name = "CS Stable"
    override val acronym = "CSS"
    override val description = "Converts osu!droid circle size to osu!stable circle size."
    override val type = ModType.DifficultyIncrease
    override val isRanked = false

    override fun applyToDifficulty(
        mode: GameMode,
        difficulty: BeatmapDifficulty,
        mods: Iterable<Mod>
    ) {
        difficulty.difficultyCS += DROID_STANDARD_CS_OFFSET
        difficulty.gameplayCS += DROID_STANDARD_CS_OFFSET
    }

    companion object {
        /**
         * The offset used to convert between osu!droid and osu!standard circle sizes.
         */
        private const val DROID_STANDARD_CS_OFFSET = 6.855634f
    }
}
