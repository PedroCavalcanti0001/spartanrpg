package utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author FrachDev_
 * @version 3.0
 * @category Utils
 */

public class ItemStackBuilder {

    private static HashMap<Player, List<InventoryAction>> inventoryActions = Maps.newHashMap();
    private ItemStack item;
    private ItemMeta meta;
    private EnchantmentStorageMeta storage;
    private List<String> lore;
    private boolean glow;
    private SkullMeta skullMeta;

    public ItemStackBuilder(final ItemStack item) {
        this.glow = false;
        this.item = item;
        if (item.getType() == Material.ENCHANTED_BOOK) {
            this.storage = (EnchantmentStorageMeta) item.getItemMeta();
            this.lore = ((this.storage != null && this.storage.hasLore()) ? this.storage.getLore()
                    : new ArrayList<String>());
        } else if (item.getType() == Material.LEGACY_SKULL_ITEM || item.getType() == Material.LEGACY_SKULL) {
            this.skullMeta = (SkullMeta) item.getItemMeta();
            this.lore = ((this.skullMeta != null && this.skullMeta.hasLore()) ? this.skullMeta.getLore()
                    : new ArrayList<String>());
        } else {
            this.meta = item.getItemMeta();
            this.lore = ((this.meta != null && this.meta.hasLore()) ? this.meta.getLore() : new ArrayList<String>());
        }
    }

    public ItemStackBuilder(final Material material) {
        this(new ItemStack(material));
    }

    public static void init() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            inventoryActions.put(player, Lists.newArrayList());
        });
    }

    public ItemStackBuilder setLore(final List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemStackBuilder setType(final Material type) {
        this.item.setType(type);
        return this;
    }

    public ItemStackBuilder setOwner(final String owner) {
        if (this.item.getType() == Material.LEGACY_SKULL_ITEM || this.item.getType() == Material.LEGACY_SKULL) {
            this.skullMeta.setOwner(owner);
            return this;
        }
        return this;
    }

    public ItemStackBuilder setName(final String name) {
        if (this.item.getType() == Material.ENCHANTED_BOOK) {
            this.storage.setDisplayName(name);
            return this;
        }
        if (this.item.getType() == Material.LEGACY_SKULL_ITEM || this.item.getType() == Material.LEGACY_SKULL) {
            this.skullMeta.setDisplayName(name);
            return this;
        }
        this.meta.setDisplayName(name);
        return this;
    }

    public ItemStackBuilder addLore(final String... l) {
        for (final String x : l) {
            this.lore.add(x);
        }
        return this;
    }

    public ItemStackBuilder addLoreList(final List<String> l) {
        for (final String s : l) {
            this.lore.add(s.replace("&", "\u00c2ยง"));
        }
        return this;
    }

    public ItemStackBuilder addStoredEnchantment(final Enchantment e, final int level) {
        this.storage.addStoredEnchant(e, level, true);
        return this;
    }

    public ItemStackBuilder addEnchantment(final Enchantment e, final int level) {
        this.meta.addEnchant(e, level, true);
        return this;
    }

    public ItemStackBuilder setDurability(final int durability) {
        this.item.setDurability((short) durability);
        return this;
    }

    public ItemStackBuilder setAmount(final int amount) {
        this.item.setAmount(amount);
        return this;
    }

    public ItemStackBuilder clearLore() {
        this.lore = new ArrayList<String>();
        return this;
    }

    public ItemStackBuilder replaceLore(final String oldLore, final String newLore) {
        for (int i = 0; i < this.lore.size(); ++i) {
            if (this.lore.get(i).contains(oldLore)) {
                this.lore.remove(i);
                this.lore.add(i, newLore);
                break;
            }
        }
        return this;
    }

    public ItemStack build() {
        if (this.item.getType() == Material.ENCHANTED_BOOK) {
            if (!this.lore.isEmpty()) {
                this.storage.setLore(this.lore);
                this.lore.clear();
            }
            this.item.setItemMeta(this.storage);
            return this.item;
        }
        if (this.item.getType() == Material.LEGACY_SKULL || this.item.getType() == Material.LEGACY_SKULL_ITEM) {
            if (!this.lore.isEmpty()) {
                this.skullMeta.setLore(this.lore);
                this.lore.clear();
            }
            this.item.setItemMeta(this.skullMeta);
            return this.item;
        }
        if (!this.lore.isEmpty()) {
            this.meta.setLore(this.lore);
            this.lore.clear();
        }
        this.item.setItemMeta(this.meta);
        if (this.glow) {
            this.setGlow(this.glow);
        }
        return this.item;
    }

    public ItemStackBuilder setGlow(final boolean glow) {
        if (glow) {
            this.addEnchantment(Enchantment.DAMAGE_ALL, 1);
            this.addFlag(ItemFlag.HIDE_ENCHANTS);
        } else {
            this.removeFlag(ItemFlag.HIDE_ENCHANTS);
        }
        return this;
    }

    public ItemStackBuilder addFlag(final ItemFlag... flags) {
        if (this.item.getType() == Material.ENCHANTED_BOOK) {
            this.storage.addItemFlags(flags);
        } else if (this.item.getType() == Material.LEGACY_SKULL_ITEM || this.item.getType() == Material.LEGACY_SKULL) {
            this.skullMeta.addItemFlags(flags);
        } else {
            this.meta.addItemFlags(flags);
        }
        return this;
    }

    public ItemStackBuilder removeFlag(final ItemFlag... flags) {
        if (this.item.getType() == Material.ENCHANTED_BOOK) {
            this.storage.removeItemFlags(flags);
        } else if (this.item.getType() == Material.LEGACY_SKULL_ITEM || this.item.getType() == Material.LEGACY_SKULL) {
            this.skullMeta.removeItemFlags(flags);
        } else {
            this.meta.removeItemFlags(flags);
        }
        return this;
    }

    public ItemStackBuilder addInventoryAction(Player player, Runnable runnable, boolean cancelled,
                                               Inventory inventory) {
        inventoryActions.get(player).add(new InventoryAction(player, runnable, this.build(), cancelled, inventory));
        return this;
    }

    public static class ItemStackEvents implements Listener {

        @EventHandler
        public void inventoryClick(InventoryClickEvent e) {
            Player player = (Player) e.getWhoClicked();
            Inventory inventory = e.getInventory();
            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
                return;
            if (inventoryActions.get(player) == null)
                return;
            List<InventoryAction> actions = inventoryActions.get(player);
            loocking:
            for (InventoryAction action : actions) {
                if (inventory.getHolder() != action.getInventory().getHolder()
                        && e.getView().getTitle() != action.getPlayer().getOpenInventory().getTitle())
                    continue;
                if (action.getPlayer() != player)
                    continue;
                if (!e.getCurrentItem().equals(action.getItem())) {
                    continue;
                }
                e.setCancelled(action.isCancelled());
                action.getRunnable().run();
                break loocking;
            }
        }

        @EventHandler
        public void inventoryClosE(InventoryCloseEvent e) {
            Player player = (Player) e.getPlayer();
            Inventory inventory = e.getInventory();
            if (inventoryActions.get(player) == null)
                return;
            List<InventoryAction> actions = inventoryActions.get(player);
            actions = actions.stream().filter(v -> v.getInventory().getHolder() != inventory.getHolder()
                    || !v.getPlayer().getOpenInventory().getTitle().equals(e.getView().getTitle())).collect(Collectors.toList());
            inventoryActions.put(player, actions);
        }

        /*
         * DON'T TOUCH IN THIS CODE.
         */

        @EventHandler
        public void playerJoin(PlayerJoinEvent e) {
            Player player = e.getPlayer();
            inventoryActions.put(player, Lists.newArrayList());
        }

    }

    public static class InventoryAction {

        private Player player;
        private ItemStack item;
        private boolean cancelled;
        private Runnable runnable;
        private Inventory inventory;

        public InventoryAction(Player player, Runnable runnable, ItemStack item, boolean cancelled,
                               Inventory inventory) {
            this.player = player;
            this.item = item;
            this.cancelled = cancelled;
            this.inventory = inventory;
            this.runnable = runnable;
        }

        public Inventory getInventory() {
            return inventory;
        }

        public void setInventory(Inventory inventory) {
            this.inventory = inventory;
        }

        public Player getPlayer() {
            return player;
        }

        public void setPlayer(Player player) {
            this.player = player;
        }

        public ItemStack getItem() {
            return item;
        }

        public void setItem(ItemStack item) {
            this.item = item;
        }

        public boolean isCancelled() {
            return cancelled;
        }

        public void setCancelled(boolean cancelled) {
            this.cancelled = cancelled;
        }

        public Runnable getRunnable() {
            return runnable;
        }

        public void setRunnable(Runnable runnable) {
            this.runnable = runnable;
        }

    }

}