package com.gildedrose;

class ConjuredItem extends NormalItem {

    ConjuredItem(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        super.updateQuality();
        super.updateQuality();
    }
}
