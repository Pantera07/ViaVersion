package com.viaversion.viaversion.bukkit.listeners.v26_2to26_3;

import com.viaversion.viaversion.ViaVersionPlugin;
import com.viaversion.viaversion.bukkit.listeners.ViaBukkitListener;
import com.viaversion.viaversion.protocols.v26_2to26_3.Protocol26_2To26_3;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerSwingAnimationListener extends ViaBukkitListener {

    public PlayerSwingAnimationListener(final ViaVersionPlugin plugin) {
        super(plugin, Protocol26_2To26_3.class);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onInventoryClick(final InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof final Player player)) {
            return;
        }

        final ClickType click = event.getClick();
        if (click == ClickType.WINDOW_BORDER_LEFT || click == ClickType.WINDOW_BORDER_RIGHT
            || click == ClickType.DROP || click == ClickType.CONTROL_DROP) {
            player.swingMainHand();
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerDropItem(final PlayerDropItemEvent event) {
        final Player player = event.getPlayer();
        player.swingMainHand();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockPlace(final BlockPlaceEvent event) {
        final Player player = event.getPlayer();
        if (event.getHand() == EquipmentSlot.HAND) {
            player.swingMainHand();
        } else if (event.getHand() == EquipmentSlot.OFF_HAND) {
            player.swingOffHand();
        }
    }
}
