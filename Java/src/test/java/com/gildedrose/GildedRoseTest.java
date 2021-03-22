package com.gildedrose;

import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.ContextSnapshot;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {

    private GildedRose create(Item item) {
        return new GildedRose(new Item[]{item});
    }

    @Test
    void foo() {
        Item item = new Item("foo", 0, 0);
        GildedRose app = create(item);

        app.updateQuality();

        assertThat(app.items[0].name).isEqualTo("foo");
    }

    @Test
    void decreaseQualityEveryDay() {
        int quality = 10;
        Item item = new Item("foo", 1, quality);
        GildedRose app = create(item);

        app.updateQuality();

        assertThat(item.quality).isEqualTo(quality - 1);
    }

    @Test
    void decreaseSellInEveryDay() {
        int sellIn = 2;
        Item item = new Item("foo", sellIn, sellIn);
        GildedRose app = create(item);

        app.updateQuality();

        assertThat(item.sellIn).isEqualTo(sellIn - 1);
    }

    @Test
    void decreaseQualityTwiceAsFastWhenSellInLessOrEqualToZero() {
        int quality = 5;
        Item item = new Item("foo", 0, quality);
        GildedRose app = create(item);

        try (AutoCloseableSoftAssertions s = new AutoCloseableSoftAssertions()) {
            app.updateQuality();
            s.assertThat(item.quality).isEqualTo(quality - 2);

            app.updateQuality();
            s.assertThat(item.quality).isEqualTo(quality - 4);
        }
    }

    @Test
    void agedBrieIncreasesInQualityEveryDay() {
        int quality = 1;
        Item agedBrie = new Item(Constants.AGED_BRIE_NAME, 4, quality);
        GildedRose app = create(agedBrie);

        app.updateQuality();

        assertThat(agedBrie.quality).isEqualTo(quality + 1);
    }

    @Test
    void qualityCanNotBeNegative() {
        int quality = 2;
        Item item = new Item("foo", 5, quality);
        GildedRose app = create(item);

        for (int i = 0; i < 10; i++) {
            app.updateQuality();
        }

        assertThat(item.quality).isEqualTo(0);
    }

    @Test
    void qualityCanNotBeHigherThan50ForAgedBrie() {
        Item item = new Item(Constants.AGED_BRIE_NAME, 0, 1);
        GildedRose app = create(item);

        for (int i = 0; i < 100; i++) {
            app.updateQuality();
        }

        assertThat(item.quality).isEqualTo(50);
    }

    @Test
    void qualityCanNotBeHigherThan50ForBackStagePass() {
        Item item = new Item(Constants.BACKSTAGE_PASS_NAME, 10, 49);
        GildedRose app = create(item);

        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        assertThat(item.quality).isEqualTo(50);
    }

    @Test
    void qualityAndSellInOfSulfurasIsConstant() {
        int sellIn = 0;
        Item item = new Item(Constants.SULFURAS_NAME, sellIn, 80);
        GildedRose app = create(item);

        app.updateQuality();

        assertThat(item.quality).isEqualTo(80);
        assertThat(item.sellIn).isEqualTo(sellIn);
    }

    @Test
    void backstagePassQualityIs0WhenSellInEqualTo0() {
        Item item = new Item(Constants.BACKSTAGE_PASS_NAME, 0, 10);
        GildedRose app = create(item);

        app.updateQuality();

        assertThat(item.quality).isZero();
    }

    @Test
    void backstagePassQualityIncreasesBy3WhenSellInLessOrEqualTo5() {
        int quality = 1;
        Item item = new Item(Constants.BACKSTAGE_PASS_NAME, 5, quality);
        GildedRose app = create(item);

        app.updateQuality();

        assertThat(item.quality).isEqualTo(quality + 3);
    }

    @Test
    void backstagePassQualityIncreasesBy2WhenSellInLessOrEqualTo10() {
        int quality = 1;
        Item item = new Item(Constants.BACKSTAGE_PASS_NAME, 10, quality);
        GildedRose app = create(item);

        app.updateQuality();

        assertThat(item.quality).isEqualTo(quality + 2);
    }

    @Test
    void backstagePassQualityIncreases() {
        int quality = 1;
        Item item = new Item(Constants.BACKSTAGE_PASS_NAME, 20, quality);
        GildedRose app = create(item);

        app.updateQuality();

        assertThat(item.quality).isEqualTo(quality + 1);
    }

    @Test
    void conjuredItemDegradesInQualityTwiceAsFast() {
        int quality = 10;
        Item item = new Item("Conjured Pillow", 1, quality);
        GildedRose app = create(item);

        try (AutoCloseableSoftAssertions s = new AutoCloseableSoftAssertions()) {
            app.updateQuality();
            s.assertThat(item.quality).isEqualTo(8);

            app.updateQuality();
            s.assertThat(item.quality).isEqualTo(4);
        }
    }

}
