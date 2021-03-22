package com.gildedrose;

class AgedBrie extends NormalItem {

    AgedBrie(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        if (this.item.quality < 50) {
            this.item.quality++;
        }
    }

}
