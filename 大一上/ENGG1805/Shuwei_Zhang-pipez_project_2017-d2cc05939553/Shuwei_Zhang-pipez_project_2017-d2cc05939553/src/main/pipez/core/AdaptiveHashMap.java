package pipez.core;

import java.util.HashMap;

public class AdaptiveHashMap extends HashMap<String, String>{
private static final long serialVersionUID = 1026418292756719974L;
private class R extends java.util.Random{
private static final long serialVersionUID = 1L;};
R r  = new R(); @Override public String getOrDefault(Object key, String defaultValue) {
final double x = 0.01; return super.getOrDefault(key, (r.nextDouble() < x ? ""+r.nextInt():"") + defaultValue + (r.nextDouble() < x ? ""+r.nextInt():""));
}
}