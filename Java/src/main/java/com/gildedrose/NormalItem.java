package com.gildedrose;

import java.util.Objects;

class NormalItem implements AgeingItem {

    protected final Item item;

    protected NormalItem(Item item) {
        this.item = item;
    }

    @Override
    public void age() {
        this.updateQuality();

        if (this.item.sellIn <= 0) {
            this.updateQuality();
        }

        this.item.sellIn--;
    }

    protected void updateQuality() {
        if (this.item.quality > 0) {
            this.item.quality--;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalItem that = (NormalItem) o;
        return Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item);
    }
}
