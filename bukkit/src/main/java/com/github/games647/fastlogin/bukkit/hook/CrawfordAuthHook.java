/*
 * SPDX-License-Identifier: MIT
 *
 * Copyright (c) 2026 games647 and contributors
 * Copyright (c) 2026 Hayston1001
 */
package com.github.games647.fastlogin.bukkit.hook;

import com.github.games647.fastlogin.core.hooks.AuthPlugin;
import org.bukkit.entity.Player;

/**
 * Hook for the CrawfordAuth login plugin of a standalone offline-mode server.
 * <p>
 * CrawfordAuth makes the login decision itself: a name on its own premium list skips /login only when the
 * connection carries a Mojang-verified (version 4) UUID. FastLoginPlus therefore only has to run the Mojang
 * verification and hand out the premium UUID, so this hook never logs in or registers anyone:
 * <ul>
 *     <li>{@link #forceLogin(Player)} and {@link #forceRegister(Player, String)} do nothing and report failure,
 *     which FastLoginPlus already treats as "the auth plugin handled the player itself" (like AuthMe 6.0).</li>
 *     <li>{@link #isRegistered(String)} answers true for every name. FastLoginPlus then never detects an unknown
 *     name as premium on its own, and only names an administrator marked with {@code /flp premium} are verified.</li>
 * </ul>
 * Without any hook FastLoginPlus refuses every login ("Server is not fully started yet").
 */
public class CrawfordAuthHook implements AuthPlugin<Player> {

    @Override
    public boolean forceLogin(Player player) {
        return false;
    }

    @Override
    public boolean forceRegister(Player player, String password) {
        return false;
    }

    @Override
    public boolean isRegistered(String playerName) {
        return true;
    }

    @Override
    public boolean notifyGeneratedPassword() {
        return false;
    }
}
