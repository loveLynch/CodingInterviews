package com.lynch.structure;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lynch on 2019-04-03. <br>
 **/
public class CommonTreeNode {
    public char val;
    public List<CommonTreeNode> children;
    public CommonTreeNode(char val){
        this.val = val;
        children = new LinkedList<>();
    }
    public void addChildren(CommonTreeNode... children){
        for(CommonTreeNode child:children)
            this.children.add(child);
    }
}
