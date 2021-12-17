package main;

import java.util.HashMap;

public class ReturnMsg {
    public final String msg;
    private final HashMap<Integer, Integer> content;
    private final double cost;

    public ReturnMsg(String msg, HashMap<Integer, Integer> content, double cost) {
        this.msg = msg;
        this.content = content;
        this.cost = cost;
    }
}
