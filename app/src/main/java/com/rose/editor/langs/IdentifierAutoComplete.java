/*
 Copyright 2020 Rose2073

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.rose.editor.langs;

import com.rose.editor.android.AutoCompletePanel;
import com.rose.editor.text.TextAnalyzer;
import com.rose.editor.interfaces.AutoCompleteProvider;
import com.rose.editor.struct.ResultItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class IdentifierAutoComplete implements AutoCompleteProvider {

    private String[] mKeywords;
    private boolean mKeywordsAreLowCase;

    public void setKeywords(String[] keywords,boolean lowCase) {
        mKeywords = keywords;
        mKeywordsAreLowCase = true;
    }

    public String[] getKeywords() {
        return mKeywords;
    }

    public static class Identifiers {

        private List<String> identifiers = new ArrayList<>();
        private HashMap<String,Object> cache;
        private final static Object SIGN = new Object();

        public void addIdentifier(String identifier) {
            if(cache == null) {
                throw new IllegalStateException("begin() have not been called");
            }
            if(cache.put(identifier,SIGN) == SIGN) {
                return;
            }
            identifiers.add(identifier);
        }

        public void begin() {
            cache = new HashMap<>();
        }

        public void finish() {
            cache.clear();
            cache = null;
        }

        public List<String> getIdentifiers() {
            return identifiers;
        }

    }

    @Override
    public List<ResultItem> getAutoCompleteItems(String prefix, boolean isInCodeBlock, TextAnalyzer.TextColors colors, int line) {
        List<ResultItem> keywords = new ArrayList<>();
        final String[] keywordArray = mKeywords;
        final boolean lowCase = mKeywordsAreLowCase;
        String match = prefix.toLowerCase();
        if(keywordArray != null) {
            if(lowCase) {
                for(String kw : keywordArray) {
                    if(kw.startsWith(match)) {
                        keywords.add(new ResultItem(kw,"Keyword",ResultItem.TYPE_KEYWORD));
                    }
                }
            }else{
                for(String kw : keywordArray) {
                    if(kw.toLowerCase().startsWith(match)) {
                        keywords.add(new ResultItem(kw,"Keyword",ResultItem.TYPE_KEYWORD));
                    }
                }
            }
        }
        Collections.sort(keywords, AutoCompletePanel.RES_COMP);
        Object extra = colors.mExtra;
        Identifiers userIdentifiers = (extra instanceof Identifiers) ? (Identifiers)extra : null;
        if(userIdentifiers != null) {
            List<ResultItem> words = new ArrayList<>();
            for(String word : userIdentifiers.getIdentifiers()) {
                if(word.toLowerCase().startsWith(match)) {
                    keywords.add(new ResultItem(word,"Identifier",ResultItem.TYPE_LOCAL_METHOD));
                }
            }
        }
        return keywords;
    }


}
