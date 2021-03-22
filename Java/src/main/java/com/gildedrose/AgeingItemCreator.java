package com.gildedrose;

final class AgeingItemCreator {

    static AgeingItem create(Item item) {
        if (item.name.startsWith("Conjured")) {
            return new ConjuredItem(item);
        }

        switch (item.name) {
            case Constants.AGED_BRIE_NAME:
                return new AgedBrie(item);
            case Constants.SULFURAS_NAME:
                return new Sulfuras();
            case Constants.BACKSTAGE_PASS_NAME:
                return new BackstagePass(item);
            default:
                return new NormalItem(item);
        }
    }

}
