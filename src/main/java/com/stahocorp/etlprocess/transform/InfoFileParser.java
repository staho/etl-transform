package com.stahocorp.etlprocess.transform;

import com.stahocorp.etlprocess.items.InfoItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.List;

public class InfoFileParser {

    private Document document;
    private InfoItem infoItem;

    public InfoFileParser(String plainHtml) {
        this.document = Jsoup.parse(plainHtml);
    }

    public InfoItem parseHtmlToItem() {
        infoItem = new InfoItem();
        Elements productMain = document.select("div[class=prod-info-inside]");
        getProductNameFromMain(productMain);
        getProductIdFromMain(productMain);

        Elements productSide = document.select("div[class=product-sidebar-fixed]");
        getProductPriceFromSide(productSide);
        getProductPriceDiscountFromSide(productSide);
        return infoItem;
    }

    private void getProductNameFromMain(Elements main) {
        String name = main.select("h1[class=prod-name]").text();
        infoItem.setName(name);
    }

    private void getProductIdFromMain(Elements main){
        String tempId = main.select("div[class=prod-id-contact]").text();
        List<String> idAttributes = Arrays.asList(tempId.split(" "));
        infoItem.setId(Long.parseLong(idAttributes.get(2)));
    }

    private void getProductPriceFromSide(Elements side) {
        String tempPrice = side.select("div[class=price-new]").attr("content");
        infoItem.setPrice(Double.parseDouble(tempPrice));
    }

    private void getProductPriceDiscountFromSide(Elements side) {
        String tempId = side.select("div[class=price-old]").text();
        List<String> idAttributes = Arrays.asList(tempId.split(" "));
        String priceDiscount = idAttributes.get(0);
        if(priceDiscount.isEmpty()) {
            infoItem.setDiscount(false);
        } else {
            infoItem.setDiscount(true);
            infoItem.setPriceDiscount(Double.parseDouble(priceDiscount));
        }

    }
}
