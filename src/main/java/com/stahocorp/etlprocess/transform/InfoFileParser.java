package com.stahocorp.etlprocess.transform;

import com.stahocorp.etlprocess.items.InfoItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InfoFileParser {

    private Document document;
    private InfoItem infoItem;
    private String fileName;

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
        getProductUnitsAvailableFromSide(productSide);

        Elements attributesCard = document.select("div[class=specification-table]");
        getProductAttributesFromAttributesCard(attributesCard);


        infoItem.setProcessingDate(LocalDateTime.now());
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

    private void getProductUnitsAvailableFromSide(Elements side) {
        String tempNo = side.select("div[class=prod-available]").text();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(tempNo);
        if(matcher.find())
            infoItem.setNoOfUnitAvailable(Integer.parseInt(matcher.group()));
    }

    private void getProductAttributesFromAttributesCard(Elements card) {
        Element table = card.select("div[class=specification-table]").first();
        Elements children = table.children();
        Map<String, Map<String, String>> tempMap = new LinkedHashMap<>();
        String tempMainAttribute = "";
        for(Element x: children) {
            if(x.tagName().equalsIgnoreCase("h5")){
                tempMap.put(x.text(), new LinkedHashMap<>());
                tempMainAttribute = x.text();
            } else {
                Elements names = x.children()
                        .select("div[class=table-info-item]")
                        .stream()
                        .filter(c -> !c.text().isEmpty())
                        .collect(Collectors.toCollection(Elements::new));
                tempMap.put(tempMainAttribute, extractSpecificationFromInfoItem(names));
            }
        }

        infoItem.setAttributes(tempMap);
    }

    private Map<String, String> extractSpecificationFromInfoItem(Elements inf) {
        Map<String,String> tempMap = new LinkedHashMap<>();

        for(Element x: inf) {
            StringBuilder stringBuilder = new StringBuilder();
            x.select("div[class=info-item]")
                    .forEach(c -> {
                        if(stringBuilder.length() != 0) stringBuilder.append(", ");
                        stringBuilder.append(c.text());
                    });
            tempMap.put(x.getElementsByAttributeValueContaining("class", "table-info-inner name").first().text(), stringBuilder.toString());
        }

        return tempMap;
    }
}
