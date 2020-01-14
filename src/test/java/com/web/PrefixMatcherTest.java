package com.web;

import com.uc.renren.service.PrefixMatcher;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.Set;
import org.apache.storm.guava.collect.Sets;

/**
 * Created by lovesqcc on 16-2-29.
 */
public class PrefixMatcherTest {

    public static void main(String[] args) {
        ServiceLoader<PrefixMatcher> matcher = ServiceLoader.load(PrefixMatcher.class);
        Iterator<PrefixMatcher> matcherIter = matcher.iterator();
        while (matcherIter.hasNext()) {
            PrefixMatcher wordMatcher = matcherIter.next();
            System.out.println(wordMatcher.getClass().getName());
            System.out.println(wordMatcher.call("aa"));
        }

        Set<String> set = Sets.newHashSet("03","00");
        set.contains("str");

    }
}