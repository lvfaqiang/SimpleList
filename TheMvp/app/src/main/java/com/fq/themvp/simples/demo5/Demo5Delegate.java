/*
 * Copyright (c) 2015, 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fq.themvp.simples.demo5;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.fq.themvp.R;
import com.fq.themvp.module.view.AppDelegate;


/**
 * 使用Butterknife与EvenBus
 *
 * @author kymjs (http://www.kymjs.com/) on 10/28/15.
 */
public class Demo5Delegate extends AppDelegate {

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_toolbar;
    }

    @Override
    public int getOptionsMenuId() {
//        return R.menu.menu;
        return 0;
    }

    public Toolbar getToolbar() {
        return get(R.id.toolbar);
    }

    public void setText(String text) {
        TextView textView = get(R.id.text);
        textView.setText(text);
    }
}
