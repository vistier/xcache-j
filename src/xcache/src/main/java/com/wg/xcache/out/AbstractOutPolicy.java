package com.wg.xcache.out;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import com.wg.xcache.Element;
import com.wg.xcache.store.Store;

/**
 * 抽象退出策略
 * @author enychen Aug 10, 2009
 */
public abstract class AbstractOutPolicy implements OutPolicy {

    /** 随机数 */
    protected static final Random R = new Random();

    /*
     * (non-Javadoc)
     * @see com.wg.xcache.out.OutPolicy#vote(com.wg.xcache.store.Store)
     */
    public Element vote(Store store) {
        Element[] candidates = this.getCandidates(store.getAll());
        Element outElement = this.getOutElement(candidates);

        return outElement;
    }

    /**
     * 获取退出元素
     * @param candidates 候选人
     * @return 退出元素
     */
    protected Element getOutElement(Element[] candidates) {
        if (candidates == null) {
            return null;
        }

        Element outElement = null;

        for (Element candidate : candidates) {
            if (candidate == null) {
                continue;
            }

            if (outElement == null) {
                outElement = candidate;
            } else {
                outElement = this.compare(outElement, candidate);
            }
        }

        return outElement;
    }

    /**
     * 获取候选人
     * @param elements 元素集合
     * @return 候选人
     */
    protected Element[] getCandidates(Collection<Element> elements) {
        if (elements == null) {
            return null;
        }

        int amount = elements.size();
        int candidateAmount;

        // ----------------------------------------------
        // 计算候选人数量：
        // 如果有0个元素，没有候选人；
        // 如果大于0个元素，小于10000个元素，最多10个候选人；
        // 如果大于10000个元素，最多100个候选人。
        // ----------------------------------------------
        if (amount == 0) {
            return null;
        } else if (amount < 10000) {
            candidateAmount = Math.min(amount, 10);
        } else {
            candidateAmount = 100;
        }

        // ---------------------------------------------------------------------
        // 如果有8002个元素，候选人有10个，间隔为800，见下图坐标：
        // 0----800----1600----2400----3200----4000----4800--------8000--8002
        // 以下算法就是从每个间隔内随机找到一个候选人。
        // ---------------------------------------------------------------------
        Iterator<Element> iterator = elements.iterator();
        Element[] candidates = new Element[candidateAmount];
        int interval = amount / candidateAmount;
        int forward = 0;
        int random = 0;
        int oldRandom = 0;

        for (int i = 0; i < candidateAmount; i++) {
            oldRandom = random;
            random = R.nextInt(interval);
            forward = (i == 0) ? random : (interval - oldRandom) + random;

            for (int j = 0; j < forward - 1; j++) {
                try {
                    iterator.next();
                } catch (NoSuchElementException e) {
                }
            }

            try {
                candidates[i] = iterator.next();
            } catch (NoSuchElementException e) {
            }
        }

        return candidates;
    }

    /**
     * 比较两个元素，选出一个退出
     * @param element1 元素1
     * @param elements 元素2
     * @return 退出元素
     */
    public abstract Element compare(Element element1, Element element2);

}
