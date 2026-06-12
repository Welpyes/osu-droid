package com.osudroid.mods

import com.osudroid.GameMode
import com.osudroid.beatmaps.sections.BeatmapDifficulty
import com.osudroid.utils.ModUtils
import org.junit.Assert
import org.junit.Test

class ModCSStableTest {
    @Test
    fun `Test basic CS conversion`() {
        val difficulty = BeatmapDifficulty(cs = 4f)
        val mods = listOf(ModCSStable())
        
        ModUtils.applyModsToBeatmapDifficulty(difficulty, GameMode.Droid, mods)
        
        // 4 + 6.855634 = 10.855634
        Assert.assertEquals(10.855634f, difficulty.difficultyCS, 1e-6f)
        Assert.assertEquals(10.855634f, difficulty.gameplayCS, 1e-6f)
    }

    @Test
    fun `Test stacking with Hard Rock`() {
        val difficulty = BeatmapDifficulty(cs = 4f)
        // Order in list shouldn't matter now because CSS is IModApplicableToDifficultyWithMods
        val mods = listOf(ModCSStable(), ModHardRock())
        
        ModUtils.applyModsToBeatmapDifficulty(difficulty, GameMode.Droid, mods)
        
        // HR applies first (IModApplicableToDifficulty): min(4 * 1.3, 10) = 5.2
        // CSS applies second (IModApplicableToDifficultyWithMods): 5.2 + 6.855634 = 12.055634
        Assert.assertEquals(12.055634f, difficulty.difficultyCS, 1e-6f)
    }

    @Test
    fun `Test stacking with Hard Rock reversed list order`() {
        val difficulty = BeatmapDifficulty(cs = 4f)
        val mods = listOf(ModHardRock(), ModCSStable())
        
        ModUtils.applyModsToBeatmapDifficulty(difficulty, GameMode.Droid, mods)
        
        // Same result because of interface priority
        Assert.assertEquals(12.055634f, difficulty.difficultyCS, 1e-6f)
    }
}
