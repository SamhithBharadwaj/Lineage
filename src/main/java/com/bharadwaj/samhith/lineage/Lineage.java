package com.bharadwaj.samhith.lineage;

import com.bharadwaj.samhith.lineage.service.LineageProcessor;

public class Lineage {
    public static void main(String[] args) {
        new LineageProcessor().processFilesInFolder();
    }
}
