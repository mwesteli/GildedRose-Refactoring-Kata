package com.gildedrose;

class BackstagePass extends NormalItem {

    BackstagePass(Item item) {
        super(item);
    }

    @Override
    public void age() {
        updateQuality();
        this.item.sellIn--;
    }

    @Override
    protected void updateQuality() {
        if (this.item.sellIn <= 0) {
            this.item.quality = 0;
            return;
        }

        if (this.item.sellIn <= 5) {
            this.item.quality += 3;
        } else if (this.item.sellIn <= 10) {
            this.item.quality += 2;
        } else {
            this.item.quality++;
        }

        if (this.item.quality > 50) {
            this.item.quality = 50;
        }
    }
}
