/*
 * Copyright 2011-2016 ZuoBian.com All right reserved. This software is the confidential and proprietary information of
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.jcseg;

import java.util.ArrayList;

import com.zb.jcseg.core.IChunk;
import com.zb.jcseg.core.IRule;

/**
 * the third filter rule. - the smallest variance word length. this rule will the chunks that one the smallest variance
 * word length.
 * 
 * @author zxc Sep 3, 2014 2:25:43 PM
 */
public class SVWLRule implements IRule {

    /**
     * maxmum match rule instance.
     */
    private static SVWLRule __instance = null;

    /**
     * return the quote to the maximum match instance.
     * 
     * @return MMRule
     */
    public static SVWLRule createRule() {
        if (__instance == null) __instance = new SVWLRule();
        return __instance;
    }

    private SVWLRule() {
    }

    /**
     * smallest variance word length interface.
     * 
     * @see IRule#call(IChunk[])
     */
    @Override
    public IChunk[] call(IChunk[] chunks) {

        double smallestVariance = chunks[0].getWordsVariance();
        int j;

        // find the smallest variance word length
        for (j = 1; j < chunks.length; j++) {
            if (chunks[j].getWordsVariance() < smallestVariance) smallestVariance = chunks[j].getWordsVariance();
        }

        // get the items that the variance word length equals to
        // the max's.
        ArrayList<IChunk> chunkArr = new ArrayList<IChunk>(chunks.length);
        for (j = 0; j < chunks.length; j++) {
            if (chunks[j].getWordsVariance() == smallestVariance) chunkArr.add(chunks[j]);
        }

        IChunk[] lchunk = new IChunk[chunkArr.size()];
        chunkArr.toArray(lchunk);
        chunkArr.clear();

        return lchunk;
    }
}
